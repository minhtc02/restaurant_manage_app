package com.example.restaurant_manager_app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restaurant_manager_app.Database.CartDAO;
import com.example.restaurant_manager_app.Fragment.DishFragment;
import com.example.restaurant_manager_app.Fragment.ViewDetailFragment;
import com.example.restaurant_manager_app.Interface.OnClickItemDish;
import com.example.restaurant_manager_app.Model.Admin;
import com.example.restaurant_manager_app.Model.Cart;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;
import com.example.restaurant_manager_app.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.restaurant_manager_app.sever.sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnClickItemDish{
    private static final int NOTIFICATION_ID = 1;
    private BottomNavigationView navigationView;
private ViewPager viewPager;
ImageView imgBell, imgCart;
OnClickItemDish onClickItemDish;
DishFragment dishFragment;
ViewDetailFragment viewDetailFragment;
    SimpleDateFormat sdf = new SimpleDateFormat("dd");
    private NotificationManagerCompat notificationManagerCompat;
    CartDAO dao;

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
        this.notificationManagerCompat = NotificationManagerCompat.from(this);
        String name = "Gà nướng muối ớt";
        dao = new CartDAO(this);
        if (dao.checkCart()<0){
            startServices(name);
        }


//        for (int i = 0; i < listThongBao.size(); i++) {
//            String thoiGian = listThongBao.get(i).getNgay();
//            String ten = listThongBao.get(i).getTenTruyen();
//            if (thoiGian.equalsIgnoreCase(getNow())) {

//            }
        //}

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
                //startNoti();
                //String ten = "ok";
                //startServices(ten);
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
//    public void startNoti(){
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
//                .setContentTitle("Đơn hàng mới")
//                .setContentText("Vừa được cập nhật")
//                .setSmallIcon(R.drawable.alerticon)
//                .setLargeIcon(bitmap)
//                .build();
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if (notificationManager !=null){
//            notificationManager.notify(getNotificationId(),notification);
//        }
//
//
//    }
//    private int getNotificationId(){
//        return (int) new Date().getTime();
//    }
    public void startServices(String ten) {
        String name = ten + " Thơm ngon mời bạn ăn nha";
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("name", name);
        startService(intent);
    }

    public void stopServices() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private String getNow() {
        return sdf.format(Calendar.getInstance().getTime());
    }

    public void replaceFragment() {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        ViewDetailFragment viewDetailFragment = new ViewDetailFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("obj_dish", dish);
//        viewDetailFragment.setArguments(bundle);
//        transaction.replace(R.id.flContent, viewDetailFragment)
//                .addToBackStack(null);
//        transaction.commit();
        //dishFragment.setOnClickItemDish(viewDetailFragment.getOnClickItemDish());
        viewPager.setCurrentItem(5);
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

    @Override
    public void onClickItem(Dish dish) {
        ViewDetailFragment fragBot = (ViewDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fg_vif);
        if (fragBot != null) { // kiểm tra Fragment cần truyền data đến có thực sự tồn tại và đang hiện.
            fragBot.setUp(dish);
        }else{
            Toast.makeText(this, "Khong tim thay, hoac fragment khong hien", Toast.LENGTH_SHORT).show();
        }
    }
}