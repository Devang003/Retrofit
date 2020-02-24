package com.example.retrodemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.retrodemo.Adapter.HeroAdapter;
import com.example.retrodemo.ApiResponses.HeroResponse;
import com.example.retrodemo.utils.ApiInterface;
import com.example.retrodemo.utils.RetrofitApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecylerviewHero;
    private HeroAdapter mHeroAdapter;
    private ConstraintLayout mMainContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecylerviewHero = findViewById(R.id.RVHeros);

        mMainContainer = findViewById(R.id.MainContainer);
        getHeroList();

    }

    private void getHeroList() {

        RetrofitApi.getRetrofitInstance().create(ApiInterface.class)
                .getHeroes()
                .enqueue(new Callback<HeroResponse>() {
                    @Override
                    public void onResponse(Call<HeroResponse> call, Response<HeroResponse> response) {
                        mHeroAdapter = new HeroAdapter(MainActivity.this, response.body().getHeroes());
                        mRecylerviewHero.setAdapter(mHeroAdapter);
                        mHeroAdapter.OnHeroItemCLickListener(new ImageClick());
                        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                        mRecylerviewHero.setLayoutManager(recyclerLayoutManager);
                        mHeroAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<HeroResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
    }


    class ImageClick implements HeroAdapter.OnHeroItemClick{

        @Override
        public void OnImgClick(HeroResponse.Hero hero) {

            Toast.makeText(MainActivity.this, "Image"+hero.getName(), Toast.LENGTH_SHORT).show();



            Intent intent = new Intent(MainActivity.this,HeroDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", hero.getName());
            bundle.putString("url", hero.getImageurl());
            intent.putExtras(bundle);
            startActivity(intent);




        }
    }


}
