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
package batman.omdbapi.com.batman.ui.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import batman.omdbapi.com.batman.R;
import batman.omdbapi.com.batman.data.model.local.MovieItem;
import batman.omdbapi.com.batman.utils.GeneralUtils;


public class MainItemListAdapter extends RecyclerView.Adapter<MainItemListAdapter.MyViewHolder> {

    private Context mContext;
    private List<MovieItem> mItems;
    setOnRowClicked rowClicked;

    public MainItemListAdapter(Context mContext, List<MovieItem> items, setOnRowClicked rowClicked) {
        this.mContext = mContext;
        this.mItems = items;
        this.rowClicked = rowClicked;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MovieItem item = mItems.get(position);
        if (item != null) {
            holder.id.setText(item.getItemTitle());
            holder.name.setText(item.getItemType() + " / " + item.getItemYear());
            GeneralUtils.loadImageFromLink(mContext, holder.image, item.getItemPoster());
        }
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowClicked.onClick(item.getItemId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * View Holder for recycler view.
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView id, name;
        ConstraintLayout row;

        MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            id = itemView.findViewById(R.id.item_id);
            name = itemView.findViewById(R.id.item_name);
            row = itemView.findViewById(R.id.item_row);
        }
    }

    public interface setOnRowClicked {
        void onClick(String itemId);
    }

}