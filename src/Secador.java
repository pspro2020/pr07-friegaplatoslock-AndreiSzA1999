public class Secador implements Runnable{

    Bandejas bandejas;


    public Secador(Bandejas bandeja){

        this.bandejas= bandeja;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){

            try {
                bandejas.platosseco();
            } catch (InterruptedException e) {
                return;
            }


        }
    }


}
