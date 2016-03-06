package com.kiplening.sks.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kiplening.sks.R;
import com.kiplening.sks.view.discovery.SettingActivity;

/**
 * Created by MOON on 3/5/2016.
 */
public class DiscoveryFragment extends Fragment {
    private View view;
    private TextView friendActivities;
    private TextView saySomething;
    private TextView settings;
    private TextView logout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_others,null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        friendActivities = (TextView) view.findViewById(R.id.friends_activity);
        saySomething = (TextView) view.findViewById(R.id.say_something);
        settings = (TextView) view.findViewById(R.id.settings);
        logout = (TextView) view.findViewById(R.id.logout);

        friendActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity("friendActivity");
            }
        });
        saySomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity("saysomething");
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity("setting");
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity("logout");
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    private void toActivity(String nameOfActivity){
        Intent intent ;
        switch (nameOfActivity){
            case "setting":intent = new Intent(getActivity(), SettingActivity.class);break;
            case "saysomething":intent = new Intent(getActivity(), SettingActivity.class);break;
            case "friendActivity":intent = new Intent(getActivity(), SettingActivity.class);break;
            case "logout":intent = new Intent(getActivity(), SettingActivity.class);break;
            default:intent = new Intent(getActivity(), SettingActivity.class);
        }
        startActivity(intent);
    }
}
