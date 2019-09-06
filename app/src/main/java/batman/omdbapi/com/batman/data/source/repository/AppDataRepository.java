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
package batman.omdbapi.com.batman.data.source.repository;

import android.support.annotation.VisibleForTesting;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import batman.omdbapi.com.batman.data.model.local.Detail;
import batman.omdbapi.com.batman.data.model.local.MovieItem;
import batman.omdbapi.com.batman.data.source.prefs.PreferencesHelper;
import batman.omdbapi.com.batman.di.Local;
import batman.omdbapi.com.batman.di.madule.Remote;
import io.reactivex.Flowable;
import io.reactivex.Single;


@Singleton
public class AppDataRepository implements AppRepository {

    private final AppDataSource mLocalAppDataSource;

    private final AppDataSource mRemoteAppDataSource;

    private final PreferencesHelper mPreferenceHelper;

    @VisibleForTesting
    List<MovieItem> mCachedItemList;
    @VisibleForTesting
    Detail mCachedDetail;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    boolean mCacheIsDirty = false;


    @Inject
    public AppDataRepository(@Remote AppDataSource remoteDataSource,
                             @Local AppDataSource localDataSource,
                             PreferencesHelper preferencesHelper) {
        mRemoteAppDataSource = remoteDataSource;
        mLocalAppDataSource = localDataSource;
        mPreferenceHelper = preferencesHelper;
    }

    @Override
    public Flowable<List<MovieItem>> getItemList(boolean forceRemote) {
        if (forceRemote)
            return getItemFromServerDB();

        return getMovieItemList();
    }

    @Override
    public Single<Detail> getDetail(boolean forceRemote,String itemID) {
        if (forceRemote)
            return getDetailFromServerDB(itemID);

        return getDetail(itemID);
    }


    //get the items from the server
    private Flowable<List<MovieItem>> getItemFromServerDB() {
        return mRemoteAppDataSource
                .getMovieItemList()
                .doOnNext(items -> {
                    mLocalAppDataSource.updateMovieItemList(items);
                    mCachedItemList = items;
                    mCacheIsDirty = false;
                });
    }

    //get the elements from local db, and if empty get it from sever
    private Flowable<List<MovieItem>> getItemFromLocalDB() {
        return mLocalAppDataSource.getMovieItemList()
                .switchIfEmpty(getItemFromServerDB())
                .flatMap(items -> {
                    if (items != null && items.size() > 0) {
                        mCachedItemList = items;
                        mCacheIsDirty = false;
                        return Flowable.just(items);
                    } else {
                        mCacheIsDirty = true;
                        return getItemFromServerDB();
                    }
                });
    }


    @Override
    public void refreshItems() {
        mCacheIsDirty = true;
    }

    @Override
    public void refreshDetail() {

    }

    @Override
    public Flowable<List<MovieItem>> getMovieItemList() {
        if (mCachedItemList != null && !mCacheIsDirty) {
            return Flowable.just(mCachedItemList);
        }

        //if cache is dirty, get the data from server
        if (mCacheIsDirty) {
            return getItemFromServerDB();
        }
        return getItemFromLocalDB();
    }



    @Override
    public Single<Detail> getDetail(String itemID) {
        if (mCachedDetail != null && !mCacheIsDirty) {
            return Single.just(mCachedDetail);
        }

        //if cache is dirty, get the data from server
       /* if (mCacheIsDirty) {*/
            return getDetailFromServerDB(itemID);
/*        }
        return getDetailFromLocalDB(itemID);*/
    }

    private Single<Detail> getDetailFromLocalDB(String itemID) {
        return mLocalAppDataSource.getDetail(itemID)
                .flatMap(detail -> {
                    if (detail != null) {
                        mCachedDetail = detail;
                        mCacheIsDirty = false;
                        return Single.just(detail);
                    } else {
                        mCacheIsDirty = true;
                        return getDetailFromServerDB(itemID);
                    }
                })
              /*  .switchIfEmpty(getDetailFromServerDB())
                .flatMap(items -> {
                    if (items != null) {
                        mCachedDetail = items;
                        mCacheIsDirty = false;
                        return Flowable.just(items);
                    } else {
                        mCacheIsDirty = true;
                        return getDetailFromServerDB();
                    }
                })*/;
    }

    @Override
    public void updateMovieItemList(List<MovieItem> items) {
        mLocalAppDataSource.updateMovieItemList(items);
    }

    @Override
    public void updateDetail(Detail detail) {
        mLocalAppDataSource.updateDetail(detail);
    }

    //get the items from the server
    private Single<Detail> getDetailFromServerDB(String itemID) {
        return mRemoteAppDataSource
                .getDetail(itemID)
                ;

                /*.doOnNext(items -> {
                    mLocalAppDataSource.updateDetail(items);
                    mCachedDetail = items;
                    mCacheIsDirty = false;
                })*/
    }

}
