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

//TODO 赵效慷and江守鑫---
public class Fragment_user extends Fragment implements View.OnClickListener
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayout ll_history = (LinearLayout) getActivity().findViewById(R.id.ll_history);
        LinearLayout ll_seting = (LinearLayout) getActivity().findViewById(R.id.ll_seting);
        LinearLayout ll_set_userInfo = (LinearLayout) getActivity().findViewById(R.id.ll_set_userInfo);
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
               getActivity().finish();
               break;
           }
           case R.id.ll_seting:
           {
               Intent intent = new Intent(getActivity(),Seting.class);
               startActivity(intent);
               getActivity().finish();
               break;
           }
           case R.id.ll_set_userInfo:
           {
               Intent intent = new Intent(getActivity(),Set_userInfo.class);
               startActivity(intent);
               getActivity().finish();
               break;
           }
       }

    }
}
