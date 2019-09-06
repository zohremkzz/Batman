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
package batman.omdbapi.com.batman.data.source.repository.remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import batman.omdbapi.com.batman.data.model.local.Detail;
import batman.omdbapi.com.batman.data.model.local.MovieItem;
import batman.omdbapi.com.batman.data.model.remote.ResponseDetailItemHolder;
import batman.omdbapi.com.batman.data.model.remote.ResponseMovieItemHolder;
import batman.omdbapi.com.batman.data.source.network.NetworkAPIs;
import batman.omdbapi.com.batman.data.source.repository.AppDataSource;
import io.reactivex.Flowable;
import io.reactivex.Single;


@Singleton
public class AppRemoteDataSource implements AppDataSource {

    private final NetworkAPIs mNetworkAPIs;

    @Inject
    public AppRemoteDataSource(NetworkAPIs api) {
        this.mNetworkAPIs = api;
    }

    @Override
    public Flowable<List<MovieItem>> getMovieItemList() {
        return mNetworkAPIs.getAPIService()
                .getMovieItemList("3e974fca","batman")
                .map(ResponseMovieItemHolder::getItems);
    }

    @Override
    public Single<Detail> getDetail(String itemID) {
        return mNetworkAPIs.getAPIService()
                .getDetail("3e974fca",itemID);
    }

    @Override
    public void updateMovieItemList(List<MovieItem> items) {

    }

    @Override
    public void updateDetail(Detail detail) {

    }
}