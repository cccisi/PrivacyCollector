package com.cccisi.privacycollector.xsy;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.cccisi.privacycollector.R;
import com.cccisi.privacycollector.xsy.Utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

// TODO 使用 多thread 优化，正在运行的程序，杀死该程序


public class AppInfo extends ListFragment implements EasyPermissions.PermissionCallbacks{

    private static final String TAG = "AppInfo";
    private static final int SUCCESS = 2;
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

    @BindView(R.id.progress)
    ProgressBar progressBar;

    //app selected tag
    int appSelected;

    public AppInfo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_info, container, false);
        ButterKnife.bind(this,view);
        appSelected = Constant.THIRDAPP;
        new AppAsyncTask().execute(Constant.THIRDAPP);
        Log.d(TAG, "onCreateView: ");
        return view;
    }

    @OnClick({R.id.third_app,R.id.sys_app,R.id.running_app})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.third_app:
                appSelected = Constant.THIRDAPP;
                if (thirdAppList.isEmpty()){
                    new AppAsyncTask().execute(Constant.THIRDAPP);
                }else{
                    setListAdapter(new AppAdapter(this.getActivity(),thirdAppList));
                }
                break;
            case R.id.sys_app:
                appSelected = Constant.SYSAPP;
                if (sysAppList.isEmpty()){
                    new AppAsyncTask().execute(Constant.SYSAPP);
                }else{
                    setListAdapter(new AppAdapter(this.getActivity(),sysAppList));
                }
                break;
            case R.id.running_app:
                appSelected = Constant.RUNNING_APP;

                setListAdapter(new AppAdapter(this.getActivity(),runningAppList));
//                if (EasyPermissions.hasPermissions(getContext(),Constant.APP_PERMISSIONS_EG_L)) {
//                    if (runningAppList.isEmpty()){
//                        new AppAsyncTask().execute(Constant.RUNNING_APP);
//                    }else{
//                        setListAdapter(new AppAdapter(this.getActivity(),runningAppList));
//                    }
//                }else{
//                    EasyPermissions.requestPermissions(this,"get_usage",3,Constant.APP_PERMISSIONS_EG_L);
//                }

                break;
        }
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
       if (appSelected == Constant.THIRDAPP)
           startActivity(thirdAppList.get(position).appIntent);
    }


//    public AppItem getAppInfoByPackageName(String packageName){
//        AppItem appItem = new AppItem();
//        try {
//            pm = this.getActivity().getPackageManager();
//            PackageInfo packageInfo = pm.getPackageInfo(packageName,PackageManager.GET_ACTIVITIES);
//            appItem.appName = packageInfo.applicationInfo.loadLabel(pm).toString();
//            appItem.packageName = packageName;
//            appItem.versionName = packageInfo.versionName;
//            appItem.appIcon = packageInfo.applicationInfo.loadIcon(pm);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return appItem;
//    }


//    public ArrayList<AppItem> getRunningAppList() {
        //            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                Log.d(TAG, "doInBackground: ");
//                long INTERVAL = 30 * 60 * 1000;
//                long currentTimeMillis = System.currentTimeMillis();
//                UsageStatsManager mUsageStatsManager = (UsageStatsManager) getContext().getSystemService(Context.USAGE_STATS_SERVICE);
//                List<UsageStats> usageStatses = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, currentTimeMillis - INTERVAL, currentTimeMillis + INTERVAL);
//                if (usageStatses == null || usageStatses.isEmpty()) {
//                    Log.d(TAG, "doInBackground: no app");
//                    return null;
//                }
//                for (UsageStats usageStats : usageStatses) {
//                    runningAppList.add(getAppInfoByPackageName(usageStats.getPackageName()));
//                }
//            } else {
//                List<ActivityManager.RunningTaskInfo> runningTaskInfos = null;
//                ActivityManager mActivityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
//                try {
//                    runningTaskInfos = mActivityManager.getRunningTasks(Integer.MAX_VALUE);
//                } catch (Exception e) {
//                    Log.i(TAG, "error when getting runningTaskInfos");
//                    e.printStackTrace();
//                    return null;
//                }
//                if (runningTaskInfos == null) {
//                    Log.i(TAG, "null runningTaskInfos");
//                    return null;
//                }
//                if (runningTaskInfos.isEmpty()) {
//                    return null;
//                }
//                for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTaskInfos) {
//                    AppItem appItem = getAppInfoByPackageName(runningTaskInfo.baseActivity.getPackageName());
//                    runningAppList.add(appItem);
//                }
//            }
//        return runningAppList;
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (runningAppList.isEmpty()){
            new AppAsyncTask().execute(Constant.RUNNING_APP);
        }else{
            setListAdapter(new AppAdapter(this.getActivity(),runningAppList));
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    class AppAsyncTask extends AsyncTask<Integer,Void,ArrayList> {

        @Override
        protected ArrayList doInBackground(Integer... integers) {
            pm = getActivity().getPackageManager();
            List<PackageInfo> packages = pm.getInstalledPackages(0);
            for (PackageInfo packageInfo : packages) {
                AppItem appItem = new AppItem();
                appItem.appName = packageInfo.applicationInfo.loadLabel(pm).toString();
                appItem.packageName = packageInfo.packageName;
                appItem.appIcon = packageInfo.applicationInfo.loadIcon(pm);
                appItem.versionName = packageInfo.versionName;
                appItem.appIntent = pm.getLaunchIntentForPackage(packageInfo.packageName);
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    thirdAppList.add(appItem);
                } else {
                    sysAppList.add(appItem);
                }
            }

            if (integers[0] == Constant.THIRDAPP) return thirdAppList;
            else if (integers[0] == Constant.SYSAPP) return sysAppList;
            else if(integers[0] == Constant.RUNNING_APP) return runningAppList;
            return null;
        }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);
            if (isCancelled()) return;
            Log.d(TAG, "onPostExecute: ");
            setListAdapter(new AppAdapter(getActivity(), arrayList));
            progressBar.setVisibility(View.GONE);
        }
    }
}
