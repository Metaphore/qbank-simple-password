package com.metaphore.qbanksimplepassword;

import android.content.Context;

public class SimplePasswordAnalyzer {
    private final Context context;

    private boolean mRepeatingCharacters = false;
    private boolean mSubsequentCharacters123 = false;
    private boolean mSubsequentCharacters147 = false;

    private static final String REPEATING = "111 222 333 444 555 666 777 888 999 000";
    private static final String SEQUENTAL_123 = "01234567890 09876543210";
    private static final String SEQUENTAL_147 = "147 741 02580 08520 369 963";

    public SimplePasswordAnalyzer(Context context) {
        this.context = context;
    }

    public boolean hasRepeatingDigits(String str) {

        for (int i = 0; i < str.length(); ++i) {

            if (i + 2 >= str.length()) {
                return false;
            }

            String sub = str.substring(i, i + 3);
            if (REPEATING.indexOf(sub) != -1) {
                return true;
            }
        }

        return false;
    }

    public boolean hasSequentalDigits123(String str) {

        for (int i = 0; i < str.length(); ++i) {

            if (i + 2 >= str.length()) {
                return false;
            }

            String sub = str.substring(i, i + 3);
            if (SEQUENTAL_123.indexOf(sub) != -1) {
                return true;
            }
        }

        return false;
    }

    public boolean hasSequentalDigits147(String str) {

        for (int i = 0; i < str.length(); ++i) {

            if (i + 2 >= str.length()) {
                return false;
            }

            String sub = str.substring(i, i + 3);
            if (SEQUENTAL_147.indexOf(sub) != -1) {
                return true;
            }
        }

        return false;
    }

    public boolean analyze(String str) {

        mRepeatingCharacters = false;
        mSubsequentCharacters123 = false;
        mSubsequentCharacters147 = false;

        if (str.length() >= 3) {
            mRepeatingCharacters = hasRepeatingDigits(str);
            mSubsequentCharacters123 = hasSequentalDigits123(str);
            mSubsequentCharacters147 = hasSequentalDigits147(str);
        }

        return
                (mRepeatingCharacters == false) &&
                        (mSubsequentCharacters123 == false) &&
                        (mSubsequentCharacters147 == false)
                ;
    }

    public String getErrorMessage() {
        if (	(mRepeatingCharacters == false) &&
                (mSubsequentCharacters123 == false) &&
                (mSubsequentCharacters147 == false)
                ) {
            return "OK";
        }

        if (mRepeatingCharacters) {
            return context.getString(R.string.reg_simple_pass_cannot_contain_repeating_digits);
        }
        if (mSubsequentCharacters123) {
            return context.getString(R.string.reg_simple_pass_cannot_contain_sequental_digits_123);
        }
        if (mSubsequentCharacters147) {
            return context.getString(R.string.reg_simple_pass_cannot_contain_sequental_digits_147);
        }

        return "ERROR";
    }

}

