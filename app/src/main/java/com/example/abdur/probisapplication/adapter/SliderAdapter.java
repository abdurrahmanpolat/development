package com.example.abdur.probisapplication.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.abdur.probisapplication.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    // Arrays
    public int[] slide_images = {
            R.drawable.eat_icon,
            R.drawable.code_icon,
            R.drawable.sleep_icon
    };

    public String[] slide_headings = {
            "YEMEK",
            "KODLAMA",
            "UYKU"
    };
    public String[] slide_desc = {
            "Yemek koyulurken, “bu kadar yeter” dedikten sonra mutlaka bir kaşık daha yemek koyan kişiye ‘anne’ denir.Ve o her şeye değerdir.",
            "Programlama, bisiklete binmek gibidir; asla unutulmaz, yanlızca pratik gerektirir. Ve el elin kodunu türkü çağıra çağıra debug eder..",
            "Rüyasını yazmak isteyen adam, son derece uyanık oImaIı."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_item_layout, container, false);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/LondiniaMedium.ttf");

        ImageView slideImageView = (ImageView)view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView)view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView)view.findViewById(R.id.slide_desc);

        slideHeading.setTypeface(font);
        slideDescription.setTypeface(font);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
