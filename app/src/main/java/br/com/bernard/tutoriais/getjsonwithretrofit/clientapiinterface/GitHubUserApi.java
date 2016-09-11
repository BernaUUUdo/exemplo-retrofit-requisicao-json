package br.com.bernard.tutoriais.getjsonwithretrofit.clientapiinterface;

import br.com.bernard.tutoriais.getjsonwithretrofit.model.GitHubUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bernard on 11/09/16.
 */
public interface GitHubUserApi {

    String ENDPOINT = "https://api.github.com";

    @GET("/users/{user}")
    Call<GitHubUser> getUser(@Path("user") String user);

}
