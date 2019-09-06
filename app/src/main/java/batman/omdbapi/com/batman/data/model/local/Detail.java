package batman.omdbapi.com.batman.data.model.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import batman.omdbapi.com.batman.data.source.db.Converters;


@Entity(tableName = "detail")
public class Detail {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "itemid")
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
    @ColumnInfo(name = "item_rated")
    @SerializedName("Rated")
    @Expose
    private String itemRated;

    @NonNull
    @ColumnInfo(name = "item_released")
    @SerializedName("Released")
    @Expose
    private String itemReleased;

    @NonNull
    @ColumnInfo(name = "item_runtime")
    @SerializedName("Runtime")
    @Expose
    private String itemRuntime;

    @NonNull
    @ColumnInfo(name = "item_genre")
    @SerializedName("Genre")
    @Expose
    private String itemGenre;

    @NonNull
    @ColumnInfo(name = "item_director")
    @SerializedName("Director")
    @Expose
    private String itemDirector;

    @NonNull
    @ColumnInfo(name = "item_writer")
    @SerializedName("Writer")
    @Expose
    private String itemWriter;

    @NonNull
    @ColumnInfo(name = "item_actors")
    @SerializedName("Actors")
    @Expose
    private String itemActors;

    @NonNull
    @ColumnInfo(name = "item_language")
    @SerializedName("Language")
    @Expose
    private String itemLanguage;

    @NonNull
    @ColumnInfo(name = "item_country")
    @SerializedName("Country")
    @Expose
    private String itemCountry;

    @NonNull
    @ColumnInfo(name = "item_awards")
    @SerializedName("Awards")
    @Expose
    private String itemAwards;


    @NonNull
    @ColumnInfo(name = "item_poster")
    @SerializedName("Poster")
    @Expose
    private String itemPoster;

    @ColumnInfo(name = "item_rows")
    @SerializedName("Ratings")
    @TypeConverters(Converters.class)
    private List<Rate> itemRates;


    @NonNull
    public String getItemTitle() {
        return itemTitle;
    }

    @NonNull
    public String getItemYear() {
        return itemYear;
    }

    @NonNull
    public String getItemRated() {
        return itemRated;
    }

    @NonNull
    public String getItemReleased() {
        return itemReleased;
    }

    @NonNull
    public String getItemRuntime() {
        return itemRuntime;
    }

    @NonNull
    public String getItemGenre() {
        return itemGenre;
    }

    @NonNull
    public String getItemDirector() {
        return itemDirector;
    }

    @NonNull
    public String getItemWriter() {
        return itemWriter;
    }

    @NonNull
    public String getItemActors() {
        return itemActors;
    }

    @NonNull
    public String getItemLanguage() {
        return itemLanguage;
    }

    @NonNull
    public String getItemCountry() {
        return itemCountry;
    }

    @NonNull
    public String getItemAwards() {
        return itemAwards;
    }

    @NonNull
    public String getItemPoster() {
        return itemPoster;
    }

    public void setItemTitle(@NonNull String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setItemYear(@NonNull String itemYear) {
        this.itemYear = itemYear;
    }

    public void setItemRated(@NonNull String itemRated) {
        this.itemRated = itemRated;
    }

    public void setItemReleased(@NonNull String itemReleased) {
        this.itemReleased = itemReleased;
    }

    public void setItemRuntime(@NonNull String itemRuntime) {
        this.itemRuntime = itemRuntime;
    }

    public void setItemGenre(@NonNull String itemGenre) {
        this.itemGenre = itemGenre;
    }

    public void setItemDirector(@NonNull String itemDirector) {
        this.itemDirector = itemDirector;
    }

    public void setItemWriter(@NonNull String itemWriter) {
        this.itemWriter = itemWriter;
    }

    public void setItemActors(@NonNull String itemActors) {
        this.itemActors = itemActors;
    }

    public void setItemLanguage(@NonNull String itemLanguage) {
        this.itemLanguage = itemLanguage;
    }

    public void setItemCountry(@NonNull String itemCountry) {
        this.itemCountry = itemCountry;
    }

    public void setItemAwards(@NonNull String itemAwards) {
        this.itemAwards = itemAwards;
    }

    public void setItemPoster(@NonNull String itemPoster) {
        this.itemPoster = itemPoster;
    }




    public List<Rate> getItemRates() {
        return itemRates;
    }

    public void setItemRates(List<Rate> itemRates) {
        this.itemRates = itemRates;
    }

    @NonNull
    public String getItemId() {
        return itemId;
    }

    public void setItemId(@NonNull String itemId) {
        this.itemId = itemId;
    }
}
