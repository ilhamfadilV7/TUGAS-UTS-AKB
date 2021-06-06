package com.example.mysimpeapp10116906;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    //Nama  : Ilham fadil Azmi
    //Nim   : 10116906
    //Kelas : IF-14-K
    //TGL Pengerjaan : 06-07-2021
    BottomNavigationView bottomNavigationView;

    String[] daftar;
    ListView listView;
    Menu menu;
    protected Cursor cursor;
    Database database;
    public static MainActivity  ma;



    private BottomNavigationView.OnNavigationItemSelectedListener navigation = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
            Fragment f = null;
            switch (item.getItemId()){
                case R.id.homeview:
                    f = new Home();
                     break;
                case R.id.notes:
                    f = new Catatan();
                    break;
                case R.id.infos:
                    f = new Info();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, f).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigation);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this, Create.class);
                startActivity(pindah);
            }
        });
        ma = this;
        database = new Database(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM catatan", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i=0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0).toString();
        }
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3){
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Data","Update Data","Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                 builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), Detail.class);
                                i.putExtra("nama",selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), Edit.class);
                                in.putExtra("nama",selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = database.getWritableDatabase();
                                db.execSQL("DELETE FROM catatan WHERE nama = '" + selection + "'");
                                RefreshList();
                                break;
                        }

                    }
                });
                builder.create().show();
            }

        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
    }

}