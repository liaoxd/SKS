package com.kiplening.sks.view.work;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kiplening.sks.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOON on 3/5/2016.
 */
public class TaskListFragment extends Fragment {
    private View view;
    private EditText addTodo;
    private ListView todoList;
    private List<String> myTodo = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_todolist,null);
        addTodo = (EditText) view.findViewById(R.id.add_todo);
        todoList = (ListView) view.findViewById(R.id.todo_list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (myTodo.size() == 0){
            myTodo.add("完成代码调试");
            myTodo.add("sleep");
        }
        final MyAdapter adapter = new MyAdapter(getActivity(),myTodo);
        todoList.setAdapter(adapter);
        addTodo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER){
                    myTodo.add(addTodo.getText().toString());
                    adapter.notifyDataSetChanged();
                    addTodo.clearFocus();
                    addTodo.setText("");
                    return true;
                }
                return false;
            }
        });
    }

    private class MyAdapter extends BaseAdapter{
        public class ListItemView{   //自定义控件集合
            RadioButton check;
            TextView todo;
            Button stick;
        }
        private List<String> list = new ArrayList<>();
        private Context context;
        private LayoutInflater listContainer;

        public MyAdapter(Context context, List<String> list){
            this.context = context;
            listContainer = LayoutInflater.from(context);
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
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

                listItemView.stick = (Button) convertView.findViewById(R.id.stick);
                listItemView.todo = (TextView) convertView.findViewById(R.id.todo_info);
                listItemView.check = (RadioButton) convertView.findViewById(R.id.todo_check);

                convertView.setTag(listItemView);
            }else {
                listItemView = (ListItemView) convertView.getTag();

            }
            listItemView.todo.setText((String)list.get(selectID));
            listItemView.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        list.remove(selectID);
                        notifyDataSetChanged();
                    }
                }
            });
            listItemView.stick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temp = list.get(selectID);
                    for (int i = selectID; i > 0; i--) {
                        list.set(selectID, list.get(i - 1));
                    }
                    list.set(0, temp);
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }
}
