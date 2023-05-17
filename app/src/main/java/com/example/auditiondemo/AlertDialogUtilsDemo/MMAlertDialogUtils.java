package com.example.auditiondemo.AlertDialogUtilsDemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.auditiondemo.AlertDialogUtilsDemo.utils.DensityUtils;
import com.example.auditiondemo.R;

/**
 * @ClassName MMAlertDialogUtils
 * @Description TODO
 * @Author YangChengLei
 * @Date 2023/5/17 17:04
 * @Version 1.0
 */
public class MMAlertDialogUtils {
    /**
     * @param context        上下文
     * @param title          标题
     * @param content        内容
     * @param btnCancleText  取消按钮文本
     * @param btnSureText    确定按钮文本
     * @param touchOutside   外部取消
     * @param cancleListener 取消监听
     * @param sureListener   确定监听
     * @return
     */
    public synchronized static AlertDialog showDialog(Context context,
                                                      String title,
                                                      String content,
                                                      String btnCancleText,
                                                      String btnSureText,
                                                      boolean touchOutside,
                                                      DialogInterface.OnClickListener cancleListener,
                                                      DialogInterface.OnClickListener sureListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(touchOutside);
        dialog.setCancelable(false);

        View view = View.inflate(context, R.layout.alert_dialog, null);
        //标题
        TextView tvTitle = view.findViewById(R.id.tv_alert_title);
        //内容
        TextView tvContent = view.findViewById(R.id.tv_alert_content);
        //取消按钮
        Button buttonCancle = view.findViewById(R.id.btn_alert_cancel);
        //确定按钮
        Button buttonOk = view.findViewById(R.id.btn_alert_ok);
        //线
        View viewLine = view.findViewById(R.id.v_alert_line);

        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }

        tvContent.setText(TextUtils.isEmpty(content) ? "" : content);

        if (TextUtils.isEmpty(btnCancleText)) {
            buttonCancle.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
        } else {
            buttonCancle.setText(btnCancleText);
        }

        buttonOk.setText(TextUtils.isEmpty(btnSureText) ? "确定" : btnSureText);
        final AlertDialog dialogFinal = dialog;
        final DialogInterface.OnClickListener finalCancleListener = cancleListener;
        final DialogInterface.OnClickListener finalSureListener = sureListener;
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalCancleListener.onClick(dialogFinal, DialogInterface.BUTTON_NEGATIVE);
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalSureListener.onClick(dialogFinal, DialogInterface.BUTTON_POSITIVE);
            }
        });

        //设置背景透明,去四个角
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        dialog.getWindow().setLayout(DensityUtils.dip2px(context, 290), LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setWindowAnimations(R.style.AnimMM);
        dialog.setContentView(view);

        return dialog;
    }
}
