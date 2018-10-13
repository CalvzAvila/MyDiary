package com.diary.project.mydiary.adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.diary.project.mydiary.R;
import com.diary.project.mydiary.model.DiaryListModel;

import java.util.ArrayList;
import java.util.List;

public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.ViewHolder>{

    private ArrayList<DiaryListModel> diaryList;
    private Context context;

    public DiaryListAdapter(ArrayList<DiaryListModel> listItems) {
        this.diaryList = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.diary_list, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //DiaryListModel dl = diaryList.get(i);

//        viewHolder.tvTitle.setText(dl.getSuccess());
//        viewHolder.tvNotes.setText(dl.getSuccess());

        viewHolder.tvTitle.setText(diaryList.get(i).getTitle());
        viewHolder.tvNotes.setText(diaryList.get(i).getNotes());
    }

    @Override
    public int getItemCount() {
        return diaryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
   {
            TextView tvTitle, tvNotes;

       ViewHolder(@NonNull View itemView) {
           super(itemView);

           tvTitle = itemView.findViewById(R.id.title);
           tvNotes = itemView.findViewById(R.id.notes);
       }
   }



}
