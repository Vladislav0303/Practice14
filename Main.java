import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("~Мій щоденник~");
        System.out.println("Введіть назву файла в який хочете завантажити: ");
        String name = sc.nextLine();
            diary(name);
    }
    public static LocalDateTime[] mynote(String name) {
        LocalDateTime[] massdat = new LocalDateTime[5];
        String[] str1 = new String[5];
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);
        while (true) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(name))) {
                for (int i = 0; i < massdat.length;) {
                    System.out.println("Введіть дату в форматі(ISO_LOCAL_DATE_TIME): ");
                    String date = sc.nextLine();
                    LocalDateTime time = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    System.out.println("Введіть запис на цю дату: ");
                    String str = sc1.nextLine();
                    System.out.println("Ваша дата: " + date);
                    System.out.println("Ваш запис: " + str);
                    massdat[i] = time;
                    str1[i] = str;
                    i++;
                    bw.write(str + " " + date + "\n");
                    if (i == 5) {
                        System.out.println("Список повний.");
                        System.out.println(Arrays.toString(massdat));
                        System.out.println(Arrays.toString(str1));
                        System.out.println("Чи хочете ви зребегти щоденик?");
                        String str2 = sc3.nextLine();
                        if (str2.equals("Так")) {
                            System.out.println("Введіть куди зберігати: ");
                            System.out.println("Вкажіть такий шлях збрегігання: C:\\Users\\USER\\IdeaProjects\\Kalendar2");
                            String string = sc4.nextLine();
                            System.out.println("Збережено в: " + string);
                            bw.close();
                            break;
                        } else if(str2.equals("Ні")) {
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Помилка: " + e.getMessage());
            } catch (DateTimeException e) {
                System.out.println("Ви ввели не вірну дату: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
            return massdat;
        }
    }
    public static String diary(String name) {
        try (BufferedReader br = new BufferedReader(new FileReader(name))){
            Scanner sc = new Scanner(System.in);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Хочете записати новий файл?: ");
            String str = sc.nextLine();
            while (true) {
            if(str.equals("Так")) {
                mynote(name);
            } else {
                break;
            }
            }
        }  catch (IOException e) {
            System.out.println("Назву файла не знайдено" + e.getMessage());
        }
        return name;
    }
}