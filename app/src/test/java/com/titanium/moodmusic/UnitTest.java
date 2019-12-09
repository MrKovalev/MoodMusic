package com.titanium.moodmusic;

import com.titanium.moodmusic.data.model.artists.Artist;

import org.junit.Assert;
import org.junit.Test;

public class UnitTest {

    @Test
    public void checkArtistName() throws Exception{
        Artist artist = new Artist();
        String name = "Skillet";
        artist.setName("Skillet");

        Assert.assertEquals(name, "Skillet");
    }

    @Test
    public void checkArtistName2() throws Exception{
        Artist artist = new Artist();
        String name = "Deck";
        artist.setName("Skillet");

        Assert.assertEquals(name, "Skillet");
    }
}