package com.metaphore.qbanksimplepassword;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Calendar;

public class NumPadView extends FrameLayout implements View.OnClickListener {
    private static final String KEY_INSTANCE_STATE = "key_instance_state";
    private static final String KEY_DEL_VISIBLE = "key_del_visible";

    private Listener listener;
    private View btnDel;

    public NumPadView(Context context) {
        this(context, null);
    }
    public NumPadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public NumPadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.num_pad_view, this, true);

        btnDel = findViewById(R.id.btn_spass_delete);
        btnDel.setVisibility(INVISIBLE);
        btnDel.setOnClickListener(this);
        findViewById(R.id.btn_spass_num0).setOnClickListener(this);
        findViewById(R.id.btn_spass_num1).setOnClickListener(this);
        findViewById(R.id.btn_spass_num2).setOnClickListener(this);
        findViewById(R.id.btn_spass_num3).setOnClickListener(this);
        findViewById(R.id.btn_spass_num4).setOnClickListener(this);
        findViewById(R.id.btn_spass_num5).setOnClickListener(this);
        findViewById(R.id.btn_spass_num6).setOnClickListener(this);
        findViewById(R.id.btn_spass_num7).setOnClickListener(this);
        findViewById(R.id.btn_spass_num8).setOnClickListener(this);
        findViewById(R.id.btn_spass_num9).setOnClickListener(this);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void hideDelButton() {
        btnDel.setVisibility(INVISIBLE);
    }

    public void showDelButton() {
        btnDel.setVisibility(VISIBLE);
    }

    @Override
    public void onClick(View v) {
        String tag = v.getTag().toString();
        switch (tag) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                numPressed(tag);
                break;
            case "del":
                delPressed();
                break;
        }
    }

    private void numPressed(String num) {
        if (listener != null) {
            listener.onNumInput(num);
        }
    }

    private void delPressed() {
        if (listener != null) {
            listener.onDelete();
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putBoolean(KEY_DEL_VISIBLE, btnDel.getVisibility() == VISIBLE);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            boolean delVisible = bundle.getBoolean(KEY_DEL_VISIBLE);
            state = bundle.getParcelable(KEY_INSTANCE_STATE);

            btnDel.setVisibility(delVisible ? VISIBLE : INVISIBLE);
        }
        super.onRestoreInstanceState(state);
    }

    public interface Listener {
        void onNumInput(String num);
        void onDelete();
    }
}
