package com.tetra.brycal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tetra.brycal.R;
import com.tetra.brycal.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    List<ItemModel> itemModelList=new ArrayList<>();

    public ItemAdapter(Context context, List<ItemModel> itemModelList) {
        this.context = context;
        this.itemModelList = itemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      ItemModel itemModel=itemModelList.get(position);
      holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {

          }
      });
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RadioGroup radioGroup;
        private RadioButton rb1,rb2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioGroup=itemView.findViewById(R.id.radioGroup1);
            rb1=itemView.findViewById(R.id.rb1);
            rb2=itemView.findViewById(R.id.rb2);

        }
    }
}
