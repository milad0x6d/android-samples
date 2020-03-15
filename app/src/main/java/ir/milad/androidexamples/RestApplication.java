package ir.milad.androidexamples;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import ir.milad.androidexamples.application.di.ApplicationComponent;
import ir.milad.androidexamples.application.di.ApplicationModule;
import ir.milad.androidexamples.application.di.DaggerApplicationComponent;
import ir.milad.androidexamples.model.CredentialsManager;
import ir.milad.androidexamples.model.CredentialsObject;
import ir.milad.androidexamples.model.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApplication extends Application {

    @Inject
    Gson gson;
    @Inject
    GsonConverterFactory gsonConverterFactory;
    @Inject
    Retrofit retrofit;
    @Inject
    CredentialsManager manager;

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

//        gson = new GsonBuilder().create();
//        gsonConverterFactory = GsonConverterFactory.create(gson);
//        retrofit = new Retrofit.Builder()
//                .baseUrl("https://www.thirdeyegen.com:8080")
//                .addConverterFactory(gsonConverterFactory)
//                .build();
//
//        manager = new CredentialsManager();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();

        component.inject(this);
        

        RestService restService = manager.getService(retrofit);

        Call<CredentialsObject> call = restService.getCreddentials();

        call.enqueue(new Callback<CredentialsObject>() {
            @Override
            public void onResponse(Call<CredentialsObject> call, Response<CredentialsObject> response) {
                CredentialsObject credentialsObject = response.body();
                Log.d("response data","onResponse: apiKey "+credentialsObject.getApiKey());
                Log.d("response data","onResponse: sessionId "+credentialsObject.getSessionId());
                Log.d("response data","onResponse: token "+credentialsObject.getToken());
            }

            @Override
            public void onFailure(Call<CredentialsObject> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });

    }
}
