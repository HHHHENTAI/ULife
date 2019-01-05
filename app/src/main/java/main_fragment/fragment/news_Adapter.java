package main_fragment.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hhhhentai.ulife.R;

import java.util.List;

public class news_Adapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<news_class> mDatas;
    private Integer mScreenHeight;
    private Integer mScreenWeight;
    private Context mcontext;
    private ListView listView;
    private Bitmap_handle bitmap_handle = new Bitmap_handle();



    public news_Adapter(Context context, List<news_class> list, ListView listView) {
        this.mDatas = list;
        this.mcontext = context;
        this.listView = listView;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //重点
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false); //加载布局

            holder = new ViewHolder();
            holder.news_title = (TextView) convertView.findViewById(R.id.list_title);
            holder.news_time = (TextView) convertView.findViewById(R.id.list_time);
            holder.news_browse_count = (TextView) convertView.findViewById(R.id.list_browse_num);
            holder.news_classify = (TextView) convertView.findViewById(R.id.list_classify);
            holder.news_img = convertView.findViewById(R.id.list_img);

            //获取屏幕高度
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) mcontext.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            mScreenHeight = dm.heightPixels;//屏幕高度
            mScreenWeight = dm.widthPixels;  //屏幕宽度
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, listView.getHeight() / 5);
            convertView.setLayoutParams(lp);


           /* LinearLayout ll = convertView.findViewById(R.id.list_layout);
            ViewGroup.LayoutParams linearParams = (ViewGroup.LayoutParams) ll.getLayoutParams();
            linearParams.height = screenWidth * 250 / 460;
//                linearParams.gravity = Gravity.CENTER_VERTICAL;
            ll.setLayoutParams(linearParams);*/
            convertView.setTag(holder);
        } else {
            //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }
        news_class bean = mDatas.get(position);
        holder.news_title.setText(bean.getNews_title());
        //holder.news_title.setTextSize((mScreenWeight/14));

        String t = bean.getNews_time();
        String year = t.substring(0, 4);
        String month = t.substring(4, 6);
        String day = t.substring(6, 8);
        holder.news_time.setText(year + "/" + month + "/" + day);
        //  holder.news_time.setTextSize((mScreenWeight/21));

        holder.news_classify.setText(bean.getNews_classify());
        // holder.news_classify.setTextSize((mScreenWeight/21));

        holder.news_browse_count.setText("" + bean.getNews_browse_count());

         String path= bean.getNews_img();
        Log.i("jjj", "getView: "+path);
             Bitmap img_bitmap  = null;
             img_bitmap = bitmap_handle.pictureTobitmap(path);

        //byte[] imgData = bean.getNews_img();
        //Bitmap imagebitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
        holder.news_img.setImageBitmap(img_bitmap);
        holder.news_img.setMaxHeight(listView.getHeight() / 5);
        holder.news_img.setMaxWidth(listView.getHeight() / 5);
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        TextView news_title;
        TextView news_browse_count;
        TextView news_time;
        TextView news_classify;
        ImageView news_img;
    }
}
