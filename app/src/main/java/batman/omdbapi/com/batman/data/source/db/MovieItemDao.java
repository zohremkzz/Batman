package batman.omdbapi.com.batman.data.source.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import batman.omdbapi.com.batman.data.model.local.MovieItem;
import io.reactivex.Flowable;
@Dao
public interface MovieItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSingleItem(MovieItem item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMultipleItem(List<MovieItem> movieItemList);

    @Query("SELECT * FROM Movie WHERE item_id = :itemId")
    Flowable<MovieItem> fetchItemByItemId(int itemId);

    @Query("SELECT * FROM Movie")
    Flowable<List<MovieItem>> fetchItems();


}
