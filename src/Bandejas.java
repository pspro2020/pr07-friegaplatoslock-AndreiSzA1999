import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bandejas {




    Random random = new Random();

    ArrayList<Plato> bandejalimpios = new ArrayList<Plato>();
    ArrayList<Plato> bandejasecos = new ArrayList<Plato>();
    ArrayList<Plato> alacena = new ArrayList<Plato>();

    DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");


    protected final ReentrantLock bloqueador = new ReentrantLock(true);
    protected final Condition limpios = bloqueador.newCondition();
    protected final Condition secos = bloqueador.newCondition();




    public void platoslimpios(Plato plato) throws InterruptedException {

        bloqueador.lock();

        bandejalimpios.add(plato);

        System.out.printf("%s => %s Se ha lavado el plato nº %d \n", LocalTime.now().format(hora),Thread.currentThread().getName(), plato.getN_serie());

        limpios.signal();
        bloqueador.unlock();

    }

    public void platosseco() throws InterruptedException {

        bloqueador.lock();

        while(bandejalimpios.isEmpty()){
            System.out.println("Esperando plato limpio");
            limpios.await();

        }

        bandejasecos.add(bandejalimpios.get(0));
        TimeUnit.SECONDS.sleep(random.nextInt(3)+1);
        System.out.printf("%s => %s  Se ha secado el plato nº %d \n",LocalTime.now().format(hora),Thread.currentThread().getName(), bandejasecos.get(0).getN_serie());
        bandejalimpios.remove(0);
        secos.signal();
        bloqueador.unlock();

    }


    public  void platolisto() throws InterruptedException {

        bloqueador.lock();

        while(bandejasecos.isEmpty()){

            System.out.println("Esperando plato seco");
          secos.await();
        }

        alacena.add(bandejasecos.get(0));
        TimeUnit.SECONDS.sleep(random.nextInt(2)+1);
        System.out.printf("%s => %s Se ha añadido el plato nº %d a la alacena\n",LocalTime.now().format(hora),Thread.currentThread().getName(), bandejasecos.get(0).getN_serie());
        bandejasecos.remove(0);
        secos.signal();
        bloqueador.unlock();
    }

}
