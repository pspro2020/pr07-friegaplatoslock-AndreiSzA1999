import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Fregador implements Runnable {
    Random random = new Random();
    protected int idplato = 0;
    private Bandejas bandejas;
    Plato plato;




    public Fregador(Bandejas bandeja){

        this.bandejas= bandeja;

    }


    @Override
    public synchronized void run() {

        while (!Thread.currentThread().isInterrupted()){

            plato = new Plato(idplato);

            try {

                bandejas.platoslimpios(plato);
                idplato++;
                TimeUnit.SECONDS.sleep(random.nextInt(8)+1);

            } catch (InterruptedException e) {

                return;

            }

        }
    }





}
