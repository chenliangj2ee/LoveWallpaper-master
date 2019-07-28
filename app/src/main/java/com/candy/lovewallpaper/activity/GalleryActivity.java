package com.candy.lovewallpaper.activity;
/*
 *  描述：    画廊预览
 */

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.candy.lovewallpaper.R;
import com.candy.lovewallpaper.entity.Constants;
import com.candy.lovewallpaper.utils.ScreenUtils;
import com.candy.lovewallpaper.view.CustomDialog;
import com.stx.xhb.xbanner.XBanner;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity implements View.OnClickListener {

    private int position;
    private ArrayList<String> images;
    private ImageView iv_back;
    private Button btn_set_wallpaper;

    private WallpaperManager wpManager;
    private CustomDialog dialog_setwallpaper;
    private Button btn_lock;
    private Button btn_desktop;
    private Button btn_all;

    private LinearLayout ll_bottom_bar;
    private PopupWindow popWnd;
    private View contentView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        initView();
        initMenuWindow();
    }
    XBanner banner;
    private void initView() {
        banner = (XBanner) findViewById(R.id.banner);
        dialog_setwallpaper = new CustomDialog(this, 0, 0,
                R.layout.dialog_set_wallpaper, R.style.Theme_dialog, Gravity.BOTTOM, R.style.pop_anim_style);
        btn_lock = (Button) dialog_setwallpaper.findViewById(R.id.btn_lock);
        btn_lock.setOnClickListener(this);
        btn_desktop = (Button) dialog_setwallpaper.findViewById(R.id.btn_desktop);
        btn_desktop.setOnClickListener(this);
        btn_all = (Button) dialog_setwallpaper.findViewById(R.id.btn_all);
        btn_all.setOnClickListener(this);


        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        btn_set_wallpaper = (Button) findViewById(R.id.btn_set_wallpaper);
        btn_set_wallpaper.setOnClickListener(this);
        ll_bottom_bar = (LinearLayout) findViewById(R.id.ll_bottom_bar);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        images = intent.getStringArrayListExtra("bigUrl");

        //壁纸管理器
        wpManager = WallpaperManager.getInstance(this);



        // 为XBanner绑定数据
        banner.setData(images, null);
        banner.getViewPager().setCurrentItem(position);
        // XBanner适配数据
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getApplicationContext()).load(images.get(position)).into((ImageView) view);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_set_wallpaper:
                dialog_setwallpaper.show();
                break;
            case R.id.btn_lock:
                setLockScreenWallpaper();
                dialog_setwallpaper.dismiss();
                break;
            case R.id.btn_desktop:
                setDesktopWallpaper();
                dialog_setwallpaper.dismiss();
                break;
            case R.id.btn_all:
                setAllWallpaper();
                dialog_setwallpaper.dismiss();
                break;
            case R.id.tv_game:
                startActivity(new Intent(this,PuzzleGameActivity.class));
                break;
        }
    }

    //显示菜单window
    private void initMenuWindow() {
        contentView = LayoutInflater.from(this).inflate(R.layout.pop_item_layout, null);
        popWnd = new PopupWindow(this);
        popWnd.setContentView(contentView);
        popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setOutsideTouchable(true);
        popWnd.setBackgroundDrawable(new BitmapDrawable());
    }

    //设置桌面壁纸
    private void setDesktopWallpaper() {
        Glide.with(this).load(images.get(banner.getViewPager().getCurrentItem())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                try {
                    wpManager.setBitmap(resource);
                    Toast.makeText(GalleryActivity.this, "桌面壁纸设置成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(GalleryActivity.this, "桌面壁纸设置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //设置锁屏壁纸
    private void setLockScreenWallpaper() {
        Glide.with(this).load(images.get(banner.getViewPager().getCurrentItem())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                try {
                    //获取类名
                    Class class1 = wpManager.getClass();
                    //获取设置锁屏壁纸的函数
                    Method setWallPaperMethod = class1.getMethod("setBitmapToLockWallpaper", Bitmap.class);
                    //调用锁屏壁纸的函数，并指定壁纸的路径imageFilesPath
                    setWallPaperMethod.invoke(wpManager, resource);
                    Toast.makeText(GalleryActivity.this, "锁屏壁纸设置成功", Toast.LENGTH_SHORT).show();
                } catch (Throwable e) {
                    Toast.makeText(GalleryActivity.this, "锁屏壁纸设置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //设置所有壁纸
    private void setAllWallpaper() {
        Glide.with(this).load(images.get(banner.getViewPager().getCurrentItem())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                try {
                    //获取类名
                    Class class1 = wpManager.getClass();
                    //获取设置锁屏壁纸的函数
                    Method setWallPaperMethod = class1.getMethod("setBitmapToLockWallpaper", Bitmap.class);
                    //调用锁屏壁纸的函数，并指定壁纸的路径imageFilesPath
                    setWallPaperMethod.invoke(wpManager, resource);
                    Toast.makeText(GalleryActivity.this, "锁屏壁纸设置成功", Toast.LENGTH_SHORT).show();
                } catch (Throwable e) {
                    Toast.makeText(GalleryActivity.this, "锁屏壁纸设置失败", Toast.LENGTH_SHORT).show();
                }
                try {
                    wpManager.setBitmap(resource);
                    Toast.makeText(GalleryActivity.this, "桌面壁纸设置成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(GalleryActivity.this, "桌面壁纸设置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (contentView.getParent() != null) {
            popWnd.dismiss();
        }
    }
}

