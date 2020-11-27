package com.example.chapter3.homework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HelloFragment extends Fragment {
    private static final String ARG_PARAM1 = "firstName";
    private static final String ARG_PARAM2 = "lastName";
    private String firstName;
    private String lastName;
    private TextView textView;
    public HelloFragment() {

    }

    public static HelloFragment newInstance(String firstName, String lastName) {
        HelloFragment fragment = new HelloFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, firstName);
        args.putString(ARG_PARAM2, lastName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            firstName = getArguments().getString(ARG_PARAM1);
            lastName = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsatanceState) {
        View view = inflater.inflate(R.layout.fragment_hello, container, false);
        textView = (TextView) view.findViewById(R.id.hello_text);
        if(firstName != null && lastName != null) {
            textView.setText(String.format("Hello %s %s!", firstName, lastName));
        } else {
            textView.setText("Hello Stranger!");
        }
        return view;
    }

}
