package com.example.hhhhentai.ulife;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;

public class newsActivity extends AppCompatActivity {

    private DbHelp_NEWS help_news;
    private Database_News database_news;
    private TextView title;
    private TextView user;
    private ImageView user_pic;
    private TextView time;
    private TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        Intent intent = getIntent();
        String title = intent.getStringExtra("news_title");
        String time  = intent.getStringExtra("news_time");

        //todo 检索数据库，找到新闻 记录
         Cursor cursor = database_news.query_newsinfo(null,"NewsTitle_text = ? and NewsTime_text = ?",new String[]{title,time},null,null,null);
        if(cursor.moveToFirst()){
            //cursor 默认下标是-1
            content.setText(cursor.getString(cursor.getColumnIndex("NewsContent_text")));
        }




        String s = "前面说了一直反复说“当ScrollView发生滚动时”，那么怎么将AppBarLayout与ScrollView关联起来呢？我们注意到，AppBarLayout与ScrollView之间动作“相互依赖”，这不就是我们上一篇《CoordinateLayout的使用如此简单 》所学的内容吗？把ScrollView和AppBarLayout作为CoordinateLayout的子View，然后编写一个Behavior，在这个Behavior里面判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。\n" +
                "--------------------- \n" +
                "作者：huachao1001 \n" +
                "来源：CSDN \n" +
                "原文：https://blog.csdn.net/huachao1001/article/details/51558835 \n" +
                "版权声明：本文为博主原创文章，转载请附上博文链接！前面说了一直反复说“当ScrollView发生滚动时”，那么怎么将AppBarLayout与ScrollView关联起来呢？我们注意到，AppBarLayout与ScrollView之间动作“相互依赖”，这不就是我们上一篇《CoordinateLayout的使用如此简单 》所学的内容吗？把ScrollView和AppBarLayout作为CoordinateLayout的子View，然后编写一个Behavior，在这个Behavior里面判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。\n" +
                "--------------------- \n" +
                "作者：huachao1001 \n" +
                "来源：CSDN \n" +
                "原文：https://blog.csdn.net/huachao1001/article/details/51558835 \n" +
                "版权声明：本文为博主原创文章，转载请附上博文链接！前面说了一直反复说“当ScrollView发生滚动时”，那么怎么将AppBarLayout与ScrollView关联起来呢？我们注意到，AppBarLayout与ScrollView之间动作“相互依赖”，这不就是我们上一篇《CoordinateLayout的使用如此简单 》所学的内容吗？把ScrollView和AppBarLayout作为CoordinateLayout的子View，然后编写一个Behavior，在这个Behavior里面判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。\n" +
                "--------------------- \n" +
                "作者：huachao1001 \n" +
                "来源：CSDN \n" +
                "原文：https://blog.csdn.net/huachao1001/article/details/51558835 \n" +
                "版权声明：本文为博主原创文章，转载请附上博文链接！前面说了一直反复说“当ScrollView发生滚动时”，那么怎么将AppBarLayout与ScrollView关联起来呢？我们注意到，AppBarLayout与ScrollView之间动作“相互依赖”，这不就是我们上一篇《CoordinateLayout的使用如此简单 》所学的内容吗？把ScrollView和AppBarLayout作为CoordinateLayout的子View，然后编写一个Behavior，在这个Behavior里面判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。\n" +
                "--------------------- \n" +
                "作者：huachao1001 \n" +
                "来源：CSDN \n" +
                "原文：https://blog.csdn.net/huachao1001/article/details/51558835 \n" +
                "版权声明：本文为博主原创文章，转载请附上博文链接！前面说了一直反复说“当ScrollView发生滚动时”，那么怎么将AppBarLayout与ScrollView关联起来呢？我们注意到，AppBarLayout与ScrollView之间动作“相互依赖”，这不就是我们上一篇《CoordinateLayout的使用如此简单 》所学的内容吗？把ScrollView和AppBarLayout作为CoordinateLayout的子View，然后编写一个Behavior，在这个Behavior里面判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。\n" +
                "--------------------- \n" +
                "作者：huachao1001 \n" +
                "来源：CSDN \n" +
                "原文：https://blog.csdn.net/huachao1001/article/details/51558835 \n" +
                "版权声明：本文为博主原创文章，转载请附上博文链接！前面说了一直反复说“当ScrollView发生滚动时”，那么怎么将AppBarLayout与ScrollView关联起来呢？我们注意到，AppBarLayout与ScrollView之间动作“相互依赖”，这不就是我们上一篇《CoordinateLayout的使用如此简单 》所学的内容吗？把ScrollView和AppBarLayout作为CoordinateLayout的子View，然后编写一个Behavior，在这个Behavior里面判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。\n" +
                "--------------------- \n" +
                "作者：huachao1001 \n" +
                "来源：CSDN \n" +
                "原文：https://blog.csdn.net/huachao1001/article/details/51558835 \n" +
                "版权声明：本文为博主原创文章，转载请附上博文链接！前面说了一直反复说“当ScrollView发生滚动时”，那么怎么将AppBarLayout与ScrollView关联起来呢？我们注意到，AppBarLayout与ScrollView之间动作“相互依赖”，这不就是我们上一篇《CoordinateLayout的使用如此简单 》所学的内容吗？把ScrollView和AppBarLayout作为CoordinateLayout的子View，然后编写一个Behavior，在这个Behavior里面判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。\n" +
                "--------------------- \n" +
                "作者：huachao1001 \n" +
                "来源：CSDN \n" +
                "原文：https://blog.csdn.net/huachao1001/article/details/51558835 \n" +
                "版权声明：本文为博主原创文章，转载请附上博文链接！前面说了一直反复说“当ScrollView发生滚动时”，那么怎么将AppBarLayout与ScrollView关联起来呢？我们注意到，AppBarLayout与ScrollView之间动作“相互依赖”，这不就是我们上一篇《CoordinateLayout的使用如此简单 》所学的内容吗？把ScrollView和AppBarLayout作为CoordinateLayout的子View，然后编写一个Behavior，在这个Behavior里面判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。\n" +
                "--------------------- \n" +
                "作者：huachao1001 \n" +
                "来源：CSDN \n" +
                "原文：https://blog.csdn.net/huachao1001/article/details/51558835 \n" +
                "版权声明：本文为博主原创文章，转载请附上博文链接！前面说了一直反复说“当ScrollView发生滚动时”，那么怎么将AppBarLayout与ScrollView关联起来呢？我们注意到，AppBarLayout与ScrollView之间动作“相互依赖”，这不就是我们上一篇《CoordinateLayout的使用如此简单 》所学的内容吗？把ScrollView和AppBarLayout作为CoordinateLayout的子View，然后编写一个Behavior，在这个Behavior里面判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。\n" +
                "--------------------- \n" +
                "作者：huachao1001 \n" +
                "来源：CSDN \n" +
                "原文：https://blog.csdn.net/huachao1001/article/details/51558835 \n" +
                "版权声明：本文为博主原创文章，转载请附上博文链接！前面说了一直反复说“当ScrollView发生滚动时”，那么怎么将AppBarLayout与ScrollView关联起来呢？我们注意到，AppBarLayout与ScrollView之间动作“相互依赖”，这不就是我们上一篇《CoordinateLayout的使用如此简单 》所学的内容吗？把ScrollView和AppBarLayout作为CoordinateLayout的子View，然后编写一个Behavior，在这个Behavior里面判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。\n" +
                "--------------------- \n" +
                "作者：huachao1001 \n" +
                "来源：CSDN \n" +
                "原文：https://blog.csdn.net/huachao1001/article/details/51558835 \n" +
                "版权声明：本文为博主原创文章，转载请附上博文链接！";
       // content.setText(s);
    }

    private void initView() {
        user = findViewById(R.id.news_user);
        user_pic=findViewById(R.id.news_user_pic);
        time =findViewById(R.id.news_time);
        title=findViewById(R.id.news_title);
        content =findViewById(R.id.news_content);
        //创建信息的数据库
        help_news = new DbHelp_NEWS(this);
        //获取数据可读写对象
        database_news = new Database_News(help_news);

    }

}
