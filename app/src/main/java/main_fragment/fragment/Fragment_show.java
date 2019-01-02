package main_fragment.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hhhhentai.ulife.R;

//TODO 赵效慷and江守鑫---
public class Fragment_show extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       //todo 创建视图
        View view =inflater.inflate(R.layout.fragment_show,null);
        return view;
    }
}
