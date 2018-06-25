package com.vhps.conecta.ecobici;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vhps.conecta.ecobici.schema.Ecobici;
import com.vhps.conecta.ecobici.schema.Location;
import com.vhps.conecta.ecobici.schema.Network;
import com.vhps.conecta.ecobici.schema.Station;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Actividad principal en donde se muestra el mapa
 * @author Victor Hugo Polito Seba
 * @version 1.0
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private View view;
    private MapView mapView;
    private GoogleMap gMap;
    private String baseUrl="https://api.citybik.es/v2/";
    private EcoBici ecoBici;
    private Double longCdmx;
    private Double latiCdmx;
    private List<Station> stations;
    private MarkerOptions marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * MEtodo para obtener los datos de Retrofit, encolando las Calls
     */


    private void obtenerDatos() {
        //Se llama a los datos obtenidos de la petición del API
        Call<Ecobici> datosEcoBici=ecoBici.getEcobici();
        datosEcoBici.enqueue(new Callback<Ecobici>() {
            @Override
            public void onResponse(Call<Ecobici> call, Response<Ecobici> response) {
                //si la desearilización es correcta se inicializan las variables de la ecobici
                if(response.isSuccessful()){
                    Ecobici eco=response.body();
                    Network net=eco.getNetwork();
                    Location location=net.getLocation();
                    stations =net.getStations();
                    longCdmx =location.getLongitude();
                    latiCdmx =location.getLatitude();

                }else{
                    Toast.makeText(getBaseContext(),"Error de respuesta", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Ecobici> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }




    /**
     * Se manipula el mapa, este metodo se ejecuta asincronicamente.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        //-----------Se construye Retofit-------------
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ecoBici=retrofit.create(EcoBici.class);
        //--------------------------------------------
        obtenerDatos();


        //se crean las propiedades de inicio del mapa
        gMap=googleMap;
        //se agrega la ubicacion de Mexico
        LatLng ubicacionInicial=new LatLng(latiCdmx,longCdmx);
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

        //-----Se crea un marcador para todas las cicloestaciones----------------
        for(int i=0;i<stations.size();i++){
            Station station=stations.get(i);
            LatLng ubicacion=new LatLng(station.getLatitude(),station.getLongitude());

            marker.position(ubicacion);
            marker.title(station.getName());
            marker.snippet("Bicletas disponibles:"+station.getFreeBikes());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_icono_bici));
            gMap.addMarker(marker);
        }
        //-----------------------------------------------------------------------

        //------Se inicializa el mapa en el centro de la ciudad de Mexico.
        gMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacionInicial));
    }
}
