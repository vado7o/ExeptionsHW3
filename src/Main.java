import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws QuantityEx, IOException {
        String input = takeInput();
        String[] inputArr = checkInput(input);
        try (FileWriter writer = new FileWriter(new File(inputArr[0] + ".txt"), true)) {
            writer.write("\n<"+ inputArr[0] + "><" + inputArr[1] + "><" + inputArr[2] + "><" + inputArr[3] + "><" +
                    inputArr[4] + "><" + inputArr[5] + ">");
            System.out.println("Ваши данные успешно записаны в файл!!!");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при попытке записи в файл данных!\n");
            e.printStackTrace();
        }
    }

    public static String takeInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите одной строкой ЧЕРЕЗ ПРОБЕЛ Ваши: Фамилия, Имя, Отчество, дата рождения " +
                "(ОБЯЗАТЕЛЬНО В ФОРМАТЕ ДД.ММ.ГГГГ), номер телефона (ЦИФРАМИ!), пол('М' или 'Ж'!): ");
        return scanner.nextLine();
    }

    public static String[] checkInput(String input) throws QuantityEx {
        String[] inputArr = input.split(" ");
        // проверяем количество введённых данных
        if (inputArr.length != 6) throw new QuantityEx("Вы ввели некорректное количество данных (их должно быть 6 ЧЕРЕЗ ПРОБЕЛ)!!!");

        // проверяем указанную дату рождения
        if (!inputArr[3].matches("\\d{2}.\\d{2}.\\d{4}")) {
            throw new DateFormatEx("Дата Вашего рождения указана некорректно! Доджна указываться в формате ДД.ММ.ГГГГ !!!");
        }

        // проверяем указанный номер телефона
        try {
            Long.parseLong(inputArr[4]);
        } catch (Exception e) {
            throw new PhoneNumEx("Номер телефона укзаан НЕКОРРЕКТНО! Должен быть указан ЦИФРАМИ!");
        }

        // проверяем указанный пол
        if (!inputArr[5].equalsIgnoreCase("м") && !inputArr[5].equalsIgnoreCase("ж")) {
            throw new SexEx("НЕКОРРЕКТНО указан пол. Ваш пол должен быть обозначен как 'М' или 'Ж'!");
        }
        return inputArr;
    }
}