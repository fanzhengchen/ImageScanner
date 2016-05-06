package com.example.markfan.scanimage;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by MarkFan on 2016/5/5.
 */
public class ImageScanner {


    private Cursor cursor = null;
    private Context mContext = null;
    private ImageScanningComplete complete = null;

    public ImageScanner(Context context, ImageScanningComplete complete) {
        this.mContext = context;
        this.complete = complete;
        Logger.e("fuck before scan");
    }

    //    @RequiresPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void scan() {
        final List<ImageItem> list = new ArrayList<ImageItem>();
        String[] columnsSelected = {
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.DISPLAY_NAME,
//                        MediaStore.Images.ImageColumns.TITLE,
                MediaStore.Images.ImageColumns.SIZE,
                MediaStore.Images.ImageColumns.WIDTH,
                MediaStore.Images.ImageColumns.HEIGHT
        };

        Logger.e("fuck before scan");
        Observable.just(columnsSelected)
                .map(new Func1<String[], List<ImageItem>>() {
                    @Override
                    public List<ImageItem> call(String[] strings) {
                        Cursor cursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                strings, null, null, null);

                        if (cursor.moveToFirst()) {
                            do {
                                ImageItem imageItem = new ImageItem();
                                imageItem.setUri(Uri.parse(cursor.getString(0)));
                                imageItem.setDisplayName(cursor.getString(1));
                                imageItem.setSize(cursor.getLong(2));
                                imageItem.setWidth(cursor.getInt(3));
                                imageItem.setHeight(cursor.getInt(4));
//                                Logger.e("fuck %s", cursor.getString(0));
                                list.add(imageItem);
                            } while (cursor.moveToNext());
                        }
                        cursor.close();
                        return list;
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ImageItem>>() {
                    @Override
                    public void call(List<ImageItem> imageItems) {
                        if (complete != null) {
                            complete.scanComplete(imageItems);
                        }
                    }
                });
    }
}
