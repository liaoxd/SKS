package com.kiplening.sks.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kiplening.sks.entity.CompanyUser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MOON on 2/28/2016.
 */
public class DataBaseHelper {
    public static final String DB_DBNAME="contact";
    public static final String DB_TABLENAME="user";
    public static final int VERSION = 1;
    public static SQLiteDatabase dbInstance;
    private MyDBHelper myDBHelper;
    private StringBuffer tableCreate;
    private Context context;

    public static final String COMPANY_TABLE="departments";
    public static final String MEETING_ROOM_INFOR_TABLE="room_infor";

    public static final String ZIP_CODE_USER="zipcode";
    public static final String USERNAME_USER="name";
    public static final String IMAGE_ID_USER="othercontact";
    public static final String USER_ID="imageid";

    public DataBaseHelper(Context context){
        this.context = context;
    }

    /**
     * 打开一个数据库
     */
    public void openDataBase(){
        if (dbInstance == null){
            myDBHelper = new MyDBHelper(context,DB_DBNAME,VERSION);
            dbInstance = myDBHelper.getWritableDatabase();
        }
    }

    /**
     * 往数据库里面的user表插入一条数据，若失败返回-1
     * @param user
     * @return 失败返回-1
     */
    public long insert(CompanyUser user) {
        ContentValues values = new ContentValues();
        values.put("name", user.username);
        values.put("mobilephone", user.mobilePhone);
        values.put("officephone", user.officePhone);
        values.put("familyphone", user.familyPhone);
        values.put("address", user.address);
        values.put("othercontact", user.otherContact);
        values.put("email",user.email);
        values.put("position", user.position);
        values.put("company", user.company);
        values.put("zipcode", user.zipCode);
        values.put("remark", user.remark);
        values.put("imageid", user.imageId);
        //values.put("privacy", user.privacy);
        //return dbInstance.insert(DB_TABLENAME, null, values);
        return dbInstance.insertWithOnConflict(DB_TABLENAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }
    /**
     * 获得数据库中所有的用户，将每一个用户放到一个map中去，然后再将map放到list里
     面去返回
     * @param
     * @return list
     */
    public ArrayList getAllUser() {
        ArrayList list = new ArrayList();
        Cursor cursor = null;
		/*if(privacy) {
			cursor = dbInstance.query(DB_TABLENAME,new String[]{"_id","name","mobilephone","officephone","familyphone","address","othercontact","email","position","company","zipcode","remark","imageid"},
				"privacy=1",null,null,null,null);
		} else {
			cursor = dbInstance.query(DB_TABLENAME,new String[]{"_id","name","mobilephone","officephone","familyphone","address","othercontact","email","position","company","zipcode","remark","imageid"},
				"privacy=0",null,null,null,null);
		}*/
        cursor = dbInstance.query(DB_TABLENAME,new String[]{"_id","name","mobilephone","officephone","familyphone","address","othercontact","email","position","company","zipcode","remark","imageid"},
                null,null,null,null,null);
        while(cursor.moveToNext()) {
            HashMap item = new HashMap();
            item.put("_id",
                    cursor.getInt(cursor.getColumnIndex("_id")));
            item.put("name",
                    cursor.getString(cursor.getColumnIndex("name")));
            item.put("mobilephone",
                    cursor.getString(cursor.getColumnIndex("mobilephone")));
            item.put("officephone",
                    cursor.getString(cursor.getColumnIndex("officephone")));
            item.put("familyphone",
                    cursor.getString(cursor.getColumnIndex("familyphone")));
            item.put("address",
                    cursor.getString(cursor.getColumnIndex("address")));
            item.put("othercontact",
                    cursor.getString(cursor.getColumnIndex("othercontact")));
            item.put("email",
                    cursor.getString(cursor.getColumnIndex("email")));
            item.put("position",
                    cursor.getString(cursor.getColumnIndex("position")));
            item.put("company",
                    cursor.getString(cursor.getColumnIndex("company")));
            item.put("zipcode",
                    cursor.getString(cursor.getColumnIndex("zipcode")));
            item.put("remark",
                    cursor.getString(cursor.getColumnIndex("remark")));
            item.put("imageid",
                    cursor.getInt(cursor.getColumnIndex("imageid")));
            list.add(item);
        }
        return list;
    }




    class MyDBHelper extends SQLiteOpenHelper {

        public MyDBHelper(Context context, String name,int version) {
            super(context, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
			/*
			 * 创建用户表
			 */
            tableCreate = new StringBuffer();
            tableCreate.append("create table ")
                    .append(DB_TABLENAME)
                    .append(" (")
                    .append("_id integer primary key autoincrement,")
                    .append("name varchar(50),")
                    .append("mobilephone varchar(50),")
                    .append("officephone varchar(50),")
                    .append("familyphone varchar(50),")
                    .append("address varchar(100),")
                    .append("othercontact varchar(100),")
                    .append("email varchar(100),")
                    .append("position varchar(50),")
                    .append("company varchar(50),")
                    .append("zipcode varchar(50) unique,")	//唯一的
                    .append("imageid int,")
                    .append("remark varchar(200)")
                            //.append("privacy int ")
                    .append(")");
            System.out.println(tableCreate.toString());
            db.execSQL(tableCreate.toString());

			/*
			 * 创建部门表
			 */
            StringBuffer sb=new StringBuffer("create table ");
            sb.append(COMPANY_TABLE)
                    .append(" (")
                    .append("_id integer primary key autoincrement,")
			/*
			 * 本来在服务器端，若某个部门只有一级，那么会有两条记录，即自己的自己的子部门，但是由于这里departmentName是唯一的，所以在一级部门后面都加了一个空格
			 */
                    .append("departmentName varchar(50) unique")	//唯一的

			/*
			 * 2015-02-04
			 */
                    .append(',')
                    .append("parentDepartment varchar(50)")

			/*
			 * 2015-05-15
			 */
                    .append(',')
                    .append("remoteID integer")

                    .append(")");
            System.out.println("[DB HELPER]create company table:"+sb.toString());
            db.execSQL(sb.toString());

			/*
			 * 创建会议室表
			 */
            StringBuffer sb_meeting_room_infor=new StringBuffer("create table ");
            sb_meeting_room_infor.append(MEETING_ROOM_INFOR_TABLE)
                    .append(" (")
                    .append("_id integer primary key autoincrement,")
                    .append("capacity integer,")
                    .append("roomID integer unique,")
                    .append("roomName varchar(50),")
                    .append("equipment varchar(50),")
                    .append("responsor varchar(20)")
                    .append(")");
            db.execSQL(sb_meeting_room_infor.toString());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "drop table if exists " + DB_TABLENAME;
            db.execSQL(sql);
            myDBHelper.onCreate(db);
        }
    }
}
