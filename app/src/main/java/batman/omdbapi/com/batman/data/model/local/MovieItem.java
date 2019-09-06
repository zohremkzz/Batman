package batman.omdbapi.com.batman.data.model.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "Movie")
public class MovieItem {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "item_id")
    @SerializedName("imdbID")
    @Expose
    private String itemId;

    @NonNull
    @ColumnInfo(name = "item_title")
    @SerializedName("Title")
    @Expose
    private String itemTitle;

    @NonNull
    @ColumnInfo(name = "item_year")
    @SerializedName("Year")
    @Expose
    private String itemYear;

    @NonNull
    @ColumnInfo(name = "item_type")
    @SerializedName("Type")
    @Expose
    private String itemType;

    @NonNull
    @ColumnInfo(name = "item_poster")
    @SerializedName("Poster")
    @Expose
    private String itemPoster;

    public MovieItem(@NonNull String itemId,
                     @NonNull String itemTitle,
                     @NonNull String itemYear,
                     @NonNull String itemType,
                     @NonNull String itemPoster) {

        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemYear = itemYear;
        this.itemType = itemType;
        this.itemPoster = itemPoster;

    }

    @NonNull
    public String getItemId() {
        return itemId;
    }

    @NonNull
    public String getItemTitle() {
        return itemTitle;
    }

    @NonNull
    public String getItemYear() {
        return itemYear;
    }

    @NonNull
    public String getItemType() {
        return itemType;
    }

    @NonNull
    public String getItemPoster() {
        return itemPoster;
    }
}
