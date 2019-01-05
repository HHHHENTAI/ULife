package main_fragment.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hhhhentai.Constant.Constant;
import com.example.hhhhentai.ulife.PictureActivity;
import com.example.hhhhentai.ulife.R;
import com.example.hhhhentai.ulife.path;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

//TODO 赵效慷and江守鑫---
public class Fragment_upload extends Fragment implements View.OnClickListener {

    String title;
    String content;
    String family;
    String phone;

    ImageView[] send_iv=new ImageView[5];
    String[] get = {"", "", "", "", ""};
    Context context;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;
    private DbHelp_NEWS dbHelp_news;
    private Database_News database_news;
    private Bitmap_handle bitmap_handle;

    private LinearLayout LL_send,LL_send_out,LL_cancel,LL_family,LL_title,LL_content,LL_group;
    private TextView tv_send,tv_family,myTextView,tv_content;
    private Spinner mySpinner;
    private EditText et_title,et_content;
    private ImageView iv_picture,iv_cancle,iv_send;

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
        //初始化
        init(view);

        for (int i = 0; i < 5; i++) {
            setsize(send_iv[i]);
        }

        //数据库操作初始化
        dbHelp_news = new DbHelp_NEWS(context);
        database_news = new Database_News(dbHelp_news);
        //图片操作
        bitmap_handle = new Bitmap_handle();

        dataList = new ArrayList<Map<String, Object>>();
        getData();

        mySpinner = (Spinner) view.findViewById(R.id.spinner);
        // 第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new SimpleAdapter(context, dataList, R.layout.item, new String[]{"image", "text"}, new int[]{R.id.iv_img, R.id.tv});

