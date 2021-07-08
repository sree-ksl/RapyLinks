package com.ksl.payrapyd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    TextInputLayout name;
    TextInputLayout price;
    AutoCompleteTextView country;
    AutoCompleteTextView currency;
    String pName;
    String pPrice;
    Button generateLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        name = findViewById(R.id.nameTextField);
        price = findViewById(R.id.priceTextField);
        country = findViewById(R.id.countrySelect);
        currency = findViewById(R.id.currencySelect);
        generateLink = findViewById(R.id.generate);

        pName = name.getEditText().getText().toString();
        pPrice = price.getEditText().getText().toString();


        initUI();
    }

    private void initUI(){

        ArrayList<String> countryList = getCountryList();
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(AddProduct.this, android.R.layout.simple_spinner_item, countryList);
        country.setAdapter(countryAdapter);

        ArrayList<String> currencyList = getCurrencyList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddProduct.this, android.R.layout.simple_spinner_item, currencyList);
        currency.setAdapter(adapter);

        generateLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO - Add product to product list
                Intent showProd = new Intent(AddProduct.this, ShowProducts.class);
                startActivity(showProd);
            }
        });

    }

    private ArrayList<String> getCountryList() {
        ArrayList<String> country = new ArrayList<>();
        country.add("US");
        country.add("Singapore");
        country.add("Canada");
        country.add("India");
        return country;
    }

    private ArrayList<String> getCurrencyList() {
        ArrayList<String> currency = new ArrayList<>();
        currency.add("USD");
        currency.add("SGD");
        currency.add("CAD");
        currency.add("RUPEE");
        return currency;
    }
}
