package com.example.examen_3_consumo_apirest_retrofit;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView ivDetailProduct = findViewById(R.id.ivDetailProduct);
        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDetailPrice = findViewById(R.id.tvDetailPrice);
        TextView tvDetailRating = findViewById(R.id.tvDetailRating);
        TextView tvDetailCategory = findViewById(R.id.tvDetailCategory);
        TextView tvDetailBrand = findViewById(R.id.tvDetailBrand);
        TextView tvDetailDescription = findViewById(R.id.tvDetailDescription);

        Product product = (Product) getIntent().getSerializableExtra("PRODUCT_ITEM");

        if (product != null) {
            tvDetailTitle.setText(product.getTitle());
            tvDetailPrice.setText("$" + product.getPrice());
            tvDetailRating.setText("⭐ " + product.getRating());
            tvDetailCategory.setText("Categoría: " + product.getCategory());
            tvDetailBrand.setText("Marca: " + (product.getBrand() != null ? product.getBrand() : "N/A"));
            tvDetailDescription.setText(product.getDescription());

            Glide.with(this)
                    .load(product.getThumbnail())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivDetailProduct);
        }
    }
}
