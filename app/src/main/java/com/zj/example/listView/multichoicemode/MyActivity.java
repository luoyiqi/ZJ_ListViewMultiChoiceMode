package com.zj.example.listView.multichoicemode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 *
 * create by zhengjiong
 * Date: 2015-03-22
 * Time: 20:49
 */
public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_choice_mode_multiple:
                startActivity(new Intent(MyActivity.this, ListViewMultiChoiceActivity.class));
                break;
            case R.id.btn_choice_mode_multiple_modal:
                startActivity(new Intent(MyActivity.this, ListViewMultiChoiceModalActivity.class));
                break;
        }
    }

}
