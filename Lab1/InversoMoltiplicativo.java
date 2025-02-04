/**
 Gruppo - LO HACKER
**/

import java.util.Locale;

public class InversoMoltiplicativo {

    // Metodo per calcolare l'inverso moltiplicativo utilizzando l'algoritmo euclideo esteso
    public static int inversoMoltiplicativo(int a, int mod) {
        int m0 = mod;
        int x0 = 0;
        int x1 = 1;

        //Ogni numero in modulo 1 è invertibile in modulo 1, quindi moltiplicarlo per 0 non cambia il risultato
        if (mod == 1)
            return 0;

        //Ciclo fino a che non trovo l'inverso moltiplicativo
        while (a > 1) {
            int q = a / mod;
            int t = mod;

            mod = a % mod;
            a = t;

            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        // Rendere x1 positivo
        if (x1 < 0)
            x1 += m0;

        //Restituisco l'inverso moltiplicativo
        return x1;
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        //Controllo che vengano passati esattamente due parametri da riga di comando
        if (args.length != 2) {
            //Se viene passato un numero diverso di parametri da riga di comando, stampo un messaggio di errore
            System.err.println("Errore, inserire tutti gli input necessari <a> <mod>");
            System.exit(1);
        } else {
            try {
                //Recupero i dati passati da riga di comando
                int a = Integer.parseInt(args[0]);
                int mod = Integer.parseInt(args[1]);

                //Richiamo la funzione che mi calcola l'inverso moltiplicativo passandogli i parametri in input
                int x1=inversoMoltiplicativo(a,mod);

                //Stampo il risultato
                System.out.println("L'inverso moltiplicativo di " + a + " mod " + mod + " e': " + x1);

            }catch (NumberFormatException e){
                //In caso non vengano passati numeri da riga di comando, stampo un messaggio di errore
                System.err.println("Errore, assicurati di inserire numeri validi per <a> e <b>");
            }
        }
    }
}