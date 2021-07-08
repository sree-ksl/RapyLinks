package com.ksl.payrapyd;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TransactionAdapter adapter;
    ArrayList<Transaction> transactionItem = new ArrayList<>();
    private Context context;

    private ArrayList<Transaction> initTransactions(){
        transactionItem.add(new Transaction("$100"));
        transactionItem.add(new Transaction("$30"));
        transactionItem.add(new Transaction("$990"));
        transactionItem.add(new Transaction("$650"));
        transactionItem.add(new Transaction("$230"));
        transactionItem.add(new Transaction("$4"));
        transactionItem.add(new Transaction("$500"));
        transactionItem.add(new Transaction("$1000"));
        transactionItem.add(new Transaction("$999"));
        transactionItem.add(new Transaction("$86"));
        transactionItem.add(new Transaction("$39"));
        transactionItem.add(new Transaction("$42"));
        transactionItem.add(new Transaction("$19"));

        return transactionItem;
    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_history);

        ArrayList<Transaction> transaction = initTransactions();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.transactionRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TransactionAdapter(transaction);
        recyclerView.setAdapter(adapter);




        try {
            Thread.sleep(3000);
            progressBar.setVisibility(View.VISIBLE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        progressBar.setVisibility(View.GONE);

//        progressBar = findViewById(R.id.progressBar);



    }
}
