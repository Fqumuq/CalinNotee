package com.example.keepingup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddImagePage extends AppCompatActivity {
    private AppDatabaseHelper databaseHelper;
    private ImageView image1, image2, image3, image4, image5, image6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image_page);
        databaseHelper = new AppDatabaseHelper(this);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4); 
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
    }

    private void setImage(Uri imageUri, ImageView imageView) {
        imageView.setImageURI(imageUri);
        insertImagePath(imageUri.toString());
    }

    private void insertImagePath(String imagePath) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imagePath", imagePath);
        long result = db.insert("images", null, values);
        if (result != -1) {
            Log.d("AddImagePage", "Image path saved in the database");
        } else {
            Log.e("AddImagePage", "Failed to save image path in the database");
        }
        db.close();
    }

    public void onAddButtonClick(View view) {
        openGallery();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (image1.getDrawable() == null) {
                setImage(selectedImageUri, image1);
            } else if (image2.getDrawable() == null) {
                setImage(selectedImageUri, image2);
            } else if (image3.getDrawable() == null) {
                setImage(selectedImageUri, image3);
            } else if (image4.getDrawable() == null) {
                setImage(selectedImageUri, image4);
            } else if (image5.getDrawable() == null) {
                setImage(selectedImageUri, image5);
            } else if (image6.getDrawable() == null) {
                setImage(selectedImageUri, image6);
            }
        }
    }

    public void onDeleteAllImagesClick(View view) {
        image1.setImageResource(0);
        image2.setImageResource(0);
        image3.setImageResource(0);
        image4.setImageResource(0);
        image5.setImageResource(0);
        image6.setImageResource(0);
    }

    private void deleteAllImagePaths() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int deletedRows = db.delete("images", null, null);
        if (deletedRows > 0) {
            Log.d("AddImagePage", "Deleted all image paths from the database");
        } else {
            Log.e("AddImagePage", "Failed to delete image paths from the database");
        }
        db.close();
    }

    public void buClick(View view) {
        int buSelected = view.getId();
        if (buSelected == R.id.btnBack) {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        } else if (buSelected == R.id.btnDelete) {
            onDeleteAllImagesClick(view);
        }
    }
}
