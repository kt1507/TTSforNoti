package com.example.ttsfornoti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

        // 설치된 앱 목록 가져오기
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list =  packageManager.queryIntentActivities(intent, 0);

        Collections.sort(list,new Comparator<ResolveInfo>(){
            public int compare(ResolveInfo a, ResolveInfo b){
                PackageManager pm = getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(
                        a.loadLabel(pm).toString(),
                        b.loadLabel(pm).toString());
            }
        });

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