package com.example.yu_enpit.mydiary;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.internal.Context;

/**
 * Created by enPiT-P11 on 2017/08/25.
 */

public class DiaryRealmAdapter extends RealmRecyclerViewAdapter<Diary, DiaryRealmAdapter.DiaryViewHolder> {

    Context context;

    public static class DiaryViewHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected TextView bodyText;
        protected TextView date;
        protected ImageView photo;

        public DiaryViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            bodyText = (TextView) itemView.findViewById(R.id.body);
            date = (TextView) itemView.findViewById(R.id.date);
            photo = (ImageView) itemView.findViewById(R.id.diary_photo);
        }
    }

    public DiaryRealmAdapter(@NonNull FragmentActivity context, @Nullable OrderedRealmCollection<Diary> date , boolean autoUpdate){
        super(date, autoUpdate);
        this.context = context;
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        final DiaryViewHolder holder = new DiaryViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(DiaryViewHolder holder, int position) {
        Diary diary = getData().get(position);
        holder.title.setText(diary.title);
        holder.bodyText.setText(diary.bodyText);
        holder.date.setText(diary.date);
        if(diary.image != null && diary.image.length != 0){
            Bitmap bmp = MyUtils.getImageFromByte(diary.image);
            holder.photo.setImageBitmap(bmp);
        }

    }
}
