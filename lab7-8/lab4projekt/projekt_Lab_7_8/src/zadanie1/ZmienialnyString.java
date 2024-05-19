package zadanie1;
//klasa utworzona aby móc zasymulować przekazanie napisu przez referencję - ponieważ funkcje wewnątrz monitorów
//nie powinny nic zwracać z powodu problemów z semantyką - signal() powinno być ostatnią instrukcją
public class ZmienialnyString {

    String zawartosc;
    public ZmienialnyString()
    {
        zawartosc = "";
    }
    public void ustawString(String nowy)
    {
        zawartosc = nowy;
    }
    public String zwrocString()
    {
        return zawartosc;
    }
}
