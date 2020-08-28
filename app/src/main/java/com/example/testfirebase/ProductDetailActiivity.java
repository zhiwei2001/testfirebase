package com.example.testfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailActiivity extends AppCompatActivity {
    EditText edittext;
    Button button;
    TextView name;
    TextView price;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_actiivity);

        name=findViewById(R.id.name2);
        price=findViewById(R.id.price2);
        image=findViewById(R.id.rImageView2);

        byte[] bytes = getIntent().getByteArrayExtra("ImageProd1");
        String mname = getIntent().getStringExtra("NameProd1");
        String mprice = getIntent().getStringExtra("PriceProd1");

        Bitmap bmp = BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        name.setText(mname);
        price.setText(mprice);
        image.setImageBitmap(bmp);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.send_message, Toast.LENGTH_SHORT);
        toast.show();
        edittext=findViewById(R.id.txtComment);
        edittext.setText("");
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}