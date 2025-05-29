import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataAPI dataAPI = new DataAPI();
        DataUpdater dataUpdater = new DataUpdater(dataAPI, 5000); // Обновление каждые 5 сек
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n1. Получить данные по ID");
            System.out.println("2. Обновить данные");
            System.out.println("3. Сформировать отчет");
            System.out.println("4. Экспортировать результаты");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Введите ID: ");
                    int id = scanner.nextInt();
                    System.out.println("Результат: " + dataAPI.getDataContent(id));
                    break;
                case 2:
                    System.out.print("Введите ID и новое содержимое: ");
                    int updateId = scanner.nextInt();
                    String newContent = scanner.next();
                    boolean success = dataAPI.updateDataContent(updateId, newContent);
                    System.out.println(success ? "Успешно обновлено" : "Ошибка обновления");
                    break;
                case 3:
                    System.out.print("Введите IDs через пробел: ");
                    scanner.nextLine();
                    String[] idsStr = scanner.nextLine().split(" ");
                    int[] ids = new int[idsStr.length];
                    for (int i = 0; i < idsStr.length; i++) {
                        ids[i] = Integer.parseInt(idsStr[i]);
                    }
                    dataAPI.generateReport(ids);
                    break;
                case 4:
                    System.out.print("Введите IDs через пробел: ");
                    scanner.nextLine();
                    String[] exportIdsStr = scanner.nextLine().split(" ");
                    int[] exportIds = new int[exportIdsStr.length];
                    for (int i = 0; i < exportIdsStr.
                         length; i++) {
                        exportIds[i] = Integer.parseInt(exportIdsStr[i]);
                    }
                    System.out.print("Экспортировать в один файл? (y/n): ");
                    boolean singleFile = scanner.next().equalsIgnoreCase("y");
                    dataAPI.exportResults(exportIds, singleFile);
                    break;
                case 5:
                    dataUpdater.stop();
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }
}
