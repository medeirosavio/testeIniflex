package io.github.medeirosavio.util;

public class CnpjValidator {

    public static boolean isValid(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14 || isAllDigitsEqual(cnpj)) {
            return false;
        }

        int[] multiplicadores1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        int[] multiplicadores2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

        int digito1 = calcularDigito(cnpj, multiplicadores1);
        int digito2 = calcularDigito(cnpj, multiplicadores2);

        return Character.getNumericValue(cnpj.charAt(12)) == digito1
                && Character.getNumericValue(cnpj.charAt(13)) == digito2;
    }

    private static int calcularDigito(String cnpj, int[] multiplicadores) {
        int soma = 0;
        for (int i = 0; i < multiplicadores.length; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * multiplicadores[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }

    private static boolean isAllDigitsEqual(String cnpj) {
        char firstDigit = cnpj.charAt(0);
        for (char digit : cnpj.toCharArray()) {
            if (digit != firstDigit) {
                return false;
            }
        }
        return true;
    }
}
