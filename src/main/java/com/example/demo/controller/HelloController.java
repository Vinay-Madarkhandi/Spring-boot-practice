package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@RestController
public class HelloController {

   private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
//    private User user ;
//
//    @GetMapping("/welcome")
//    public User welcome() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        IAPIInterface apiservice = retrofit.create(IAPIInterface.class);
//
//        Call<User> call = apiservice.getResults();
//
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
//
//                if(response.isSuccessful()){
//                    user = response.body();
//                } else {
//                    System.out.print("cant fetch the data!");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                System.err.print("Error");
//                t.printStackTrace();
//            }
//        });
//
//        return user;
//    }

    @GetMapping("/welcome")
    public Todo welcome() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAPIInterface apiservice = retrofit.create(IAPIInterface.class);

        Call<Todo> call = apiservice.getResults();

        Response<Todo> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new RuntimeException("Failed to fetch data");
        }
    }

}
