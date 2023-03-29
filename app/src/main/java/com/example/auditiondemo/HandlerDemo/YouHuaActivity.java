package com.example.auditiondemo.HandlerDemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.auditiondemo.R;
import com.example.auditiondemo.databinding.ActivityYouHuaBinding;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*****
 *
 *
 * 优化Handler+weak引用从而去掉内存泄漏 的风险
 * */
public class YouHuaActivity extends AppCompatActivity {
    private ActivityYouHuaBinding binding;
    Handler handler=new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_you_hua);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_you_hua);
        Request request= new Request.Builder()
                .url("https://wap.peopleapp.com/article/5933922/5852020")
                .build();
        OkHttpClient client=new OkHttpClient();
        Call call= client.newCall(request);
       //异步请求，如果同步请求需要开启线程
        call.enqueue(new Callback() {
           @Override
           public void onFailure(@NotNull Call call, @NotNull IOException e) {

           }

           @Override
           public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Bundle bundle=new Bundle();
                bundle.putString("response",response.toString());
                Message msg=Message.obtain();
                msg.what=1;
                msg.setData(bundle);
                handler.sendMessage(msg);
           }
       });
    }

    private static class MyHandler extends Handler{
        WeakReference<YouHuaActivity> mWeakReference;
        private MyHandler(YouHuaActivity youHuaActivity){
            mWeakReference=new WeakReference<>(youHuaActivity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if(mWeakReference.get()!=null){
                        mWeakReference.get().binding.mTV.setText(msg.getData().getString("response"));
                    }
                    break;
                case 2:
                    break;

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}