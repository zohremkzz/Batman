package batman.omdbapi.com.batman.data.source.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import batman.omdbapi.com.batman.data.model.local.Rate;

public class Converters {
  @TypeConverter
  public static List<Rate> fromString(String value) {
    Type listType = new TypeToken<List<Rate>>() {}.getType();
    return new Gson().fromJson(value, listType);
  }

  @TypeConverter
  public static String fromArrayLisr(List<Rate> list) {
    Gson gson = new Gson();
    String json = gson.toJson(list);
    return json;
  }
}