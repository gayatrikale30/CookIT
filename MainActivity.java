package com.example.cookit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cookit.Adapters.RandomRecipeAdapter;
import com.example.cookit.Listener.RandomRecipeResponseListener;
import com.example.cookit.Models.RandomRecipeApiResponse;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;
    @Deprecated(forRemoval=true)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading....");
        manager=new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);
        dialog.show();


    }
    private final RandomRecipeResponseListener randomRecipeResponseListener=new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
        recyclerView=findViewById(R.id.recycler_random);
        recyclerView.setHasFixedSize(true);
        randomRecipeAdapter=new RandomRecipeAdapter(MainActivity.this, response.recipes);
        recyclerView.setAdapter(randomRecipeAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));

        //randomRecipeAdapter=new RandomRecipeAdapter(MainActivity.this, response.recipes);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}