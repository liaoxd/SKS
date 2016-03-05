package com.kiplening.sks.entity;

import java.io.Serializable;

/**
 * Created by MOON on 2/28/2016.
 */
public class CompanyUser implements Serializable {
    /*
     * 每个人工号是不同的
     */
    public int _id;	//主键
    public String username;	//名字
    public String mobilePhone;	//手机号码
    public String officePhone;	//办公室电话
    public String familyPhone;	//家里座机
    public String position;	//职位
    public String company;	//部门
    public String address;	//住址
    public String zipCode;	//工号
    public String email;	//E-mail
    public String otherContact;
    public String remark;	//具体工作

    public int imageId;//用户在服务器的ID

    public String toString(){
        StringBuffer sb=new StringBuffer();

        sb.append("id=")
                .append(_id)
                .append(",username=")
                .append(username)
                .append(",mobilePhone=")
                .append(mobilePhone)
                .append(",officePhone=")
                .append(officePhone)
                .append(",familyPhone=")
                .append(familyPhone)
                .append(",position=")
                .append(position)
                .append(",company=")
                .append(company)
                .append(",address=")
                .append(address)
                .append(",zipCode=")
                .append(zipCode)
                .append(",email=")
                .append(email)
                .append(",otherContact=")
                .append(otherContact)
                .append(",remark=")
                .append(remark);

        return sb.toString();
    }
}
