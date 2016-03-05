package com.kiplening.sks.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.kiplening.sks.R;
import com.kiplening.sks.entity.MessageDef;

public class LoginActivity extends Activity {

    private SharedPreferences spf;
    private SharedPreferences.Editor edt;
    public static final String preference_name="userinfor";
    public static String USERNAME;
    private boolean fail_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spf = getSharedPreferences(preference_name,MODE_PRIVATE);
        edt = spf.edit();
        toMainActivity();
     //   isLogin();

        fail_flag = false;
    }

    /**
     * 判断当前是否处于已登录的状态
     */
    private void isLogin() {
        new Thread(){
            public void run(){
                String nm=spf.getString("username", null);
                String pw=spf.getString("pwd", null);

                System.out.println("IN PREFERENCE:username="+nm+",pwd="+pw);
                //以当前本机存储的账号密码尝试登陆,如果没保存直接结束这个线程
                /*
                if(nm!=null&&pw!=null){
                    USERNAME=nm;
                    String qry=HttpUtil.BASE_URL+"LoginServ?username="+nm+"&pwd="+pw;

                    handler.sendEmptyMessage(MainActivity.SHOW_BAR);

                    String ret=HttpUtil.queryStringForGet(qry);

                    if(ret!=null&&ret.startsWith(HttpUtil.LOGIN_RESULT)){
						//ret=ret.substring(HttpUtil.LOGIN_RESULT.length()+1);
						//if(ret.equals("1")){
						//	USERNAME=nm;
						//	handler.sendEmptyMessage(MainActivity.LOGIN_SUCCESS);
						//}
                        LoginResult lr=new LoginResult();
                        StringUtil.parseStringForLogin(ret, lr);
                        boolean res=checkUpdate(lr);
                        if(res){
                            loginSucceed(nm);
                        }
                        else{
                            System.out.println("LOGIN FAILED!!!!!");
                            handler.sendEmptyMessage(MainActivity.LOGIN_FAIL);
                        }
                    }
                    else if(ret==null||ret.equals(HttpUtil.HTTP_OVERTIME)){
                        handler.sendEmptyMessage(MainActivity.HTTP_OVERTIME);
                        return;
                    }

                }
                else{
                    System.out.println("THERE IS NO PREFERENCE!");
                }
                */

            }

        }.start();
    }
    private Handler handler=new Handler(){
        boolean main_flag=true;

        public void handleMessage(Message msg) {
            switch(msg.what){
                case MessageDef.LOGIN_SUCCESS:
				//progress_bar.dismissBar();

				//toMainActivity();
                    //if(contract_user_update&&main_flag){
                    if(main_flag){
                        //progress_bar.dismissBar();
                        main_flag=false;
                        toMainActivity();
                    }
                    break;
                case MessageDef.USERNAME_ERROR:
                    Toast.makeText(LoginActivity.this, "用户名错误", Toast.LENGTH_SHORT).show();;
                    break;
                case MessageDef.PWD_ERROR:
                    Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();;
                    break;
                case MessageDef.LOGIN_FAIL:
                    //progress_bar.dismissBar();
                    fail_flag=true;

                    Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新输入!", Toast.LENGTH_SHORT).show();
                    break;
                case MessageDef.HTTP_OVERTIME:
                    //progress_bar.dismissBar();
                    fail_flag=true;

                    Toast.makeText(LoginActivity.this, "网络不给力,请重试!", Toast.LENGTH_SHORT).show();
                    break;
                case MessageDef.SHOW_BAR:
                    //progress_bar.showBar("",10*1000);
                    fail_flag=false;

                    break;
                case MessageDef.LOAD_OVER_CONTACT:
                    //if(seccess_flag&&main_flag){
                    if(main_flag){
                        
                        //progress_bar.dismissBar();
                        main_flag=false;

                        toMainActivity();
                    }
                    break;

                default:
                    break;
            }

        };

    };

    private void toMainActivity() {
        Intent toMainIntent = new Intent(LoginActivity.this,MainActivity.class);
        toMainIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(toMainIntent);
        finish();
    }

}
