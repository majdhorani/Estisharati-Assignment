package com.majd.techestisharatiassignment.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.majd.techestisharatiassignment.R;
import com.majd.techestisharatiassignment.models.CategoryModel;
import com.majd.techestisharatiassignment.ui.activities.category.CategoryActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Holder> {
        List<CategoryModel> categories;
        Context context;

        public CategoriesAdapter(Context context, List<CategoryModel> categories){
            this.categories = categories;
            this.context = context;
        }


        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_recycler_item,viewGroup,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position){
            CategoryModel currentCategory = categories.get(position);

            // Bind current view with its data
            holder.bindData(currentCategory);

            // OnClickActionListener
            holder.itemView.setOnClickListener(view -> {
                context.startActivity(new Intent(context, CategoryActivity.class));
            });
        }

        @Override
        public int getItemCount(){
            return categories.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            ImageView image;
            TextView title;

            public Holder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
                title = itemView.findViewById(R.id.name);
            }

            public void bindData(CategoryModel category) {
                if(category.getImage() != null) {
                    Picasso.get().load(category.getImage()).fit().centerCrop().into(image);
                }
                if(category.getName() != null) {
                    title.setText(category.getName());
                }
            }
        }
}
