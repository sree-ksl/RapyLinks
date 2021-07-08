package com.ksl.payrapyd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ShowProducts extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ProductsAdapter adapter;
    ArrayList<ProductItem> productItems = new ArrayList<>();
    private Context context;

    private ArrayList<ProductItem> initProducts(){
//        ArrayList<ProductItem> productItems = new ArrayList<>();
        productItems.add(new ProductItem("iPhone", "$999"));
        productItems.add(new ProductItem("LG 24inch monitor", "$749"));
        productItems.add(new ProductItem("Bath&Body Works", "$65"));
        productItems.add(new ProductItem("Desk Organizer", "$9"));
        productItems.add(new ProductItem("Pink Lily", "$18"));
        productItems.add(new ProductItem("Sea Fresh- Scented candle", "35"));
        productItems.add(new ProductItem("Lightening cable", "$5"));
        productItems.add(new ProductItem("Star Wars toy", "$99"));
        productItems.add(new ProductItem("Standing Desk", "$499"));
        productItems.add(new ProductItem("Tempered Glass", "$59"));
        return productItems;
    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_list);

        ArrayList<ProductItem> productItems = initProducts();

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProductsAdapter(productItems);
        recyclerView.setAdapter(adapter);

        try {
            Thread.sleep(3000);
            progressBar.setVisibility(View.VISIBLE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        progressBar.setVisibility(View.GONE);

        FloatingActionButton addFab = findViewById(R.id.add_fab);
        addFab.setOnClickListener(view -> {
            Intent addProd = new Intent(ShowProducts.this, AddProduct.class);
            startActivity(addProd);
        });

    }

}
