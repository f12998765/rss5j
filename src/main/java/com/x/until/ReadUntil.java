package com.x.until;

import com.x.mode.Entry;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zero on 2017/5/28.
 */
public class ReadUntil {
    public static List<Entry> read(String url, String rss){
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = DocumentHelper.parseText(rss);
        } catch (DocumentException e) {
//            e.printStackTrace();
            System.out.println(url+"\t解析失败");
            return null;
        }
        Element root = document.getRootElement();
        if("rss".equals(root.getName()))
            return readRss2(root);
        else if("feed".equals(root.getName())){
            return readAtom(root);
        }
        else
            return null;
    }

    private static List<Entry> readRss2(Element root) {
        List<Entry> entries = new ArrayList<>();

        for (Iterator i = root.element("channel").elementIterator( "item" ); i.hasNext(); ) {
            Element entry = (Element) i.next();
            String title = entry.element("title").getText();
            String link = entry.element("link").getText();
            String published = entry.element("pubDate").getText();
            entries.add(new Entry(title,link, ZonedDateTime.parse(published, DateTimeFormatter.RFC_1123_DATE_TIME)));
        }

        return entries;
    }

    private static List<Entry> readAtom(Element root) {

        List<Entry> entries = new ArrayList<>();
        for (Iterator i = root.elementIterator( "entry" ); i.hasNext(); ) {
            Element entry = (Element) i.next();
            String title = entry.element("title").getText();
            String link = entry.element("link").attribute("href").getText();
            String published = null;
            if(entry.element("published")!=null)
                published = entry.element("published").getText();
            else
                published = entry.element("updated").getText();
            entries.add(new Entry(title,link,published));
        }

        return entries;
    }
}
