package com.titanium.moodmusic.feature.track.detail;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.component.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WebFragment extends BaseFragment {

    private static final String EXTRA_URL_TRACK = "extra_track";

    @BindView(R.id.wv_track)
    WebView webView;

    private Unbinder unbinder;

    private WebFragment() {
    }

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
    protected int setupLayoutRes() {
        return R.layout.fragment_track_detail_webview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        initWebView();
    }

    private void initWebView() {
        if (getArguments() != null) {
            String url = getArguments().getString(EXTRA_URL_TRACK);

            webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient());
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.loadUrl(url);
        } else {
            throw new IllegalArgumentException("Url not found");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
