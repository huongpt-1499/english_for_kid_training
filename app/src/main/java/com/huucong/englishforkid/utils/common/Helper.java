package com.huucong.englishforkid.utils.common;

import android.content.Context;
import android.widget.Toast;

public final class Helper {
    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
