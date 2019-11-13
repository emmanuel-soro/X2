package ar.edu.soa.interfaces;

import ar.edu.soa.model.Imagen;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {


    @GET("/getPhoto")
    Call<ResponseBody> getPhoto(@Query("id") String photoID);


}
