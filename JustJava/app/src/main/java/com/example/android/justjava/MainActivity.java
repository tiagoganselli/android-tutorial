package com.example.android.justjava;

import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import static android.icu.text.NumberFormat.CURRENCYSTYLE;

public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayPrice();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage =
                "Total: " + NumberFormat.getCurrencyInstance().format(numberOfCoffees * 5) +
                        "\nThank you!";
        displayMessage(priceMessage);
    }

    public void increment(View view) {
        numberOfCoffees++;
        display(numberOfCoffees);
        displayPrice();
    }

    public void decrement(View view) {
        if (numberOfCoffees > 0) {
            numberOfCoffees--;
            display(numberOfCoffees);
            displayPrice();
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice() {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(numberOfCoffees * 5));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}
