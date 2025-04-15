package app._03_usecase._03_deliverytimecalculator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DeliverySystem {
    private final Scanner scanner;
    private final Map<String, GeographicZone> zones;
    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter timeFormatter;
    private HolyDaysAdmin holyDaysAdmin;

    public DeliverySystem() {
        this.scanner = new Scanner(System.in);
        this.zones = new HashMap<>();
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    }

    public void loadConfigurations() {
        // Configuración para Madrid
        GeographicZone madrid = new GeographicZone("Madrid");
        madrid.setCutoffTime(LocalTime.of(14, 0)); // Corte a las 14:00
        madrid.setStandardDeliveryDays(2); // 2 días laborables
        HolyDaysAdmin holyAdminMadrid = new HolyDaysAdmin(madrid);
        holyAdminMadrid.createHolyDays(LocalDate.of(2025, 4, 17)); // Jueves santo
        holyAdminMadrid.createHolyDays(LocalDate.of(2025, 4, 18)); // viernes santo
        holyAdminMadrid.createHolyDays(LocalDate.of(2025, 5, 1)); // Día del Trabajo
        holyAdminMadrid.createHolyDays(LocalDate.of(2025, 5, 2)); // Fiesta de la Comunidad de Madrid

        // Configuración para Barcelona
        GeographicZone barcelona = new GeographicZone("Barcelona");
        barcelona.setCutoffTime(LocalTime.of(15, 0)); // Corte a las 15:00
        barcelona.setStandardDeliveryDays(3); // 3 días laborables
        HolyDaysAdmin holyAdminBarcelona = new HolyDaysAdmin(barcelona);
        holyAdminBarcelona.createHolyDays(LocalDate.of(2025, 4, 17)); // Jueves santo
        holyAdminBarcelona.createHolyDays(LocalDate.of(2025, 4, 18)); // viernes santo
        holyAdminBarcelona.createHolyDays(LocalDate.of(2025, 5, 1)); // Día del Trabajo
        holyAdminBarcelona.createHolyDays(LocalDate.of(2025, 6, 24)); // San Juan

        // Configuración para Valencia
        GeographicZone valencia = new GeographicZone("Valencia");
        valencia.setCutoffTime(LocalTime.of(13, 30)); // Corte a las 13:30
        valencia.setStandardDeliveryDays(4); // 4 días laborables
        HolyDaysAdmin holyAdminValencia = new HolyDaysAdmin(valencia);
        holyAdminValencia.createHolyDays(LocalDate.of(2025, 4, 17)); // Jueves santo
        holyAdminValencia.createHolyDays(LocalDate.of(2025, 4, 18)); // viernes santo
        holyAdminValencia.createHolyDays(LocalDate.of(2025, 5, 1)); // Día del Trabajo

        // Agregar zonas al sistema
        zones.put("madrid", madrid);
        zones.put("barcelona", barcelona);
        zones.put("valencia", valencia);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== SISTEMA DE CALCULO DE PLAZOS DE ENTREGA ===");
            System.out.println("1. Calcular fecha de entrega");
            System.out.println("2. Gestionar días festivos");
            System.out.println("3. Configurar zona geográfica");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int option = readIntInput();

            switch (option) {
                case 1:
                    calculateDeliveryDate();
                    break;
                case 2:
                    manageHolidays();
                    break;
                case 3:
                    configureZone();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }


    }
    private void manageHolidays() {
        System.out.println("\n--- GESTIÓN DE DÍAS FESTIVOS ---");

        GeographicZone zone = selectZone();
        if (zone == null) return;

        boolean back = false;
        while (!back) {
            System.out.println("\nZona: " + zone.getName());
            System.out.println("1. Ver días festivos");
            System.out.println("2. Añadir día festivo");
            System.out.println("3. Eliminar día festivo");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int option = readIntInput();

            switch (option) {
                case 1:
                    displayHolidays(zone);
                    break;
                case 2:
                    addHoliday(zone);
                    break;
                case 3:
                    removeHoliday(zone);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    private void addHoliday(GeographicZone zone) {
        holyDaysAdmin = new HolyDaysAdmin(zone);
        System.out.print("\nFecha del nuevo día festivo (dd/MM/yyyy): ");
        LocalDate holiday = readDateInput();

        if (holyDaysAdmin.createHolyDays(holiday)) {
            System.out.println("Día festivo añadido correctamente.");
        } else {
            System.out.println("El día festivo ya existe en esta zona.");
        }
    }
    private void removeHoliday(GeographicZone zone) {
        holyDaysAdmin = new HolyDaysAdmin(zone);
        Set<LocalDate> holidays = zone.getHolidays();


        if (holidays.isEmpty()) {
            System.out.println("\nNo hay días festivos para eliminar.");
            return;
        }
        List<LocalDate> holidaysList = new ArrayList<>(holidays);

        displayHolidays(zone);
        System.out.print("\nNúmero del día festivo a eliminar (0 para cancelar): ");
        int index = readIntInput();

        if (index == 0) {
            System.out.println("Operación cancelada.");
        } else if (index > 0 && index <= holidaysList.size()) {
            LocalDate holidayToRemove = holidaysList.get(index - 1);
            holyDaysAdmin.removeHoliday(holidayToRemove);
            System.out.println("Día festivo eliminado correctamente.");
        } else {
            System.out.println("Número no válido.");
        }
    }
    private void displayHolidays(GeographicZone zone) {
        Set<LocalDate> holidays = zone.getHolidays();
        System.out.println("\nDías festivos para " + zone.getName() + ":");

        if (holidays.isEmpty()) {
            System.out.println("No hay días festivos configurados.");
        } else {
            holidays.forEach(day -> System.out.println("\t" + day.format(dateFormatter)));

        }
    }
    private void configureZone() {
        System.out.println("\n--- CONFIGURACIÓN DE ZONA GEOGRÁFICA ---");

        GeographicZone zone = selectZone();
        if (zone == null) return;

        boolean back = false;
        while (!back) {
            System.out.println("\nZona: " + zone.getName());
            System.out.println("1. Cambiar hora de corte (actual: " + zone.getCutoffTime().format(timeFormatter) + ")");
            System.out.println("2. Cambiar días de entrega estándar (actual: " + zone.getStandardDeliveryDays() + " días)");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int option = readIntInput();

            switch (option) {
                case 1:
                    changeCutoffTime(zone);
                    break;
                case 2:
                    changeStandardDeliveryDays(zone);
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void calculateDeliveryDate() {
        System.out.println("\n--- CÁLCULO DE FECHA DE ENTREGA ---");

        GeographicZone zone = selectZone();
        if (zone == null) return;

        System.out.print("Fecha del pedido (dd/MM/yyyy): ");
        LocalDate orderDate = readDateInput();

        System.out.print("Hora del pedido (HH:mm): ");
        LocalTime orderTime = readTimeInput();

        LocalDateTime orderDateTime = LocalDateTime.of(orderDate, orderTime);
        DeliveryTimeCalculator deliveryTimeCalculator = new DeliveryTimeCalculator(zone);
        LocalDate deliveryDate = deliveryTimeCalculator.calculateDeliveryDate(orderDateTime);

        System.out.println("\nRESULTADO DEL CÁLCULO:");
        System.out.println("Fecha del pedido: " + orderDate.format(dateFormatter) + " a las " + orderTime.format(timeFormatter));
        System.out.println("Zona geográfica: " + zone.getName());
        System.out.println("Hora de corte: " + zone.getCutoffTime().format(timeFormatter));
        System.out.println("Tiempo estándar de entrega: " + zone.getStandardDeliveryDays() + " días laborables");
        System.out.println("Fecha estimada de entrega: " + deliveryDate.format(dateFormatter));
    }

    private void changeStandardDeliveryDays(GeographicZone zone) {
        System.out.print("\nNuevo tiempo estándar de entrega (días laborables): ");
        int days = readIntInput();

        if (days > 0) {
            zone.setStandardDeliveryDays(days);
            System.out.println("Tiempo estándar de entrega actualizado correctamente.");
        } else {
            System.out.println("El tiempo de entrega debe ser un número positivo.");
        }
    }
    private void changeCutoffTime(GeographicZone zone) {
        System.out.print("\nNueva hora de corte (HH:mm): ");
        LocalTime newCutoffTime = readTimeInput();
        zone.setCutoffTime(newCutoffTime);
        System.out.println("Hora de corte actualizada correctamente.");
    }

    private GeographicZone selectZone() {
        System.out.println("\nZonas disponibles:");
        for (String zoneName : zones.keySet()) {
            System.out.println("- " + zoneName);
        }

        System.out.print("\nNombre de la zona: ");
        String zoneName = scanner.nextLine().toLowerCase();

        GeographicZone zone = zones.get(zoneName);
        if (zone == null) {
            System.out.println("La zona especificada no existe.");
        }

        return zone;
    }

    private int readIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private LocalDate readDateInput() {
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine(), dateFormatter);
            } catch (Exception e) {
                System.out.print("Formato incorrecto. Utilice dd/MM/yyyy: ");
            }
        }
    }

    private LocalTime readTimeInput() {
        while (true) {
            try {
                return LocalTime.parse(scanner.nextLine(), timeFormatter);
            } catch (Exception e) {
                System.out.print("Formato incorrecto. Utilice HH:mm: ");
            }
        }
    }


}
