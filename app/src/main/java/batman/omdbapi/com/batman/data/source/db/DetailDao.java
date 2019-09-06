package batman.omdbapi.com.batman.data.source.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import batman.omdbapi.com.batman.data.model.local.Detail;
import io.reactivex.Single;

@Dao
public interface DetailDao {


    @Query("SELECT * FROM detail WHERE itemid=:id")
    Single<Detail> fetchItemByItemId(String id);



}