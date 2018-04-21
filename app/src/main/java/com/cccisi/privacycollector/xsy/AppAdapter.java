package com.cccisi.privacycollector.xsy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cccisi.privacycollector.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AppAdapter extends BaseAdapter {

    static class ViewHolder{
        @BindView(R.id.app_name)
        TextView appName;
        @BindView(R.id.pkg_name)
        TextView pkgName;
        @BindView(R.id.version_name)
        TextView verName;
        @BindView(R.id.app_icon)
        ImageView appIcon;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    List<AppItem> appList;
    LayoutInflater inflater;

    public AppAdapter(Context context, List<AppItem> apps) {
        inflater = LayoutInflater.from(context);
        appList = apps;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.app_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        AppItem appItem = (AppItem)getItem(position);
        holder.appIcon.setImageDrawable(appItem.appIcon);
        holder.appName.setText(appItem.appName);
        holder.pkgName.setText(appItem.packageName);
        holder.verName.setText(appItem.versionName);
        return convertView;
    }
}
