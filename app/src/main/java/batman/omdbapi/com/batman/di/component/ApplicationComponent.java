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
package batman.omdbapi.com.batman.di.component;;

import android.app.Application;
import android.content.Context;



import javax.inject.Singleton;

import batman.omdbapi.com.batman.base.MainApplication;
import batman.omdbapi.com.batman.data.source.repository.AppRepository;
import batman.omdbapi.com.batman.di.ApplicationContext;
import batman.omdbapi.com.batman.di.madule.ApplicationModule;
import batman.omdbapi.com.batman.di.madule.DataModule;
import batman.omdbapi.com.batman.di.madule.NetworkModule;
import dagger.Component;



@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(MainApplication app);

    @ApplicationContext
    Context context();

    Application application();

    AppRepository getAppRepository();

}
