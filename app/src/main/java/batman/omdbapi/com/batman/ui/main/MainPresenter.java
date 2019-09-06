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
package batman.omdbapi.com.batman.ui.main;

import batman.omdbapi.com.batman.R;
import batman.omdbapi.com.batman.base.BasePresenter;
import batman.omdbapi.com.batman.data.source.repository.AppRepository;
import batman.omdbapi.com.batman.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private Disposable mDisposable;

    @Inject
    public MainPresenter(AppRepository appRepository,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(appRepository, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadItems(boolean refresh) {
        getView().showProgressDialog(R.string.please_wait);

        if (refresh)
            getDataSource().refreshItems();

        //remove the previous disposable from composite disposable, for multiple load items calls
        if (mDisposable != null)
            getCompositeDisposable().delete(mDisposable);

        mDisposable = getDataSource().getMovieItemList()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(items -> {
                    if (!isViewAttached())
                        return;

                    getView().dismissProgressDialog();
                    if (items != null && items.size() > 0)
                        getView().refreshItemList(items);
                    else
                        getView().showEmptyListUI();
                }, throwable -> {
                    if (!isViewAttached())
                        return;

                    getView().dismissProgressDialog();
                    handleApiError(throwable);
                });

        getCompositeDisposable().add(mDisposable);
    }
}
