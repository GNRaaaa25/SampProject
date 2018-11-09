package com.example.antoine.quizanimaux;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

   private static final String LOG_TAG = "HttpClientGET";
    QuizDB quizDB;
    Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            quizDB = new QuizDB(this);
            cursor = quizDB.getReadableDatabase().rawQuery("SELECT * FROM quiz", null);
            cursor.moveToFirst();
        String s =cursor.getString(cursor.getColumnIndex("name"));
           // String s = cursor.getString(cursor.getColumnIndex("name"));
        Log.i(LOG_TAG, "Test : "+s);
            HttpPage tacheHttpPage = new HttpPage() ;
            tacheHttpPage.execute() ;
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(MainActivity.this, Menu.class) ;
//        startActivity (intent) ;
    }

    private void getPage(String adresse) {
        BufferedReader bufferedReader = null;
        HttpURLConnection urlConnection = null ;
        try {

            URL url = new URL(adresse);

            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

//                String ligneLue = bufferedReader.readLine();
//                while (ligneLue != null) {
//                    Log.i(LOG_TAG, ligneLue);
//                    ligneLue = bufferedReader.readLine();
//                }


			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse (inputStream);
			doc.getDocumentElement().normalize ();


			NodeList nodeList ;
			Node channelElement = doc.getElementsByTagName("channel").item(0);
			StringBuffer stringBuffer = new StringBuffer();
			nodeList = ((Element)channelElement).getElementsByTagName("title");
			if(nodeList.getLength() != 0){
			    for (int i = 0; i < nodeList.getLength(); i++) {
                    stringBuffer.append (nodeList.item(i).getChildNodes().item(0).getNodeValue()) ;
                }
				Log.i(LOG_TAG, "Titre : "+stringBuffer);
			}
			else{
				//
			}

            }
            else {
                Log.i(LOG_TAG, "Response : " + responseCode);
            }

        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            }
            if (urlConnection != null) urlConnection.disconnect();
        }
    }

    private class HttpPage extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground (Void... params) {
            // getPage("https://www.lemonde.fr/rss/tag/enseignement-superieur.xml") ;
            getPage("https://www.programmez.com/rss/rss_actu.php") ;
            return null;
        }
        @Override
        protected void onPostExecute (Void result) {
            // System.out.println (page) ;
            // Log.i(LOG_TAG, page.toString());
        }
    }
}
