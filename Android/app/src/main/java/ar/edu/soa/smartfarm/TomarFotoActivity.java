package ar.edu.soa.smartfarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import ar.edu.soa.interfaces.RestService;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TomarFotoActivity extends AppCompatActivity {

    Button botonVolver;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomar_foto);

        imageView = (ImageView) findViewById(R.id.imageDisplay);

        botonVolver = (Button) findViewById(R.id.botonVolver);

        botonVolver.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent mainIntent = new Intent(TomarFotoActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        this.getPhoto();
    }


    private void getPhoto() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8087")
                .client(client)
                .build();

        RestService restService = retrofit.create(RestService.class);
        Call<ResponseBody> call = restService.getPhoto("20191012115947");


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                System.out.println("Hubo respuesta exitosa " + response.isSuccessful());
                System.out.println("Codigo respuesta " + response.code());

                if (response.isSuccessful()) {

                    // display the image data in a ImageView or save it
                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                    imageView.setImageBitmap(bmp);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                System.out.println("request failed: " + t.getMessage());
                System.out.println("Anda mal la toma de foto");
            }
        });
    }

}