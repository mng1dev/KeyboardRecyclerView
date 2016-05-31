package com.vektor.keyboardrecyclerview.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vektor.keyboardrecyclerview.R;

/**
 * Created by vektor on 31/05/16.
 */
public class ListItemAdapter extends InputTrackingRecyclerViewAdapter<ListItemAdapter.ViewHolder> {

    int[] data;

    private final static int SELECTOR_COLOR = 0xFFBDBDBD;

    public ListItemAdapter(Context context) {
        super(context);
        data = new int[100];
        for(int i=0;i<data.length;i++){
            data[i]=i+1;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        boolean isSelected = position == getSelectedItem();

        ColorDrawable selectedDrawable = new ColorDrawable(isSelected ? SELECTOR_COLOR : 0X00000000);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            holder.itemView.setBackground(selectedDrawable);
        else
            holder.itemView.setBackgroundDrawable(selectedDrawable);
        holder.itemView.setSelected(isSelected);

        holder.textView.setText(""+data[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(getSelectedItem());
                setSelectedItem(getRecyclerView().getChildLayoutPosition(v));
                notifyItemChanged(getSelectedItem());
                Toast.makeText(getContext(),"Click "+position+"!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        LinearLayout innerLayout,outerLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.text_view);
            innerLayout = (LinearLayout)itemView.findViewById(R.id.inner_llayout);
            outerLayout=(LinearLayout)itemView.findViewById(R.id.root_llayout);
        }
    }
}
