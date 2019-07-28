package com.candy.lovewallpaper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.candy.lovewallpaper.activity.SettingActivity;
import com.candy.lovewallpaper.fragment.AlbumFragment;
import com.candy.lovewallpaper.fragment.WallpaperFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawer;

    private WallpaperFragment menuFragment01;
    private WallpaperFragment menuFragment02;
    private WallpaperFragment menuFragment03;
    private WallpaperFragment menuFragment04;
    private WallpaperFragment menuFragment05;
    private WallpaperFragment menuFragment06;
    private WallpaperFragment menuFragment07;

    private AlbumFragment menuFragment08;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @SuppressLint("NewApi")
    private void initView() {


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //显示原本的色彩
        navigationView.setItemIconTintList(null);
        if (menuFragment01 == null)
            menuFragment01 = new WallpaperFragment();
        changeMenu(R.string.menu_01, menuFragment01);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_01:
                if (menuFragment01 == null)
                    menuFragment01 = new WallpaperFragment();
                changeMenu(R.string.menu_01, menuFragment01);
                break;
            case R.id.menu_02:
                if (menuFragment02 == null)
                    menuFragment02 = new WallpaperFragment();
                changeMenu(R.string.menu_01, menuFragment02);
                break;
            case R.id.menu_03:
                if (menuFragment03 == null)
                    menuFragment03 = new WallpaperFragment();
                changeMenu(R.string.menu_01, menuFragment03);
                break;
            case R.id.menu_04:
                if (menuFragment04 == null)
                    menuFragment04 = new WallpaperFragment();
                changeMenu(R.string.menu_04, menuFragment04);
                break;
            case R.id.menu_05:
                if (menuFragment05 == null)
                    menuFragment05 = new WallpaperFragment();
                changeMenu(R.string.menu_05, menuFragment05);
                break;
            case R.id.menu_06:
                if (menuFragment06 == null)
                    menuFragment06 = new WallpaperFragment();
                changeMenu(R.string.menu_06, menuFragment06);
                break;
            case R.id.menu_07:
                if (menuFragment07 == null)
                    menuFragment07 = new WallpaperFragment();
                changeMenu(R.string.menu_07, menuFragment07);
                break;
            case R.id.menu_08:
                if (menuFragment08 == null)
                    menuFragment08 = new AlbumFragment();
                changeMenu(R.string.menu_08, menuFragment08);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    private void changeMenu(int id, Fragment fragment) {
        getSupportActionBar().setTitle(getString(id));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded() == false)
            transaction.add(R.id.main_frame_layout, fragment);
        hideFragment(transaction);
        transaction.show(fragment);
        transaction.commit();
    }


    private void hideFragment(FragmentTransaction transaction) {
        if (menuFragment01 != null) {
            transaction.hide(menuFragment01);
        }
        if (menuFragment02 != null) {
            transaction.hide(menuFragment02);
        }
        if (menuFragment03 != null) {
            transaction.hide(menuFragment03);
        }
        if (menuFragment04 != null) {
            transaction.hide(menuFragment04);
        }

        if (menuFragment05 != null) {
            transaction.hide(menuFragment05);
        }
        if (menuFragment06 != null) {
            transaction.hide(menuFragment06);
        }
        if (menuFragment07 != null) {
            transaction.hide(menuFragment07);
        }
        if (menuFragment08 != null) {
            transaction.hide(menuFragment08);
        }
    }

    //点击事件
    @Override
    public void onClick(View view) {

    }
}
