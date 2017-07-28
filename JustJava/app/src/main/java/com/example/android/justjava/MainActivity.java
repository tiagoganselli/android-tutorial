package com.example.android.justjava;

import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static android.icu.text.NumberFormat.CURRENCYSTYLE;

public class MainActivity extends AppCompatActivity {

    /*********************************************************************************************
     * Variables
     *********************************************************************************************/

    private int numberOfCoffees = 2;

    /*********************************************************************************************
     * Main methods
     *********************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayPrice();
    }

    /*********************************************************************************************
     * Display related methods
     *********************************************************************************************/

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary(calculatePrice()));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice() {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(calculatePrice()));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /*********************************************************************************************
     * Calculation related methods
     *********************************************************************************************/

    public void increment(View view) {
        numberOfCoffees++;
        displayQuantity(numberOfCoffees);
        displayPrice();
    }

    public void decrement(View view) {
        if (numberOfCoffees > 0) {
            numberOfCoffees--;
            displayQuantity(numberOfCoffees);
            displayPrice();
        }
    }

    private int calculatePrice() {
        return numberOfCoffees * 5;
    }

    /**
     * Create order summary.
     *
     * @param price Order total price.
     * @return String to be displayed.
     */
    private String createOrderSummary(int price) {
        String priceMessage = "Name: Tiago" + "\n" +
                "Quantity: " + numberOfCoffees + "\n" +
                "Total: " + NumberFormat.getCurrencyInstance().format(price) + "\n" +
                "Thank You!";

        //Toast.makeText(this, "Order Summary created!", Toast.LENGTH_SHORT).show();

        return priceMessage;
    }

}
