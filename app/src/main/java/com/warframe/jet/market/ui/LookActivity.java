package com.warframe.jet.market.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.warframe.jet.market.R;
import com.warframe.jet.market.date.Lost;
import com.warframe.jet.market.utils.StaticClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by jet on 2018-08-11.
 */

public class LookActivity extends BaseActivity{
    private ListView listView;
    private Lost date;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground);
        //连接Bmob id
        Bmob.initialize(this, StaticClass.BMOB_ID);
        findView();
        loadDate();
    }
//查询
    private void loadDate() {
        StaticClass.data.clear();
        BmobQuery<Lost> bmobQuery = new BmobQuery<Lost>();
        bmobQuery.findObjects(new FindListener<Lost>() {
            @Override
            public void done(List<Lost> list, BmobException e) {
                if (e == null){
                    Toast.makeText(LookActivity.this,"连接成功！",Toast.LENGTH_SHORT).show();
                    int n = list.size();
                    //遍历所有数据
                    for (int i = 0; i< n ;i++){
                        StaticClass.data.add(list.get(i));
                    }
                    Collections.sort(StaticClass.data,c);
                    for (int i = 0; i <StaticClass.data.size();i++){
                        System.out.println(StaticClass.data.get(i).getTitle() + " " +
                                StaticClass.data.get(i).getDescribe() + StaticClass.data.get(i).getPhone());
                    }
                    initData();
                }else {
                    Toast.makeText(LookActivity.this, "连接失败！失败原因：" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        listView.setAdapter(new ItemListAdapter());
    }

    Comparator c = new Comparator<Lost>() {
        @Override
        public int compare(Lost o1, Lost o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };
    //适配器
    class ItemListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return StaticClass.data.size();
        }

        @Override
        public Object getItem(int position) {
            return StaticClass.data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_layout,null);

                viewHolder = new ViewHolder();
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
                date = StaticClass.data.get(position);
                viewHolder.tv_name.setText(date.getTitle());
                viewHolder.tv_price.setText(date.getPhone());
                viewHolder.tv_desc.setText(date.getDescribe());

            return convertView;
        }
        public class ViewHolder{
            public TextView tv_name;
            public TextView tv_price;
            public TextView tv_desc;
        }
    }
    private void findView() {
        listView = (ListView) findViewById(R.id.listView);
    }
}
