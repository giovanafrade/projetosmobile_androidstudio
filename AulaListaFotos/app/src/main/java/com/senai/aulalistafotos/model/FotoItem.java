package com.senai.aulalistafotos.model;

import android.graphics.Bitmap;
import android.net.Uri;

public class FotoItem {

    private Uri uri;
    private Bitmap bitmap;

//    criando construtores
//    alt + insert --> atalho
    public FotoItem(Uri uri) {
        this.uri = uri;
    }

    public FotoItem(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Uri getUri() {
        return uri;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
