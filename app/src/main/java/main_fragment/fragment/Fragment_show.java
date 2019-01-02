package main_fragment.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hhhhentai.ulife.R;

//TODO 赵效慷and江守鑫---
import java.util.ArrayList;
import java.util.List;

public class Fragment_show extends Fragment {
    private ViewPager pager;
    private TabLayout tab;
    private List<String> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       //todo 创建视图
        View view =inflater.inflate(R.layout.fragment_show,null);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("jjj", "onViewCreated: ");


        this.pager = (ViewPager) view.findViewById(R.id.pager);
        this.tab = (TabLayout) view.findViewById(R.id.tab);
        initData();

        /*设置Adapter*/
        pager.setAdapter(new MyAdapter(getChildFragmentManager(),list));

        /*Tab与ViewPager绑定*/
        tab.setupWithViewPager(pager);

    }
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            list.add("第"+i+"页");

        }
    }
}
