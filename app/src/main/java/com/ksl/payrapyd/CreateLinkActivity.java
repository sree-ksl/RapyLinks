package com.ksl.payrapyd;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class CreateLinkActivity extends AppCompatActivity {

    TextInputLayout amount;
    AutoCompleteTextView country;
    AutoCompleteTextView currency;
    Button generateLink;
    String amountVal;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_link);

        country = findViewById(R.id.countrySelect);
        ArrayList<String> countryList = getCountryList();
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(CreateLinkActivity.this, android.R.layout.simple_spinner_item, countryList);
        country.setAdapter(countryAdapter);

        currency = findViewById(R.id.currencySelect);
        ArrayList<String> currencyList = getCurrencyList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateLinkActivity.this, android.R.layout.simple_spinner_item, currencyList);
        currency.setAdapter(adapter);

        amount = findViewById(R.id.priceTextField);
        amountVal = amount.getEditText().getText().toString();

        generateLink = findViewById(R.id.generateLink);
        generateLink.setOnClickListener(view -> {

            //Share option
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            //TODO - add checkout link to be shared
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://sandboxcheckout.rapyd.net/?token=checkout_4675ab3d9f1c55af92c510f8839cc77f");
            Intent.createChooser(sendIntent, "Share link using");
            startActivity(sendIntent);
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

//    private void checkout() {
//        RetrofitClient.getRapydClient().getCheckoutPage(accessKey, signature, salt, Long.toString(timestamp), checkoutBody).enqueue(new Callback<CheckoutBody>() {
//            @Override
//            public void onResponse(Call<CheckoutBody> call, Response<CheckoutBody> response) {
//                Log.e(TAG, "Response: " + response.body());
//                if(response.isSuccessful() && response.body() != null){
//                    Toast.makeText(CreateLinkActivity.this, "Checkout Success", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CheckoutBody> call, Throwable t) {
//                Toast.makeText(CreateLinkActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
