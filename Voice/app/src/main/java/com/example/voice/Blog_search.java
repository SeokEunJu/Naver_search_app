package com.example.voice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Blog_search extends AppCompatActivity {
    String result;
    int display = 5;
    private ListView listView;
    private MyAdapter adapter;
    private ImageView image;
    private static final int PICK_IMAGE = 777;
    public String[] title = new String[display];
    public String[] link = new String[display];
    public String[] description = new String[display];
    public String[] postdate = new String[display];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_blog_search);
        Intent intent = getIntent();
        result = intent.getExtras().getString("result_string");

        image = (ImageView) findViewById(R.id.imageView);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference islandRef = storageRef.child("images/Blog.png");
        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                image.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("Firebase","Image Load is Failed");
            }
        });

        String[] array;
        array = result.split("\"");


        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("title"))
                title[k] = array[i + 2];
            if (array[i].equals("link"))
                link[k] = array[i + 2];
            if (array[i].equals("description"))
                description[k] = array[i + 2];
            if (array[i].equals("postdate")) {
                postdate[k] = array[i + 2];
                k++;
            }
        }

        adapter = new MyAdapter();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        for (int i = 0; i < title.length; i++) {
            adapter.addvalue(title[i],link[i],description[i],postdate[i]);
        }

    }
}