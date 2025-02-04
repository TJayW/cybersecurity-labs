/**
 Gruppo - LO HACKER
**/

import java.util.Locale;

public class EuclideEsteso {
    public static int euclideEsteso(int a, int b, int[] coefficienti) {
        int r0 = a;
        int r = b;
        int s0 = 1;
        int s = 0;
        int t0 = 0;
        int t = 1;

        while (r != 0) {
            int q = r0 / r;
            int temp;

            temp = r0;
            r0 = r;
            r = temp - q * r;

            temp = s0;
            s0 = s;
            s = temp - q * s;

            temp = t0;
            t0 = t;
            t = temp - q * t;
        }

        coefficienti[0] = s0;
        coefficienti[1] = t0;

        return r0;  // Restituisce il MCD
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        //Controllo che vengano passati esattamente due parametri da riga di comando
        if (args.length != 2) {
            //Se viene passato un numero diverso di parametri da riga di comando, stampo un messaggio di errore
            System.err.println("Errore, inserire tutti gli input necessari <a> <b>");
            System.exit(1);
        } else {
            try {
                //Recupero i dati passati da riga di comando
                int a = Integer.parseInt(args[0]);
                int b = Integer.parseInt(args[1]);

                //Definisco un array di dimensione 2 per salvare i coefficienti 's' e 't'
                int[] coefficienti = new int[2];

                //Richiamo la funzione che mi calcola il massimo comune divisore dei numeri passati in input
                int gcd = euclideEsteso(a, b, coefficienti);

                //Recupero i coefficienti 's' e 't'
                int s = coefficienti[0];
                int t = coefficienti[1];

                //Stampo i risultati
                System.out.println("GCD(" + a + ", " + b + ") = " + gcd);
                System.out.println("Coefficiente s: " + s);
                System.out.println("Coefficiente t: " + t);
            }catch (NumberFormatException e){
                //In caso non vengano passati numeri da riga di comando, stampo un messaggio di errore
                System.err.println("Errore, assicurati di inserire numeri validi per <a> e <b>");
            }
        }
    }
}