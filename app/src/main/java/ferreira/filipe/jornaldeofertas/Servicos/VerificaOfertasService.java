package ferreira.filipe.jornaldeofertas.servicos;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Base64;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ferreira.filipe.jornaldeofertas.OfertasActivity;
import ferreira.filipe.jornaldeofertas.R;
import ferreira.filipe.jornaldeofertas.dao.ProdutosDAO;
import ferreira.filipe.jornaldeofertas.entidades.Produto;

/**
 * Created by Filipe on 13/08/2017.
 */

public class VerificaOfertasService extends Service implements Runnable{
    List<Produto> produtos = new ArrayList<>();

    int i = 0;
    boolean resposta;
    Produto produto;

    public void onCreate() {
        new Thread(VerificaOfertasService.this).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void run() {

        Log.d("Thread","entrou na Thread");

        //pega um produto aleatoriamente

        boolean pronto = false;
        ProdutosDAO produtosDAO = new ProdutosDAO();
        produtos = produtosDAO.listar(1, this.getBaseContext(),1);


        while(!produtosDAO.pronto && i < 35){
            try {
                i++;
                Log.d("Thread","aguardando retornar produtos");
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        resposta = produtosDAO.pronto;
        final NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);



        if(resposta){
            //pega um produto aleatoriamente
            Random random = new Random();
            int x = random.nextInt(produtos.size());
            produto = produtos.get(x);

            byte[] imagemEmbytes = Base64.decode(produto.getImagem(), Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(imagemEmbytes, 0, imagemEmbytes.length);

            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, OfertasActivity.class), 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ofertas)
                    .setTicker("Não perca nossas ofertas!")
                    .setContentTitle("Produto: "+produto.getNome())
                    .setContentText("Custando hoje o valor de R$"+String.valueOf(produto.getPreco())+" e não é só isso, venha conferir tudo que preparamos pra você.")
                    .setLargeIcon(bmp)
                    .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                    .setSound(alarmSound)
                    .setAutoCancel( true )
                    .setContentIntent(pi)
                    .setStyle( new NotificationCompat.BigPictureStyle().bigPicture(bmp));




             notifyManager.notify(x, builder.build() );

        }else{

            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, OfertasActivity.class), 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ofertas)
                    .setTicker("Não perca nossas ofertas!")
                    .setContentTitle("Jornal de ofertas")
                    .setContentText("venha conferir tudo que preparamos pra você.")
                    .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                    .setSound(alarmSound)
                    .setAutoCancel( true )
                    .setContentIntent(pi);

            notifyManager.notify(0, builder.build() );
        }



    }
}
