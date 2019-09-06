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
package batman.omdbapi.com.batman.base;

import android.app.Application;
import android.content.Context;


import javax.inject.Inject;

import batman.omdbapi.com.batman.data.source.repository.AppRepository;
import batman.omdbapi.com.batman.di.component.ApplicationComponent;


import batman.omdbapi.com.batman.di.component.DaggerApplicationComponent;
import batman.omdbapi.com.batman.di.madule.ApplicationModule;
import batman.omdbapi.com.batman.di.madule.DataModule;
import batman.omdbapi.com.batman.di.madule.NetworkModule;
import batman.omdbapi.com.batman.utils.AppLogger;


public class MainApplication extends Application {

    @Inject
    AppRepository mRepository;

    private static Application sInstance;

    private ApplicationComponent mApplicationComponent;

    // Anywhere in the application where an instance is required, this method
    // can be used to retrieve it.
    public static Application getInstance() {
        return sInstance;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .networkModule(new NetworkModule())
                .build();

        mApplicationComponent.inject(this);

        sInstance = this;

        ((MainApplication) sInstance).initializeInstance();

    }

    // Here we do one-off initialisation which should apply to all activities
    // in the application.
    protected void initializeInstance() {
        //globally initialize the App Logger
        AppLogger.init();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
