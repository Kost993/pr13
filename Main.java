import java.util.Scanner;

public class Main {
    static final int MAX_RECORDS = 50;
    static String[] dates = new String[MAX_RECORDS];
    static String[] entries = new String[MAX_RECORDS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Мій щоденник ===");
            System.out.println("1. Додати запис");
            System.out.println("2. Видалити запис");
            System.out.println("3. Переглянути усі записи");
            System.out.println("4. Вийти");
            System.out.print("Оберіть опцію: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                addEntry(scanner);
            } else if (choice.equals("2")) {
                deleteEntry(scanner);
            } else if (choice.equals("3")) {
                viewEntries();
            } else if (choice.equals("4")) {
                running = false;
            } else {
                System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }

        scanner.close();
    }

    static void addEntry(Scanner scanner) {
        System.out.print("Введіть дату (у форматі РРРР-ММ-ДД): ");
        String date = scanner.nextLine();

        if (!isValidDate(date)) {
            System.out.println("Невірний формат дати.");
            return;
        }

        System.out.println("Введіть запис (завершіть введення пустим рядком):");
        String text = "";
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            text += line + "\n";
        }

        for (int i = 0; i < MAX_RECORDS; i++) {
            if (dates[i] == null) {
                dates[i] = date;
                entries[i] = text;
                System.out.println("Запис додано.");
                return;
            }
        }

        System.out.println("Щоденник переповнений.");
    }

    static void deleteEntry(Scanner scanner) {
        System.out.print("Введіть дату запису, який хочете видалити: ");
        String date = scanner.nextLine();

        for (int i = 0; i < MAX_RECORDS; i++) {
            if (date.equals(dates[i])) {
                dates[i] = null;
                entries[i] = null;
                System.out.println("Запис видалено.");
                return;
            }
        }

        System.out.println("Запис з такою датою не знайдено.");
    }

    static void viewEntries() {
        boolean empty = true;
        for (int i = 0; i < MAX_RECORDS; i++) {
            if (dates[i] != null) {
                System.out.println("Дата: " + dates[i]);
                System.out.println("Запис:\n" + entries[i]);
                System.out.println("----------------------");
                empty = false;
            }
        }

        if (empty) {
            System.out.println("Щоденник порожній.");
        }
    }

    static boolean isValidDate(String date) {
        if (date.length() != 10) return false;
        if (date.charAt(4) != '-' || date.charAt(7) != '-') return false;
        for (int i = 0; i < date.length(); i++) {
            if (i == 4 || i == 7) continue;
            char c = date.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }
}
