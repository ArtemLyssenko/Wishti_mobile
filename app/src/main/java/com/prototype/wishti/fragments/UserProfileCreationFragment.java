package com.prototype.wishti.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.prototype.wishti.R;
import com.prototype.wishti.adapters.ComboBoxWithScrollAdapter;
import com.prototype.wishti.custom_views.ComboBoxWithScrollItemView;
import com.prototype.wishti.custom_views.ComboBoxWithScrollView;
import com.prototype.wishti.interfaces.OnComboBoxItemSelectedListener;
import com.prototype.wishti.models.ComboBoxWithScrollItemModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.create_user_profile)
public class UserProfileCreationFragment extends Fragment {

    @ViewById(R.id.add_photo_button)
    public View addPhotoButton;

    @ViewById(R.id.add_photo_alert)
    public ComboBoxWithScrollView comboBoxWithScrollView;

    @ViewById(R.id.user_nickname)
    public EditText userNickname;

    private final int CREATE_NEW_ACTION = 1;
    private final int CHOOSE_FROM_GALLERY_ACTION = 2;

    @AfterViews
    void calledAfterViewInjection() {

        this.comboBoxWithScrollView.setTextForBottomButton("Отменить");
        this.comboBoxWithScrollView.setTextForHeader("Добавить фото");


        List<ComboBoxWithScrollItemModel> items = new ArrayList<>();
        items.add(new ComboBoxWithScrollItemModel("Сделать новое", CREATE_NEW_ACTION, false));
        items.add(new ComboBoxWithScrollItemModel("Выбрать из галереи", CHOOSE_FROM_GALLERY_ACTION, false));


        ComboBoxWithScrollAdapter adapter = new ComboBoxWithScrollAdapter(this.getActivity(),items);

        this.comboBoxWithScrollView.setAdapter(adapter);
        this.comboBoxWithScrollView.setOnComboBoxItemSelectListener(new OnComboBoxItemSelectedListener() {
            @Override
            public void OnComboBoxItemSelected(ComboBoxWithScrollItemView item) {

                switch (item.getItemValue()) {

                    case CREATE_NEW_ACTION:
                        createNewPhoto();
                        comboBoxWithScrollView.hide();
                        break;

                    case CHOOSE_FROM_GALLERY_ACTION:
                        loadFromGallery();
                        comboBoxWithScrollView.hide();
                        break;
                }
            }
        });

        userNickname.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                }
                return false;
            }
        });



        this.addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comboBoxWithScrollView.show();
            }
        });

    }

    private void createNewPhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CREATE_NEW_ACTION);
    }

    private void loadFromGallery(){
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, CHOOSE_FROM_GALLERY_ACTION);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = this.getActivity().getContentResolver().query(contentUri, projection, null, null, null);

        if (cursor.moveToFirst()) {

            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
}
