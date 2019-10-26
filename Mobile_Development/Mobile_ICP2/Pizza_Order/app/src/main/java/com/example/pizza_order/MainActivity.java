package com.example.pizza_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    double quantity = 1;
    final double pizzaPrice = 5.0;
    final double cheesePrice = 0.5;
    final double sausagePrice = 1.1;
    final double chickenPrice = 0.9;
    final double baconPrice = 1.0;
    Button order;
    Button summary;
    EditText userinput;
    String orderSummary = "Order Summary";
    String orderSummaryMessage;
    CheckBox Cheese;
    CheckBox Sausage;
    CheckBox Chicken;
    CheckBox Bacon;
    boolean hasCheese;
    boolean hasSausage;
    boolean hasChicken;
    boolean hasBacon;
    double totalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        order = (Button)findViewById(R.id.order);
        summary = findViewById(R.id.summary);
        userinput = findViewById(R.id.username);


        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cheese = findViewById(R.id.topping1);
                hasCheese = Cheese.isChecked();
                Sausage = findViewById(R.id.topping2);
                hasSausage = Sausage.isChecked();
                Chicken = findViewById(R.id.topping3);
                hasChicken = Chicken.isChecked();
                Bacon = findViewById(R.id.topping4);
                hasBacon = Bacon.isChecked();
                totalPrice = calculatePrice(hasCheese, hasSausage, hasChicken, hasBacon);
                orderSummaryMessage = createOrderSummary(hasCheese, hasSausage, hasChicken,hasBacon, totalPrice);
                Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
                intent.putExtra("summary", orderSummary);
                intent.putExtra("username", userinput.getText().toString());
                intent.putExtra("summaryBody", orderSummaryMessage);
                startActivity(intent);
            }
        });
        order.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Cheese = findViewById(R.id.topping1);
                hasCheese = Cheese.isChecked();
                Sausage = findViewById(R.id.topping2);
                hasSausage = Sausage.isChecked();
                Chicken = findViewById(R.id.topping3);
                hasChicken = Chicken.isChecked();
                Bacon = findViewById(R.id.topping4);
                hasBacon = Bacon.isChecked();
                totalPrice = calculatePrice(hasCheese, hasSausage, hasChicken, hasBacon);
                orderSummaryMessage = createOrderSummary(hasCheese, hasSausage, hasChicken,hasBacon, totalPrice);

                sendEmail(userinput.getText().toString(),orderSummaryMessage);
            }
        });

    }
    public void sendEmail(String userinput,String message){
        Intent emailintent = new Intent(Intent.ACTION_SEND);
        emailintent.putExtra(Intent.EXTRA_SUBJECT, userinput);
        emailintent.putExtra(Intent.EXTRA_EMAIL, new String[]{userinput+"@gmail.com"});
        emailintent.putExtra(Intent.EXTRA_TEXT, message);
        emailintent.setType("text/plain");
        if(emailintent.resolveActivity(getPackageManager())!= null){
            startActivity(emailintent);
        }


    }

    private double calculatePrice(boolean hasCheese, boolean hasSausage, boolean hasChicken, boolean hasBacon){
        double basePrice = pizzaPrice;
        if(hasCheese){
            basePrice += cheesePrice;
        }
        if(hasSausage){
            basePrice += sausagePrice;
        }
        if(hasChicken){
            basePrice += chickenPrice;
        }
        if(hasBacon){
            basePrice += baconPrice;
        }
        System.out.println(quantity*basePrice);
        return quantity * basePrice;

    }
    private String boolToString(boolean bool){
        if(bool){
            return getString(R.string.yes);
        }
        else{
            return getString(R.string.no);
        }

    }


    private String createOrderSummary(boolean hasCheese, boolean hasSausage, boolean hasChicken, boolean hasBacon, double price){
        String Price = Double.toString(price);
        String orderSummaryMessage = "Your toppings are: "+"\n"+"Cheese: "+boolToString(hasCheese)+"\n"+"Sausage: "+boolToString(hasSausage)+"\n"+
                "Chicken: "+boolToString(hasChicken)+"\n"+ "Bacon: "+boolToString(hasBacon)+ "\n" +"Your total price is: "+ Price;

        return orderSummaryMessage;

    }
    private void display(double number){
        TextView quantity = (TextView) findViewById(R.id.increment);
        quantity.setText("" + number);
    }
    public void increment(View view){
        if(quantity < 100){
            quantity++;
            display(quantity);
        }else{

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "Please select less coffee, addiction is bad", duration);
            toast.show();
            return;

        }
    }
    public void decrement(View view){
        if(quantity > 1){
            quantity--;
            display(quantity);
        }else{

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "Please select at least 1 cup of coffee", duration);
            toast.show();
            return;


        }
    }
}
