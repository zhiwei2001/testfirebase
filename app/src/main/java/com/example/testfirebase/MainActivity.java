package com.example.testfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=findViewById(R.id.recyelerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Data");
    }

    @Override
    protected  void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Member,ViewHolder>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Member, ViewHolder>(
                        Member.class   ,
                        R.layout.image,
                        ViewHolder.class,
                        reference
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Member member, int i) {
                        viewHolder.setdetails(getApplicationContext(), member.getNameProd1(), member.getPriceProd1(), member.getImageProd1());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
                        ViewHolder viewHolder = super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnclickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemclick(View view, int position) {
                                TextView mname=view.findViewById(R.id.name);
                                TextView mprice=view.findViewById(R.id.price);
                                ImageView mimageView= view.findViewById(R.id.rImageView);

                                String name=mname.getText().toString();
                                String price=mprice.getText().toString();
                                Drawable mDrawable = mimageView.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();
                                Intent intent=new  Intent(view.getContext(),ProductDetailActiivity.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                                byte[]bytes = stream.toByteArray();

                                intent.putExtra("ImageProd1",bytes);
                                intent.putExtra("NameProd1",name);
                                intent.putExtra("PriceProd1",price);
                                startActivity(intent);

                            }

                            @Override
                            public void onItemLongclick(View view, int position) {

                            }
                        });

                       return viewHolder;
                    }
                };



        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

}