package com.example.mvvm.Respitory;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.mvvm.Model.NameModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Repo {
    private static final String TAG="jagrati";
    private  static Repo instance;
    private MutableLiveData<List<NameModel>> data=new MutableLiveData<>();
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    List<NameModel> arrayList=new ArrayList<>();

    public static Repo getInstance(){
        if (instance==null)
        {
            instance=new Repo();
            //instance
        }

      return instance;
    }
    public MutableLiveData<List<NameModel>> getNamedata(){

      loadNameModel();
      data.setValue(arrayList);
       return data;
    }

    private void loadNameModel() {
        FirebaseFirestore db=FirebaseFirestore.getInstance();
       db.collection("posts")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
            if(!queryDocumentSnapshots.isEmpty()){
                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot documentSnapshot:list) {
                    arrayList.add(documentSnapshot.toObject(NameModel.class));
                }
            }
                Log.d("jag", String.valueOf(arrayList));
                Log.d("jag", String.valueOf(arrayList));

                }

        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for(DocumentSnapshot doc : task.getResult()){
                    Log.d("jag", doc.getId() + " => " + doc.getData());
                    //array.add(String.valueOf(doc.getData()));
                    //sort and fetch last two index
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG,"onFailure : " + e );
                ///////
            }
        });
    }
}


