package me.yangguofeng.myapplication;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchLayout extends LinearLayout {

    private static final String TAG = "SearchLayout";

    private EditText input;
    private TextView cancelView;

    private OnSearchTextChangedListener onSearchTextChangedListener;

    public SearchLayout(Context context) {
        super(context);
        initView();
    }

    public SearchLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SearchLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    //初始化view
    private void initView() {
        //LayoutInflater.from(getContext()).inflate(R.layout.layout_search, this);

        inflate(getContext(), R.layout.layout_search, this);

        input = findViewById(R.id.edit);
        cancelView = findViewById(R.id.cancel);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "onTextChanged: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(onSearchTextChangedListener != null) {
                    onSearchTextChangedListener.onTextChangedCallback(s.toString());
                }
            }
        });

        cancelView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    public void setOnSearchTextChangedListener(OnSearchTextChangedListener listener) {
        onSearchTextChangedListener = listener;
    }

    public interface OnSearchTextChangedListener {
        void onTextChangedCallback(String text);
    }
}
