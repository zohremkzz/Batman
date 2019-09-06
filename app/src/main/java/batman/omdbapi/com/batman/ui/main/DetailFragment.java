package batman.omdbapi.com.batman.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import batman.omdbapi.com.batman.R;
import batman.omdbapi.com.batman.base.BaseMVPFragment;
import batman.omdbapi.com.batman.data.model.local.Detail;
import batman.omdbapi.com.batman.di.component.ActivityComponent;
import batman.omdbapi.com.batman.ui.adapter.MainItemListAdapter;
import batman.omdbapi.com.batman.ui.adapter.RateListAdapter;
import batman.omdbapi.com.batman.utils.GeneralUtils;


public class DetailFragment extends BaseMVPFragment<DetailContract.Presenter> implements DetailContract.View {
    private View inflatedView;
    TextView tvItemTitle,
            tvItemYear,
            tvItemRated,
            tvItemReleased,
            tvItemRuntime,
            tvItemGenre,
            tvItemDirector,
            tvItemWriter,
            tvItemActors,
            tvItemLanguage,
            tvItemCountry,
            tvItemAwards;
    ImageView ivPoster;
    RecyclerView rv_rate;

    @Inject
    DetailContract.Presenter mPresenter;
    String itemID;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_detail, container, false);
        initViews();
        itemID = getArguments().getString("id");
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);

            mPresenter.loadPage(false, itemID);
        }
        return inflatedView;
    }


    @Override
    protected void initViews() {
        tvItemTitle = inflatedView.findViewById(R.id.tvItemTitle);
        tvItemYear = inflatedView.findViewById(R.id.tvItemYear);
        tvItemRated = inflatedView.findViewById(R.id.tvItemRated);
        tvItemReleased = inflatedView.findViewById(R.id.tvItemReleased);
        tvItemRuntime = inflatedView.findViewById(R.id.tvItemRuntime);
        tvItemGenre = inflatedView.findViewById(R.id.tvItemGenre);
        tvItemDirector = inflatedView.findViewById(R.id.tvItemDirector);
        tvItemWriter = inflatedView.findViewById(R.id.tvItemWriter);
        tvItemActors = inflatedView.findViewById(R.id.tvItemActors);
        tvItemLanguage = inflatedView.findViewById(R.id.tvItemLanguage);
        tvItemCountry = inflatedView.findViewById(R.id.tvItemCountry);
        tvItemAwards = inflatedView.findViewById(R.id.tvItemAwards);
        ivPoster = inflatedView.findViewById(R.id.ivPoster);
        rv_rate = inflatedView.findViewById(R.id.rv_rate);
    }

    @Override
    public void refreshPage(@NonNull Detail detail) {
        GeneralUtils.loadImageFromLink(getContext(), ivPoster, detail.getItemPoster());
        tvItemTitle.setText(detail.getItemTitle());
        tvItemYear.setText(detail.getItemYear());
        tvItemRated.setText(detail.getItemRated());
        tvItemReleased.setText(detail.getItemReleased());
        tvItemRuntime.setText(detail.getItemRuntime());
        tvItemGenre.setText(detail.getItemGenre());
        tvItemDirector.setText(detail.getItemDirector());
        tvItemWriter.setText(detail.getItemWriter());
        tvItemActors.setText(detail.getItemActors());
        tvItemLanguage.setText(detail.getItemLanguage());
        tvItemCountry.setText(detail.getItemCountry());
        tvItemAwards.setText(detail.getItemAwards());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RateListAdapter itemAdapter = new RateListAdapter(getContext(), detail.getItemRates());

        rv_rate.setLayoutManager(linearLayoutManager);
        rv_rate.setNestedScrollingEnabled(false);
        rv_rate.setAdapter(itemAdapter);
    }

    @Override
    public void showEmptyMsg() {

    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
