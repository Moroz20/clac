import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите ваше выражение: ");
        Scanner str = new Scanner(System.in);
        String str1 = str.nextLine();

        try {
            // Вызываем метод, который может выбросить исключение
            checkMath(str1);
            checkTwoNumSys(str1);
            System.out.print("Ваш ответ: " + calc(str1));
        } catch (CustomException e) {
            // Обрабатываем исключение
            System.out.println("Ошибка: " + e.getMessage());
        }

    }
    //Метод проверки математической операции и удовлетворению 2 слагаемым
    static void checkMath(String str1) throws CustomException{
        char[] signControl = str1.toCharArray();
        if(signControl.length < 3)
        {throw new CustomException("Это операция является не математической");}
        else {
            int flags = 0;
            for (int i = 0; i < signControl.length; i++) {
                if(signControl[i] == '+' || signControl[i] == '-' || signControl[i] == '*' || signControl[i] == '/' ){
                    flags += 1;
                }
            }
            if (flags > 1) { throw new CustomException("Эта операция не удовлетворяет заданию, так как знаков больше 1");}
            else if (flags<1) {
                throw new CustomException("Эта операция не удовлетворяет условию, так как нет знаков");
            }
        }
    }
    //Метод конвертации араб в рим
     static String arabicToRoman(int sum) {
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((sum > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= sum) {
                sb.append(currentSymbol.name());
                sum-= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
    //Метод проверки на разные системы счисления
    static void checkTwoNumSys(String str1) throws CustomException{
        char[] SignControl2 = str1.toCharArray();
        boolean firstType = false, secondType = false;
        for (int i = 0; i < SignControl2.length; i++) {
            if (SignControl2[i] == '+' || SignControl2[i] == '-' || SignControl2[i] == '*' || SignControl2[i] == '/') {
                String[] Numbers = str1.split("[+\\-*/]");
                if (Numbers[0].equals("I") || Numbers[0].equals("V") || Numbers[0].equals("X") || Numbers[0].equals("II") || Numbers[0].equals("III")
                        || Numbers[0].equals("IV") || Numbers[0].equals("VI") || Numbers[0].equals("VII") || Numbers[0].equals("VIII")
                        || Numbers[0].equals("IX")) {
                    firstType = true;
                }
                if (Numbers[1].equals("I") || Numbers[1].equals("V") || Numbers[1].equals("X") || Numbers[1].equals("II") || Numbers[1].equals("III")
                        || Numbers[1].equals("IV") || Numbers[1].equals("VI") || Numbers[1].equals("VII") || Numbers[1].equals("VIII")
                        || Numbers[1].equals("IX")) {
                    secondType = true;
                }
                if (firstType != secondType) {
                    throw new CustomException("Нельзя использовать разные системы счислений");
                }
                break;
            }
        }
    }

    static String calc(String str1) throws CustomException, NumberFormatException{
        //постоянные переменные для заключительного подсчета
        String value10 = " ";
        boolean flag = false;
        //замена римских чисел
        char[] lettersChecker = str1.toCharArray();
        for (int i = 0; i < lettersChecker.length; i++) {
            if (lettersChecker[i] == 'I' || lettersChecker[i] == 'V' || lettersChecker[i] == 'X') {
                String value1 = str1.replaceAll("VIII", "8");
                String value2 = value1.replaceAll("VII", "7");
                String value3 = value2.replaceAll("VI", "6");
                String value4 = value3.replaceAll("IV", "4");
                String value5 = value4.replaceAll("V", "5");
                String value6 = value5.replaceAll("III", "3");
                String value7 = value6.replaceAll("II", "2");
                String value8 = value7.replaceAll("IX", "9");
                String value9 = value8.replaceAll("I", "1");
                value10 = value9.replaceAll("X", "10");
                flag = true;
                break;
            } else {
                value10 = str1;
            }
        }
        //Нoвый калькулятор
        char[] SignControl = value10.toCharArray();
        int sum=0;
        boolean prob = false;
        int firstN = 0,secondN= 0;
        String b = "";
        for (int i = 0; i < SignControl.length; i++) {
            if (SignControl[i] == '+') {
                String[] Numbers = value10.split("[+]");
                try{
                    firstN = Integer.parseInt(Numbers[0]);
                    secondN = Integer.parseInt(Numbers[1]);
                }
                catch (NumberFormatException e){
                    System.out.println("Данное приложение использует только целые числа");
                }

                if (firstN > 10 || secondN > 10 || firstN < 1 || secondN < 1) {
                    prob = true;
                }
                sum = firstN + secondN;
                break;
            } else if (SignControl[i] == '-') {
                String[] Numbers = value10.split("[-]");
                try{
                    firstN = Integer.parseInt(Numbers[0]);
                    secondN = Integer.parseInt(Numbers[1]);
                }
                catch (NumberFormatException e){
                    System.out.println("Данное приложение использует только целые числа");
                }
                if (firstN > 10 || secondN > 10 || firstN < 1 || secondN < 1) {
                    prob = true;
                }
                sum = firstN - secondN;
                break;
            }
            else if (SignControl[i] == '*') {
                String[] Numbers = value10.split("[*]");
                try{
                    firstN = Integer.parseInt(Numbers[0]);
                    secondN = Integer.parseInt(Numbers[1]);
                }
                catch (NumberFormatException e){
                    System.out.println("Данное приложение использует только целые числа");
                }
                if (firstN > 10 || secondN > 10 || firstN < 1 || secondN < 1) {
                    prob = true;
                }
                sum = firstN * secondN;
                break;
            }
            else if (SignControl[i] == '/') {
                String[] Numbers = value10.split("[/]");
                try{
                    firstN = Integer.parseInt(Numbers[0]);
                    secondN = Integer.parseInt(Numbers[1]);
                }
                catch (NumberFormatException e){
                    System.out.println("Данное приложение использует только целые числа");
                }
                if (firstN > 10 || secondN > 10 || firstN < 1 || secondN < 1) {
                    prob = true;
                }
                sum = firstN / secondN;
                break;
            }
            else{

            }
        }
        //Проверка на положительные арабские числа
        if (flag == true && sum < 1) {
            throw new CustomException("В римской системе счислений нет отрицательных чисел");
        } else if (flag == true) {
            b = arabicToRoman(sum);
        } else{
            //Преобразование
            b = Integer.toString(sum);
        }

        if (prob == true) {
            throw new CustomException("Значение двух переменных должно быть от 1 до 10 (по требованиям задания)");
        }
        return b;
    }
}
