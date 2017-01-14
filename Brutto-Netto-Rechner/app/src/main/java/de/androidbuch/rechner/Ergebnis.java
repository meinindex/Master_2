package de.androidbuch.rechner;

/**
 * Created by Claudi on 15.11.2016.
 */

public class Ergebnis {

    public float betrag;
    public boolean isNetto;
    public int ustProzent;

    public float betragNetto;
    public float betragBrutto;
    public float betragUst;

    public void berechneErgebnis()
    {
        // Berechne Brutto aus Netto

        if(isNetto)
        {
            betragNetto = betrag;
            betragUst = betrag*ustProzent/100;
            betragBrutto=betrag+betragUst;
        }
        else
        {
            //Berecfhne Nettobetrag aus Bruttobetrag;
            betragBrutto = betrag;
            betragUst = betrag*ustProzent/
                    (100+ustProzent);
            betragNetto = betrag - betragUst;
        }
    }
}
