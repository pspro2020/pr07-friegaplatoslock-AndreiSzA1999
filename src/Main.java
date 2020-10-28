import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Bandejas bandejas = new Bandejas();

        Thread fregador  = new Thread(new Fregador(bandejas));
        Thread secador = new Thread(new Secador(bandejas));
        Thread organizador = new Thread(new Organizador(bandejas));

        fregador.setName("Jose");
        secador.setName("Antonio");
        organizador.setName("Luis");

        fregador.start();
        secador.start();
        organizador.start();


        TimeUnit.SECONDS.sleep(60);

        fregador.interrupt();
        secador.interrupt();
        organizador.interrupt();

        fregador.join();
        secador.join();
        organizador.join();

        System.out.println("Feliz cumpleaños!");
    }
}
