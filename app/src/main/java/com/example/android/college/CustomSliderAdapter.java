package com.example.android.college;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CustomSliderAdapter extends PagerAdapter {
    private int[] images = {R.drawable.oneataktu,R.drawable.companylogos,R.drawable.careteam,R.drawable.celebration,R.drawable.itdepartment};
    private Context context;
    private LayoutInflater layoutInflater;
    private int custom_position=0;
    public CustomSliderAdapter(Context context)
    {
        this.context=context;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View v, @NonNull Object o) {
        return (v==(LinearLayout)o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (custom_position>4)
            custom_position=0;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = view.findViewById(R.id.imageview);
        //TextView textView = view.findViewById(R.id.imagecount);
        imageView.setImageResource(images[custom_position]);
        custom_position++;
        //textView.setText("image "+position);
        container.addView(view);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
