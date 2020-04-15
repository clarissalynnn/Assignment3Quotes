package com.example.assignment3quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment3quotes.Entities.QuotesResponse;
import com.example.assignment3quotes.Entities.QuotesService;

import java.security.cert.Extension;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

private Button button1;
private TextView text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.buttonGen);
        TextView text1 = findViewById(R.id.quotesField);

        //setOnClickListener to trigger the API call
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.chucknorris.io/").addConverterFactory(GsonConverterFactory.create()).build();
                QuotesService service = retrofit.create(QuotesService.class);
                Call<QuotesResponse> quotesResponseCall = service.getResponse();
                quotesResponseCall.enqueue(new Callback<QuotesResponse>() {
                    @Override
                    //if call and response is successful, the text field will display the quotes retrieved from the API
                    //code below is adapted from https://codinginflow.com/tutorials/android/retrofit/part-1-simple-get-request
                    public void onResponse(Call<QuotesResponse> call, Response<QuotesResponse> response) {
                        if(response.isSuccessful()) {
                            String quotesField = "";
                            quotesField = response.body().getValue();
                            text1.setText(quotesField);
                        }
                    }
                    //if failed, the text field will display hrowable message
                    @Override
                    public void onFailure(Call<QuotesResponse> call, Throwable t) {
                        text1.setText(t.getMessage());
                    }
                });
            }
        });

    }


}
