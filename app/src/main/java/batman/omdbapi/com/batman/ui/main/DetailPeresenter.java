package batman.omdbapi.com.batman.ui.main;

import batman.omdbapi.com.batman.R;
import batman.omdbapi.com.batman.base.BasePresenter;
import batman.omdbapi.com.batman.data.source.repository.AppRepository;
import batman.omdbapi.com.batman.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DetailPeresenter extends BasePresenter<DetailContract.View> implements DetailContract.Presenter {
    private Disposable mDisposable;

    public DetailPeresenter(AppRepository appRepository, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(appRepository, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadPage(boolean refresh,String itemID) {
        getView().showProgressDialog(R.string.please_wait);

        if (refresh)
            getDataSource().refreshItems();

        //remove the previous disposable from composite disposable, for multiple load items calls
        if (mDisposable != null)
            getCompositeDisposable().delete(mDisposable);

        mDisposable = getDataSource().getDetail(itemID)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(items -> {
                    if (!isViewAttached())
                        return;

                    getView().dismissProgressDialog();
                    if (items != null)
                        getView().refreshPage(items);
                    else
                        getView().showEmptyMsg();
                }, throwable -> {
                    if (!isViewAttached())
                        return;

                    getView().dismissProgressDialog();
                    handleApiError(throwable);
                });

        getCompositeDisposable().add(mDisposable);
    }
}
