package ferreira.filipe.jornaldeofertas.servicos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Filipe on 13/08/2017.
 */

public class BroadCastService extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){

            Log.d("esta no BoadCast","antes de criar intent");


            Intent it = new Intent(context.getApplicationContext(), VerificaOfertasService.class);
            context.startService(it);

            Toast.makeText(context, "broadCast executado", Toast.LENGTH_SHORT).show();


        }else{
            Log.d("esta no BoadCast","sem internet");
        }
    }
}


