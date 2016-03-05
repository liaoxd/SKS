package com.kiplening.sks.entity;

import java.util.Date;

/**
 * Created by MOON on 3/5/2016.
 */
public class Message {
    private int id;
    private String title;
    private String content;
    private String overview;
    private Date send_time;

    public Message(String title, String content, Date send_time){
        this.title = title;
        this.content = content;
        this.send_time = send_time;
        if (content.length() < 20){
            this.overview = content;
        }else {
            this.overview = content.substring(0,20);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }
}
