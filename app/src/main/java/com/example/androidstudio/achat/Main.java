package com.example.androidstudio.achat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstudio.Page_Internet;
import com.example.androidstudio.R;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecyclerView_Adapter adapter;
    private ArrayList<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        recyclerView = findViewById(R.id.recyclerView);

        buildRecyclerView();


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        onClick(view ,  position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        return true;


    }

    private void filter(String text) {
        ArrayList<Data> filteredlist = new ArrayList<>();

        for (Data item : data) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(filteredlist);
        }
    }

    private void buildRecyclerView() {

        data = new ArrayList<>();

        data.add(new Data("Kit Elegoo",      R.drawable.achat_kit,             "https://amzn.to/38Rlsl1"));
        data.add(new Data("Carte Arduino",   R.drawable.achat_carte_arduino,   "https://amzn.to/3typI0J"));
        data.add(new Data("Capteur distance",R.drawable.achat_distance,        "https://amzn.to/3ty0oI3"));
        data.add(new Data("Bouton poussoir", R.drawable.achat_bouton,          "https://amzn.to/3cEPaLa"));
        data.add(new Data("Résistance",      R.drawable.achat_resistance,      "https://amzn.to/38UpVn3"));
        data.add(new Data("Joystick",        R.drawable.achat_joystick,        "https://amzn.to/3lAU3ZD"));
        data.add(new Data("Capteur de son",  R.drawable.achat_capteur_son,     "https://amzn.to/3c1Ur0m"));
        data.add(new Data("Buzzer",          R.drawable.achat_buzzer,          "https://amzn.to/3sa2iyr"));
        data.add(new Data("Shock",           R.drawable.achat_shock,           "https://amzn.to/3d7lK8X"));
        data.add(new Data("Photorésistance", R.drawable.achat_photoresistance, "https://amzn.to/3raz1lU"));
        data.add(new Data("Humidité",        R.drawable.achat_humidite,        "https://amzn.to/2PgFrCD"));
        data.add(new Data("Motorshield",     R.drawable.achat_motorshield,     "https://amzn.to/3bWX3wx"));
        data.add(new Data("Carte méga",      R.drawable.achat_carte_mega,      "https://amzn.to/2OL1SjC"));
        data.add(new Data("Carte nano",      R.drawable.achat_carte_nano,      "https://amzn.to/30YsU9A"));
        data.add(new Data("Carte raspberry", R.drawable.achat_raspberry,       "https://amzn.to/3907o95"));
        data.add(new Data("Kit rapsberry",   R.drawable.achat_kit_raspberry,   "https://amzn.to/2OVNSUj"));
        data.add(new Data("Machine à souder",R.drawable.achat_machine_soudure, "https://amzn.to/38TxeLW"));
        data.add(new Data("Etain",           R.drawable.achat_etain,           "https://amzn.to/3vJkGjI"));
        data.add(new Data("Kit soudure",     R.drawable.achat_kit_soudure,     "https://amzn.to/310olvr"));
        data.add(new Data("Dénudeur de fil", R.drawable.achat_pince_soudure,   "https://amzn.to/3c2NT1C"));
        data.add(new Data("Moteur dc",       R.drawable.achat_moteur_dc,       "https://amzn.to/3r21pXx"));
        data.add(new Data("Servomoteur",     R.drawable.achat_servomoteur,     "https://amzn.to/3lw1l0R"));
        data.add(new Data("Led",             R.drawable.achat_led,             "https://amzn.to/3c2W0Lz"));
        data.add(new Data("Breadboard",      R.drawable.achat_breadboard,      "https://amzn.to/3c1Jdc8"));
        data.add(new Data("Prototype",       R.drawable.prototype,             "https://amzn.to/38VLPpY"));
        data.add(new Data("Module wifi",     R.drawable.achat_module_wifi,     "https://amzn.to/3vDmPNY"));
        data.add(new Data("Module bluetooth",R.drawable.achat_module_bluetooth,"https://amzn.to/3tKZCI9"));

        adapter = new RecyclerView_Adapter(data, Main.this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
    }

    public void onClick(View v, int position){

        Intent intent_achat= new Intent(this, Page_Internet.class);
        intent_achat.putExtra("url_achat",data.get(position).getLien());
        startActivity(intent_achat);
    }

}