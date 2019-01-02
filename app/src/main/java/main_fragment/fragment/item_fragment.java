package main_fragment.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hhhhentai.ulife.R;

public class item_fragment extends Fragment {

    public item_fragment(){};



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.viewpager_test,null);
        return view;
    }

    /*  在onCreateView 后触发的事件  */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //todo 页面显示
        TextView textView = (TextView)view.findViewById(R.id.tx_test);
        textView.setText(getArguments().getString("text"));
    }

     /* Bundle 传参数 */
    public static item_fragment newInstance(String text)
    {
        Bundle bundle =new Bundle();
        bundle.putString("text",text);
        item_fragment item = new item_fragment();
        item.setArguments(bundle);
        return item;
    }
}
