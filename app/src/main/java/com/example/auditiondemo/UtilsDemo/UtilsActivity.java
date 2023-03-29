package com.example.auditiondemo.UtilsDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.auditiondemo.R;
import com.example.auditiondemo.UtilsDemo.http.HttpRequest;
import com.example.auditiondemo.UtilsDemo.listener.CallBackList;
import com.example.auditiondemo.UtilsDemo.model.LoginBackModel;
import com.example.auditiondemo.databinding.ActivityUtilsBinding;
/*TODO
   listener文件夹，utils文件夹，CallBackList,HttpCall,HttpStatusCode,ResponseModel,RetrofitHelper
 * 以上文件是封装一个Retrofit的库
 * 具体实现Demo:UtilsActivity+http文件夹+model文件夹
 * */
public class UtilsActivity extends AppCompatActivity {
    ActivityUtilsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_utils);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_utils);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginName=binding.etLoginName.getText().toString().trim();
                String pwd=binding.etPwd.getText().toString().trim();
                if(TextUtils.isEmpty(loginName)){
                    Toast.makeText(UtilsActivity.this, "请输入登录名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(UtilsActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.progressBar.setVisibility(View.VISIBLE);
                HttpRequest.login(UtilsActivity.this, loginName, pwd, new CallBackList<LoginBackModel>() {
                    @Override
                    public void onSuccess(String method, LoginBackModel content) {
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(UtilsActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String method, String error) {
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(UtilsActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}