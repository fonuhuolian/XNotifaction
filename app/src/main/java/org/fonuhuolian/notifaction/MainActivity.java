package org.fonuhuolian.notifaction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.fonuhuolian.xnotifaction.dialog.SimpleNoticeDialog;
import org.fonuhuolian.xnotifaction.utils.XNotifactionUtils;

public class MainActivity extends AppCompatActivity {

    private SimpleNoticeDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (XNotifactionUtils.isNeedShowDialog(this)) {
            dialog = new SimpleNoticeDialog(this);
            dialog.show();
        }
    }
}
