package com.example.demo.controller;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAPIInterface {
    @GET("todos/1")
    Call<Todo> getResults();
}
