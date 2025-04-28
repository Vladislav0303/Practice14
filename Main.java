import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }
    public static void menu() {
        LocalDate[] massdat = new LocalDate[5];
        Scanner sc = new Scanner(System.in);
        String[] str1 = new String[5];
        System.out.println("~Мій щоденник~");
        System.out.println("Введіть назву файлу: ");
        String name = sc.nextLine();
        System.out.println("Хочете переглянути вміст файлу?: ");
        if(sc.nextLine().equals("Так")) {
            diary(name);
        } else {
            mynote(massdat,str1,name);
    }
    }
    public static LocalDate[] mynote(LocalDate[] massdat, String[] str1, String name) {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);
        while (true) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(name))) {
                for (int i = 0; i < massdat.length;) {
                    System.out.println("Введіть рік: ");
                    int year = sc.nextInt();
                    System.out.println("Введіть місяць: ");
                    int month = sc.nextInt();
                    System.out.println("Введіть день: ");
                    int day = sc.nextInt();
                    LocalDate data = LocalDate.of(year, month, day);
                    System.out.println("Введіть запис на цю дату: ");
                    String str = sc1.nextLine();
                    System.out.println("Ваша дата: " + data);
                    System.out.println("Ваш запис: " + str);
                    massdat[i] = data;
                    str1[i] = str;
                    i++;
                    bw.write(str + " " + data + "\n");
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
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }  catch (IOException e) {
            System.out.println("Назву файла не знайдено" + e.getMessage());
        }
        return name;
    }
}