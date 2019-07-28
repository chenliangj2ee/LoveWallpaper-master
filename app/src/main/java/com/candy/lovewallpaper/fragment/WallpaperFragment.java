package com.candy.lovewallpaper.fragment;
/*
 *  描述：    壁纸
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.candy.lovewallpaper.R;
import com.candy.lovewallpaper.activity.GalleryActivity;
import com.candy.lovewallpaper.adapter.SpecialGridAdapter;
import com.candy.lovewallpaper.model.SpecialGridModel;

import java.util.ArrayList;
import java.util.List;

public class WallpaperFragment extends Fragment{

    private GridView mGridView;
    private SpecialGridAdapter mSpecialGridAdapter;
    private List<SpecialGridModel> mList = new ArrayList<>();
    private ArrayList<String>mListBigUrl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallpaper,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mGridView= (GridView) view.findViewById(R.id.mGridView);
        parsingJson();

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                intent.putExtra("position", i);
                intent.putStringArrayListExtra("bigUrl", mListBigUrl);
                startActivity(intent);
            }
        });
    }

    private void parsingJson() {
            SpecialGridModel models = new SpecialGridModel();
            mListBigUrl.add("http://www.kdh965.pw/SPIC/14/b/128702-KxHEALpujym0smdzze.jpg");
            models.setSmall("http://www.kdh965.pw/SPIC/14/b/128702-KxHEALpujym0smdzze.jpg");
            mList.add(models);

        models = new SpecialGridModel();
        mListBigUrl.add("http://img.qqzhi.com/upload/img_3_1692012691D650385746_27.jpg");
        models.setSmall("http://img.qqzhi.com/upload/img_3_1692012691D650385746_27.jpg");
        mList.add(models);


        models = new SpecialGridModel();
        mListBigUrl.add("http://hbimg.huabanimg.com/40f50daa0b11c6d989753428a29d6837d5818e3c37b6b-w9qXYT_fw658");
        models.setSmall("http://hbimg.huabanimg.com/40f50daa0b11c6d989753428a29d6837d5818e3c37b6b-w9qXYT_fw658");
        mList.add(models);

        models = new SpecialGridModel();
        mListBigUrl.add("http://img8.zol.com.cn/bbs/upload/19910/19909973_0800.jpg");
        models.setSmall("http://img8.zol.com.cn/bbs/upload/19910/19909973_0800.jpg");
        mList.add(models);


        models = new SpecialGridModel();
        mListBigUrl.add("https://img.cct58.com/caiji/5857/201710/2818/20171028180542_483.jpg");
        models.setSmall("https://img.cct58.com/caiji/5857/201710/2818/20171028180542_483.jpg");
        mList.add(models);


        models = new SpecialGridModel();
        mListBigUrl.add("http://img.improve-yourmemory.com/pic/9be0dca70587f4b6e2e7fc60a4639cd9-0.jpg");
        models.setSmall("http://img.improve-yourmemory.com/pic/9be0dca70587f4b6e2e7fc60a4639cd9-0.jpg");
        mList.add(models);


        models = new SpecialGridModel();
        mListBigUrl.add("http://www.dnzhuti.com/uploads/allimg/171113/95-1G113150937.jpg");
        models.setSmall("http://www.dnzhuti.com/uploads/allimg/171113/95-1G113150937.jpg");
        mList.add(models);

        mSpecialGridAdapter = new SpecialGridAdapter(getActivity(), mList);
        mGridView.setAdapter(mSpecialGridAdapter);
    }
}
