package com.kiplening.sks.entity;

import java.util.Date;

/**
 * Created by MOON on 2/25/2016.
 */
public class CompanyActivities extends News{
    private Date startdate;

    public CompanyActivities(int id){

        this.news_id=id;

    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
}
