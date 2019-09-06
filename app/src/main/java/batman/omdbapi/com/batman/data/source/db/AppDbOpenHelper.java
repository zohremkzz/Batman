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
package batman.omdbapi.com.batman.data.source.db;

import android.arch.persistence.room.Room;
import android.content.Context;


import javax.inject.Inject;
import javax.inject.Singleton;

import batman.omdbapi.com.batman.di.ApplicationContext;
import batman.omdbapi.com.batman.di.madule.DatabaseInfo;



@Singleton
public class AppDbOpenHelper {

    private final AppDatabase mDatabase;

    @Inject
    public AppDbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        mDatabase = Room.databaseBuilder(context, AppDatabase.class,name)
                .build();
    }

    public AppDatabase getDatabase() {
        return mDatabase;
    }
}
