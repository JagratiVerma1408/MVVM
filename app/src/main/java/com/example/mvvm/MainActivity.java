package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;


import com.example.mvvm.Model.NameModel;
import com.example.mvvm.ViewModel.NameViewModel;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDataAdded{
    UserAdapter userAdapter;
    private RecyclerView recyclerView;
    private ArrayList<NameModel> arrayList;
    private NameViewModel nameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recylerview12);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        nameViewModel= ViewModelProviders.of(MainActivity.this).get(NameViewModel.class);
        nameViewModel.init(this);
        UserAdapter userAdapter=new UserAdapter(nameViewModel.getNameModel().getValue(),MainActivity.this);
        Toast.makeText(this, ""+nameViewModel.getNameModel().getValue() , Toast.LENGTH_SHORT).show();
        recyclerView.setAdapter(userAdapter);

    }


    @Override
    public void added() {
        userAdapter.notifyDataSetChanged();
    }
}