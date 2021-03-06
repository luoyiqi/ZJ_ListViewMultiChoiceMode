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
 * 使用ListView的CHOICE_MODE_MULTIPLE模式,來實現多選效果
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
        //設置成CHOICE_MODE_MULTIPLE多選模式
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.notifyDataSetChanged();
                updateSelectCount();
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

    /**
     * 更新ActionBar顯示的條數
     */
    private void updateSelectCount() {
        //mListView.getCheckedItemCount()獲取當前被選擇的條數
        mActionBar.setTitle("已選擇:" + mListView.getCheckedItemCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_all, menu);
        return true;
    }

    /**
     * onOptionsItemSelected執行之前會先調用此方法
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_all_select);
        if (mAdapter.getCount() == mListView.getCheckedItemCount()) {
            menuItem.setTitle("不全選");
        } else {
            menuItem.setTitle("全選");
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_all_select) {
            if (mAdapter.getCount() == mListView.getCheckedItemCount()) {
                unSelectAll();
            } else {
                selectAll();
            }
            updateSelectCount();
            //必須要加上notifyDataSetChanged,不然unSelectAll的時候,有些item還是被選擇狀態
            mAdapter.notifyDataSetChanged();
            return true;
        }else if (id == android.R.id.home) {
            ListViewMultiChoiceActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 清除全選
     */
    private void unSelectAll(){
        mListView.clearChoices();
    }

    /**
     * 全選
     */
    private void selectAll() {
        for (int i = 0, count = mAdapter.getCount(); i < count; i++) {
            mListView.setItemChecked(i, true);
        }
    }
}
