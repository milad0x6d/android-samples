package ir.milad.androidexamples.model;

import retrofit2.Retrofit;

public class CredentialsManager {

    public RestService getService(Retrofit retrofit){
        return retrofit.create(RestService.class);
    }

}
