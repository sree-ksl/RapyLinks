package com.ksl.payrapyd;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import retrofit2.http.Tag;

import static android.content.ContentValues.TAG;

public class GetPOS {

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[]args)throws Exception {
        try {
            System.out.println("GetPOS Start");
            String salt = givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect(); // Randomly generated for each request.
            long timestamp = System.currentTimeMillis() / 1000L; // Unix time.
            String accessKey = "10FBB5A43C699B66FF6C";                    // The access key received from Rapyd.
            String secretKey = "41ee21f3f99348229e0c2a1a3be030743e06ba76d55c039115e30811772f2d7b2ad6a81b2ac1b676";                    // Never transmit the secret key by itself.
            String toEnc = "get/v1/data/countries" + salt + Long.toString(timestamp) + accessKey + secretKey;
            System.out.println("String TO BE Encrypted::" + toEnc);
            String StrhashCode = hmacDigest("get/v1/data/countries" + salt +
                    Long.toString(timestamp) +
                    accessKey +
                    secretKey, "HmacSHA256",null);
            String signature = Base64.getEncoder().encodeToString(StrhashCode.getBytes());
//            HttpClient httpclient = HttpClients.createDefault();
//
//            try {
//                HttpGet httpget = new HttpGet("https://sandboxapi.rapyd.net/v1/checkout");
//
//                httpget.addHeader("Content-Type", "application/json");
//                httpget.addHeader("access_key", accessKey);
//                httpget.addHeader("salt", salt);
//                httpget.addHeader("timestamp", Long.toString(timestamp));
//                httpget.addHeader("signature", signature);
//
//                // Create a custom response handler
//                ResponseHandler < String > responseHandler = new ResponseHandler < String > () {
//                    @ Override
//                    public String handleResponse(
//                            final HttpResponse response)throws ClientProtocolException,
//                            IOException {
//                        int status = response.getStatusLine().getStatusCode();
//                        HttpEntity entity = response.getEntity();
//                        return entity != null ? EntityUtils.toString(entity) : null;
//                    }
//                };
//                String responseBody = httpclient.execute(httpget, responseHandler);
                System.out.println("----------------------------------------");
//                System.out.println(responseBody);
//                Log.d(TAG, "Response: " + responseBody);
            }
            finally {
            }
//        } catch (Exception e) {
//        }
    }
}