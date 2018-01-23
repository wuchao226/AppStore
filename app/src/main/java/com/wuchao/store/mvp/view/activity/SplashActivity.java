package com.wuchao.store.mvp.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.widget.Button;
import android.widget.Toast;

import com.wuchao.store.R;
import com.wuchao.store.R2;
import com.wuchao.store.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @BindView(R2.id.enter_button)
    Button mEnterButton;
    private SharedPreferences sp = null;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initView() {
        sp = getSharedPreferences("appStore", Context.MODE_PRIVATE);
        if (!sp.getBoolean("isFirst", true)) {
            openActivity(HomeActivity.class);
            finish();
        }
    }

    @OnClick(R2.id.enter_button)
    public void onViewClicked() {
        sp.edit().putBoolean("isFirst", false).apply();
        initPermission();
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
            //ToastUtils.showToast("授权SD卡成功");
            Toast.makeText(this, "授权SD卡成功", Toast.LENGTH_LONG).show();

        } else {
            //ToastUtils.showToast("没有授权SD卡，可能会影响应用的使用");
            Toast.makeText(this, "没有授权SD卡，可能会影响应用的使用", Toast.LENGTH_LONG).show();
        }
        openActivity(HomeActivity.class);
        finish();
    }

}
