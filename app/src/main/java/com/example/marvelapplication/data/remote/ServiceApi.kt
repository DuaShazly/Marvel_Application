package com.example.marvelapplication.data.remote

import com.example.marvelapplication.data.model.characters.MarvelCharactersModel
import com.example.marvelapplication.data.model.comics.ComicsModel
import com.example.marvelapplication.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ServiceApi {

    //Get All Characters
    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String = Constants.API_PUBLIC_KEY,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = Constants.PAGE_SIZE,
        @Query("offset") offset: Int? = 0): Response<MarvelCharactersModel>

    //Get All Comics
    @GET("characters/{characterId}/comics")
    suspend fun getComics(
        @Path("characterId") characterId: Int,
        @Query("apikey") apiKey: String = Constants.API_PUBLIC_KEY,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("dateRange") dateRange: String,

    ): Response<ComicsModel>

    @GET("characters")
    suspend fun searchCharacter2(
        @Query("nameStartsWith") query: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): Response<MarvelCharactersModel>


    @GET("characters")
    suspend fun searchCharacter(@Query("ts") ts: String,
                                        @Query("hash") hash: String,@Url url:String): Response<MarvelCharactersModel>

}