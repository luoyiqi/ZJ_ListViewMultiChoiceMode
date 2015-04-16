package com.zj.example.listView.multichoicemode;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 使用ListView的CHOICE_MODE_MULTIPLE_MODAL,配合ActionBar的ActionMode實現Listview多選效果
 *
 * create by zhengjiong
 * Date: 2015-03-22
 * Time: 21:01
 */
public class ListViewMultiChoiceModalActivity extends Activity{

    private ActionBar mActionBar;
    private ActionMode mMode;
    private ListView mListView;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_multichoice_layout);


        mListView = (ListView) findViewById(R.id.listview);
        //設置成CHOICE_MODE_MULTIPLE_MODAL多選模式
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);

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

        initListener();
    }

    private void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewMultiChoiceModalActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            /**
             * listview中Item點擊狀態改變的時候會調用,也就是進入actionMode之後每次點擊item會調用
             */
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Log.i("zj", "onItemCheckedStateChanged position=" + position + " ,checked=" + checked);
                updateActionBarSelectCount();
                mAdapter.notifyDataSetChanged();
            }

            /**
             * 當進入ActionMode的時候會回調此方法,
             * 用於設置actionbar的title和設置menu菜單
             * @param mode
             * @param menu
             * @return
             */
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mMode = mode;
                getMenuInflater().inflate(R.menu.select_all, menu);
                mode.setTitle("已選擇:0");
                Log.i("zj", "onCreateActionMode");
                return true;
            }

            /**
             * 1.此方法會在onCreateActionMode之後調用,
             * 2.每次點擊menu之前會調用一次,
             * 3.mMode.invalidate()也會調用此方法
             * @param mode
             * @param menu
             * @return
             */
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                Log.i("zj", "onPrepareActionMode");
                MenuItem menuItem = menu.findItem(R.id.action_all_select);
                if (mListView.getCheckedItemCount() == mAdapter.getCount()) {
                    menuItem.setTitle("取消全选");
                } else {
                    menuItem.setTitle("全选");
                }
                return true;
            }

            /**
             * menu菜單點擊后會調用
             * @param mode
             * @param item
             * @return
             */
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                Log.i("zj", "onActionItemClicked");
                if (item.getItemId() == R.id.action_all_select) {
                    if (mListView.getCheckedItemCount() == mAdapter.getCount()) {
                        unSelectAllItem();
                    } else {
                        selectAllItem();
                    }
                }
                return true;
            }

            /**
             * 退出ActionMode模式之後調用
             * @param mode
             */
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                Log.i("zj", "onDestroyActionMode");
            }
        });
    }

    /**
     * 取消全選
     */
    private void unSelectAllItem() {
        mListView.clearChoices();
        /**
         * 這裡setItemChecked(0, false),
         * 是讓Listview退出ActionMode
         */
        mListView.setItemChecked(0, false);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 全選
     */
    private void selectAllItem() {
        for (int i = 0, count = mAdapter.getCount(); i < count; i++) {
            mListView.setItemChecked(i, true);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 更新ActionMode時候的ActionBar的title
     */
    private void updateActionBarSelectCount() {
        Log.i("zj", "updateActionBarSelectCount");
        mMode.setTitle("已選擇:" + mListView.getCheckedItemCount());

        /**
         * 會調用onPrepareActionMode方法
         *
         * 教程上寫的需要加這個,
         * 但是去掉也不影響,感覺沒必要加這個,暫時去掉
         */
        //mMode.invalidate();
    }

    /**
     * 進入ActionMode
     */
    private void actionModeStart() {
        mListView.setItemChecked(0, true);
        mListView.clearChoices();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.into_actionmode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_into_actionmode) {
            actionModeStart();
        }else if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
