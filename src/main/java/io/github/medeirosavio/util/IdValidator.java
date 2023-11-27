package io.github.medeirosavio.util;

public class IdValidator {
    public static boolean isIdValid(Long id) {

        return id != null && id > 0;
    }
}
