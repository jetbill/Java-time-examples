package app._02_operations._07_multilanguagedate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class MultiLanguageDateExamples {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        // Formatear la fecha en diferentes idiomas/locales
        Locale[] locales = {
                Locale.US,              // Inglés (Estados Unidos)
                Locale.UK,              // Inglés (Reino Unido)
                new Locale("es", "ES"), // Español (España)
                Locale.FRANCE,          // Francés
                Locale.GERMANY,         // Alemán
                Locale.ITALY,           // Italiano
                Locale.JAPAN,           // Japonés
                Locale.CHINA,           // Chino
                new Locale("ru", "RU"), // Ruso
                new Locale("ar", "SA")  // Árabe (Arabia Saudita)
        };

        System.out.println("Fecha actual en diferentes idiomas:");

        for (Locale locale : locales) {
            // Formato corto (por ejemplo, "dd/MM/yy")
            DateTimeFormatter shortFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                    .withLocale(locale);

            // Formato medio (por ejemplo, "dd-MMM-yyyy")
            DateTimeFormatter mediumFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                    .withLocale(locale);

            // Formato largo (por ejemplo, "dd de MMMM de yyyy")
            DateTimeFormatter longFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                    .withLocale(locale);

            // Formato completo (por ejemplo, "domingo, 15 de marzo de 2025")
            DateTimeFormatter fullFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                    .withLocale(locale);

            System.out.println("\nIdioma: " + locale.getDisplayLanguage());
            System.out.println("Corto:    " + today.format(shortFormatter));
            System.out.println("Medio:    " + today.format(mediumFormatter));
            System.out.println("Largo:    " + today.format(longFormatter));
            System.out.println("Completo: " + today.format(fullFormatter));
        }

        // Crear un formateador personalizado con texto traducido
        DateTimeFormatter customFormatter = DateTimeFormatter
                .ofPattern("'Hoy es' EEEE, d 'de' MMMM 'de' yyyy")
                .withLocale(new Locale("es", "ES"));

        System.out.println("\nFormato personalizado en español: " + today.format(customFormatter));
    }

}
