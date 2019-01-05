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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hhhhentai.ulife.ChangeHeadphoto;
import com.example.hhhhentai.ulife.History;
import com.example.hhhhentai.ulife.R;
import com.example.hhhhentai.ulife.Set_userInfo;
import com.example.hhhhentai.ulife.Seting;

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

        //数据库操作封装函数
        help_news = new DbHelp_NEWS(context);
        database_news = new Database_News(help_news);

        //获取用户手机号
        user_num = getArguments().getString("user_num");

        LinearLayout ll_history = (LinearLayout) view.findViewById(R.id.ll_history);
        LinearLayout ll_seting = (LinearLayout) view.findViewById(R.id.ll_seting);
        LinearLayout ll_set_userInfo = (LinearLayout) view.findViewById(R.id.ll_set_userInfo);
        iv_user_headPhoto = (ImageView) view.findViewById(R.id.iv_user_headPhoto);
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        tv_user_info = (TextView) view.findViewById(R.id.tv_user_info);

        ll_history.setOnClickListener(this);
        ll_seting.setOnClickListener(this);
        ll_set_userInfo.setOnClickListener(this);
        iv_user_headPhoto.setOnClickListener(this);

        Log.d("user_num", user_num);
        Cursor cursor = database_news.query_personinfo(user_num);
        cursor.moveToFirst();
        String PersonName_text = cursor.getString(cursor.getColumnIndex("PersonName_text"));
        String PersonSig_text = cursor.getString(cursor.getColumnIndex("PersonSig_text"));
        String PersonImage_blob = cursor.getString(cursor.getColumnIndex("PersonImage_blob"));
        //昵称与签名
        tv_user_name.setText(PersonName_text);
        tv_user_info.setText(PersonSig_text);
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
                intent.putExtra("user_num", user_num);
                startActivityForResult(intent, 1);
                break;
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    imagePath = data.getStringExtra("imagePath");
                    if (imagePath.equals("")) {
                        Toast.makeText(context, "头像路径无效，未更新！", Toast.LENGTH_SHORT).show();
                    } else {
                        FileInputStream fis = null;
                        try {
                            fis = new FileInputStream(imagePath);
                        } catch (FileNotFoundException e) {
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
