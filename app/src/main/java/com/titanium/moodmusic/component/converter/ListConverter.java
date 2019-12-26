package com.titanium.moodmusic.component.converter;

import java.util.List;

public interface ListConverter<From, To> {

    List<To> convert(List<From> list);
}
