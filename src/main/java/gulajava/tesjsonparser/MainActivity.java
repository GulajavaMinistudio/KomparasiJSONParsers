package gulajava.tesjsonparser;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import gulajava.tesjsonparser.deserials.Berita;
import gulajava.tesjsonparser.deserials.Deserialis;

public class MainActivity extends AppCompatActivity {



    private Spinner spintipe;
    private ArrayAdapter<String> adapterspin;
    private String[] arrstrtipe = {"Jackson","GSON","Moshi"};


    private TextView teksjson;
    private TextView teks_waktu;

    private ScrollView scrollview;
    private ListView listView;

    private Button tombolparse;
    private Button tombolihathasil;


    private Calendar calsawal;
    private Calendar calakhir;

    private long waktuawal = 0;
    private long waktuakhir = 0;

    private Deserialis deserialjson;

    private int posisipilihan = 0;

    private List<Berita> arrlistberita;

    private AdapterList adapterlist;

    private AsycTaskJackson asyncjakson;
    private AsycTaskGSON asycTaskGSON;
    private AsycTaskMoshi asycTaskMoshi;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapterspin = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, arrstrtipe);
        adapterspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spintipe = (Spinner) findViewById(R.id.spintipejson);
        spintipe.setAdapter(adapterspin);
        spintipe.setOnItemSelectedListener(listenerspiner);


        teksjson = (TextView) findViewById(R.id.teksjudul);
        teks_waktu = (TextView) findViewById(R.id.teks_waktu);
        scrollview = (ScrollView) findViewById(R.id.scrollview);
        listView = (ListView) findViewById(R.id.list_hasil);

        tombolparse = (Button) findViewById(R.id.tombol_parsejson);
        tombolparse.setOnClickListener(listenertombol);

        tombolihathasil = (Button) findViewById(R.id.tombol_lihathasil);
        tombolihathasil.setOnClickListener(listenertombol);


        deserialjson = new Deserialis(MainActivity.this);
        AsycTaskStringAset asyncaset = new AsycTaskStringAset();
        asyncaset.execute();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




    AdapterView.OnItemSelectedListener listenerspiner = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            posisipilihan = i;

            scrollview.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            teks_waktu.setText("-");
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };




    View.OnClickListener listenertombol = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Log.w("TOMBOL KLIK", "PARSE JSON DIMULAI");

            switch (view.getId()) {

                case R.id.tombol_parsejson :

                    switch (posisipilihan) {

                        case 0 :

                            asyncjakson = new AsycTaskJackson();
                            asyncjakson.execute();
                            break;

                        case 1 :

                            asycTaskGSON = new AsycTaskGSON();
                            asycTaskGSON.execute();
                            break;

                        case 2 :

                            asycTaskMoshi = new AsycTaskMoshi();
                            asycTaskMoshi.execute();
                            break;

                    }
                    break;

                case R.id.tombol_lihathasil :

                    listView.setVisibility(View.VISIBLE);
                    scrollview.setVisibility(View.GONE);
                    break;
            }
        }
    };




    protected class AsycTaskJackson extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {


            calsawal = Calendar.getInstance();
            waktuawal = calsawal.getTimeInMillis();
            arrlistberita = deserialjson.deserialJSONJackson();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            calakhir = Calendar.getInstance();
            waktuakhir = calakhir.getTimeInMillis();

            if (arrlistberita != null) {

                adapterlist = new AdapterList();
                listView.setAdapter(adapterlist);

                listView.setVisibility(View.VISIBLE);
                scrollview.setVisibility(View.GONE);

                Log.w("HASIL " , "HASIL " + arrlistberita.size());

                setelHasil();
            }

        }
    }


    protected class AsycTaskStringAset extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            deserialjson.muatJsonAset();
            return null;
        }

    }




    protected class AsycTaskGSON extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            calsawal = Calendar.getInstance();
            waktuawal = calsawal.getTimeInMillis();
            arrlistberita = deserialjson.deserialJSONGson();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            calakhir = Calendar.getInstance();
            waktuakhir = calakhir.getTimeInMillis();

            if (arrlistberita != null) {

                adapterlist = new AdapterList();
                listView.setAdapter(adapterlist);

                listView.setVisibility(View.VISIBLE);
                scrollview.setVisibility(View.GONE);

                setelHasil();
            }
        }
    }




    protected class AsycTaskMoshi extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            calsawal = Calendar.getInstance();
            waktuawal = calsawal.getTimeInMillis();
            arrlistberita = deserialjson.deserialJSONMoshi();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            calakhir = Calendar.getInstance();
            waktuakhir = calakhir.getTimeInMillis();

            if (arrlistberita != null) {

                adapterlist = new AdapterList();
                listView.setAdapter(adapterlist);

                listView.setVisibility(View.VISIBLE);
                scrollview.setVisibility(View.GONE);

                setelHasil();
            }
        }
    }




    protected class AdapterList extends ArrayAdapter<Berita> {

        private LayoutInflater inflaters;

        public AdapterList() {
            super(MainActivity.this, R.layout.rowitems,arrlistberita);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolders viewholder;

            if (convertView == null) {

                inflaters = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflaters.inflate(R.layout.rowitems,parent,false);

                viewholder = new ViewHolders(convertView);
                convertView.setTag(viewholder);
            }
            else {
                viewholder = (ViewHolders) convertView.getTag();
            }

            Berita berita = arrlistberita.get(position);
            viewholder.getTeksjudul().setText(berita.getTitle());


            return convertView;
        }
    }


    private void setelHasil() {


        long waktuhasil = waktuakhir - waktuawal;

        teks_waktu.setText("" + waktuhasil + " ms");
    }








}
