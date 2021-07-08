package com.ksl.payrapyd;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    public static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    public static String bytesToHex(byte[]bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt: bytes)
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    public static String hmacDigest(String msg, String keyString, String algo) {
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("ASCII"), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(key);

            byte[]bytes = mac.doFinal(msg.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (UnsupportedEncodingException e) {
            System.out.println("hmacDigest UnsupportedEncodingException");
        }
        catch (InvalidKeyException e) {
            System.out.println("hmacDigest InvalidKeyException");
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("hmacDigest NoSuchAlgorithmException");
        }
        return digest;
    }

    public static String givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {
        int leftLimit = 97;   // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char)randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return (generatedString);
    }


    String salt = givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect(); // Randomly generated for each request.
    long timestamp = System.currentTimeMillis() / 1000L; // Unix time.
    String accessKey = "10FBB5A43C699B66FF6C";                    // The access key received from Rapyd.
    String secretKey = "41ee21f3f99348229e0c2a1a3be030743e06ba76d55c039115e30811772f2d7b2ad6a81b2ac1b676";                    // Never transmit the secret key by itself.
    String toEnc = "/checkout" + salt + Long.toString(timestamp) + accessKey + secretKey;
    String StrhashCode = hmacDigest("/checkout" + salt +
            Long.toString(timestamp) +
            accessKey +
            secretKey, "HmacSHA256",toEnc);
    String signature = Base64.getEncoder().encodeToString(StrhashCode.getBytes());

    CheckoutBody checkoutBody = new CheckoutBody(20, "US", "USD");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // generate checkout on button click
        Button checkout = findViewById(R.id.checkoutBtn);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkout();
            }
        });

    }

    private void checkout() {
        RetrofitClient.getRapydClient().getCheckoutPage(accessKey, signature, salt, Long.toString(timestamp), checkoutBody).enqueue(new Callback<CheckoutBody>() {
            @Override
            public void onResponse(Call<CheckoutBody> call, Response<CheckoutBody> response) {
                Log.e(TAG, "Response: " + response.body());
                if(response.isSuccessful() && response.body() != null){
                    Toast.makeText(MainActivity.this, "Checkout Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckoutBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}