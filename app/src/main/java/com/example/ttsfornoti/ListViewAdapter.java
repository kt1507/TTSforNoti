package com.example.ttsfornoti;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ImageView iconImageView;
    private TextView titleTextView;
    private Switch switchView;
    public String[] Check_Appname = new String[100];

    //Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    //ListviewAdapter의 생성자
    public ListViewAdapter(){
    }

    //Adapter에 사용되는 데이터의 개수를 리턴
    @Override
    public int getCount(){
        return listViewItemList.size();
    }

    //position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        //"listview_item" Layout 을 inflate하여 convertView 참조 획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        //화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        titleTextView = (TextView) convertView.findViewById(R.id.title);
        iconImageView = (ImageView) convertView.findViewById(R.id.icon);
        switchView = (Switch) convertView.findViewById(R.id.switch1);

        ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        titleTextView.setText(listViewItem.getTitle());
        iconImageView.setImageDrawable(listViewItem.getIcon());

        //스위치 작동
        switchView.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //스위치 체크 Up
                if(isChecked == true) {
                    //체크 된 리스트의 Appname 가져옴
                    String Appname =  listViewItemList.get(pos).getTitle();
                    Log.i("App_List", "Check Up : " + Appname);

                    int check_size = 0;
                    Log.i("AppList","check_size : " + check_size);

                    //Check_Appname 배열에 동일한 Appname이 있으면 check_size 1 증가
                    for(int i = 0; i < Check_Appname.length; i++){
                        if(Appname.equals(Check_Appname[i])){
                            check_size++;
                            Log.i("AppList","check_size++ : " + check_size);
                        }
                    }
                    //check_size가 1이 아니면 Check_Appname 배열에 추가
                    if(check_size != 1){
                        for(int i = 0; i < Check_Appname.length; i++){
                            if(Check_Appname[i] == null){
                                Check_Appname[i] = Appname;
                                Log.i("AppList","Check_Appname[" + i + "] : " + Check_Appname[i]);
                                break;
                            }
                        }
                    }
                }//if
                //스위치 체크 down
                if(isChecked == false){
                    String Appname =  listViewItemList.get(pos).getTitle();

                    for(int i = 0; i < Check_Appname.length; i++){
                        if(Appname.equals(Check_Appname[i])){
                            Log.i("AppList","Check_Appname[" + i + "]_" + Appname + " -> null");
                            Check_Appname[i] = null;
                        }
                    }
                }//if

                //값 전달하기
                Intent intent = new Intent(buttonView.getContext(), TTSService.class);
                intent.putExtra("Check_Appname_length", Check_Appname.length);
                intent.putExtra("Check_Appname",Check_Appname);
                context.startService(intent);

            }//onCheckedChanged
        });//setOnCheckedChangeListener

        return convertView;
    }//getView

    //지정한 위치(position)에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    //지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String title, Drawable icon){
        ListViewItem item = new ListViewItem();

        item.setTitle(title);
        item.setIcon(icon);

        listViewItemList.add(item);
    }

}
