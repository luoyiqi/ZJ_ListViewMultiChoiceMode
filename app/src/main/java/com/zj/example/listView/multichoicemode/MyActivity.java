package com.zj.example.listView.multichoicemode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
