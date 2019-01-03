package main_fragment.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hhhhentai.ulife.R;
import java.util.ArrayList;
import java.util.List;

public class Fragment_show extends Fragment {
    //pager是下边的内容展示
    //tablayout是上边的滑动的小组件
    private ViewPager pager;
    /*TabLayout提供了一个水平的布局用来展示Tabs*/
    private TabLayout tab;
    private List<String> list = new ArrayList<String>(){};
    private String [] strings = {"推荐","学习","游戏","生活"};
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

        this.pager = view.findViewById(R.id.pager);
        this.tab =  view.findViewById(R.id.tab);
        initData();

        /*设置Adapter*/
        pager.setAdapter(new ViewPageAdapter(getChildFragmentManager(),list));
        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
        /*Tab与ViewPager绑定*/
        tab.setupWithViewPager(pager);
    }
    private void initData() {
        for (int i = 0; i < 4 ; i++) {
            list.add(strings[i]);
        }
    }
}
