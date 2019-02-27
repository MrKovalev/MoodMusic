package com.titanium.moodmusic.data.api;

public class Constants {
    public static final String API_KEY = "aa08283e997897e1702dff08c3f00379";
    public static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";
    public static final int TOP_ITEMS_LIMIT_CHART = 20;
    public static final int TOP_ITEMS_LIMIT_SEARCH = 150;
    public static final int DEFAULT_PAGE_CHART = 1;
    public static final int DEFAULT_PAGE_SEARCH = 1;

    public static final class Params {

    }

    public static final class Endpoint{
        public static final String TOP_CHART_ARTISTS_SEARCH = "?method=chart.gettopartists&format=json";
        public static final String SEARCH_ARTIST = "?method=artist.search&format=json";
        public static final String TOP_CHART_TRACKS_SEARCH = "?method=chart.gettoptracks&format=json";
        public static final String SEARCH_TRACK = "?method=track.search&format=json";
        public static final String SEARCH_TRACKS_BY_ARTIST = "?method=artist.gettoptracks&format=json";
    }
}
