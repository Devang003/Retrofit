package com.example.retrodemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.retrodemo.ApiResponses.HeroResponse;
import com.example.retrodemo.R;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder>{

    private Context mCtx;

    //we are storing all the products in a list
    private List<HeroResponse.Hero> heroList;

    public HeroAdapter(Context mCtx, List<HeroResponse.Hero> heroList) {
        this.mCtx = mCtx;
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.hero_item_layout, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {

        final HeroResponse.Hero hero = heroList.get(position);

        //binding the data with the viewholder views
        holder.txtHeroName.setText(hero.getName());
//        holder.imgReminderEdit.setImageDrawable(mCtx.getResources().getDrawable(product.getEdit()));

        String url = hero.getImageurl();

        Glide.with(mCtx).load(url)
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(holder.imgHero);

        holder.imgHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnHeroItemClick.OnImgClick(hero);
            }
        });


    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }


    public interface OnHeroItemClick {

        public void OnImgClick(HeroResponse.Hero hero);

    }


    OnHeroItemClick mOnHeroItemClick;

    public void OnHeroItemCLickListener(OnHeroItemClick onHeroItemClick) {
        mOnHeroItemClick = onHeroItemClick;
    }


    class HeroViewHolder extends RecyclerView.ViewHolder {

        TextView txtHeroName;
        ImageView imgHero;

        public HeroViewHolder(View itemView) {
            super(itemView);


            txtHeroName = itemView.findViewById(R.id.txtHeroName);
            imgHero = itemView.findViewById(R.id.imgHero);

        }


    }
}
