package com.prototype.wishti.activities;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.prototype.wishti.R;
import com.prototype.wishti.proxies.ImageServiceClient;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@EActivity
public class UserDetailsActivity extends Activity {

    final int PICK_IMAGE_REQUEST = 2;

    final int REQUEST_CAMERA = 1;

    @RestService
    ImageServiceClient imageService;

    MultiValueMap<String,Object> params;


    @ViewById(R.id.add_photo_button)
    TextView addPhotoButton;

    @ViewById(R.id.take_photo_button)
    TextView takePhotoButton;

    @ViewById(R.id.img)
    ImageView profilePhoto;

    @ViewById(R.id.user_name)
    EditText userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_init_fragment);

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i,PICK_IMAGE_REQUEST);


            }
        });

        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if(requestCode == PICK_IMAGE_REQUEST) {

                Uri originalUri = data.getData();

                String path = getPathFromUri_managedQuery(originalUri);


                params = new LinkedMultiValueMap<>();
                params.add("img", new FileSystemResource(path));


                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), originalUri);

                    profilePhoto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(requestCode == REQUEST_CAMERA){

                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);


                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                params = new LinkedMultiValueMap<>();
                params.add("img", new FileSystemResource(destination));

                profilePhoto.setImageBitmap(thumbnail);

            }
        }
    }

    private String getPathFromUri_managedQuery(Uri uri){
        String [] projection = { MediaStore.Images.Media.DATA };

        Cursor cursor = getContentResolver().query(
                uri,
                projection,
                null,   //selection
                null,   //selectionArgs
                null   //sortOrder
        );

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        cursor.close();

        return cursor.getString(column_index);
    }


    @Background
    void uploadImage(MultiValueMap in){

        try {
            imageService.uploadImg(in);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Click(R.id.finish_button)
    void click_finish_button(View view){

        String name = userName.getText().toString().trim();

        if(!name.isEmpty() && !name.equals("Enter user name")){

            params.add("userName",name);
            uploadImage(params);

            Intent myIntent = new Intent(UserDetailsActivity.this, MainActivity.class);
            UserDetailsActivity.this.startActivity(myIntent);
        }
    }
}
