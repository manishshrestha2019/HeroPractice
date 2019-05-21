package api;

import java.util.List;
import java.util.Map;

import model.Heroes;
import model.ImageResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface HeroesAPI {
    //using object
    @POST("heroes")
    Call<Void> addHero(@Body Heroes employees);

    //using Field
    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addHeroField(@Field("name")String name, @Field("desc")String desc);

    //using FieldMap
    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addHeroFieldMap(@FieldMap Map<String, String> map);

    //For image upload
    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    //Get and Display
    @GET("heroes")
    Call<List<Heroes>> getAllHeroes();
}
