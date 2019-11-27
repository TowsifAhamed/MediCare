package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainPageActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    //TextView mdate,mtest;
    //TextView msummary,mimages;
    Button mlogout,mreport;
    ArrayList<String> UserDate = new ArrayList<String>();
    ArrayList<String> UserTest = new ArrayList<String>();
    ArrayList<String> UserTestSummary = new ArrayList<String>();
    ArrayList<String> UserTestimages = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mlogout = findViewById(R.id.logout);
        mreport = findViewById(R.id.report);
        /*mdate = findViewById(R.id.date);
        mtest = findViewById(R.id.test);
        msummary = findViewById(R.id.summary);
        mimages = findViewById(R.id.images);*/

        mlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OneSignal.setSubscription(false);
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
            }
        });
        InitializeReportData();
        mreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InitializeReportView();
            }
        });
    }

    public void InitializeReportData() {

        final String UserID = FirebaseAuth.getInstance().getUid().toString();

        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(UserID).child("name");
        final ArrayList<String> UserIDName = new ArrayList<String>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String UserIDName = dataSnapshot.getValue().toString();
                final DatabaseReference reportDB = FirebaseDatabase.getInstance().getReference("report").child(UserIDName);
                final ArrayList<String> ReportDate = new ArrayList<String>();
                reportDB.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsdate : dataSnapshot.getChildren()) {
                            String ReportDate = dsdate.getKey().toString();
                            //mdate.setText(ReportDate);

                            final DatabaseReference reportDateDB = reportDB.child(ReportDate);
                            final ArrayList<String> ReportTest = new ArrayList<String>();
                            reportDateDB.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dstest : dataSnapshot.getChildren()) {
                                        String ReportTestDate = dataSnapshot.getKey();
                                        Toast toastdate = Toast.makeText(getApplicationContext(), ReportTestDate, Toast.LENGTH_SHORT);
                                        toastdate.show();
                                        UserDate.add(ReportTestDate);
                                        String ReportTest = dstest.getKey().toString();
                                        Toast toast = Toast.makeText(getApplicationContext(), ReportTest, Toast.LENGTH_SHORT);
                                        toast.show();
                                        UserTest.add(ReportTest);
                                        String summary = dstest.child("summary").getValue().toString();
                                        Toast toastsummary = Toast.makeText(getApplicationContext(), summary, Toast.LENGTH_SHORT);
                                        toastsummary.show();
                                        UserTestSummary.add(summary);
                                        String images = dstest.child("images").getValue().toString();
                                        Toast toastimages = Toast.makeText(getApplicationContext(), images, Toast.LENGTH_SHORT);
                                        toastimages.show();
                                        UserTestimages.add(images);
                                        //mtest.setText(ReportTest);

                                                /*final DatabaseReference reportTestSummaryDB = reportDateDB.child(ReportTest).child("summary");
                                                final ArrayList<String> summary = new ArrayList<String>();
                                                reportTestSummaryDB.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        String summary = dataSnapshot.getValue().toString();
                                                        Toast toast = Toast.makeText(getApplicationContext(), summary, Toast.LENGTH_SHORT);
                                                        toast.show();
                                                        //msummary.setText(summary);
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError error) {
                                                        // Failed to read value
                                                    }
                                                });
                                                final DatabaseReference reportTestImagesDB = reportDateDB.child(ReportTest).child("images");
                                                final ArrayList<String> images = new ArrayList<String>();
                                                reportTestImagesDB.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        String images = dataSnapshot.getValue().toString();
                                                        Toast toast = Toast.makeText(getApplicationContext(), images, Toast.LENGTH_SHORT);
                                                        toast.show();
                                                        //mimages.setText(images);
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError error) {
                                                        // Failed to read value
                                                    }
                                                });*/
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        InitializeReportView();
    }

    public void InitializeReportView() {
        RecyclerView recyclerView = findViewById(R.id.report_view);
        ReportViewAdapter adapter = new ReportViewAdapter(UserDate, UserTest, UserTestSummary, UserTestimages, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Toast toast = Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT);
        toast.show();
    }


}




















