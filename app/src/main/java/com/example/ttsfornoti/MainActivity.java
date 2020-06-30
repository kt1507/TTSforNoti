package com.example.ttsfornoti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adapter 생성
        adapter = new ListViewAdapter();

        //리스트뷰 참조 및 Adapter 달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        adapter.addItem("제목1", R.drawable.hannam_logo, "내용1");
        adapter.addItem("제목2", R.drawable.hannam_logo, "내용2");
        adapter.addItem("제목3", R.drawable.hannam_logo, "내용3");
        adapter.addItem("제목4", R.drawable.hannam_logo, "내용4");
        adapter.addItem("제목5", R.drawable.hannam_logo, "내용5");

        adapter.notifyDataSetChanged(); //어댑터의 변경을 알림

        // 설치된 앱 목록 가져오기
        ArrayList<String> apps = new ArrayList<String>();
        List<PackageInfo> pack = getPackageManager().getInstalledPackages(0);
        for(int i = 0; i < pack.size(); i++){
            apps.add(pack.get(i).packageName);
        }

// 패키지명을 이용해 아이콘 가져오기
        try {
            Drawable App_icon = getPackageManager().getApplicationIcon("App_packageName");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

// 패키지명을 이용해 앱 이름 가져오기
        try{
            String App_name = getPackageManager().getApplicationLabel
                    (getPackageManager().getApplicationInfo
                            ("App_packageName",PackageManager.GET_UNINSTALLED_PACKAGES))
                    .toString();
        } catch (PackageManager.NameNotFoundException e) {}}
}