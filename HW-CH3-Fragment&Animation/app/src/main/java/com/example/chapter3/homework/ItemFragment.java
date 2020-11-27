package com.example.chapter3.homework;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chapter3.homework.dummy.DummyContent;

public class ItemFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "colomn-count";
    private int mColumnCount = 1;
    private View view;
    private String TAG;

    public ItemFragment() {

    }

    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        this.view = view;
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS));
        }
        view.setAlpha(0);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ItemFragment", "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        TAG = "ItemFragment";
        Log.d(TAG, "onPause: ");
    }

    private void fadeAnimate(float start, float end) {
        Log.d(TAG, "fadeAnimate: " + start + "\t" + end);
        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, start, end);
        fadeInAnimator.setDuration(500);
        fadeInAnimator.start();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
        if (isVisibleToUser) {
            fadeAnimate(0, 1);
        }
    }
}
