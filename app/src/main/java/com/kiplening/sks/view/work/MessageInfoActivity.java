package com.kiplening.sks.view.work;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.kiplening.sks.R;

/**
 * Created by MOON on 3/5/2016.
 */
public class MessageInfoActivity extends Activity{
    private TextView content;
    private TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.layout_message_info);
        setTitle(intent.getStringExtra("title"));

        content = (TextView) findViewById(R.id.message_content);
        time = (TextView) findViewById(R.id.info_time);

        content.setText(intent.getStringExtra("content"));
        time.setText(intent.getStringExtra("time"));
    }
}
