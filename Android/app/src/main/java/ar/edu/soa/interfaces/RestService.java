package ar.edu.soa.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {

    @GET("/getLastPhoto")
    Call<ResponseBody> getLastPhoto(@Query("id") String photoID);

    @GET("/dato")
    Call<ResponseBody> getDato(@Query("id") String value);


}
