package com.example.ttsfornoti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.Collections;
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
/*
        adapter.addItem("제목1", R.drawable.hannam_logo, "내용1");
        adapter.addItem("제목2", R.drawable.hannam_logo, "내용2");
        adapter.addItem("제목3", R.drawable.hannam_logo, "내용3");
        adapter.addItem("제목4", R.drawable.hannam_logo, "내용4");
        adapter.addItem("제목5", R.drawable.hannam_logo, "내용5");
*/

        // 설치된 앱 목록 가져오기
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list =  packageManager.queryIntentActivities(intent, 0);

        for (ResolveInfo info : list) {
            String appActivity = info.activityInfo.name;
            String appPackageName = info.activityInfo.packageName;
            String appName = info.loadLabel(packageManager).toString();

            Drawable drawable = info.activityInfo.loadIcon(packageManager);

            adapter.addItem(appName, drawable);

            Log.i("TEST", "appName : " + appName + ", appActivity : " + appActivity + ", appPackageName : " + appPackageName);

        }
        adapter.notifyDataSetChanged(); //어댑터의 변경을 알림
    }

}