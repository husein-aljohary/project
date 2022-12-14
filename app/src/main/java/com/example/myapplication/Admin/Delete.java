package com.example.myapplication.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Delete extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnDelete;
    DatabaseReference reff ,dbref;
    //**/*/*/*/*/*/*/*/*/*/*/
    Spinner spinner2;
    String chosen_spinner;
    ////****/*//*/*/*/**//*/*/////
    FirebaseStorage storage1;
    StorageReference storageReferenceCity;
    //////////////////////////////////////////////////////////////
    Spinner spinner3;
    String chosen_spinner3;


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        chosen_spinner=adapterView.getItemAtPosition(i).toString();
        fetchdata();
        //Toast.makeText(adapterView.getContext(),chosen_spinner,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //////////////////////////////////////////////////////////////////////
    ValueEventListener listener;
    ArrayList<String>list;
    ArrayAdapter<String>adapter1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        reff=FirebaseDatabase.getInstance().getReference().child("mallinfo");

        btnDelete=findViewById(R.id.BtnDelete);



        spinner2=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);
////////////////////////////////////////////////////////////////////////////////////
        dbref=FirebaseDatabase.getInstance().getReference().child("mallinfo");
        spinner3=findViewById(R.id.spinner3);
        list=new ArrayList<>();
        adapter1=new ArrayAdapter<>(Delete.this,android.R.layout.simple_spinner_item,list);
        spinner3.setAdapter(adapter1);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chosen_spinner3=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



///*//////////////////****
        storage1=FirebaseStorage.getInstance();
        storageReferenceCity=storage1.getReference().child("City/");




        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    reff.child(chosen_spinner).child(chosen_spinner3).removeValue();
                    ///************delete th image from the storage
                    storageReferenceCity.child(chosen_spinner).child(chosen_spinner3).delete();
                    Toast.makeText(Delete.this, " Done Delete "+chosen_spinner3, Toast.LENGTH_SHORT).show();
                //}

            }
        });




    }

    public void fetchdata(){
        listener=dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot mydata:snapshot.child(chosen_spinner).getChildren() ){
                    list.add(String.valueOf(String.valueOf(mydata.getKey())));
                }

                adapter1.notifyDataSetChanged();
            }
                        /*
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot mydata:snapshot.child("mallinfo").child("Amman").getChildren() )
                    list.add(String.valueOf(String.valueOf(mydata.getKey())));
                adapter1.notifyDataSetChanged();
            }

             */

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }






}
