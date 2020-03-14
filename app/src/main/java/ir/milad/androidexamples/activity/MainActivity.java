package ir.milad.androidexamples.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ir.milad.androidexamples.R;
import ir.milad.androidexamples.activity.di.ActivityComponent;
import ir.milad.androidexamples.activity.di.ActivityModule;
import ir.milad.androidexamples.activity.di.DaggerActivityComponent;
import ir.milad.androidexamples.application.di.ApplicationComponent;
import ir.milad.androidexamples.application.di.ApplicationModule;
import ir.milad.androidexamples.application.di.DaggerApplicationComponent;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ApplicationComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();

        Retrofit re = component.getRetrofit();
    }
}
