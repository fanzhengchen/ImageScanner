package com.example.markfan.scanimage;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MarkFan on 2016/5/6.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_view)
    ImageView draweeView;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void fillData(ImageItem imageItem) {
        Logger.e("fuck loading");
        Uri uri = Uri.parse("http://img1.imgtn.bdimg.com/it/u=36757216,56548701&fm=11&gp=0.jpg");
        draweeView.setImageURI(uri);
//        draweeView.setImageURI(imageItem.getUri());
    }
}
