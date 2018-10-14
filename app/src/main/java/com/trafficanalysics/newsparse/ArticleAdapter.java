

package com.trafficanalysics.newsparse;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trafficanalysics.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DucTien on 14/10/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private ArrayList<News> news;

    private int rowLayout;
    private Context mContext;
    private WebView articleView;

    public ArticleAdapter(ArrayList<News> list, int rowLayout, Context context) {
        this.news = list;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    public void clearData() {
        if (news != null)
            news.clear();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

        News currentNews = news.get(position);

        Locale.setDefault(Locale.getDefault());
        Date date = currentNews.getPubDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        final String pubDateString = sdf.format(date);

        viewHolder.title.setText(currentNews.getTitle());

//        Picasso.get()
//                .load(currentNews.getImage())
//                .placeholder(R.drawable.placeholder)
//                .into(viewHolder.image);

        Glide.with(mContext)
                .load(currentNews.getImage())
                .into(viewHolder.image);

        viewHolder.pubDate.setText(pubDateString);

        String categories = "";
        if (currentNews.getCategories() != null) {
            for (int i = 0; i < currentNews.getCategories().size(); i++) {
                if (i == currentNews.getCategories().size() - 1) {
                    categories = categories + currentNews.getCategories().get(i);
                } else {
                    categories = categories + currentNews.getCategories().get(i) + ", ";
                }
            }
        }

        viewHolder.category.setText(categories);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //show article content inside a dialog
                articleView = new WebView(mContext);

                articleView.getSettings().setLoadWithOverviewMode(true);

                String title = news.get(viewHolder.getAdapterPosition()).getTitle();
                String content = news.get(viewHolder.getAdapterPosition()).getContent();

                articleView.getSettings().setJavaScriptEnabled(true);
                articleView.setHorizontalScrollBarEnabled(false);
                articleView.setWebChromeClient(new WebChromeClient());
                articleView.loadDataWithBaseURL(null, "<style>img{display: inline; height: auto; max-width: 100%;} " +

                        "</style>\n" + "<style>iframe{ height: auto; width: auto;}" + "</style>\n" + content, null, "utf-8", null);

                android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(mContext).create();
                alertDialog.setTitle(title);
                alertDialog.setView(articleView);
                alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

                ((TextView) alertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
    }

    @Override
    public int getItemCount() {

        return news == null ? 0 : news.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView pubDate;
        ImageView image;
        TextView category;

        public ViewHolder(View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.title);
            pubDate = itemView.findViewById(R.id.pubDate);
            image = itemView.findViewById(R.id.image);
            category = itemView.findViewById(R.id.categories);
        }
    }
}