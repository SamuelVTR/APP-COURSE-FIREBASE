package com.example.chronicle972.siblings;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class listecourse extends AppCompatActivity {
    private String temp_key;
    private Button add;
    private EditText item;
    private Random alea;
    private ListView Listitem;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_rooms = new ArrayList<>();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
    private DatabaseReference root1 = FirebaseDatabase.getInstance().getReference().getRoot();
    private ArrayList<String> listcode = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listecourse);

        add =(Button)findViewById(R.id.ajouterbtn);
        item=(EditText)findViewById(R.id.txtitem);
        Listitem=(ListView)findViewById(R.id.listviewitem);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_multichoice,list_of_rooms);
        Listitem.setAdapter(arrayAdapter);

       //faire notification
        // parametre mute son
        // soundbox



    }

    @Override
    protected void onResume()
    {
        super.onResume();

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {




                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){





                        set.add((String) ((DataSnapshot) i.next()).getKey());




                }



                list_of_rooms.clear();
                list_of_rooms.addAll(set);

                arrayAdapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Listitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {


                AlertDialog.Builder builder = new AlertDialog.Builder(listecourse.this);
                builder.setTitle("Supprimer vraiment?");
                builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                       String article = Listitem.getItemAtPosition(i).toString();

                        root = FirebaseDatabase.getInstance().getReference().child(article);

                        root.removeValue();

                        alea = new Random();

/// gere le son ***************************
                        int hasard = alea.nextInt(4);
                        if(article.contains("viande") || article.contains("poulet") || article.contains("steak") || article.contains("dinde") || article.contains("saumon"))
                        {
                            final MediaPlayer mp = MediaPlayer.create(listecourse.this, R.raw.is_that_meat);
                            mp.start();
                        }
                        else if(article.contains("glace") || article.contains("dessert") || article.contains("magnum") || article.contains("creme glacée"))
                        {
                            final MediaPlayer mp = MediaPlayer.create(listecourse.this, R.raw.icecream);
                            mp.start();
                        }
                        else if(article.contains("pizza") )
                        {
                            final MediaPlayer mp = MediaPlayer.create(listecourse.this, R.raw.pizza);
                            mp.start();
                        }
                        else if(article.contains("papier") || article.contains("sopalin") || article.contains("aluminium") )
                        {
                            final MediaPlayer mp = MediaPlayer.create(listecourse.this, R.raw.paper_now);
                            mp.start();
                        }
                        else if(article.contains("citron") )
                        {
                            final MediaPlayer mp = MediaPlayer.create(listecourse.this, R.raw.sour);
                            mp.start();
                        }


                        else
                        {

                            if (hasard==0){
                                final MediaPlayer mp = MediaPlayer.create(listecourse.this, R.raw.whatdidido);
                                mp.start();
                            }
                           else if (hasard==1){
                                final MediaPlayer mp = MediaPlayer.create(listecourse.this, R.raw.heuheu);
                                mp.start();
                            }
                            else if (hasard==2){
                                final MediaPlayer mp = MediaPlayer.create(listecourse.this, R.raw.hoo_noo);
                                mp.start();
                            }
                            else if (hasard==3){
                                final MediaPlayer mp = MediaPlayer.create(listecourse.this, R.raw.what_is_that);
                                mp.start();
                            }

                        }
                        /// gere le son ***************************

                    }
                });

                builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });

                builder.show();



            }
        });



    }

    @Override
    protected void onRestart() {
        super.onRestart();


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void ajouterclick(View v)
    {

        Map<String,Object> map = new HashMap<String, Object>();

      //  root.child("article").push().setValue("");

        map.put(item.getText().toString(),"");
        root1.updateChildren(map);


        item.setText("");


    }


}
