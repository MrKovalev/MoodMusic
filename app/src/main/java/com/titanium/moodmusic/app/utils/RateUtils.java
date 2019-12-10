package com.titanium.moodmusic.app.utils;

import android.content.Intent;
import android.net.Uri;

public class RateUtils {

    public static Intent findApplicationsForRate(){
        return new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:UsefulAndroidApps"));
    }
}
