package com.example.e_commerce_galaxy_mart.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.e_commerce_galaxy_mart.R;

import org.w3c.dom.Text;

public class SliderAdaptor extends PagerAdapter {


    public SliderAdaptor(Context context) {
        this.context = context;
    }

    Context context;
    LayoutInflater layoutInflater;

    int imagesArray []={

            R.drawable.onboardscreen1,
            R.drawable.onboardscreen2,
            R.drawable.onboardscreen3
    };

    int headingArray []={

            R.string.first_slide,
            R.string.second_slide,
            R.string.third_slide
    };

    int discriptionArray[]={

            R.string.description,
            R.string.description,
            R.string.description
    };




    @Override
    public int getCount() {
        return headingArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

       layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       View view= layoutInflater.inflate(R.layout.slides_activity,container,false);

        ImageView imageView= view.findViewById(R.id.slider_img);
        TextView heading= view.findViewById(R.id.heading);
        TextView description= view.findViewById(R.id.description);

        imageView.setImageResource(imagesArray[position]);
        heading.setText(headingArray[position]);
        description.setText(discriptionArray[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

       container.removeView((ConstraintLayout)object);

    }
}
