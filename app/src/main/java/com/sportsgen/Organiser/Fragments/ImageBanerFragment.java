package com.sportsgen.Organiser.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.sportsgen.CommonClasses.HelperClasses.Utils;
import com.sportsgen.Organiser.Models.CreateEventData;
import com.sportsgen.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageBanerFragment extends Fragment implements View.OnClickListener {

    ImageView img_view_banner;
    Button btn_img_picker,btn_submit;
    private int RESULT_LOAD_IMG = 301;


    public static Fragment newInstance(CreateEventData modelalldata){
        Fragment f=new ImageBanerFragment();
        return f;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_create_event_banner,container,false);
        img_view_banner=v.findViewById(R.id.img_banner);
        btn_img_picker=v.findViewById(R.id.btn_img_picker);
        btn_submit=v.findViewById(R.id.btn_submit_img);
        btn_img_picker.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_img_picker:{
                pickimage();
                break;
            }
            case R.id.btn_submit_img:{
                break;
            }
        }
    }

    private void pickimage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                img_view_banner.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Utils.toast(getActivity(),"Something went wrong");
            }

        }else {
            Utils.toast(getActivity(),"You haven't picked Image");
        }
    }
}
