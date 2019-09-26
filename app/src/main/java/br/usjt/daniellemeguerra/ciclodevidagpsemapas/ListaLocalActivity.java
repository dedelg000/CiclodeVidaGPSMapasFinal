package br.usjt.daniellemeguerra.ciclodevidagpsemapas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListaLocalActivity extends AppCompatActivity {

    private ListView locaisListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_local);
        Bundle extra = getIntent().getBundleExtra("extra");
        final ArrayList <String> localAtual = (ArrayList<String>) extra.getSerializable("locais");
        locaisListView = findViewById(R.id.localListView);
        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, localAtual);
        locaisListView.setAdapter(adapter);
        locaisListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String localEscolhido = localAtual.get(position);
                String [] partes = localEscolhido.split(", ");
                String latLocal = partes[0];
                String longLocal = partes[1];
                String [] lat2 = latLocal.split(": ");
                double latLocal2 = Double.valueOf(lat2[1].replaceAll(",", "."));
                String [] long2 = longLocal.split(": ");
                double longLocal2 = Double.valueOf(long2[1].replaceAll(",","."));
                Uri gmmIntentUri = Uri.parse(String.format("geo:%f,%f",latLocal2, longLocal2));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}
