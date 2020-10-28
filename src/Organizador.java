public class Organizador implements Runnable {
    Bandejas bandejas;

    public Organizador(Bandejas bandeja){

        this.bandejas= bandeja;

    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){

            try {
                bandejas.platolisto();
            } catch (InterruptedException e) {
                return;
            }


        }
    }


}


