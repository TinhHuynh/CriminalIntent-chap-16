package com.example.tinhhuynh.criminalintent.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.tinhhuynh.criminalintent.R;
import com.example.tinhhuynh.criminalintent.utils.PictureUtils;

import java.io.File;

/**
 * Created by TINH HUYNH on 5/3/2017.
 */

public class ViewImageDetailDialog extends DialogFragment {

    private static final String KEY_PHOTO = "photo";
    private static final String KEY_CRIME_TITLE = "crimeTitle";

    public static ViewImageDetailDialog newInstance(File photoFile, String crimeTitle) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_PHOTO, photoFile);
        args.putString(KEY_CRIME_TITLE, crimeTitle);
        ViewImageDetailDialog fragment = new ViewImageDetailDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        File photo = (File) getArguments().getSerializable(KEY_PHOTO);
        String crimeTitle = (String) getArguments().getString(KEY_CRIME_TITLE);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_view_image_detail, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.view_image_detail_image_view);
        if(photo != null && photo.exists()){
            Bitmap bitmap = PictureUtils.getScaledBitmap(photo.getPath(), getActivity());
            imageView.setImageBitmap(bitmap);
        }
        return new AlertDialog.Builder(getActivity())
                .setTitle(crimeTitle)
                .setView(view)
                .setPositiveButton("Return", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onDestroy();
                    }
                })
                .create();
    }
}
