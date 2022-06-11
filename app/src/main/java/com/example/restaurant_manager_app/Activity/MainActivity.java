package com.example.restaurant_manager_app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restaurant_manager_app.Model.Admin;
import com.example.restaurant_manager_app.Model.Cart;
import com.example.restaurant_manager_app.R;
import com.example.restaurant_manager_app.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.restaurant_manager_app.sever.sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private BottomNavigationView navigationView;
private ViewPager viewPager;
ImageView imgBell, imgCart;

ArrayList<Admin> arrAdmin;
public static ArrayList<Cart> manggiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottom);
        viewPager = findViewById(R.id.view_pager);
        imgBell = findViewById(R.id.imgBell);
        imgCart = findViewById(R.id.imgCart);
        arrAdmin = new ArrayList<>();

        if(manggiohang!=null)
        {

        }
        else
        {
            manggiohang = new ArrayList<>();
        }

        getAdmin();
        setUpViewPager();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.datBan:
//                        Toast.makeText(MainActivity.this, "Đặt Bàn", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.datMon:
//                        Toast.makeText(MainActivity.this, "Đặt Món", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.Account:
//                        Toast.makeText(MainActivity.this, "Tôi", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
        imgBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
            }
        });
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(4);
            }
        });
    }
    private void setUpViewPager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
    }

    void getAdmin(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(sever.dataAdmin, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    String username = "";
                    String pass ="";
                    for(int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            username = jsonObject.getString("username");
                            pass = jsonObject.getString("password");

                            arrAdmin.add(new Admin(username, pass));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //3. thuc thi
        queue.add(jsonArrayRequest);
    }
}