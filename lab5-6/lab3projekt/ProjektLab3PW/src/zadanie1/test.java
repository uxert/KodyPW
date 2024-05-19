package zadanie1;

public class test {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Rozpoczynam uruchomienie programu");

        final int N = 15;// pojemnosc bufora

        final int m = 4; // liczba producentow
        final int n = 5; // liczba konsumentow

        final int liczbaPowtorzenKonsumenta = 100;
        final int liczbaPowtorzenProducenta = (int) (liczbaPowtorzenKonsumenta * n/m);

        Producent[] producenci = new Producent[m];
        Konsument[] konsumenci = new Konsument[n];

        Dane mojeDane = new Dane(N);
        Semafory mojeSemafory = new  Semafory(N);

        for (int i = 0; i < m; i++)
        {
            producenci[i] = new Producent(liczbaPowtorzenProducenta, N, i + 1, mojeSemafory, mojeDane);
            producenci[i].start();
        }
        for (int i = 0; i < n; i++)
        {
            konsumenci[i] = new Konsument(liczbaPowtorzenKonsumenta, N, i + 1, mojeSemafory, mojeDane);
            konsumenci[i].start();
        }

        for (int i = 0; i < m; i++)
        {
            producenci[i].join();
        }
        for (int i = 0; i < n; i++)
        {
            konsumenci[i].join();
        }
        System.out.println("\nProgram zakonczony poprawnie");


    }

}
