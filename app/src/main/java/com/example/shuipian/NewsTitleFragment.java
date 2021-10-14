package com.example.shuipian;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        RecyclerView newsTitleRecyclerView = view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i <=50; i++) {
            News news = new News();
            news.setTitle("今日，一名周姓男子持刀闯入102宿舍" + i);
            news.setCon(getRand("当时宿舍里的人紧张的一批" + i + "."));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRand(String content) {
        Random random = new Random();
        int legth = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < legth; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;//可以找到news_content_layout布局时，为双页
        } else {
            isTwoPane = false;
        }
    }
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView newsTit;
            public ViewHolder(View view) {
                super(view);
                newsTit = view.findViewById(R.id.news_title);
            }
        }

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }
        @Override
        public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        NewsCpmtemtFragment newsCpmtemtFragment = (NewsCpmtemtFragment) getFragmentManager().findFragmentById(R.id.news_content_frag);
                        newsCpmtemtFragment.refresh(news.getTitle(), news.getCon());
                    } else {
                        NewContentActivity.actionStart(getActivity(), news.getTitle(), news.getCon());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTit.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }
}
