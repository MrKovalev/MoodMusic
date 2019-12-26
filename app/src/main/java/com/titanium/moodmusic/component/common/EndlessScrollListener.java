package com.titanium.moodmusic.component.common;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private Scrollable onLoad;

    public EndlessScrollListener(Scrollable onLoad) {
        this.onLoad = onLoad;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        if (layoutManager != null) {
            int totalItemCount = layoutManager.getItemCount();
            int visibleItemCount = layoutManager.getChildCount();
            int lastVisibleItem = layoutManager.findFirstVisibleItemPosition();

            if (visibleItemCount + lastVisibleItem >= totalItemCount && dy < 0) {
                onLoad.onScrolled();
            }
        }
    }
}


