package com.ksl.payrapyd;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://sandboxapi.rapyd.net/v1";
    
    public static Retrofit retrofit = null;
    public static RapydInterface getRapydClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(RapydInterface.class);
    }

}
