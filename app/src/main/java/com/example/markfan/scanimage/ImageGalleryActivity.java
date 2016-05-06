package com.example.markfan.scanimage;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MarkFan on 2016/5/6.
 */
public class ImageGalleryActivity extends Activity implements ImageScanningComplete {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private Adapter adapter;
    private ImageScanner imageScanner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        ButterKnife.bind(this);
        initData();
        setUpRecyclerView();
    }

    private void initData() {
        imageScanner = new ImageScanner(getApplicationContext(), this);
        imageScanner.scan();
    }

    private void setUpRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void scanComplete(List<ImageItem> imageItems) {
        Logger.e("fuck finish scanning %d",imageItems.size());
        adapter = new Adapter(imageItems);
        setUpRecyclerView();
        mRecyclerView.setAdapter(adapter);
    }
}
