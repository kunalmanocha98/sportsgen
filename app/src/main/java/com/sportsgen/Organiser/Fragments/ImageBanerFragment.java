package com.sportsgen.Organiser.Fragments;

import android.content.Context;
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

import com.sportsgen.CommonClasses.HelperClasses.Constants;
import com.sportsgen.CommonClasses.HelperClasses.Utils;
import com.sportsgen.Organiser.Models.CreateEventData;
import com.sportsgen.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageBanerFragment extends Fragment implements View.OnClickListener {

    ImageView img_view_banner;
    Button btn_img_picker,btn_submit;
    private int RESULT_LOAD_IMG = 301;
    private static String OBJECT_KEY="52";
    CreateEventData modelalldata;
    CreateEventData.OnDataEntryListener onDataEntryListener;
    private Bitmap selectedImage;


    public static Fragment newInstance(CreateEventData modelalldata){
        Fragment f=new ImageBanerFragment();
        Bundle b= new Bundle();
        b.putParcelable(OBJECT_KEY,modelalldata);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onDataEntryListener=(CreateEventData.OnDataEntryListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_create_event_banner,container,false);
        modelalldata=getArguments().getParcelable(OBJECT_KEY);
        img_view_banner=v.findViewById(R.id.img_banner);
        btn_img_picker=v.findViewById(R.id.btn_img_picker);
        btn_submit=v.findViewById(R.id.btn_submit_img);
        btn_img_picker.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        checkmodeldata();
        return v;
    }

    private void checkmodeldata() {
        if (modelalldata != null){
            if (modelalldata.getImg_banner()!= null){
                img_view_banner.setImageBitmap(modelalldata.getImg_banner());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_img_picker:{
                pickimage();
                break;
            }
            case R.id.btn_submit_img:{
                checkdata();
                break;
            }
        }
    }

    private void checkdata() {
        if (selectedImage !=null){
            proceesdsubmit();
        }else {
            Utils.toast(getActivity(),"Please select a image");
        }
    }

    private void proceesdsubmit(){
        onDataEntryListener.set_img_banner(selectedImage);
        Constants.StringConstants.is_Image_banner_Submitted=true;
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
                selectedImage = BitmapFactory.decodeStream(imageStream);
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
