package com.bytedance.network.util;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyPartBuilder {
    AssetManager assetManager;
    String key;
    String fileName;
    String mediaType;

    public MyPartBuilder setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
        return this;
    }

    public MyPartBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public MyPartBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public MyPartBuilder setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    @SuppressLint("LongLogTag")
    public MultipartBody.Part build() {
        if (assetManager == null || fileName == null || key == null || mediaType == null) {
            Log.d("MyMultipartBodyPartBuilder", "build: some args not set");
            return null;
        }

        byte[] fileByteArray = new byte[0];
        try {
            fileByteArray = getByteArrayFromFileName();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse(mediaType), fileByteArray);

        return MultipartBody.Part.createFormData(key, fileName, body);
    }

    private byte[] getByteArrayFromFileName() throws IOException {
        InputStream inputStream = assetManager.open(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n;
        while (-1 != (n = inputStream.read(buffer))) {
            outputStream.write(buffer, 0, n);
        }

        return outputStream.toByteArray();
    }
}