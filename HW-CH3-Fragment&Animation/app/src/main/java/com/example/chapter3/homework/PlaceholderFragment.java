package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlaceholderFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Animator appearAni = AnimatorInflater.loadAnimator(this.getContext(), R.animator.alpha_appear);
        appearAni.setTarget(getView().findViewById(R.id.friend_list));
        final Animator disapprAni1 = AnimatorInflater.loadAnimator(this.getContext(),R.animator.alpha_disappear);
        disapprAni1.setTarget(getView().findViewById(R.id.animation_view_3));
        final Animator disapprAni2 = AnimatorInflater.loadAnimator(this.getContext(),R.animator.alpha_disappear);
        disapprAni2.setTarget(getView().findViewById(R.id.loading_text));

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(appearAni, disapprAni1, disapprAni2);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                animatorSet.start();
            }
        }, 5000);
    }
}
