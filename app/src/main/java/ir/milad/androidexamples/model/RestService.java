package ir.milad.androidexamples.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {
    @GET("/room/microdoers")
    Call<CredentialsObject> getCreddentials();
}