        // 第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(R.layout.item);
        // 第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        // 第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                /* String s=adapterView.getItemAtPosition(i).toString();*/
                TextView tv_test = view.findViewById(R.id.tv);
                family = tv_test.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //初始化
    private void init(View view) {
        et_title = view.findViewById(R.id.et_title);////////////////
        et_content = view.findViewById(R.id.et_content);////////////////////
        iv_picture = view.findViewById(R.id.iv_picture);////////////////////
        iv_picture.setOnClickListener(this);
        iv_cancle = view.findViewById(R.id.iv_cancle);/////////////////////
        iv_cancle.setOnClickListener(this);
        iv_send = view.findViewById(R.id.iv_send);
        iv_send.setOnClickListener(this);
        send_iv[0] = view.findViewById(R.id.send_iv_one);
        send_iv[1] = view.findViewById(R.id.send_iv_two);
        send_iv[2] = view.findViewById(R.id.send_iv_three);
        send_iv[3] = view.findViewById(R.id.send_iv_fore);
        send_iv[4] = view.findViewById(R.id.send_iv_five);

        LL_send = view.findViewById(R.id.send);/////////////////////////
        LL_send_out = view.findViewById(R.id.LL_send_out);////////////////////////
        LL_cancel = view.findViewById(R.id.LL_cancel);//////////////////////////
        LL_family = view.findViewById(R.id.family);////////////////////////////
        LL_title = view.findViewById(R.id.title);////////////////////////
        LL_content = view.findViewById(R.id.content);/////////////////////////
        LL_group = view.findViewById(R.id.LL_group);////////////////////////

        tv_send = view.findViewById(R.id.tv_send);////////////////////////////
        myTextView = view.findViewById(R.id.tv_title);/////////////////////////////
        tv_family = view.findViewById(R.id.tv_family);//////////////////////
        tv_content = view.findViewById(R.id.tv_content);////////////////

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (Constant.displayHeight * 0.1f + 0.5f));
        params1.setMargins(0,0,0,0);
        LL_send.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.1f + 0.5f),
                (int) (Constant.displayHeight * 0.1f + 0.5f));
        params2.setMargins(0,0,0,0);
        LL_cancel.setLayoutParams(params2);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.06f + 0.5f),
                (int) (Constant.displayHeight * 0.06f + 0.5f));
        params3.setMargins(0,0,0,0);
        iv_cancle.setLayoutParams(params3);

        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.1f + 0.5f),
                (int) (Constant.displayHeight * 0.1f + 0.5f));
        params4.setMargins(0,0,0,0);
        LL_send_out.setLayoutParams(params4);

        LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.06f + 0.5f),
                (int) (Constant.displayHeight * 0.06f + 0.5f));
        params5.setMargins(0,0,0,0);
        iv_send.setLayoutParams(params5);

        LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth-(Constant.displayHeight * 0.1f + 0.5f)*2),
                (int) (Constant.displayHeight * 0.1f + 0.5f));
        params6.setMargins(0,0,0,0);
        tv_send.setLayoutParams(params6);

        tv_send.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.035f);


        LinearLayout.LayoutParams params7 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params7.setMargins(0,0,0,0);
        LL_family.setLayoutParams(params7);

        LinearLayout.LayoutParams params8 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.2f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params8.setMargins(0,0,0,0);
        tv_family.setLayoutParams(params8);

        tv_family.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);

        LinearLayout.LayoutParams params9 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params9.setMargins(0,0,0,0);
        LL_title.setLayoutParams(params9);

        LinearLayout.LayoutParams params10 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.2f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params10.setMargins(0,0,0,0);
        myTextView.setLayoutParams(params10);

        myTextView.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);

        LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.7f + 0.5f),
                (int) (Constant.displayHeight * 0.03f + 0.5f));
        params11.setMargins(0,0,0,0);
        et_title.setLayoutParams(params11);

        LinearLayout.LayoutParams params12 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (Constant.displayHeight * 0.3f + 0.5f));
        params12.setMargins(0,0,0,0);
        LL_content.setLayoutParams(params12);

        LinearLayout.LayoutParams params13 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.2f + 0.5f),
                (int) (Constant.displayHeight * 0.1f + 0.5f));
        params13.setMargins(0,0,0,0);
        tv_content.setLayoutParams(params13);

        tv_content.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);

        LinearLayout.LayoutParams params14 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.7f + 0.5f),
                (int) (Constant.displayHeight * 0.3f + 0.5f));
        params14.setMargins(0, 0,0,0);
        et_content.setLayoutParams(params14);

        LinearLayout.LayoutParams params15 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params15.setMargins(0,0,0,0);
        LL_group.setLayoutParams(params15);

        LinearLayout.LayoutParams params16 = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.1f + 0.5f),
                (int) (Constant.displayHeight * 0.1f + 0.5f));
        params16.setMargins(0,0,0,0);
        iv_picture.setLayoutParams(params16);
    }

    private void getData() {
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("image", R.drawable.yjl_study);
        map1.put("text", "学习");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("image", R.drawable.yjl_game);
        map2.put("text", "游戏");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("image", R.drawable.yjl_life);
        map3.put("text", "生活");
        dataList.add(map1);
        dataList.add(map2);
        dataList.add(map3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload, null);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //TODO 点击图片
            case R.id.iv_picture:
                for (int i = 0; i < 5; i++) {
                    send_iv[i].setImageDrawable(null);
                }
                Intent intent1 = new Intent(context, PictureActivity.class);
                startActivityForResult(intent1, 100);
                break;
            //点击发送
            case R.id.iv_send:
                title = et_title.getText().toString();
                content = et_content.getText().toString();
                phone=getArguments().getString("user_num");
                if (TextUtils.isEmpty(et_title.getText())) {
                    Toast.makeText(context, "标题不能为空!", Toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(et_content.getText())) {
                    Toast.makeText(context, "内容不能为空!", Toast.LENGTH_LONG).show();
                }
                if (!TextUtils.isEmpty(et_title.getText()) && !TextUtils.isEmpty(et_content.getText())) {
                    //时间戳
                    Calendar calendar = Calendar.getInstance();
                    String ymd = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH) + "";
                    String hms = calendar.get(Calendar.HOUR_OF_DAY) * 10000 + calendar.get(Calendar.MINUTE) * 100 + calendar.get(Calendar.SECOND) + "";
                    String NewsTime_text = "";
                    if (calendar.get(Calendar.HOUR_OF_DAY) < 10) {
                        NewsTime_text = ymd + "0" + hms;
                    } else {
                        NewsTime_text = ymd + hms;
                    }

                    for (int i = 0; i < 5; i++) {
                        if (!get[i].equals("")) {
                            database_news.insert_newsinfo(i, phone, title, content, family, get[i], 0, NewsTime_text);
                        }
                    }
                    if (get[0].equals("") && get[1].equals("") && get[2].equals("") && get[3].equals("") && get[4].equals("")) {
                        database_news.insert_newsinfo(0, phone, title, content, family, "", 0, NewsTime_text);
                    }
                    Toast.makeText(context,"发送成功",Toast.LENGTH_SHORT).show();
                    et_title.setText(null);
                    et_content.setText(null);
                    for (int i = 0; i < 5; i++) {
                        get[i] = "";
                        send_iv[i].setImageDrawable(null);
                    }
                }

                break;

            case R.id.iv_cancle:
                et_title.setText(null);
                et_content.setText(null);
                for (int i = 0; i < 5; i++) {
                    get[i] = "";
                    send_iv[i].setImageDrawable(null);
                }
                break;
        }
    }

    //设置图片的宽高
    public void setsize(ImageView iv_size) {
        ViewGroup.LayoutParams params;
        params = iv_size.getLayoutParams();
        iv_size.setAdjustViewBounds(true);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        Integer h = dm.heightPixels;//屏幕高度
        Integer w = dm.widthPixels;  //屏幕宽度
        params.height = h / 6;
        params.width = w / 5;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10) {
            path p = (path) data.getSerializableExtra("iv");
            get[0] = p.getone();
            get[1] = p.gettwo();
            get[2] = p.getthree();
            get[3] = p.getfore();
            get[4] = p.getfive();
            for (int i = 0; i < 5; i++) {
                if (!get[i].equals("")) {
                    Bitmap bm = BitmapFactory.decodeFile(get[i]);
                    send_iv[i].setImageBitmap(bm);
                }
            }
        }
    }

    /* Bundle 传参数 */
    public static Fragment_upload newInstance(String user_num) {
        Bundle bundle = new Bundle();
        bundle.putString("user_num", user_num);
        Fragment_upload item = new Fragment_upload();
        item.setArguments(bundle);
        return item;
    }
}
