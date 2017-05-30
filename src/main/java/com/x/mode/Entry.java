package com.x.mode;

import java.time.ZonedDateTime;

/**
 * Created by zero on 2017/5/27.
 */
public class Entry {
    private String title;
    private String link;
    private ZonedDateTime published;

    public Entry(String title, String link, String published) {
        this.title = title;
        this.link = link;
        this.published = ZonedDateTime.parse(published);
    }
    public Entry(String title, String link, ZonedDateTime published) {
        this.title = title;
        this.link = link;
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ZonedDateTime getPublished() {
        return published;
    }

    public void setPublished(ZonedDateTime published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "{ 'title':'" + title + "', 'link':'" + link + "', 'published':'" + published +"'}";
    }
}
