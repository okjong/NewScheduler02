package com.jeilpharm.newscheduler02;

import android.app.Activity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

interface NetworkCallback{
   public void prevGetList();
   public void endGetList(ArrayList<Recycler_item> arrayList);
}

public class NetworkModule {
   private Activity activity;
   private NetworkCallback networkCallback;
   public NetworkModule(Activity activity,NetworkCallback networkCallback) {
      this.activity = activity;
      this.networkCallback = networkCallback;
   }
   private static final String apikey="266dab6435574f46baa66dd18771c8e9";

   public void runGetList(){
      Thread thread= new Thread(){
         @Override
         public void run() {
            activity.runOnUiThread(new Runnable() {
               @Override
               public void run() {
                  networkCallback.prevGetList();
               }
            });
            final ArrayList<Recycler_item> items = NetworkModule.getList();
            activity.runOnUiThread(new Runnable() {
               @Override
               public void run() {
                  //adapter.notifyDataSetChanged();
                  networkCallback.endGetList(items);
               }
            });
         }
      };
      thread.start();
   }

   public static ArrayList<Recycler_item> getList(){
      String address= "https://openapi.gg.go.kr/PrivateHospital"
         +"?key="+apikey;
      ArrayList<Recycler_item> array = new ArrayList<>();
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

            case XmlPullParser.START_TAG:
               String tagName=xpp.getName();
               if ("row".equals(tagName)){
                  item=new Recycler_item();
               }else if ("SIGUN_NM".equals(tagName)){
                  xpp.next();
                  item.tvSigun=xpp.getText();
               }else if ("BIZPLC_NM".equals(tagName)){
                  xpp.next();
                  item.tvHospital=xpp.getText();
               }
               break;

            case XmlPullParser.TEXT:
               break;
            case XmlPullParser.END_TAG:
               if (xpp.getName().equals("row")){
                  array.add(item);
               }
               break;

            }
            eventType= xpp.next();
         }

      } catch (MalformedURLException e) {
         e.printStackTrace();
         return null;
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      } catch (XmlPullParserException e) {
         e.printStackTrace();
         return null;
      }
      return array;
   }
}
