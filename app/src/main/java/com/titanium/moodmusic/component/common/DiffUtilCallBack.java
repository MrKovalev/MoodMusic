package com.titanium.moodmusic.component.common;

import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

public class DiffUtilCallBack<T> extends DiffUtil.Callback {

    private final DiffUtilComparator comparator;

    private List<T> oldList = new ArrayList<>();
    private List<T> newList = new ArrayList<>();

    public DiffUtilCallBack(DiffUtilComparator comparator) {
        this.comparator = comparator;
    }

    public DiffUtil.DiffResult getDiffResult(List<T> newList, boolean detectMoves) {
        oldList = this.newList;
        this.newList = newList;
        return DiffUtil.calculateDiff(this, detectMoves);
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = oldList.get(oldItemPosition);
        T newItem = newList.get(newItemPosition);

        return comparator.compare(oldItem, newItem);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = oldList.get(oldItemPosition);
        T newItem = newList.get(newItemPosition);

        return oldItem.equals(newItem);
    }
}
