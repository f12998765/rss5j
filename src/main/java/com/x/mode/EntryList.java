package com.x.mode;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zero on 2017/5/28.
 */
public class EntryList {
    public static Map<String,Boolean> UrlMap;
    public static int num=0;
    public static Map<String,List<Entry>> map;
    public static LocalDateTime time ;

    static {
        UrlMap = new HashMap<>();
        map = new HashMap<>();

        time = LocalDateTime.now();

        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File("src/main/resources/url.xml"));
            Element root = document.getRootElement();
            for (Iterator i = root.element("body").elementIterator( "outline" ); i.hasNext(); ) {

                Element entry = (Element) i.next();
                for (Iterator j = entry.elementIterator( "outline" ); j.hasNext(); ) {
                    Element out = (Element) j.next();
                    String url = out.attribute("xmlUrl").getText();
                    UrlMap.put(url,false);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        num = UrlMap.size();
    }

    public static void test(){
        if(num<=0)
            System.out.println(map);
    }


}
