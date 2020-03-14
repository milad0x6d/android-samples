package ir.milad.androidexamples.activity.di;

import dagger.Component;
import ir.milad.androidexamples.application.di.ApplicationModule;

@Component(modules = {ActivityModule.class,ApplicationModule.class})
public interface ActivityComponent {

}
