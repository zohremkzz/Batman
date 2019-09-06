package batman.omdbapi.com.batman.ui.main;

import android.support.annotation.NonNull;

import java.util.List;

import batman.omdbapi.com.batman.base.BaseContract;
import batman.omdbapi.com.batman.data.model.local.Detail;
import batman.omdbapi.com.batman.data.model.local.MovieItem;

public interface DetailContract {

    interface View extends BaseContract.View<DetailContract.Presenter> {

        void refreshPage(@NonNull Detail detail);

        void showEmptyMsg();

    }

    interface Presenter extends BaseContract.Presenter<DetailContract.View> {

        void loadPage(boolean refresh,String itemID);

    }
}
