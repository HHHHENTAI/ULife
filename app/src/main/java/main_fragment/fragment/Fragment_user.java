package main_fragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hhhhentai.ulife.History;
import com.example.hhhhentai.ulife.R;
import com.example.hhhhentai.ulife.Set_userInfo;
import com.example.hhhhentai.ulife.Seting;

//TODO 张松---
public class Fragment_user extends Fragment implements View.OnClickListener
{
    //用户手机号
    private String user_num;

    /* Bundle 传参数 */
    public static Fragment_user newInstance(String user_num)
    {
        Bundle bundle =new Bundle();
        bundle.putString("user_num",user_num);
        Fragment_user item = new Fragment_user();
        item.setArguments(bundle);
        return item;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,null);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //获取用户手机号
        user_num=getArguments().getString("user_num");
       // Log.i("jjj", "onViewCreated: "+user_num);
        LinearLayout ll_history = (LinearLayout) view.findViewById(R.id.ll_history);
        LinearLayout ll_seting = (LinearLayout) view.findViewById(R.id.ll_seting);
        LinearLayout ll_set_userInfo = (LinearLayout) view.findViewById(R.id.ll_set_userInfo);
        ll_history.setOnClickListener(this);
        ll_seting.setOnClickListener(this);
        ll_set_userInfo.setOnClickListener(this);

    }



    @Override
    public void onClick(View view)
    {
       switch (view.getId())
       {
           case R.id.ll_history:
           {
               Intent intent = new Intent(getActivity(),History.class);
               startActivity(intent);
               break;
           }
           case R.id.ll_seting:
           {
               Intent intent = new Intent(getActivity(),Seting.class);
               startActivity(intent);
               break;
           }
           case R.id.ll_set_userInfo:
           {
               Intent intent = new Intent(getActivity(),Set_userInfo.class);
               startActivity(intent);
               break;
           }
       }

    }
}
