package gulajava.tesjsonparser.deserials;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import gulajava.tesjsonparser.Konstanta;

/**
 * Created by Kucing Imut on 8/21/15.
 */


public class Deserialis {


    private ObjectMapper objekmapper;
    private Gson gsonmapper;
    private Moshi moshimapper;
    private JsonAdapter<ArrayBerita> jsonadaptermoshi;

    private String string_jsonbahanparse = "";

    private Context ctxs;


    public Deserialis(Context konteks) {

        this.ctxs = konteks;

        //jackson
        objekmapper = new ObjectMapper();
        objekmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objekmapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

        //gson
        gsonmapper = new Gson();

        //moshi
        moshimapper = new Moshi.Builder().build();
        jsonadaptermoshi = moshimapper.adapter(ArrayBerita.class);

    }


    //PARSE JSON JACKSON
    public List<Berita> deserialJSONJackson() {

        List<Berita> listberita = new ArrayList<>();

        try {

            ArrayBerita arrayberita = objekmapper.readValue(string_jsonbahanparse, ArrayBerita.class);
            listberita = arrayberita.getResult();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


        return listberita;
    }



    //PARSE JSON GSON
    public List<Berita> deserialJSONGson() {

        List<Berita> listberita = new ArrayList<>();

        try {

            ArrayBerita arrayberita = gsonmapper.fromJson(string_jsonbahanparse, ArrayBerita.class);
            listberita = arrayberita.getResult();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


        return listberita;
    }



    //PARSE JSON MOSHI
    public List<Berita> deserialJSONMoshi() {

        List<Berita> listberita = new ArrayList<>();

        try {

            ArrayBerita arrayberita = jsonadaptermoshi.fromJson(string_jsonbahanparse);
            listberita = arrayberita.getResult();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


        return listberita;
    }

    public String loadJSONFromAsset() {

        String json = "";
        try {

            InputStream is = ctxs.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public void muatJsonAset() {

        this.string_jsonbahanparse = loadJSONFromAsset();

        Log.w("JSON","JSON " + string_jsonbahanparse);
    }

}
