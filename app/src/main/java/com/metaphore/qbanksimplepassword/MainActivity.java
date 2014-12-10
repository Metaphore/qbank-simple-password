package com.metaphore.qbanksimplepassword;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements SimplePasswordView.Listener {

    private SimplePasswordView spassView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spassView = ((SimplePasswordView) findViewById(R.id.spass_view));
        spassView.setListener(this);

        findViewById(R.id.btn_show_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowInputClicked();
            }
        });
    }

    private void onShowInputClicked() {
        String input = spassView.getInput();
        Toast.makeText(this, "Input: " + input, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onInputComplete(String input) {
        Toast.makeText(this, "Input: " + input, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInputError(String error) {
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}
