package com.vhps.conecta.ecobici;

import com.vhps.conecta.ecobici.schema.Ecobici;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Esta interface obtiene la peticion GET de retrofit
 *
 */


public interface EcoBici {
    /**
     * metodo getEcobici que obtiene la peticion GET.
     * @return Se retorna el json de la url indicada.
     */

    //método de  petición "GET" de Retrofit, se ingresa la dirección que se ingresará en la url
    @GET("networks/ecobici/")
    //este metodo retornará lo que haya en la petición "GET"
    Call<Ecobici> getEcobici();
}
