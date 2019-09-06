/*
    Copyright 2018 Gaurav Kumar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package batman.omdbapi.com.batman.data.source.repository.local;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import batman.omdbapi.com.batman.data.model.local.Detail;
import batman.omdbapi.com.batman.data.model.local.MovieItem;
import batman.omdbapi.com.batman.data.source.db.AppDatabase;
import batman.omdbapi.com.batman.data.source.db.DetailDao;
import batman.omdbapi.com.batman.data.source.db.MovieItemDao;
import batman.omdbapi.com.batman.data.source.repository.AppDataSource;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Concrete implementation of a data source as a db using room.
 */
@Singleton
public class AppLocalDataSource implements AppDataSource {

    private final MovieItemDao mItemDao;
    private final DetailDao mIDetailDao;

    @Inject
    public AppLocalDataSource(@NonNull AppDatabase mDatabase) {
        mItemDao = mDatabase.musicDao();
        mIDetailDao = mDatabase.detailDao();
    }


    @Override
    public Flowable<List<MovieItem>> getMovieItemList() {
        return mItemDao.fetchItems();
    }

    @Override
    public Single<Detail> getDetail(String itemID) {
        return mIDetailDao.fetchItemByItemId(itemID);
    }

    @Override
    public void updateMovieItemList(List<MovieItem> items) {
        mItemDao.insertMultipleItem(items);
    }

    @Override
    public void updateDetail(Detail detail) {

    }
}