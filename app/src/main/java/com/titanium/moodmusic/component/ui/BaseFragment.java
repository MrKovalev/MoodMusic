package com.titanium.moodmusic.component.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import moxy.MvpAppCompatFragment;

public abstract class BaseFragment extends MvpAppCompatFragment {

    abstract protected @LayoutRes
    int setupLayoutRes();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int resId = setupLayoutRes();
        return inflater.inflate(resId, container, false);
    }

    protected void showErrorDialog(String message) {
        ErrorDialog.newInstance(message)
                .show(getChildFragmentManager(), null);
    }
}
