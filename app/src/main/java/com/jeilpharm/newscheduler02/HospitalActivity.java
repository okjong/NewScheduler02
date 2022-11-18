package com.jeilpharm.newscheduler02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jeilpharm.newscheduler02.databinding.ActivityHospitalBinding;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HospitalActivity extends AppCompatActivity {

    ActivityHospitalBinding binding;

    ArrayList<Recycler_item> items= new ArrayList<>();
    MyAdapter adapter;

    String apikey="266dab6435574f46baa66dd18771c8e9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHospitalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter= new MyAdapter(this, items);
        binding.recyclerView.setAdapter(adapter);

        binding.btnSet.setOnClickListener(view -> {
            Thread thread= new Thread(){
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            items.clear();
                            adapter.notifyDataSetChanged();
                        }
                    });

                    String address= "https://openapi.gg.go.kr/PrivateHospital"
                            +"?key="+apikey;

                    try {
                        URL url=new URL(address);
                        InputStream is= url.openStream();
                        InputStreamReader isr= new InputStreamReader(is);

                        XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                        XmlPullParser xpp= factory.newPullParser();
                        xpp.setInput(isr);

                        int eventType= xpp.getEventType();
                        Recycler_item item=null;

                        while (eventType!=XmlPullParser.END_DOCUMENT){
                            switch (eventType){
                                case XmlPullParser.START_DOCUMENT:
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(HospitalActivity.this, "파싱시작", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    case XmlPullParser.START_TAG:
                                        String tagName=xpp.getName();
                                        if (tagName.equals("row")){
                                            item=new Recycler_item();
                                        }else if (tagName.equals("SIGUN_NM")){
                                            xpp.next();
                                            item.tvSigun=xpp.getText();
                                        }else if (tagName.equals("BIZPLC_NM")){
                                            xpp.next();
                                            item.tvHospital=xpp.getText();
                                        }
                                        break;

                                        case XmlPullParser.TEXT:
                                            break;
                                        case XmlPullParser.END_TAG:
                                            if (xpp.getName().equals("row")){
                                                items.add(item);
                                            }
                                            break;

                            }
                            eventType= xpp.next();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HospitalActivity.this,"개"+items.size(), Toast.LENGTH_SHORT).show();
                                adapter.notifyDataSetChanged();
                            }
                        });


                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }

                }
            };
            thread.start();

        });

    }
}