package com.titanium.moodmusic.component.converter;

public interface Converter<From, To> {

    To convert(From from);
}
