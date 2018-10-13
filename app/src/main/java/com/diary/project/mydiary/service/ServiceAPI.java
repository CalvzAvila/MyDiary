package com.diary.project.mydiary.service;

import com.diary.project.mydiary.model.AddNoteModel;
import com.diary.project.mydiary.model.DiaryListModel;
import com.diary.project.mydiary.model.LoginModel;
import com.diary.project.mydiary.model.RegisterModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceAPI {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterModel> isRegister(@Field("username") String username,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginModel> isLogin(@Field("username") String username,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("saveNote.php")
    Call<AddNoteModel> isAddNotes(@Field("title") String title,
                                  @Field("notes") String notes);


    @FormUrlEncoded
    @POST("getData.php")
    Call<ArrayList<DiaryListModel>>  isDiaryList(@Field("title") String title,
                                                @Field("notes") String notes);





}
