package com.prototype.wishti.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.prototype.wishti.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import de.hdodenhof.circleimageview.CircleImageView;

@EFragment(R.layout.crop_image_fragment)
public class CropImageFragment extends Fragment {

    @ViewById(R.id.profile_image)
    public CircleImageView circleImageView;

    @AfterViews
    void onCreateFragment(){

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.icon_addphoto_multicolor);

        circleImageView.setImageBitmap(largeIcon);

    }
}
