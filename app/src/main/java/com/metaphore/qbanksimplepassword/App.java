package com.metaphore.qbanksimplepassword;

import android.app.Application;
import android.graphics.Typeface;

public class App extends Application {
    public static Typeface rf_bold;
    public static Typeface rf;

    @Override
    public void onCreate() {
        super.onCreate();

        rf_bold = Typeface.createFromAsset(getAssets(), "fonts/svyaznoy_rf_bold.otf");
        rf = Typeface.createFromAsset(getAssets(), "fonts/svyaznoy_rf.otf");
    }
}
