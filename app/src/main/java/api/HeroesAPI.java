package api;

import java.util.Map;

import model.Heroes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
