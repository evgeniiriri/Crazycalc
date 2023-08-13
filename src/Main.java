import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Calc Calculator = new Calc();
        String message = "Добро пожаловать в мир калькулятора!\nКалькулятор принимает запись в виде\n" +
                "операнд пробел оператор пробел операнд\nКалькулятор работает как с арабскими числами, так и с римскими" +
                "числами, но не вместе.\nЖелаю хороших вычислений. \n\nВведите ваш пример\n";
        System.out.println(message);
        Scanner scan = new Scanner(System.in);
        String result = Calculator.run(scan.nextLine().trim());
        String byeMessage = "Ваш ответ " + result + " Спасибо^_^";
        System.out.println(byeMessage);
    }
}

class Calc {

    String[] romeNumber = {
            //Список римских чисел для вывода ответа.
            "0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI",
            "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX",
            "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII",
            "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI",
            "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII",
            "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    String run(String data) throws Exception {
        //Запускающий метод.
        String[] arrayData = validators(data);
        String result;

        if (Character.isDigit(data.charAt(0))) {
            result = process(arrayData);
        } else {
            arrayData[0] = translatorRomeToArab(arrayData[0]);
            arrayData[2] = translatorRomeToArab(arrayData[2]);
            result = process(arrayData);
            int index = Integer.parseInt(result);
            result = romeNumber[index];
        }

        return result;
    }

    String[] validators(String data) throws Exception {
        //Метод вывода ошибок.
        String[] arrayData = data.split(" ");

        if (arrayData.length == 1) {
            throw new Exception("строка не является математической операцией");
        }
        if (arrayData.length > 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (!arrayData[0].matches("\\d+") & arrayData[2].matches("\\d+")) {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if (arrayData[0].matches("\\d+") & !arrayData[2].matches("\\d+")) {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if ((!arrayData[0].matches("\\d+") | !arrayData[2].matches("\\d+"))) {
            int a = Integer.parseInt(translatorRomeToArab(arrayData[0]));
            int b = Integer.parseInt(translatorRomeToArab(arrayData[2]));
            if (a < b && arrayData[1].equals("-")) {
                throw new Exception("В римской системе нет отрицательных чисел.");
            }
        }
        return arrayData;
    }

    String translatorRomeToArab(String data) {
        //Преобразование Римских чисел в арабские.
        String result = "";
        for (int i = 0; i < romeNumber.length; i++) {
            if (data.equals(romeNumber[i])) {
                result = Integer.toString(i);
                break;
            }
        }
        return result;
    }

    String process(String[] data) {
        //Метод вычисления.
        int firsNumber = Integer.parseInt(data[0]);
        int secondNumber = Integer.parseInt(data[2]);
        int result = 0;

        if (data[1].equals("+")) {
            result = firsNumber + secondNumber;
        } else if (data[1].equals("-")) {
            result = firsNumber - secondNumber;
        } else if (data[1].equals("*")) {
            result = firsNumber * secondNumber;
        } else if (data[1].equals("/")) {
            result = firsNumber / secondNumber;
        }
        return String.valueOf(result);
    }

}

