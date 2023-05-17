package com.example.auditiondemo.AlertDialogUtilsDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.auditiondemo.R;

public class AlertDialogMainActivity extends BaseAlertDialogActivity {

    Button bt_alertDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_main);
        bt_alertDialog=findViewById(R.id.bt_alertdialog_1);
        bt_alertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MMAlertDialogUtils.showDialog(
                        AlertDialogMainActivity.this,
                        "标题",
                        "我是中国人，我爱我的祖国。祝祖国繁荣富强",
                        "取消",
                        "确定",
                        false,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogMainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        },
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogMainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

            }
        });
    }
}