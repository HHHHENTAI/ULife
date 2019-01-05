package main_fragment.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import com.example.hhhhentai.Constant.Constant;
import com.example.hhhhentai.ulife.ChangeHeadphoto;
import com.example.hhhhentai.ulife.History;
import com.example.hhhhentai.ulife.R;
import com.example.hhhhentai.ulife.Set_userInfo;
import com.example.hhhhentai.ulife.Seting;
import com.example.hhhhentai.ulife.publishActivity;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;

import static android.app.Activity.RESULT_OK;

//TODO 张松---
public class Fragment_user extends Fragment implements View.OnClickListener {
    //用户手机号
    private String user_num;
    private Context context;
    private ImageView iv_user_headPhoto;
    private DbHelp_NEWS help_news;
    private Database_News database_news;
    private TextView tv_user_name;
    private TextView tv_user_info;
    private String imagePath;
    private LinearLayout publish;
     private  TextView pub_num;

    private ImageView iv_history,iv_his,iv_setting,iv_set,iv_personinfo,iv_per;
    private TextView tv_history,tv_setting,tv_personinfo;
    private TextView tv_publish,tv_publish_c,tv_follow,tv_follow_c,tv_fans,tv_fans_c,tv_message,tv_message_c;
    private LinearLayout LL_p,LL_head;
    private String headimg;


    /* Bundle 传参数 */
    public static Fragment_user newInstance(String user_num) {
        Bundle bundle = new Bundle();
        bundle.putString("user_num", user_num);
        Fragment_user item = new Fragment_user();
        item.setArguments(bundle);
        return item;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;

        //数据库操作封装函数
        help_news = new DbHelp_NEWS(context);
        database_news = new Database_News(help_news);
        pub_num =view.findViewById(R.id.tv_publish);
        //获取用户手机号
        user_num = getArguments().getString("user_num");

        LinearLayout ll_history = (LinearLayout) view.findViewById(R.id.ll_history);
        LinearLayout ll_seting = (LinearLayout) view.findViewById(R.id.ll_seting);
        LinearLayout ll_set_userInfo = (LinearLayout) view.findViewById(R.id.ll_set_userInfo);
        iv_user_headPhoto = (ImageView) view.findViewById(R.id.iv_user_headPhoto);
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        tv_user_info = (TextView) view.findViewById(R.id.tv_user_info);

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
                (int) (Constant.displayWidth * 0.4f + 0.5f),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        tv_history.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f),
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f));
        params2.setMargins((int)(0.54*Constant.displayWidth-Constant.displayHeight*0.8f/7.5f),0,0,0);
        iv_his.setLayoutParams(params2);


        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f),
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f));
        params3.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        iv_setting.setLayoutParams(params3);

        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.4f + 0.5f),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params4.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        tv_setting.setLayoutParams(params4);

        LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f),
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f));
        params5.setMargins((int)(0.54*Constant.displayWidth-Constant.displayHeight*0.8f/7.5f),0,0,0);
        iv_set.setLayoutParams(params5);


        LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f),
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f));
        params6.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        iv_personinfo.setLayoutParams(params6);

        LinearLayout.LayoutParams params7 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.4f + 0.5f),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params7.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        tv_personinfo.setLayoutParams(params7);

        LinearLayout.LayoutParams params8 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f),
                (int) (Constant.displayHeight * 0.4f/7.5f + 0.5f));
        params8.setMargins((int)(0.54*Constant.displayWidth-Constant.displayHeight*0.8f/7.5f),0,0,0);
        iv_per.setLayoutParams(params8);

        LinearLayout.LayoutParams params9 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (Constant.displayHeight * 1.f/7.5f + 0.5f));
        params9.setMargins(0,(int) (Constant.displayHeight * 0.02 + 0.5f),0,0);
        LL_head.setLayoutParams(params9);


        publish = view.findViewById(R.id.ll_publish);
        publish.setOnClickListener(this);
        ll_history.setOnClickListener(this);
        ll_seting.setOnClickListener(this);
        ll_set_userInfo.setOnClickListener(this);
        iv_user_headPhoto.setOnClickListener(this);

        Log.d("user_num", user_num);
        Cursor cursor1 = database_news.query_publish_news(user_num);
        int publish_num = cursor1.getCount();
        pub_num.setText(""+publish_num);

    }

    @Override
    public void onStart() {
        super.onStart();
        Cursor cursor = database_news.query_personinfo(user_num);
        cursor.moveToFirst();
        String PersonName_text = cursor.getString(cursor.getColumnIndex("PersonName_text"));
        String PersonSig_text = cursor.getString(cursor.getColumnIndex("PersonSig_text"));
        String PersonImage_blob = cursor.getString(cursor.getColumnIndex("PersonImage_blob"));
        headimg = PersonImage_blob;
        //昵称与签名
        if (PersonName_text.equals("")) {
            tv_user_name.setText("昵 称");
        } else {
            tv_user_name.setText(PersonName_text);
        }
        if (PersonSig_text.equals("")) {
            tv_user_info.setText("个 人 签 名");
        } else {
            tv_user_info.setText(PersonSig_text);
        }


        //头像
        if (PersonImage_blob == null) {
            Log.i("imagePath", "onViewCreated: " + PersonImage_blob);
        } else {
            try {
                FileInputStream fis = new FileInputStream(PersonImage_blob);
                Bitmap bitmap = BitmapFactory.decodeStream(fis);
                iv_user_headPhoto.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_history: {
                Intent intent = new Intent(getActivity(), History.class);
                intent.putExtra("user_num", user_num);
                startActivity(intent);
                break;
            }
            case R.id.ll_seting: {
                Intent intent = new Intent(getActivity(), Seting.class);
                intent.putExtra("user_num", user_num);
                startActivity(intent);
                break;
            }
            case R.id.ll_set_userInfo: {
                Intent intent = new Intent(getActivity(), Set_userInfo.class);
                intent.putExtra("user_num", user_num);
                startActivity(intent);
                break;
            }
            case R.id.iv_user_headPhoto: {
                Intent intent = new Intent(getActivity(), ChangeHeadphoto.class);
                Log.i("headimg  Fragment_user", headimg);
                intent.putExtra("headimg", headimg);
                intent.putExtra("user_num", user_num);
                startActivityForResult(intent, 1);
                break;

            }
            case R.id.ll_publish:
                Intent intent = new Intent(getActivity(), publishActivity.class);
                intent.putExtra("user_num", user_num);
                startActivity(intent);

                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK)
                {
                    imagePath = data.getStringExtra("imagePath");
                    if (imagePath.equals(""))
                    {
                        Toast.makeText(context, "头像路径无效，未更新！", Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                        FileInputStream fis = null;
                        try
                        {
                            fis = new FileInputStream(imagePath);
                        }
                        catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        }
                        Bitmap bitmap = BitmapFactory.decodeStream(fis);
                        iv_user_headPhoto.setImageBitmap(bitmap);
                    }
                }
                break;
        }
    }

}
