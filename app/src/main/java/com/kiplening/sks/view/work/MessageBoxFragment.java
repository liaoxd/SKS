package com.kiplening.sks.view.work;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kiplening.sks.R;
import com.kiplening.sks.entity.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MOON on 3/5/2016.
 */
public class MessageBoxFragment extends Fragment {
    private View view;
    private ListView messageList;
    private List<Message> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_messagebox,null);
        messageList = (ListView) view.findViewById(R.id.message_list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        if (list.size() == 0){
            list.add(new Message("通知","明天下午开会",new Date()));
        }
        MyListAdapter adapter = new MyListAdapter(getActivity(), list);
        messageList.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private class MyListAdapter extends BaseAdapter{

        private Context context;
        private LayoutInflater listContainer;
        private List<Message> list;

        private class ListItemView{
            private ImageView image;
            private TextView sender;
            private TextView sendTime;
            private TextView overview;
        }
        public MyListAdapter(Context context, List<Message> list){
            this.context = context;
            listContainer = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int selectID = position;
            //自定义视图
            ListItemView listItemView = null;
            if (convertView == null){
                listItemView = new ListItemView();
                convertView = listContainer.inflate(R.layout.todo_list_item,null);

                listItemView.sender = (TextView) convertView.findViewById(R.id.message_sender_name);
                listItemView.overview = (TextView) convertView.findViewById(R.id.message_overview);
                listItemView.sendTime = (TextView) convertView.findViewById(R.id.message_send_time);

                convertView.setTag(listItemView);
            }else {
                listItemView = (ListItemView) convertView.getTag();

            }
            listItemView.sender.setText((String)list.get(selectID).getTitle());
            listItemView.overview.setText((String) list.get(selectID).getOverview());
            listItemView.sendTime.setText(list.get(selectID).getSend_time().toString());
            return convertView;
        }
    }
}
