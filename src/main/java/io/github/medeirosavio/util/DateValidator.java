package io.github.medeirosavio.util;

import java.time.LocalDate;

public class DateValidator {

    public static boolean isAdult(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false; // Se a data de nascimento for nula, consideramos como não adulto
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate eighteenYearsAgo = currentDate.minusYears(18);

        return dateOfBirth.isBefore(eighteenYearsAgo);
    }
}
