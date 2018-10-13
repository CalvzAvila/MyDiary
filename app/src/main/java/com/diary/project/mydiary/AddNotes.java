package com.diary.project.mydiary;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.diary.project.mydiary.model.AddNoteModel;
import com.diary.project.mydiary.model.RegisterModel;
import com.diary.project.mydiary.service.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNotes extends AppCompatActivity {

    private EditText title, notes;
    private static final String TAG = "AddNotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        title  = findViewById(R.id.etTitle);
        notes  =findViewById(R.id.etNotes);

        FloatingActionButton fab = findViewById(R.id.saveButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
               validate();
               addNotes();
               clearText();
            }
        });
    }

    private void clearText()
    {
        title.setText("");
        notes.setText("");
    }


    private boolean validate() {

        String vTitle = title.getText().toString();
        String vNotes = notes.getText().toString();

        if (vTitle.length() == 0 ) {
            title.setError("please in put a title");
            return false;
        } else if(vNotes.length()==0){
            notes.setError("Please input a content");
            return false;
        }
        else{
            return true;
        }
    }

  private void addNotes()
    {
        String etTitle = title.getText().toString();
        String etNotes = notes.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAPI api = retrofit.create(ServiceAPI.class);
        Call<AddNoteModel> callback = api.isAddNotes(etTitle,etNotes);
        callback.enqueue(new Callback<AddNoteModel>() {
            @Override
            public void onResponse(Call<AddNoteModel> call, retrofit2.Response<AddNoteModel> response) {
                Log.d(TAG, "onResponse: "+response.body());
                if (response.body().getSuccess() == 1){
                    Toast.makeText(AddNotes.this, "Saved", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddNotes.this, "Not Saved", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddNoteModel> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

}
