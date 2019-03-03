package com.titanium.moodmusic.data.db;

import android.os.AsyncTask;
import android.util.Log;

import com.titanium.moodmusic.data.db.dao.MusicDao;
import com.titanium.moodmusic.data.db.entity.FavoriteAlbumTable;

import java.util.List;

public class AsyncDataLoader extends AsyncTask<Void,Void, List<FavoriteAlbumTable>> {

    private AsyncResponceResult asyncResponceResult = null;

    MusicDao musicDao;

    public AsyncDataLoader() { }

    @Override
    protected List<FavoriteAlbumTable> doInBackground(Void... voids) {
        return musicDao.getAllAlbums();
    }

    @Override
    protected void onPostExecute(List<FavoriteAlbumTable> favoriteAlbumTableList) {
        super.onPostExecute(favoriteAlbumTableList);

        if (favoriteAlbumTableList != null && asyncResponceResult != null){
            asyncResponceResult.onTaskComplete(favoriteAlbumTableList);
        }
    }

    public void setMusicDao(MusicDao musicDao) {
        this.musicDao = musicDao;
    }

    public void setAsyncResponceResult(AsyncResponceResult asyncResponceResult) {
        this.asyncResponceResult = asyncResponceResult;
    }

    public interface AsyncResponceResult{
        void onTaskComplete(List<FavoriteAlbumTable> result);
    }
}
