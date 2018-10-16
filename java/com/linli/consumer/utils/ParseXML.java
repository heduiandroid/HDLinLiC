package com.linli.consumer.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/28.
 */
public class ParseXML {
    public static String parseResponseXML(String xml,String PName) {
        try {
            InputStream inStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));

            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inStream, "UTF-8");
            int eventType = parser.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT)  {
                switch (eventType)
                {
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if(PName.equals(name))
                        {
                            return parser.nextText();
                        }
                        break;
                }
                eventType = parser.next();
            }

        } catch (Exception e) {
            return "";
        }

        return "";
    }

    public static List<String> parseResponseXMLs(String xml,String PName) {
        List<String> list = new ArrayList<String>();
        try {
            InputStream inStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));

            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inStream, "UTF-8");
            int eventType = parser.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT)  {
                switch (eventType)
                {
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if(PName.equals(name))
                        {
                            list.add(parser.nextText());
                        }
                        break;
                }
                eventType = parser.next();
            }

        } catch (Exception e) {
        }

        return list;
    }


    public static String inputStream2String (InputStream in) throws IOException {

        StringBuffer out = new StringBuffer();
        byte[]  b = new byte[4096];
        int n;
        while ((n = in.read(b))!= -1){
            out.append(new String(b,0,n));
        }
        return  out.toString();
    }
}
