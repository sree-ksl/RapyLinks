package com.ksl.payrapyd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WalletInfo extends AppCompatActivity {

    Button createWallet;
    Button done;
    private TextView firstName;
    private TextView lastName;
    private TextView contactNum;
    private TextView email;

    String uFirstName;
    String uLastName;
    String uPhone;
    String uEmail;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_info);

        createWallet = findViewById(R.id.createWallet);
        done = findViewById(R.id.done);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        contactNum = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        Intent walletInfo = getIntent();
        uFirstName = walletInfo.getStringExtra("uFirstname");


        firstName.setText(""+RDPUser.getFirstName());
        lastName.setText(""+RDPUser.getLastName());
        contactNum.setText(""+RDPUser.getContactNum());
        email.setText(""+RDPUser.getContactEmail());

        createWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create = new Intent(WalletInfo.this, CreateWalletActivity.class);
                startActivity(create);
            }
        });


        //go back to home activity
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(WalletInfo.this, HomeActivity.class);
                startActivity(home);
            }
        });
    }
}
