package com.pramod.languageexample;

import android.app.Application;
import android.content.Context;

public class AppController extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}
