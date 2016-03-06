package com.kiplening.sks.view;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kiplening.sks.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOON on 3/5/2016.
 */
public class ContactFragment extends Fragment {
    private View view;
    private ListView listView;
    /**联系人显示名称**/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;

    /**电话号码**/   //后面的数字都是我调试测出来的
    private static final int PHONES_NUMBER_INDEX = 7;

    /**头像ID**/
    private static final int PHONES_PHOTO_ID_INDEX = 2;

    /**联系人的ID**/
    private static final int PHONES_CONTACT_ID_INDEX = 3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_contact,null);
        listView = (ListView) view.findViewById(R.id.contact_list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final MyAdapter adapter = new MyAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri
                        .parse("tel:" +adapter.list.get(position).getPhoneNum()));
                startActivity(dialIntent);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private class MyAdapter extends BaseAdapter{
        private Context context;
        private List<MyContact> list = new ArrayList<>();
        private LayoutInflater listContainer;
        private class ListItemView{
            ImageView photo;
            TextView name;
            TextView phoneNum;
        }
        public MyAdapter(Context context){
            this.context = context;
            listContainer = LayoutInflater.from(context);
            getPhoneContacts();
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

                convertView = listContainer.inflate(R.layout.contact_list_item,null);

                listItemView.photo = (ImageView) convertView.findViewById(R.id.contact_icon);
                listItemView.name = (TextView) convertView.findViewById(R.id.contact_name);
                listItemView.phoneNum = (TextView) convertView.findViewById(R.id.phone_num);

                convertView.setTag(listItemView);
            }else {
                listItemView = (ListItemView) convertView.getTag();

            }
            //listItemView.sender.setText((String)list.get(selectID).getTitle());
            //listItemView.photo.setImageDrawable(list.get(selectID).getPhoto());
            listItemView.name.setText(list.get(selectID).getName());
            listItemView.phoneNum.setText(list.get(selectID).getPhoneNum());
            return convertView;
        }

        /**得到手机通讯录联系人信息**/
        private void getPhoneContacts() {

            ContentResolver resolver = context.getContentResolver();
            Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (cursor != null){
                System.out.println(cursor.getCount());
                for (int i = 0; i < cursor.getColumnCount(); i++){
                    System.out.println(cursor.getColumnName(i).toString());
                }
                while (cursor.moveToNext()){
                    //得到手机号码
                    String phoneNumber = cursor.getString(PHONES_NUMBER_INDEX);
                    //当手机号码为空的或者为空字段 跳过当前循环
                    if (TextUtils.isEmpty(phoneNumber))
                        continue;

                    MyContact contact = new MyContact();
                    contact.setPhoneNum(phoneNumber);
                    //得到联系人名称
                    contact.setName(cursor.getString(PHONES_DISPLAY_NAME_INDEX));

                    //得到联系人ID
                    contact.setContactId(cursor.getLong(PHONES_CONTACT_ID_INDEX));

                    //得到联系人头像ID
                    Long contactid = cursor.getLong(PHONES_PHOTO_ID_INDEX);

                    //得到联系人头像Bitamp
                    Bitmap contactPhoto = null;

                    //photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
                    if(cursor.getLong(PHONES_PHOTO_ID_INDEX) > 0 ) {
                        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactid);
                        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);
                        contact.setPhoto(BitmapFactory.decodeStream(input));
                    }else {
                        // contact.setPhoto(BitmapFactory.decodeResource(getResources(), R.drawable.contact_photo));
                        contact.setPhoto(null);
                    }
                    list.add(contact);
                }
            }

        }
        private class MyContact {
            private String name;
            private String phoneNum;
            private Bitmap photo;
            private Long contactId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoneNum() {
                return phoneNum;
            }

            public void setPhoneNum(String phoneNum) {
                this.phoneNum = phoneNum;
            }

            public Bitmap getPhoto() {
                return photo;
            }

            public void setPhoto(Bitmap photo) {
                this.photo = photo;
            }

            public Long getContactId() {
                return contactId;
            }

            public void setContactId(Long contactId) {
                this.contactId = contactId;
            }
        }
    }


}
