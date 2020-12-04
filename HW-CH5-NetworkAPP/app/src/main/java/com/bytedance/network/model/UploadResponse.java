package com.bytedance.network.model;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {
    @SerializedName("success")
    boolean success;

    @SerializedName("url")
    String url;

    @SerializedName("result")
    String result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UploadResponse{" +
                "result=" + result +
                ", success=" + success +
                ", url='" + url + '\'' +
                '}';
    }
}