package com.kiplening.sks.entity;

/**
 * Created by MOON on 2/28/2016.
 */
public class MessageDef {
    /*
	 * 用于ListView更新
	 */
    public static final int HTTP_OVERTIME=4;
    public static final int REQUEST_SIZE=5;
    public static final int LOAD_OVER=100;
    public static final int NO_DATA=101;
    public static final int COMPRESSED_IMAGE_DOWNLOAD=102;
    public static final int LOAD_OVER_CONTACT=110;
    public static final int IMAGE_ROLL=103;
    public static final int LOAD_OVER_ZONE=104;
    public static final int READING_NUMBER_LOAD_OVER=105;
    public static final int ZONE_UPLOAD_OVER=106;
    public static final int ZONE_UPLOAD_FAIL=107;
    /*
     * 用于登录
     */
    public static final int LOGIN_SUCCESS=201;
    public static final int USERNAME_ERROR=202;
    public static final int PWD_ERROR=203;
    public static final int LOGIN_FAIL=204;
    public static final int SHOW_BAR=205;
    public static final int DISMISS_BAR=206;
    /*
     * 会议室
     */
    public static final int BOOK_INFORMATION_ERROR=301;
    public static final int BOOK_SECCESS=302;
    public static final int INFORMATION_LOAD_OVER=303;
    public static final int IMAGE_DOWNLOAD_OVER=304;
    public static final int DELETE_OVER=305;
    public static final int INTENT_FROM_CONTACT=306;
    public static final int SHOW_OCCUPIED=307;
    /*
     * 新闻
     */
    public static final int SPECIFIC_DEPARTMENT_NEWS=401;
    public static final int ALL_DEPARTMENT_NEWS=402;
    public static final int NEWS_SECONDARY=403;
    public static final int LOAD_NEWS=404;

    /*
     * 用户MainActivity
     */
    public static final int PWD_CHANGE_FAILURE=501;
    public static final int PWD_CHANGE_SECCESS=502;
    public static final int DO_IN_BACKGROUND=503;

    /*
     * 由于listview在加载时，有时候是从0开始计数，有时候是从1开始计数，所以需要一个这个冗余
     */
    public static final int ADAPTER_REDUNDENCE=1;
}
