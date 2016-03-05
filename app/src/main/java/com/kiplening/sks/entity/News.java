package com.kiplening.sks.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by MOON on 2/25/2016.
 */
public class News implements Serializable {
    protected int news_id;
    protected String title;
    protected String content;
    protected Date news_date;
    protected int dpid;

    protected String dp_name;

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getNews_date() {
        return news_date;
    }

    public void setNews_date(Date news_date) {
        this.news_date = news_date;
    }

    public int getDpid() {
        return dpid;
    }

    public void setDpid(int dpid) {
        this.dpid = dpid;
    }

    public String getDp_name() {
        return dp_name;
    }

    public void setDp_name(String dp_name) {
        this.dp_name = dp_name;
    }
}
