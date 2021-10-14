package com.example.shuipian;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NewContentActivity extends AppCompatActivity {
    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsTitle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String newsTitle = getIntent().getStringExtra("news_title");//获取传入新闻标题
        String newsContent = getIntent().getStringExtra("news_content");//获取传入的新闻内容
        NewsCpmtemtFragment newsCpmtemtFragment = (NewsCpmtemtFragment) getSupportFragmentManager().findFragmentById(R.id.news_content);
        newsCpmtemtFragment.refresh(newsTitle, newsContent);//刷新NewsContent界面
    }
}