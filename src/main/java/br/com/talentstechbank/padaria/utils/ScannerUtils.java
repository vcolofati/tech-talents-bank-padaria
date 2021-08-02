package br.com.talentstechbank.padaria.utils;

import br.com.talentstechbank.padaria.exceptions.EntradaInvalidaException;

import java.util.Scanner;

public class ScannerUtils {

    public static int validaePegaInteiro(Scanner in) throws EntradaInvalidaException {
        if(!in.hasNextInt()) {
            in.next();
            throw new EntradaInvalidaException();
        }
        return in.nextInt();
    }
    public static String validaePegaString(Scanner in) throws EntradaInvalidaException {
        if(!in.hasNext()) {
            in.next();
            throw new EntradaInvalidaException();
        }
        return in.next();
    }
}
