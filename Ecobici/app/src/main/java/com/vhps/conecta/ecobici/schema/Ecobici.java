package com.vhps.conecta.ecobici.schema;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 *  * Clase Ecobici que sirve como modelo del Json obtenido.

 */

public class Ecobici {

    @SerializedName("network")
    @Expose
    private Network network;

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

}