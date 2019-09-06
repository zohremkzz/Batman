package batman.omdbapi.com.batman.data.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import batman.omdbapi.com.batman.data.model.local.Detail;

public class ResponseDetailItemHolder implements Serializable {

    private Detail detail;

    public ResponseDetailItemHolder(Detail detail) {
        this.detail = detail;
    }

    public Detail getDetail() {
        return detail;
    }
}
