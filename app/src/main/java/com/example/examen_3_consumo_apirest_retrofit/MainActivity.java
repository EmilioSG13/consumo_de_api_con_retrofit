package com.example.examen_3_consumo_apirest_retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ProgressBar progressBar;
    private TextView tvError;
    private Button btnFetch;
    private List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        tvError = findViewById(R.id.tvError);
        btnFetch = findViewById(R.id.btnFetch);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(this, productList, product -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("PRODUCT_ITEM", product);
            startActivity(intent);
        });
        recyclerView.setAdapter(productAdapter);

        btnFetch.setOnClickListener(v -> fetchProducts());

        // Automatically fetch products on launch
        fetchProducts();
    }

    private void fetchProducts() {
        progressBar.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

        ApiService apiService = ApiClient.getApiService();
        Call<ProductResponse> call = apiService.getProducts();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    productList = response.body().getProducts();
                    productAdapter.updateData(productList);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    showError("Error en la respuesta del servidor: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                showError("Error de conexión: " + t.getLocalizedMessage());
            }
        });
    }

    private void showError(String message) {
        tvError.setText(message);
        tvError.setVisibility(View.VISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
