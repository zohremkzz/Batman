package batman.omdbapi.com.batman.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import batman.omdbapi.com.batman.R;
import batman.omdbapi.com.batman.data.model.local.Rate;

public class RateListAdapter extends RecyclerView.Adapter<RateListAdapter.MyViewHolder> {
    private Context mContext;
    List<Rate> rateList;

    public RateListAdapter(Context mContext, List<Rate> rateList) {
        this.mContext = mContext;
        this.rateList = rateList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rate, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Rate item = rateList.get(i);
        if (item != null) {
            myViewHolder.rateTitle.setText(item.getItemSource());
            myViewHolder.rateValue.setText(item.getItemValue());
        }

    }

    @Override
    public int getItemCount() {
        return rateList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rateTitle, rateValue;


        MyViewHolder(View itemView) {
            super(itemView);
            rateTitle = itemView.findViewById(R.id.tv_rate);
            rateValue = itemView.findViewById(R.id.tv_rateValue);

        }
    }
}
