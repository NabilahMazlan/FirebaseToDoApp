package com.example.firebasetodoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    private TextView tvTitle;
    private EditText etTitle;
    private TextView tvDate;
    private EditText etDate;
    private Button btnUpdate;

    private FirebaseFirestore firestore;
    private CollectionReference collection;
    private DocumentReference document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.textViewTitle);
        etTitle = findViewById(R.id.editTextTitle);
        tvDate = findViewById(R.id.textViewDate);
        etDate = findViewById(R.id.editTextDate);

        btnUpdate = findViewById(R.id.buttonUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUpdateOnClick(v);
            }
        });

        firestore = FirebaseFirestore.getInstance();

        collection = firestore.collection("todolist");
        document = collection.document("toDoItem");
        document.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    return;
                }
                if(documentSnapshot != null && documentSnapshot.exists()){
                    ToDoList todolist = documentSnapshot.toObject(ToDoList.class);
                    tvTitle.setText(todolist.getTitle());
                    tvDate.setText(todolist.getDate());
                }
            }
        });
    }

    private void btnUpdateOnClick(View v) {
        String title = etTitle.getText().toString();
        String date = etDate.getText().toString();
        ToDoList todolist = new ToDoList(title, date);
        document.set(todolist);

    }
}
