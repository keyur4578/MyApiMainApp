package com.karon.myapimainapp.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.karon.myapimainapp.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class OnlinePaymentExample extends AppCompatActivity implements PaymentResultListener {

    Button payBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_online_payment_example);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        payBtn = (Button) findViewById(R.id.payBtn);
        payBtn.setOnClickListener(view -> startPayment());
    }
    private void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_aro7DmNCYha043");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Karon Infotech");
            options.put("description", "Training Fees");
            options.put("currency", "INR");
            options.put("amount", "10000"); // amount in paise (100 = ₹1)
            options.put("prefill.email", "student@email.com");
            options.put("prefill.contact", "9876543210");

            checkout.open(this, options);
        } catch(Exception e) {
            Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPaymentSuccess(String paymentid) {
        Toast.makeText(this, "Payment Success : "+paymentid, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Fail", Toast.LENGTH_SHORT).show();
    }
    //Success
    //Fail
}