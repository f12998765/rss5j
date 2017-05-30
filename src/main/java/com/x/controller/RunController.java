package com.x.controller;

import com.x.mode.EntryList;
import com.x.until.HttpUntil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Created by zero on 2017/5/29.
 */
@Controller
public class RunController {


    @RequestMapping("/update")
    @ResponseBody
    public String update() {
        HttpUntil httpUntil = new HttpUntil();

        EntryList.num = EntryList.UrlMap.size();

        for(String url : EntryList.UrlMap.keySet()){
            httpUntil.get(url);
        }

        String loasttime = EntryList.time.toString();

        EntryList.time = LocalDateTime.now();

        return "上次更新时间 "+ loasttime +"\n"+
                "RSS 更新中 ,共 "+EntryList.UrlMap.size();
    }

    @RequestMapping("/look")
    @ResponseBody
    public String look() {
        return "更新时间 "+ EntryList.time +"\n"+
                "未更新 "+EntryList.num;
    }

    @RequestMapping("/rss")
    public String rss(Model model) {
        model.addAttribute("time",EntryList.time);
        model.addAttribute("maps",EntryList.map);
        return "rss";
    }

}

