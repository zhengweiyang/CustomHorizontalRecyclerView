package com.example.zhengweiyang.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ScaleLayoutManager scaleLayoutManager;
    private RecyclerView recyclerView;
    private List<String> strList = new ArrayList<>();
    private int strListCount = 100;
    private int itemSpace = -50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initStrList();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        NumAdapter adapter = new NumAdapter(strList);
        scaleLayoutManager = new ScaleLayoutManager(this, itemSpace);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(scaleLayoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();//选中项居中
        snapHelper.attachToRecyclerView(recyclerView);
    }

    public void leftBtnOnClick(View view) {
        int currentPosition = scaleLayoutManager.getCurrentPosition();
        if(currentPosition > 0) {
            recyclerView.smoothScrollToPosition(currentPosition - 1);
        } else {
            Toast.makeText(getApplicationContext(),"已到最小值!",Toast.LENGTH_SHORT).show();
        }
    }

    public void rightBtnOnClick(View view) {
        int currentPosition = scaleLayoutManager.getCurrentPosition();
        if(currentPosition < strListCount - 1) {
            recyclerView.smoothScrollToPosition(currentPosition + 1);
        } else {
            Toast.makeText(getApplicationContext(),"已到最大值!",Toast.LENGTH_SHORT).show();
        }
    }

    private void initStrList() {
        for(int i = 1; i <= strListCount; i++) {
            strList.add(i + "");
        }
    }

    public class NumAdapter extends RecyclerView.Adapter<NumAdapter.ViewHolder> {

        private List<String> mStrList;

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView strtext;

            public ViewHolder(View view) {
                super(view);
                strtext = (TextView) view.findViewById(R.id.str_text);
            }
        }

        public NumAdapter(List<String> strList) {
            mStrList = strList;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            final ViewHolder holder = new ViewHolder(view);
            holder.strtext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion = holder.getAdapterPosition();
                    String str = mStrList.get(postion);
                    Toast.makeText(getApplicationContext(),"你点击的是：" + str,Toast.LENGTH_SHORT).show();
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.strtext.setText(mStrList.get(position));
        }

        @Override
        public int getItemCount() {
            return mStrList.size();
        }
    }
}
