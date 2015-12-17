package com.principal.projetolivia.com.principal.projetolivia.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Xml;

import com.principal.projetolivia.main.MainActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roosq on 03/12/2015.
 */
public class fileConnector {

    private final String fileUserData = "data";
    private InputStream fileQuestion;

    private static final String ns = null;

    public fileConnector (InputStream fileQuestion) {
        this.fileQuestion = fileQuestion;
    }


    public List<User> getProfileList(Context context) {
        List<User> returnObject = new ArrayList<User>();
        returnObject.clear();
        try {
            FileInputStream fis = context.openFileInput(fileUserData);
            ObjectInputStream is = new ObjectInputStream(fis);
            returnObject = (List<User>) is.readObject();
            is.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return returnObject;
    }

    public void setProfileList(Context context, List<User> userList) {
        try {
            FileOutputStream fos = context.openFileOutput(fileUserData, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(userList);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Question> getQuestionList() {
        List<Question> questions = new ArrayList<>();






        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(fileQuestion, null);
            try {
                parser.nextTag();
                readFeed(parser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return questions;
    }

    private void readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Question> questions = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "questions");
        while (parser.next() != XmlPullParser.END_TAG) {
            String temp = parser.getName();
            String temps = temp;
        }
    }
}
