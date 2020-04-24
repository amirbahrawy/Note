package com.example.android.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.note.Room.Item;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NoteHolder> {
    List<Item> items=new ArrayList<>();
    OnNoteListener onNoteListener;

    public RecyclerViewAdapter(OnNoteListener onNoteListener) {
        this.onNoteListener=onNoteListener;
    }

    public void setData(List<Item> list){
        items=list;
        notifyDataSetChanged();
     }
     public Item getItemAt(int position){
         return items.get(position);
     }
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        Item item=items.get(position);
       String b=item.getBody();
       if(b.length()>16)
       b=b.substring(0,15)+"...";
        holder.title.setText(item.getTitle());
        holder.body.setText(b);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title , body ;
        OnNoteListener mOnNoteListener;
        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            mOnNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOnNoteListener!=null)
            mOnNoteListener.onNoteClick(items.get(getAdapterPosition()));
        }
    }
    public interface OnNoteListener{
        void onNoteClick(Item item);
    }
}
