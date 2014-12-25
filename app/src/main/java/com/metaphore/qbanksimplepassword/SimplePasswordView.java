package com.metaphore.qbanksimplepassword;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class SimplePasswordView extends FrameLayout implements NumPadView.Listener, InputTokensView.Listener {

    private final NumPadView numPad;
    private final InputTokensView inputTokens;
    private final SimplePasswordAnalyzer analyzer;
    private Listener listener;

    public SimplePasswordView(Context context) {
        this(context, null);
    }
    public SimplePasswordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public SimplePasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.simple_password_view, this, true);

        analyzer = new SimplePasswordAnalyzer(context);

        numPad = ((NumPadView) findViewById(R.id.num_pad));
        numPad.setListener(this);

        inputTokens = ((InputTokensView) findViewById(R.id.input_tokens));
        inputTokens.setListener(this);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public String getInput() {
        return inputTokens.getInput();
    }

    public void clearInput() {
        inputTokens.clearInput();
    }

    @Override
    public void onNumInput(String num) {
        Log.d("", num);
        numPad.showDelButton();
        inputTokens.handleInput(num);
    }

    @Override
    public void onDelete() {
        Log.d("", "delete");
        inputTokens.delete();
    }

    @Override
    public void onInputComplete() {
        Log.d("", "input complete");

        String input = inputTokens.getInput();

        if (!analyzer.analyze(input) && listener != null) {
            listener.onInputError(analyzer.getErrorMessage());
            return;
        }

        if (listener != null) {
            listener.onInputComplete(input);
        }
    }

    @Override
    public void onInputEmpty() {
        Log.d("", "input empty");
        numPad.hideDelButton();
    }

    public interface Listener {
        void onInputComplete(String input);
        void onInputError(String error);
    }
}
