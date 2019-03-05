package com.titanium.moodmusic.ui.fragments.trackDetailWeb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.titanium.moodmusic.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WebFragment extends Fragment {

    public static final String EXTRA_URL_TRACK = "extra_track";

    @BindView(R.id.wv_track)
    WebView webView;

    private Unbinder unbinder;

    public WebFragment() { }

    public static WebFragment newInstance(String url) {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_URL_TRACK, url);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);

        unbinder = ButterKnife.bind(this, view);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView.loadUrl(getArguments().getString(EXTRA_URL_TRACK));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
