package com.example.testfirebase;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    View view;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;

        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                mClicklistener.onItemclick(view,getAdapterPosition());
            }

        });

        itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                mClicklistener.onItemLongclick(view,getAdapterPosition());
                return false;
            }
        });
    }

    public void setdetails(Context context, String NameProd1, String PriceProd1, String ImageProd1){
        TextView mNameProd1=view.findViewById(R.id.name);
        TextView mPriceProd1= view.findViewById(R.id.price);
        ImageView mImageProd1= view.findViewById(R.id.rImageView);

        mNameProd1.setText(NameProd1);
        mPriceProd1.setText(PriceProd1);
        Picasso.get().load(ImageProd1).into(mImageProd1);

        Animation animation = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        itemView.startAnimation(animation);
    }

    private ViewHolder.ClickListener mClicklistener;
    public interface  ClickListener{
        void onItemclick(View view, int position);
        void onItemLongclick(View view, int position);
    }
    public void setOnclickListener(ViewHolder.ClickListener clickListener){
        mClicklistener= clickListener;
    }


}

