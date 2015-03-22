package com.zj.example.listView.multichoicemode;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * create by zhengjiong
 * Date: 2015-03-22
 * Time: 21:01
 */
public class ListViewMultiChoiceActivity extends Activity{
    private ActionBar mActionBar;
    private ListView mListView;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_multichoice_layout);

        mListView = (ListView) findViewById(R.id.listview);
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.notifyDataSetChanged();
                mActionBar.setTitle("已選擇:" + mListView.getCheckedItemCount());
            }
        });

        mAdapter = new ListAdapter(this, mListView);
        mListView.setAdapter(mAdapter);

        mActionBar = getActionBar();
        /*actionBar.setDisplayOptions(
                ActionBar.DISPLAY_SHOW_CUSTOM,
                ActionBar.DISPLAY_SHOW_CUSTOM |
                        ActionBar.DISPLAY_SHOW_TITLE |
                        ActionBar.DISPLAY_SHOW_HOME
        );*/
        mActionBar.setDisplayHomeAsUpEnabled(true);//顯示返回箭頭
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(true);//顯示標題
        mActionBar.setTitle("已選擇:0");
        //actionBar.setCustomView(R.layout.custom_actionbar);
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
        }else if (id == android.R.id.home) {
            ListViewMultiChoiceActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
