package zadanie3;

public class test3 {
    final static int liczbaFilozofow = 5; // w przypadku modyfikacji należy także zmienić komunikaty wypisujące

    public static void main(String[] args) throws InterruptedException {

        final int liczbaPowtorzen = 100;
        final Filozof[] filozofowie = new Filozof[liczbaFilozofow];
        final Widelce instancjaWidelcy = new Widelce(liczbaFilozofow);

        for (int i = 0; i < liczbaFilozofow; i++)
        {
            filozofowie[i] = new Filozof(i, liczbaPowtorzen, instancjaWidelcy);
        }

        for (int i = 0; i < liczbaFilozofow; i++)
        {
            filozofowie[i].start();
        }

        for (int i = 0; i < liczbaFilozofow; i++)
        {
            filozofowie[i].join();
        }

        System.out.println("Wszystkie wątki potomne zakończone, program poprawny");
    }

}
