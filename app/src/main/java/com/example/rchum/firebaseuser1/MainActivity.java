package com.example.rchum.firebaseuser1;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId( "1:107443467199:android:bf7d7ac302f97640")
                .setDatabaseUrl("https://fir-user2-baec5.firebaseio.com/")
                .build();

        database = FirebaseDatabase.getInstance(FirebaseApp.initializeApp(this,options, "secondary"));
        myRef = database.getReferenceFromUrl("https://fir-user2-baec5.firebaseio.com/");
    }

    public void sendMessage(View button) {
        String sendText = ( (EditText) findViewById(R.id.EditTextMessage)).getText().toString();

        //Messages message = new Messages();
        //message.setMessage(sendText);



        //write a message to the database

        //myRef.child(message.getMessage()).setValue(message);
        myRef.setValue(sendText);
    }

    protected void onStart() {
        super.onStart();
        final TextView message = (TextView) findViewById(R.id.TextViewTitle);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                message.setText(data);
                Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
