package com.example.planetze;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
        TextView tagline = findViewById(R.id.catchphrase);
        String text = "Empowering You to Live \n Sustainably Everyday";
        SpannableString spantext1 = new SpannableString(text);
        spantext1.setSpan(new ForegroundColorSpan(Color.parseColor("#009999")), 25, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spantext1.setSpan(new RelativeSizeSpan(1.35f), 25, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tagline.setShadowLayer(5.0f, 0, 0, Color.parseColor("#d0d9d2"));
        tagline.setText(spantext1);
        TextView description = findViewById(R.id.description);
        String descript = "Sustainability isn’t just a buzzword!\nIt’s a MOVEMENT, and it starts with you!";
        SpannableString spantext2 = new SpannableString(descript);
        spantext2.setSpan(new ForegroundColorSpan(Color.parseColor("#009999")), 0, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spantext2.setSpan(new ForegroundColorSpan(Color.parseColor("#009999")), 55, 78, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        description.setText(spantext2);
    }
}