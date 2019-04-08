package org.fonuhuolian.xnotifaction.dialog;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.fonuhuolian.xnotifaction.R;
import org.fonuhuolian.xnotifaction.utils.XNotifactionUtils;

public class SimpleNoticeDialog {

    private final TextView content;

    private AlertDialog dialog;

    private Activity activity;

    public SimpleNoticeDialog(final Activity context) {

        this.activity = context;

        View contentView = LayoutInflater.from(context).inflate(R.layout.x_notice_dialog, null);

        content = ((TextView) contentView.findViewById(R.id.x_custom_content));
        TextView cancle = ((TextView) contentView.findViewById(R.id.x_notice_cancle));
        TextView open = ((TextView) contentView.findViewById(R.id.x_notice_open));

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                XNotifactionUtils.setVersionNeverNotify(context);
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                XNotifactionUtils.jumpToAppDetailsSettings(context);
            }
        });

        dialog = new AlertDialog.Builder(context)
                .setView(contentView)
                .setCancelable(false)
                .create();
    }


    public void close() {

        if (activity != null && !activity.isFinishing()) {
            dialog.dismiss();
        }
    }

    public void show() {

        if (activity != null && !activity.isFinishing()) {
            dialog.show();
        }
    }

    public void show(String str) {

        if (activity != null && !activity.isFinishing()) {
            content.setText(str);
            dialog.show();
        }
    }
}
