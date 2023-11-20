package com.example.viewimage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private int currentImageIndex = 0;
    private int[] imageIds = {
            R.drawable.scuba_diver, R.drawable.whales, // ... other images
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageDialog();
            }
        });

        Button nextButton = findViewById(R.id.nextButton);
        Button prevButton = findViewById(R.id.prevButton);

        nextButton.setOnClickListener(v -> showNextImage());
        prevButton.setOnClickListener(v -> showPreviousImage());

        updateImage();
    }

    private void showImageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_image, null);
        ImageView dialogImageView = dialogView.findViewById(R.id.dialogImageView);
        dialogImageView.setImageResource(imageIds[currentImageIndex]);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showNextImage() {
        currentImageIndex = (currentImageIndex + 1) % imageIds.length;
        updateImage();
    }

    private void showPreviousImage() {
        currentImageIndex = (currentImageIndex - 1 + imageIds.length) % imageIds.length;
        updateImage();
    }

    private void updateImage() {
        imageView.setImageResource(imageIds[currentImageIndex]);
    }

}