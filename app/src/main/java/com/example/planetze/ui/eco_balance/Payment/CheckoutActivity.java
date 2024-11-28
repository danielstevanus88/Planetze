package com.example.planetze.ui.eco_balance.Payment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.planetze.R;
import com.example.planetze.classes.EcoBalance.PaymentUtilities.Constants;
import com.example.planetze.classes.EcoBalance.PaymentUtilities.PaymentUtilities;
import com.example.planetze.databinding.ActivityCheckoutBinding;
import com.google.android.gms.wallet.button.ButtonOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeUi() {

        // Use view binding to access the UI elements
        ActivityCheckoutBinding layoutBinding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(layoutBinding.getRoot());

        // The Google Pay button is a layout file – take the root view
        googlePayButton = layoutBinding.googlePayButton;
        try {
            googlePayButton.initialize(
                    ButtonOptions.newBuilder()
                            .setAllowedPaymentMethods(PaymentsUtil.getAllowedPaymentMethods().toString()).build()
            );
            googlePayButton.setOnClickListener(this::requestPayment);
        } catch (JSONException e) {
            // Keep Google Pay button hidden (consider logging this to your app analytics service)
        }
    }

    private static JSONObject getTransactionInfo(String price) throws JSONException {
        return new JSONObject()
                .put("totalPrice", price)
                .put("totalPriceStatus", "FINAL")
                .put("countryCode", Constants.COUNTRY_CODE)
                .put("currencyCode", Constants.CURRENCY_CODE)
                .put("checkoutOption", "COMPLETE_IMMEDIATE_PURCHASE");
    }

    private static JSONObject getMerchantInfo() throws JSONException {
        return new JSONObject().put("Planetze", "Planetze");
    }



}