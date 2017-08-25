package com.example.yu_enpit.mydiary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by enPiT-P11 on 2017/08/25.
 */

public class MyUtils {
    public static Bitmap getImageFromByte(byte[] bytes){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opt);
        int bitmapSize = 1;
        if((opt.outHeight * opt.outWidth) > 500000){
            double outSize = (double) (opt.outHeight * opt.outWidth) / 500000;
            bitmapSize = (int) (Math.sqrt(outSize) + 1);
        }

        opt.inJustDecodeBounds = false;
        opt.inSampleSize = bitmapSize;
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0 , bytes.length, opt);
        return bmp;
    }

    public static byte[] getFromImage(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArrey = stream.toByteArray();
        return byteArrey;
    }
}
