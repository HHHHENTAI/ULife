package main_fragment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hhhhentai.Constant.Constant;
import com.example.hhhhentai.ulife.ChangeHeadphoto;
import com.example.hhhhentai.ulife.History;
import com.example.hhhhentai.ulife.R;
import com.example.hhhhentai.ulife.Set_userInfo;
import com.example.hhhhentai.ulife.Seting;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

//TODO 张松---
public class Fragment_user extends Fragment implements View.OnClickListener
{
    //用户手机号
    private String user_num;
    private Context context;

    private ImageView iv_history,iv_his,iv_setting,iv_set,iv_personinfo,iv_per;
    private TextView tv_user_name,tv_user_info;
    private TextView tv_history,tv_setting,tv_personinfo;
    private TextView tv_publish,tv_publish_c,tv_follow,tv_follow_c,tv_fans,tv_fans_c,tv_message,tv_message_c;
    private LinearLayout LL_p,LL_head;


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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;

        //获取用户手机号
        user_num=getArguments().getString("user_num");

        LinearLayout ll_history = (LinearLayout) view.findViewById(R.id.ll_history);
        LinearLayout ll_seting = (LinearLayout) view.findViewById(R.id.ll_seting);
        LinearLayout ll_set_userInfo = (LinearLayout) view.findViewById(R.id.ll_set_userInfo);
        ImageView iv_user_headPhoto = (ImageView) view.findViewById(R.id.iv_user_headPhoto);

        LL_p = (LinearLayout) view.findViewById(R.id.LL_p);
        LL_head = (LinearLayout) view.findViewById(R.id.LL_head);

        iv_history = (ImageView) view.findViewById(R.id.iv_history);
        iv_his = (ImageView) view.findViewById(R.id.iv_his);
        tv_history = (TextView) view.findViewById(R.id.tv_history);

        iv_setting = (ImageView) view.findViewById(R.id.iv_setting);
        iv_set = (ImageView) view.findViewById(R.id.iv_set);
        tv_setting = (TextView) view.findViewById(R.id.tv_setting);

        iv_personinfo = (ImageView) view.findViewById(R.id.iv_personinfo);
        iv_per = (ImageView) view.findViewById(R.id.iv_per);
        tv_personinfo = (TextView) view.findViewById(R.id.tv_personinfo);

        tv_publish = (TextView) view.findViewById(R.id.tv_publish);
        tv_publish_c = (TextView) view.findViewById(R.id.tv_publish_c);
        tv_follow = (TextView) view.findViewById(R.id.tv_follow);
        tv_follow_c = (TextView) view.findViewById(R.id.tv_follow_c);
        tv_fans = (TextView) view.findViewById(R.id.tv_fans);
        tv_fans_c = (TextView) view.findViewById(R.id.tv_fans_c);
        tv_message = (TextView) view.findViewById(R.id.tv_message);
        tv_message_c = (TextView) view.findViewById(R.id.tv_message_c);
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        tv_user_info = (TextView) view.findViewById(R.id.tv_user_info);

        tv_user_name.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.04f + 0.5f);
        tv_user_info.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f + 0.5f);

        tv_history.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.03f + 0.5f);
        tv_setting.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.03f + 0.5f);
        tv_personinfo.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.03f + 0.5f);

        tv_publish.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.03f + 0.5f);
        tv_publish_c.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.03f + 0.5f);
        tv_follow.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.03f + 0.5f);
        tv_follow_c.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.03f + 0.5f);
        tv_fans.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.03f + 0.5f);
        tv_fans_c.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.03f + 0.5f);
        tv_message.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.03f + 0.5f);
        tv_message_c.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.03f + 0.5f);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f),
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f));
        params.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        iv_history.setLayoutParams(params);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.35f + 0.5f),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        tv_history.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f),
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f));
        params2.setMargins((int)(0.59*Constant.displayWidth-Constant.displayHeight*0.8f/7.5f),0,0,0);
        iv_his.setLayoutParams(params2);


        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f),
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f));
        params3.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        iv_setting.setLayoutParams(params3);

        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.35f + 0.5f),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params4.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        tv_setting.setLayoutParams(params4);

        LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f),
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f));
        params5.setMargins((int)(0.59*Constant.displayWidth-Constant.displayHeight*0.8f/7.5f),0,0,0);
        iv_set.setLayoutParams(params5);


        LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f),
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f));
        params6.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        iv_personinfo.setLayoutParams(params6);

        LinearLayout.LayoutParams params7 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.35f + 0.5f),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params7.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        tv_personinfo.setLayoutParams(params7);

        LinearLayout.LayoutParams params8 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f),
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f));
        params8.setMargins((int)(0.59*Constant.displayWidth-Constant.displayHeight*0.8f/7.5f),0,0,0);
        iv_per.setLayoutParams(params8);

        LinearLayout.LayoutParams params9 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (Constant.displayHeight * 1.f/7.5f + 0.5f));
        params9.setMargins(0,(int) (Constant.displayHeight * 0.02 + 0.5f),0,0);
        LL_head.setLayoutParams(params9);


        ll_history.setOnClickListener(this);
        ll_seting.setOnClickListener(this);
        ll_set_userInfo.setOnClickListener(this);
        iv_user_headPhoto.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
       switch (view.getId())
       {
           case R.id.ll_history:
           {
               Intent intent = new Intent(getActivity(),History.class);
               intent.putExtra("user_num", user_num);
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
               intent.putExtra("user_num", user_num);
               startActivity(intent);
               break;
           }
           case R.id.iv_user_headPhoto:
           {
               Intent intent = new Intent(getActivity(),ChangeHeadphoto.class);
               intent.putExtra("user_num", user_num);
               startActivity(intent);
               break;
           }
       }

    }
}
