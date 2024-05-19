package zadanie3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Widelce {

    final int liczbaFilozofow;
    final Lock zamek = new ReentrantLock();
    final Condition lokaj = zamek.newCondition();
    final Condition[] widelec;
    final Boolean[] zajety;
    int jest = 0;

    public Widelce(int liczbaFilozofow)
    {
        this.liczbaFilozofow = liczbaFilozofow;

        widelec = new Condition[liczbaFilozofow];
        zajety = new Boolean[liczbaFilozofow];
        for (int i = 0; i < liczbaFilozofow; i++)
        {
            zajety[i] = false;
            widelec[i] = zamek.newCondition();
        }
    }

    public void wez(int nr, int pow) throws InterruptedException
    {
        zamek.lock();
        System.out.printf(">>>(1) [F-%d, %d] :: [%b, %b, %b, %b, %b] - %d\n", nr, pow, zajety[0], zajety[1], zajety[2],
                zajety[3], zajety[4], jest);
        if (jest == liczbaFilozofow - 1)
        {
            lokaj.await();
        }
        jest +=1;
        if (zajety[nr])
        {
            widelec[nr].await();
        }
        zajety[nr] = true;
        if (zajety[(nr + 1) % liczbaFilozofow])
        {
            widelec[(nr + 1) % liczbaFilozofow].await();
        }
        zajety[(nr + 1) % liczbaFilozofow] = true;
        System.out.printf(">>>(2) [F-%d, %d] :: [%b, %b, %b, %b, %b] - %d\n", nr, pow, zajety[0], zajety[1], zajety[2],
                zajety[3], zajety[4], jest);
        zamek.unlock();
    }

    public void odloz(int nr, int pow)
    {
        zamek.lock();
        System.out.printf("<<<(1) [F-%d, %d] :: [%b, %b, %b, %b, %b] - %d\n", nr, pow, zajety[0], zajety[1], zajety[2],
                zajety[3], zajety[4], jest);
        zajety[nr] = false;
        widelec[nr].signal();
        zajety[(nr + 1) % liczbaFilozofow] = false;
        widelec[(nr + 1) % liczbaFilozofow].signal();
        jest--;
        lokaj.signal();
        System.out.printf("<<<(2) [F-%d, %d] :: [%b, %b, %b, %b, %b] - %d\n", nr, pow, zajety[0], zajety[1], zajety[2],
                zajety[3], zajety[4], jest);
        zamek.unlock();
    }


}
