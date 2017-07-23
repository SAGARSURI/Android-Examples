package com.sagar.android_examples.fragment_to_activity_communication;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sagar.android_examples.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContainerActivity extends AppCompatActivity {

    @BindView(R.id.dialogBtn)
    Button dialogBtn;
    @BindView(R.id.passDataText)
    TextView passDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        ButterKnife.bind(this);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog dialog = new MyDialog(new MyDialog.DataPassListener() {
                    @Override
                    public void onDataPassClickListener(Dialog dialog, String data) {
                        if(!data.isEmpty()){
                            passDataText.setText(data);
                        }
                    }
                });
                dialog.show(getSupportFragmentManager(), "MyDialog");
            }
        });
    }
}
