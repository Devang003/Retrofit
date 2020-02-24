package com.example.retrodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class HeroDetailActivity extends AppCompatActivity {

    private ImageView mImgHero;
    private TextView mTxtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);


        String Url = getIntent().getExtras().getString("url");
        String name = getIntent().getExtras().getString("name");
        mImgHero = findViewById(R.id.imgHero);
        mTxtName = findViewById(R.id.txtHeroName);


        mTxtName.setText(name);

        Glide.with(HeroDetailActivity.this).load(Url)
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(mImgHero);




    }
}
