package com.ksl.payrapyd;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RapydInterface {

    @Headers({
            "Content-Type: application/json"
    })
    @POST("/checkout")
    Call<CheckoutBody> getCheckoutPage(@Header ("access-key") String access_key ,
                                             @Header("signature") String signature,
                                             @Header("salt") String salt,
                                             @Header("timestamp") String timestamp,
                                             @Body CheckoutBody checkoutBody);

    // collect sdk url


}
