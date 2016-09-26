package com.example.tim.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Vincent on 2015/12/6.
 */
public class Woeid {
    String placename;
    String Woeid="";

    public Woeid(String placename){
        new MyQueryYahooPlaceTask().execute();
        this.placename = placename;
    }

    public String getWoeid(){
        return Woeid;
    }

    private class MyQueryYahooPlaceTask extends AsyncTask<Void, Void, Void> {

        ArrayList<String> list;

        @Override
        protected Void doInBackground(Void... arg0) {
            list = QueryYahooPlaceAPIs(placename);
            if(list.size()!=0)
               Woeid = list.get(0);
            return null;
        }

    }

    private ArrayList<String> QueryYahooPlaceAPIs(String placename){
        String uriPlace = Uri.encode(placename);
        final String yahooPlaceApisBase = "http://query.yahooapis.com/v1/public/yql?q=select*from%20geo.places%20where%20text=";
        final String yahooapisFormat = "&format=xml";

        String yahooPlaceAPIsQuery = yahooPlaceApisBase
                + "%22" + uriPlace + "%22"
                + yahooapisFormat;

        String woeidString = QueryYahooWeather(yahooPlaceAPIsQuery);
        Document woeidDoc = convertStringToDocument(woeidString);
        return  parseWOEID(woeidDoc);
    }

    private ArrayList<String> parseWOEID(Document srcDoc){
        ArrayList<String> listWOEID = new ArrayList<String>();

        if(srcDoc!=null){
            NodeList nodeListDescription = srcDoc.getElementsByTagName("woeid");
            if(nodeListDescription.getLength()>=0){
                for(int i=0; i<nodeListDescription.getLength(); i++){
                    listWOEID.add(nodeListDescription.item(i).getTextContent());
                }
            }else{
                listWOEID.clear();
            }
        }


        return listWOEID;
    }

    private Document convertStringToDocument(String src){
        Document dest = null;

        DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder parser;

        try {
            parser = dbFactory.newDocumentBuilder();
            dest = parser.parse(new ByteArrayInputStream(src.getBytes()));
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dest;
    }

    private String QueryYahooWeather(String queryString){
        String qResult = "";

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(queryString);

        try {
            HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();

            if (httpEntity != null){
                InputStream inputStream = httpEntity.getContent();
                Reader in = new InputStreamReader(inputStream);
                BufferedReader bufferedreader = new BufferedReader(in);
                StringBuilder stringBuilder = new StringBuilder();

                String stringReadLine = null;

                while ((stringReadLine = bufferedreader.readLine()) != null) {
                    stringBuilder.append(stringReadLine + "\n");
                }

                qResult = stringBuilder.toString();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return qResult;
    }
}
