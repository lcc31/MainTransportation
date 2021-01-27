package com.example.maintransportation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AboutActivity extends AppCompatActivity {

    public static final String URL_IMAGE = "https://scontent.fias1-1.fna.fbcdn.net/v/t1.0-9/74911765_3109812712367494_1990000150877044736_o.jpg?_nc_cat=106&ccb=2&_nc_sid=09cbfe&_nc_ohc=5F4p8I1QaDkAX8YJoQi&_nc_ht=scontent.fias1-1.fna&oh=94f6a7b70ab21d853b1fe37b00764de1&oe=601EB719";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageView imageView = findViewById(R.id.iv_about);
        Picasso.get().load(URL_IMAGE).placeholder(R.drawable.ic_launcher_background).into(imageView);

    }
}