package com.example.pizza_order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SummaryActivity extends AppCompatActivity {
    TextView summary;
    TextView usernameSummary;
    TextView summarybody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        summary = findViewById(R.id.summarytext);
        usernameSummary=findViewById(R.id.usernameSummary);
        summarybody = findViewById(R.id.summaryBody);
        Bundle bundle = getIntent().getExtras();
        String orderSummary = bundle.getString("summary");
        String username = "Dear "+bundle.getString("username")+",";
        String summaryBody = bundle.getString("summaryBody");
        summary.setText(orderSummary);
        usernameSummary.setText(username);
        summarybody.setText(summaryBody);
    }
}
