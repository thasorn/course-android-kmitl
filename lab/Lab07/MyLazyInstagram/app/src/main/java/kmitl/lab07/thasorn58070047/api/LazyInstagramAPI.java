package kmitl.lab07.thasorn58070047.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Amoeba on 10/6/2017.
 */

public interface LazyInstagramAPI {
    String BASE_URL = "https://us-central1-retrofit-course.cloudfunctions.net";
    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);
}
