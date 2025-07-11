package com.karon.myapimainapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.karon.myapimainapp.views.QuoteActivity;
import com.karon.myapimainapp.views.RegisterActivity;
import com.karon.myapimainapp.views.SettingsActivity;
import com.karon.myapimainapp.views.ViewProducts;
import com.karon.myapimainapp.views.category.AddCategory;
import com.karon.myapimainapp.views.category.ViewCategory;
import com.karon.myapimainapp.views.category.ViewCategoryRec;
import com.karon.myapimainapp.views.subcategory.AddSubCategory;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
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
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_quote) {
                Intent intent = new Intent(MainActivity.this, QuoteActivity.class);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.nav_register) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.nav_settings) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.nav_product) {
                Intent intent = new Intent(MainActivity.this, ViewProducts.class);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.nav_add_category) {
                Intent intent = new Intent(MainActivity.this, AddCategory.class);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.nav_view_category) {
                Intent intent = new Intent(MainActivity.this, ViewCategory.class);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.nav_add_subcategory) {
                Intent intent = new Intent(MainActivity.this, AddSubCategory.class);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.nav_view_sub_cat_rec) {
                Intent intent = new Intent(MainActivity.this, ViewCategoryRec.class);
                startActivity(intent);
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
}