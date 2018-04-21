package com.cccisi.privacycollector.xsy;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.cccisi.privacycollector.R;
import com.cccisi.privacycollector.xsy.Utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// TODO 使用 多thread 优化，正在运行的程序，杀死该程序


public class AppInfo extends ListFragment {

    //three app list
    private ArrayList<AppItem> thirdAppList = new ArrayList<>();
    private ArrayList<AppItem> sysAppList = new ArrayList<>();
    private ArrayList<AppItem> runningAppList = new ArrayList<>();

    private PackageManager pm;


    //bind buttons
    @BindView(R.id.third_app)
    Button third_app;
    @BindView(R.id.sys_app)
    Button sys_app;
    @BindView(R.id.running_app)
    Button running_app;

    //app selected tag
    int appSelected;
    
    public AppInfo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appSelected = Constant.THIRDAPP;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_info, container, false);
        ButterKnife.bind(this,view);
        getAppList();
        AppAdapter adapter = new AppAdapter(this.getActivity(),thirdAppList);
        setListAdapter(adapter);
        return view;
    }

    @OnClick({R.id.third_app,R.id.sys_app,R.id.running_app})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.third_app:
                appSelected = Constant.THIRDAPP;
                AppAdapter thirdAdapter = new AppAdapter(this.getActivity(),thirdAppList);
                setListAdapter(thirdAdapter);
                break;
            case R.id.sys_app:
                appSelected = Constant.SYSAPP;
                AppAdapter sysAdapter = new AppAdapter(this.getActivity(),sysAppList);
                setListAdapter(sysAdapter);
                break;
            case R.id.running_app:
                appSelected = Constant.RUNNINGAPP;

                AppAdapter unInstAdapter = new AppAdapter(this.getActivity(),runningAppList);
                setListAdapter(unInstAdapter);
                break;
        }
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
       if (appSelected == Constant.THIRDAPP)
           startActivity(thirdAppList.get(position).appIntent);
    }


    public void getAppList(){
        pm = this.getActivity().getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo:packages){
            AppItem appItem = new AppItem();
            appItem.appName = packageInfo.applicationInfo.loadLabel(pm).toString();
            appItem.packageName= packageInfo.packageName;
            appItem.appIcon = packageInfo.applicationInfo.loadIcon(pm);
            appItem.versionName = packageInfo.versionName;
            appItem.appIntent = pm.getLaunchIntentForPackage(packageInfo.packageName);
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
            {
                thirdAppList.add(appItem);
            }else{
                sysAppList.add(appItem);
            }
        }

    }


    public AppItem getAppInfoByPackageName(String packageName){
        AppItem appItem = new AppItem();
        try {
            pm = this.getActivity().getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(packageName,PackageManager.GET_ACTIVITIES);
            appItem.appName = packageInfo.applicationInfo.loadLabel(pm).toString();
            appItem.packageName = packageName;
            appItem.versionName = packageInfo.versionName;
            appItem.appIcon = packageInfo.applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appItem;
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        AppInfoPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
//    }
}
