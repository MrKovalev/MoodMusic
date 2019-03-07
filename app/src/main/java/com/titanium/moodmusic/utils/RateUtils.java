package com.titanium.moodmusic.utils;

import android.content.Intent;
import android.net.Uri;

/** Вспомогательный класс для просмотра остальных приложений разработчика **/

public class RateUtils {
    public static Intent findApplicationsForRate(){
        return new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:UsefulAndroidApps"));
    }
}
