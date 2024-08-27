package com.example.libB;

import androidx.annotation.Keep;

@Keep
public class LibB {

    // Used to load the 'nativelib' library on application startup.
    static {
        System.loadLibrary("libB");
    }

    /**
     * A native method that is implemented by the 'nativelib' native library,
     * which is packaged with this application.
     */
    public static native String stringFromJNI();
    public static String testt() {
        return stringFromJNI().concat(String.valueOf(R.string.libB));
    }
}