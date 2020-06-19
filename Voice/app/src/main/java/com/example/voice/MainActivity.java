package com.example.voice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button image, news, blog;
    public EditText keyword;
    String ClientID = "YourClientID";
    String ClientSecret = "YourClientSecret";
    int display = 5;
    String Keyword;
    String result="";
    public int length;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("Keyword",Keyword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        news = findViewById(R.id.news);
        blog = findViewById(R.id.blog);
        keyword = findViewById(R.id.text_home);

        blog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Keyword = keyword.getText().toString();
                length = Keyword.length();
                if (length == 0) {
                    Toast.makeText(MainActivity.this, "검색할 단어를 입력해주세요!!", Toast.LENGTH_SHORT).show();
                } else {
                    Thread t1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            result = searchBlog(Keyword);
                        }
                    });
                    t1.start();
                    try {
                        t1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent blog_search = new Intent(MainActivity.this, Blog_search.class);
                            blog_search.putExtra("result_string", result);
                            startActivity(blog_search);
                        }
                    });
                }
            }

        });
        news.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Keyword = keyword.getText().toString();
                length = Keyword.length();
                if (length == 0) {
                    Toast.makeText(MainActivity.this, "검색할 단어를 입력해주세요!!", Toast.LENGTH_SHORT).show();
                } else {
                    Thread t1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            result = searchNews(Keyword);
                        }
                    });
                    t1.start();
                    try {
                        t1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent news_search = new Intent(MainActivity.this, News_search.class);
                            news_search.putExtra("result_string", result);
                            startActivity(news_search);
                        }
                    });
                }
            }

        });
    }

    public String searchBlog(final String search) {
        String data = "";
        try
        {
            String text = URLEncoder.encode(search, "UTF-8");
            String request = "https://openapi.naver.com/v1/search/blog?query=" + text + "&display=" + display + "&";
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Naver-Client-Id", ClientID);
            connection.setRequestProperty("X-Naver-Client-Secret", ClientSecret);
            connection.connect();

            int responseCode = connection.getResponseCode();

            BufferedReader mBufferreader;
            if (responseCode == 200) {
                mBufferreader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            } else {
                mBufferreader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            StringBuilder searchResult = new StringBuilder();
            String inputLine;
            while ((inputLine = mBufferreader.readLine()) != null) {
                searchResult.append(inputLine + "\n");

            }
            mBufferreader.close();
            connection.disconnect();
            data = searchResult.toString();

        } catch(Exception e) {
            Log.d("Naver", "Error");
        }
        return data;
    }

    public String searchNews(final String search) {
        String data = "";
        try
        {
            String text = URLEncoder.encode(search, "UTF-8");
            String request = "https://openapi.naver.com/v1/search/news?query=" + text + "&display=" + display + "&";
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Naver-Client-Id", ClientID);
            connection.setRequestProperty("X-Naver-Client-Secret", ClientSecret);
            connection.connect();

            int responseCode = connection.getResponseCode();

            BufferedReader mBufferreader;
            if (responseCode == 200) {
                mBufferreader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            } else {
                mBufferreader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            StringBuilder searchResult = new StringBuilder();
            String inputLine;
            while ((inputLine = mBufferreader.readLine()) != null) {
                searchResult.append(inputLine + "\n");

            }
            mBufferreader.close();
            connection.disconnect();
            data = searchResult.toString();

        } catch(Exception e) {
            Log.d("Naver", "Error");
        }
        return data;
    }
}

