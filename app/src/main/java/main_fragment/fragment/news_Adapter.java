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
import android.widget.RelativeLayout;
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

        news_class bean = mDatas.get(position);

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.list_item, parent, false); //加载布局
            holder = new ViewHolder();
            holder.news_title = (TextView) convertView.findViewById(R.id.list_title);
            holder.news_time = (TextView) convertView.findViewById(R.id.list_time);
            holder.news_browse_count = (TextView) convertView.findViewById(R.id.list_browse_num);
            holder.news_classify = (TextView) convertView.findViewById(R.id.list_classify);
            holder.news_img = convertView.findViewById(R.id.list_img);

            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, listView.getHeight() / 5);
            convertView.setLayoutParams(lp);

            convertView.setTag(holder);
        } else {
            //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }

        holder.news_title.setText(bean.getNews_title());

        String t = bean.getNews_time();
        String year = t.substring(0, 4);
        String month = t.substring(4, 6);
        String day = t.substring(6, 8);
        holder.news_time.setText(year + "/" + month + "/" + day);

        holder.news_classify.setText(bean.getNews_classify());

        holder.news_browse_count.setText("" + bean.getNews_browse_count());

        String path = bean.getNews_img();
        if (path.equals("")) {
            holder.news_img.setMaxWidth(0);
            holder.news_img.setAdjustViewBounds(true);
        } else {
            Bitmap img_bitmap = null;
            img_bitmap = bitmap_handle.pictureTobitmap(path);
            holder.news_img.setImageBitmap(img_bitmap);
            holder.news_img.setMaxHeight(listView.getHeight() / 5);
            holder.news_img.setMaxWidth(listView.getHeight() / 5);
            holder.news_img.setScaleType(ImageView.ScaleType.FIT_XY);
            //holder.news_img.setMinimumWidth(listView.getHeight() / 5);
            // 设置图片的位置
            holder.news_img.setVisibility(View.VISIBLE);

            holder.news_img.setAdjustViewBounds(true);
        }

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
