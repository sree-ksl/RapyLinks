package com.ksl.payrapyd;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.rapydsdk.ppackages.RPDSdk;
import com.rapydsdk.ppackages.RPDUser;

public class CreateWalletActivity extends AppCompatActivity {

    private Button createWallet;
    private TextInputLayout uFirstName;
    private TextInputLayout uLastName;
    private TextInputLayout uPhone;
    private TextInputLayout uEmail;

    String uFirstname;
    String uLastname;
    String uPhoneNum;
    String uEmailId;

    public static Resources resources;
    ProgressBar progressBar;
    String accessKey;
    String secretKey;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_wallet);
        resources = getResources();

        accessKey = getString(R.string.access_key);
        secretKey = getString(R.string.secret_key);

        RPDSdk.setup(getApplicationContext(), accessKey, secretKey);
        RPDSdk.setDebugMode(true);

        createWallet = findViewById(R.id.createWalletBtn);

        uFirstName = findViewById(R.id.uFirstName);
        uFirstname = uFirstName.getEditText().getText().toString().trim();

        uLastName = findViewById(R.id.uLastName);
        uLastname = uLastName.getEditText().getText().toString().trim();

        uPhone = findViewById(R.id.uContact);
        uPhoneNum = uPhone.getEditText().getText().toString().trim();

        uEmail = findViewById(R.id.uEmail);
        uEmailId = uEmail.getEditText().getText().toString().trim();


        //create user with new Wallet
        createWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RPDUser newUser = new RPDUser();
                newUser.setFirstName(uFirstname);
                newUser.setLastName(uLastname);
                newUser.setPhoneNumber(uPhoneNum);
                newUser.setEmail(uEmailId);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(CreateWalletActivity.this, "Wallet created successfully!", Toast.LENGTH_SHORT).show();

                //send info to walletInfo to display
                Intent walletInfo = new Intent(CreateWalletActivity.this, WalletInfo.class);
                walletInfo.putExtra("uFirstname", uFirstname);
                walletInfo.putExtra("uLastname", uLastname);
                walletInfo.putExtra("uPhoneNum", uPhoneNum);
                walletInfo.putExtra("uEmailId", uEmailId);
                startActivity(walletInfo);
            }
        });

    }
}
