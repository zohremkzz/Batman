package batman.omdbapi.com.batman.data.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import batman.omdbapi.com.batman.data.model.local.MovieItem;

public class ResponseMovieItemHolder implements Serializable {
    @SerializedName("Search")
    @Expose
    private List<MovieItem> items;

    public ResponseMovieItemHolder(ArrayList<MovieItem> items) {
        this.items = items;
    }

    public List<MovieItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<MovieItem> items) {
        this.items = items;
    }
}
