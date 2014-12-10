package com.metaphore.qbanksimplepassword;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class InputTokensView extends FrameLayout {
    private static final String LOG_TAG = InputTokensView.class.getSimpleName();
    private static final int TOKEN_AMOUNT = 4;

    private final ArrayList<Token> tokens;
    private final Drawable drawableEmpty;
    private final Drawable drawableFocused;
    private final Drawable drawableFilled;
    private Listener listener;

    private int focused = 0;

    public InputTokensView(Context context) {
        this(context, null);
    }
    public InputTokensView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public InputTokensView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.input_tokens_view, this, true);

        drawableEmpty = context.getResources().getDrawable(R.drawable.spass_token_empty);
        drawableFocused = context.getResources().getDrawable(R.drawable.spass_token_focused);
        drawableFilled = context.getResources().getDrawable(R.drawable.spass_token_filled);

        tokens = new ArrayList<>();
        tokens.add(new Token(R.id.spass_token_0));
        tokens.add(new Token(R.id.spass_token_1));
        tokens.add(new Token(R.id.spass_token_2));
        tokens.add(new Token(R.id.spass_token_3));

        updateTokens();
    }

    public void handleInput(String input) {
        if (focused == TOKEN_AMOUNT) {
            Log.d(LOG_TAG, "Input skipped. All tokens are full");
            return;
        }
        Log.d(LOG_TAG, "Handling: " + input);
        tokens.get(focused).setContent(input);
        focused++;
        updateTokens();

        if (focused == TOKEN_AMOUNT && listener != null) {
            listener.onInputComplete();
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void delete() {
        if (focused == 0) {
            Log.d(LOG_TAG, "Deletion canceled. No filled tokens");
            return;
        }

        Log.d(LOG_TAG, "Delete handling");
        focused--;
        updateTokens();

        if (focused == 0 && listener != null) {
            listener.onInputEmpty();
        }
    }

    public String getInput() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < focused; i++) {
            sb.append(tokens.get(i).content);
        }
        return sb.toString();
    }

    private void updateTokens() {
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            token.setState(i < focused ? TokenState.FILLED : i > focused ? TokenState.EMPTY : TokenState.FOCUSED);
        }
    }

    private enum TokenState { EMPTY, FOCUSED, FILLED }

    private class Token {
        final ImageView tokenView;
        TokenState state;
        String content = "";

        Token(int tokenViewId) {
            tokenView = ((ImageView) findViewById(tokenViewId));
        }

        void setContent(String content) {
            this.content = content;
        }

        void setState(TokenState state) {
            if (this.state == state) return;

            this.state = state;
            switch (state) {
                case EMPTY:
                    tokenView.setImageDrawable(drawableEmpty);
                    break;
                case FOCUSED:
                    tokenView.setImageDrawable(drawableFocused);
                    break;
                case FILLED:
                    tokenView.setImageDrawable(drawableFilled);
                    break;
            }
        }
    }

    public interface Listener {
        void onInputComplete();
        void onInputEmpty();
    }
}
