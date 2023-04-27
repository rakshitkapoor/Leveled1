package com.example.expense_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.expense_manager.databinding.ActivityAddExpenseBinding;
import com.example.expense_manager.databinding.ActivityMainExpenseBinding;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class activity_expense extends AppCompatActivity implements OnItemsClick
{
    ActivityMainExpenseBinding binding;
    private ExpensesAdapter expensesAdapter;
    Intent intent;
    private long income=0, expense=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainExpenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        expensesAdapter=new ExpensesAdapter(this, this);
        binding.recylcer.setAdapter(expensesAdapter);
        binding.recylcer.setLayoutManager(new LinearLayoutManager(this));

        intent = new Intent(activity_expense.this, AddExpenseActivity.class);

        binding.addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("type", "Income");
                startActivity(intent);
            }
        });
        binding.addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("type", "Expense");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please");
        progressDialog.setMessage("Wait");
        progressDialog.setCancelable(false);

        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            progressDialog.show();
            FirebaseAuth.getInstance()
                    .signInAnonymously()
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            progressDialog.cancel();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.cancel();
                            Toast.makeText(activity_expense.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        }

    @Override
    protected void onResume() {
        super.onResume();
        income=0;expense=0;
        getData();
    }

    private void getData() {
        FirebaseFirestore
                .getInstance()
                .collection("expenses")
                .whereEqualTo("uid",FirebaseAuth.getInstance().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        expensesAdapter.clear();
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot ds:dsList){
                            ExpenseModel expenseModel = ds.toObject(ExpenseModel.class);
                            if (expenseModel.getType().equals("Income")){
                                income+=expenseModel.getAmount();
                            }
                            else{
                                expense+= expenseModel.getAmount();
                            }
                            expensesAdapter.add(expenseModel);
                        }
                        setUpGraph();
                    }
                });
    }

    private void setUpGraph() {
        List<PieEntry> pieEntryList = new ArrayList<>();
        List<Integer> colorList = new ArrayList<>();
        if (income != 0) {
            pieEntryList.add(new PieEntry(income, "Income"));
            colorList.add(ContextCompat.getColor(this, R.color.teal_200));
        }

        if (expense != 0) {
            pieEntryList.add(new PieEntry(expense, "Expense"));
            colorList.add(ContextCompat.getColor(this, R.color.purple_200));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntryList, String.valueOf(income - expense));
        pieDataSet.setColors(colorList);
        PieData pieData = new PieData(pieDataSet);

        binding.pieChart.setDrawCenterText(true);
        binding.pieChart.setData(pieData);
        binding.pieChart.invalidate();
    }




    @Override
    public void onClick(ExpenseModel expenseModel) {
        intent.putExtra("model",expenseModel);
        startActivity(intent);
    }
}
