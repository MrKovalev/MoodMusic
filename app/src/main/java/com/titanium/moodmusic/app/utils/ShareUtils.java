package com.titanium.moodmusic.app.utils;

import android.content.Intent;

public class ShareUtils {

    public static Intent shareAppAction(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Let me recommend you this application\n\n";
        shareBody = shareBody + "https://github.com/MrKovalev/MoodMusic" +"\n\n";

        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Mood app_icon");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

        return Intent.createChooser(sharingIntent, "Share via");
    }
}
