package com.titanium.moodmusic.data.api;

public class Constants {
    public static final String API_KEY = "aa08283e997897e1702dff08c3f00379";
    public static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";
    public static final int TOP_ITEMS_LIMIT = 10;

    public static final class Params {

    }

    public static final class Endpoint{
        public static final String TOP_CHART_ARTISTS_SEARCH = "?method=chart.gettopartists&format=json";
    }
}
