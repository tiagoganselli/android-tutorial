package com.example.android.justjava;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static android.icu.text.NumberFormat.CURRENCYSTYLE;

public class MainActivity extends AppCompatActivity {

    /*********************************************************************************************
     * Variables
     *********************************************************************************************/

    private int numberOfCoffees = 2;
    private boolean hasCream = false;
    private boolean hasChocolate = false;

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
        Log.v("MainActivity", "Submitting order...");

        EditText name = (EditText) findViewById(R.id.name_view);

        if (TextUtils.isEmpty(name.getText())) {
            name.setError(getString(R.string.name_required));
            return;
        }

        //displayMessage(createOrderSummary(calculatePrice()));

        composeEmail(getString(R.string.mail_subject_prepend) + name.getText(),
                createOrderSummary(name.getText().toString(),
                        (hasCream ? getString(R.string.yes) : getString(R.string.no)),
                        (hasChocolate ? getString(R.string.yes) : getString(R.string.no)),
                        calculatePrice()));
    }

    public void composeEmail(String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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
        if (numberOfCoffees < 100) {
            numberOfCoffees++;
            displayQuantity(numberOfCoffees);
            displayPrice();
        }
    }

    public void decrement(View view) {
        if (numberOfCoffees > 0) {
            numberOfCoffees--;
            displayQuantity(numberOfCoffees);
            displayPrice();
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkboxCream:
                if (checked) {
                    hasCream = true;
                }
                else {
                    hasCream = false;
                }
                break;
            case R.id.checkboxChocolate:
                if (checked) {
                    hasChocolate = true;
                }
                else {
                    hasChocolate = false;
                }
                break;
            default:
                break;
        }
    }

    private int calculatePrice() {
        int basePrice = 5;
        if (hasCream) {
            basePrice += 1;
        }
        if (hasChocolate) {
            basePrice += 2;
        }
        return numberOfCoffees * basePrice;
    }

    /**
     * Create order summary.
     *
     * @param price Order total price.
     * @return String to be displayed.
     */
    private String createOrderSummary(String name, String addCream, String addChocolate, int price) {
        String priceMessage = getString(R.string.order_name) + name;
        priceMessage += "\n" + getString(R.string.order_add_cream) + addCream;
        priceMessage += "\n" + getString(R.string.order_add_chocolate) + addChocolate;
        priceMessage += "\n" + getString(R.string.order_quantity) + numberOfCoffees;
        priceMessage += "\n" + getString(R.string.order_total) + NumberFormat.getCurrencyInstance().format(price);
        priceMessage += "\n" + getString(R.string.order_thank_you);

        //Toast.makeText(this, "Order Summary created!", Toast.LENGTH_SHORT).show();

        return priceMessage;
    }

}
