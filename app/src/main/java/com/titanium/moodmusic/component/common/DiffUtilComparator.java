package com.titanium.moodmusic.component.common;

public interface DiffUtilComparator<F, S> {

    Boolean compare(F firstItem, S secondItem);
}
