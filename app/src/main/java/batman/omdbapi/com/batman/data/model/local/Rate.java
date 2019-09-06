package batman.omdbapi.com.batman.data.model.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rate {


    @SerializedName("Source")
    @Expose
    private String itemSource;


    @SerializedName("Value")
    @Expose
    private String itemValue;


    public String getItemSource() {
        return itemSource;
    }

    public String getItemValue() {
        return itemValue;
    }
}
