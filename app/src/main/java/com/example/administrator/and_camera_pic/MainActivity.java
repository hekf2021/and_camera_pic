package com.example.administrator.and_camera_pic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button openButton;
    private Button pickButton;
    private ImageView imageView;
    private final static int OPEN_RESULT=1;//打照相机
    private final static int PIC_RESULT=2;//选择图片库

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openButton = this.findViewById(R.id.button1);
        pickButton = this.findViewById(R.id.button2);
        imageView = this.findViewById(R.id.imageView);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,OPEN_RESULT);
            }
        });

        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,PIC_RESULT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            //拍照的结果
            case OPEN_RESULT:
                if(resultCode ==RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imageView.setImageBitmap(bitmap);
                }
                break;
             //选择图片的结果
            case PIC_RESULT:
                Uri uri = data.getData();
                imageView.setImageURI(uri);
                break;
        }
    }
}
