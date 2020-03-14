package ir.milad.androidexamples.application;

import javax.inject.Inject;

import ir.milad.androidexamples.application.di.ApplicationComponent;
import ir.milad.androidexamples.application.di.ApplicationModule;
import ir.milad.androidexamples.application.di.DaggerApplicationComponent;
import retrofit2.Retrofit;

public class Application extends android.app.Application {

    ApplicationComponent component;

    @Inject
    Retrofit injectRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();
        Retrofit retrofit = component.getRetrofit();
        Retrofit retrofit2 = component.getRetrofit();
        //when using application scope only one instance will be created

        component.inject(this);
    }
}
