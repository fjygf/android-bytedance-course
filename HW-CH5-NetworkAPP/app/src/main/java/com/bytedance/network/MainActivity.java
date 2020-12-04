package com.bytedance.network;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bytedance.network.api.GitHubService;
import com.bytedance.network.api.UploadService;
import com.bytedance.network.model.GetVideoResponse;
import com.bytedance.network.model.Repo;
import com.bytedance.network.model.UploadResponse;
import com.bytedance.network.util.MyPartBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private final Retrofit retrofit1 = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api-sjtu-camp.bytedance.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private final UploadService uploadService = retrofit.create(UploadService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById(R.id.btn).setOnClickListener(v -> requestSampleInfo());
        findViewById(R.id.btn).setOnClickListener(v -> requestAllInfo());
        findViewById(R.id.btn1).setOnClickListener(v -> {
            Log.d(TAG, "onCreate: click upload button");
            uploadFile();
        });

        findViewById(R.id.btn2).setOnClickListener(v -> {
            Log.d(TAG, "onCreate: click get button");
            getVideoList();
        });
    }

    private void requestSampleInfo() {
        // 通过 retrofit 对象的 create() 方法来实例化 java 接口的对象
        // 拿到该接口对象后直接调用相应 api 所对应的方法，并传入对应的参数，获取到 Call<T> 的实例化对象
        // 通过该对象即可向服务器发起网络请求
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> call = service.getRepos("JakeWharton");
        call.enqueue(new Callback<List<Repo>>() {
            @Override public void onResponse(final Call<List<Repo>> call, final Response<List<Repo>> response) {
                // 合法性校验
                if (!response.isSuccessful()) {
                    return;
                }
                final List<Repo> repoList = response.body();
                if (repoList == null || repoList.isEmpty()) {
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < repoList.size(); i++) {
                    final Repo repo = repoList.get(i);
                    stringBuilder.append("仓库名：").append(repo.getName())
                            .append("\n");
                }
                ((TextView) findViewById(R.id.tv)).setText(stringBuilder.toString());
            }

            @Override public void onFailure(final Call<List<Repo>> call, final Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void requestAllInfo() {
        GitHubService service = retrofit1.create(GitHubService.class);
        Call<List<Repo>> repos = service.getRepos("JakeWharton");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override public void onResponse(final Call<List<Repo>> call, final Response<List<Repo>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                final List<Repo> repoList = response.body();
                if (repoList == null || repoList.isEmpty()) {
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < repoList.size(); i++) {
                    final Repo repo = repoList.get(i);
                    stringBuilder.append("仓库名：").append(repo.getName())
                            .append(", 描述：").append(repo.getDescription())
                            .append(", fork 数量：").append(repo.getForksCount())
                            .append(", star 数量：").append(repo.getStarsCount())
                            .append("\n");
                }
                ((TextView) findViewById(R.id.tv)).setText(stringBuilder.toString());
            }

            @Override public void onFailure(final Call<List<Repo>> call, final Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void uploadFile() {
        AssetManager assets = getAssets();
        MultipartBody.Part coverImage = new MyPartBuilder()
                .setAssetManager(assets)
                .setFileName("pic.png")
                .setMediaType("image/png")
                .setKey("cover_image")
                .build();
        MultipartBody.Part video = new MyPartBuilder()
                .setAssetManager(assets)
                .setFileName("video.mp4")
                .setMediaType("application/octet-stream")
                .setKey("video")
                .build();
        uploadService.uploadVideo("123", "test", coverImage, video)
                .enqueue(new Callback<UploadResponse>() {
                    @Override
                    public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                        Log.d(TAG, "onResponse: received");
                        if (! response.isSuccessful()) {
                            Log.d(TAG, "onResponse: response failed");
                            return;
                        }
                        Log.d(TAG, "response: " + response.body());
                        ((TextView) findViewById(R.id.tv)).setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<UploadResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t);
                        Log.d(TAG, "onFailure: ");
                        t.printStackTrace();
                    }
                });
    }

    private void getVideoList() {
        uploadService.getVideoList().enqueue(new Callback<GetVideoResponse>() {
            @Override
            public void onResponse(Call<GetVideoResponse> call, Response<GetVideoResponse> response) {
                Log.d(TAG, "onResponse: received");
                Log.d(TAG, "onResponse: " + response.body());
                if (!response.isSuccessful()) {
                    return;
                }
                ((TextView) findViewById(R.id.tv)).setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<GetVideoResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
                t.printStackTrace();
            }
        });
    }
}