/**
 Gruppo - LO HACKER
**/

import java.math.BigInteger;
import java.util.Scanner;

public class RSA {

    public static BigInteger euclideSemplice(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    public static BigInteger inversoMoltiplicativo(BigInteger a, BigInteger mod) {
        BigInteger m0 = mod;
        BigInteger x0 = BigInteger.ZERO;
        BigInteger x1 = BigInteger.ONE;

        while (a.compareTo(BigInteger.ONE) > 0) {
            BigInteger q = a.divide(mod);

            BigInteger t = mod;
            mod = a.mod(mod);
            a = t;

            t = x0;
            x0 = x1.subtract(q.multiply(x0));
            x1 = t;
        }

        if (x1.compareTo(BigInteger.ZERO) < 0) {
            x1 = x1.add(m0);
        }

        return x1;
    }

    public static BigInteger squareAndMultiply(BigInteger base, BigInteger esponente, BigInteger modulo) {
        BigInteger z = BigInteger.ONE;
        String c_binario = esponente.toString(2);

        for (int i = 0; i < c_binario.length(); i++) {
            z = z.multiply(z).mod(modulo);  // Quadrato di z

            if (c_binario.charAt(i) == '1') {
                z = z.multiply(base).mod(modulo);  // Moltiplica per la base se il bit è 1
            }
        }
        return z;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Errore, inserire tutti gli input necessari <p> <q> <b>");
            System.exit(1);
        } else {
            try {
                BigInteger p = new BigInteger(args[0]);
                BigInteger q = new BigInteger(args[1]);
                BigInteger b = new BigInteger(args[2]);

                BigInteger n = p.multiply(q);
                BigInteger pi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

                BigInteger gcd = euclideSemplice(pi_n, b);
                if (!gcd.equals(BigInteger.ONE)) {
                    System.out.println("GCD(" + pi_n + ", " + b + ") non è 1");
                    System.exit(1);
                } else {
                    System.out.println("Il gcd tra b e pi(n) e' " + gcd);

                    BigInteger a = inversoMoltiplicativo(b, pi_n);
                    System.out.println("L'inverso moltiplicativo di " + b + " modulo " + pi_n + " e' " + a);

                    Scanner scan = new Scanner(System.in);
                    System.out.print("Inserisci il plaintext da inviare: ");
                    BigInteger plaintext = new BigInteger(scan.next());

                    BigInteger cipher = squareAndMultiply(plaintext, b, n);
                    System.out.println("Il messaggio cifrato inviato e' " + cipher);

                    BigInteger mess_decrypted = squareAndMultiply(cipher, a, n);
                    System.out.println("Il testo decrittato e': " + mess_decrypted);
                }
            } catch (NumberFormatException e) {
                System.err.println("Errore, assicurati di inserire numeri validi");
            }
        }
    }
}
