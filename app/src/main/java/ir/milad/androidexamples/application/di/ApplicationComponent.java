package ir.milad.androidexamples.application.di;

import dagger.Component;
import ir.milad.androidexamples.RestApplication;
import ir.milad.androidexamples.application.Application;
import retrofit2.Retrofit;

@ApplicationScope
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    Retrofit getRetrofit();

    //uses inject
    void inject(Application application);

    void inject(RestApplication restApplication);
}
