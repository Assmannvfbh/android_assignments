package com.example.androidassignments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WeatherForecast extends AppCompatActivity {

    ImageView weatherIcon;
    TextView currentTemp;
    TextView minTemp;
    TextView maxTemp;
    ProgressBar progressBar;
    ForecastQuery query;
    Bitmap image;
    String citySelected;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        setupLayout();
        if(getIntent().hasExtra("city")){
            citySelected = getIntent().getExtras().get("city").toString();
            Log.i("WeatherForecastLog", "City Extra passed");
        }
        description.setText(getResources().getString(R.string.weather_description) + " " + citySelected);
        query = new ForecastQuery();
    }

    @Override
    protected void onStart() {
        super.onStart();
        query.execute();
    }

    private void setupLayout() {
        progressBar = findViewById(R.id.weather_progressBar);
        weatherIcon = findViewById(R.id.weather_icon);
        currentTemp = findViewById(R.id.weather_currentTemp);
        minTemp = findViewById(R.id.weather_minTemp);
        maxTemp = findViewById(R.id.weather_maxTemp);
        description = findViewById(R.id.weather_cityName);

        progressBar.setVisibility(View.VISIBLE);

    }

    public class ForecastQuery extends AsyncTask<String, Integer, Map<String, String>>{

        public String FORECASTURL = "https://api.openweathermap.org/data/2.5/weather?q=" + citySelected + ",ca&APPID=5e837e2ca896e880c2cf63116cebb274&mode=xml&units=metric";


        @Override
        protected Map<String, String> doInBackground(String... strings) {

            Map<String, String> results= getTemperature();
            Log.i("WeatherForecastLog", "Filename: " + results.get("icon"));
            Bitmap icon;
            if(fileExistance(results.get("icon"))){
                FileInputStream fis = null;
                try {
                    Log.i("WeatherForecastLog", "Image downloading");
                    fis = openFileInput(results.get("icon"));   }
                catch (FileNotFoundException e) {
                    e.printStackTrace();  }
                icon = BitmapFactory.decodeStream(fis);
            }
            else{
                Log.i("WeatherForecastLog", "Image in storage");
                icon = getIcon(results.get("icon"));
                saveIcon(icon, results.get("icon"));
                image = icon;
            }
            return results;
        }

        private void saveIcon(Bitmap icon, String name) {
            try{
                FileOutputStream outputStream = openFileOutput( name + ".png", Context.MODE_PRIVATE);
                icon.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Map<String,String> getTemperature(){

            Map<String, String> map = new HashMap<>();

            try {
                URL url = new URL(FORECASTURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(conn.getInputStream(), null);


                while (parser.getEventType() != XmlPullParser.END_DOCUMENT){
                    if (parser.getEventType() == XmlPullParser.START_TAG){
                        if(parser.getName().equals("temperature")) {
                            map.put("value",parser.getAttributeValue(null, "value"));
                            publishProgress(25);
                            map.put("max",parser.getAttributeValue(null, "max"));
                            publishProgress(50);
                            map.put("min",parser.getAttributeValue(null, "min"));
                            publishProgress(75);
                        }
                        else if(parser.getName().equals("weather")){
                            map.put("icon",parser.getAttributeValue(null, "icon"));
                        }
                    }

                    parser.next();
                }



            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
            return map;

        }

        public Bitmap getIcon(String iconName){

            String urlString = "https://openweathermap.org/img/w/" + iconName + ".png";
            HttpURLConnection connection = null;
            try {
                URL url;
                url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    publishProgress(100);
                    return BitmapFactory.decodeStream(connection.getInputStream());
                } else
                    return null;
            } catch (Exception e) {
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }

        public boolean fileExistance(String fname){
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Map<String, String> stringStringMap) {
            super.onPostExecute(stringStringMap);

            minTemp.setText(stringStringMap.get("min"));
            maxTemp.setText(stringStringMap.get("max"));
            currentTemp.setText(stringStringMap.get("value"));
            weatherIcon.setImageBitmap(image);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }



}