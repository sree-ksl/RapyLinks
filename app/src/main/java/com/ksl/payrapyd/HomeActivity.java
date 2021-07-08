package com.ksl.payrapyd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class HomeActivity extends AppCompatActivity {

    Button createWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createWallet = findViewById(R.id.createWallet);

        MaterialCardView createLink = findViewById(R.id.createLinkCard);
        MaterialCardView transactions = findViewById(R.id.transactionsCard);
        MaterialCardView products = findViewById(R.id.prodsCard);

        createLink.setOnClickListener(view -> {
            Intent createLinkIntent = new Intent(HomeActivity.this, CreateLinkActivity.class);
            startActivity(createLinkIntent);
        });

        transactions.setOnClickListener(view -> {
            Intent transactionHistory = new Intent(HomeActivity.this, TransactionsActivity.class);
            startActivity(transactionHistory);
        });

        products.setOnClickListener(view -> {
            Intent showProducts = new Intent(HomeActivity.this, ShowProducts.class);
            startActivity(showProducts);

        });

        createWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create = new Intent(HomeActivity.this, WalletInfo.class);
                startActivity(create);
            }
        });
    }
}
