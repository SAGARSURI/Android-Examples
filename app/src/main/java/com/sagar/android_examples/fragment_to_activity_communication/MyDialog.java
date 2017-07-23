package com.sagar.android_examples.fragment_to_activity_communication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.sagar.android_examples.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagarsuri on 23/07/17.
 */

public class MyDialog extends DialogFragment {
    int width, height;
    DisplayMetrics metrics;
    @BindView(R.id.myText)
    EditText editText;
    @BindView(R.id.passBtn)
    Button passBtn;
    private DataPassListener dataPassListener;

    public MyDialog(){

    }
    public interface DataPassListener{
         void onDataPassClickListener(Dialog dialog,String data);
    }

    @SuppressLint("ValidFragment")
    public MyDialog(DataPassListener dataPassListener) {
        super();
        this.dataPassListener = dataPassListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        metrics = getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        passBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = editText.getText().toString();
                if(data.isEmpty()){
                    editText.setError("Enter some text");
                }else{
                    dataPassListener.onDataPassClickListener(getDialog(),editText.getText().toString());
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    dismiss();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout((6 * width) / 7, (2*height) / 5);
    }
}
