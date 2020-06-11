import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("Укажите вид дохода (введите число от 1 до 2):\nЗаработная плата : 1\nМатериальная помощь: 2");
            int n = Integer.parseInt(reader.readLine());
            if (checkChoice(n)) {
                Calculate(n);
                return;
            }

            System.out.println("Ошибка! Неверный выбор");
        }
    }

    private static boolean checkChoice(int n) {
        return n >= 1 && n <= 2;
    }

    private static boolean checkChoice2(int n) {
        return n >= 3 && n <= 4;
    }

    private static boolean checkChoice3(int n) {
        return n >= 5 && n <= 6;
    }

    private static void Calculate(int n) throws IOException {
        switch(n) {
            case 1:
                System.out.println("Заработная плата");
                System.out.println("Начисленная сумма: " + salary());
                break;
            case 2:
                Calculate2(n);
                break;
            default:
                System.out.println("Ошибка! Неверный выбор");
        }

    }

    private static void Calculate2(int n) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Укажите вид материальной помощи (введите число от 3 до 4):\nОблагаемая материальная помощь : 3\nНеоблагаемая материальная помощь: 4");
        n = Integer.parseInt(reader.readLine());
        if (checkChoice2(n)) {
            switch(n) {
                case 3:
                    System.out.println("Облагаемая материальная помощь");
                    System.out.println("Начисленная сумма: " + materialTax());
                    break;
                case 4:
                    Calculate3(n);
                    break;
                default:
                    System.out.println("Ошибка! Неверный выбор");
            }
        }

    }

    private static void Calculate3(int n) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Укажите вид необлагаемой материальной помощи (введите число от 5 до 6):\nМатериальная помощь при рождении : 5\nМатериальная помощь при смерти: 6");
        n = Integer.parseInt(reader.readLine());
        if (checkChoice3(n)) {
            switch(n) {
                case 5:
                    System.out.println("Материальная помощь при рождении");
                    System.out.println("Начисленная сумма: " + materialBirth());
                    break;
                case 6:
                    System.out.println("Материальная помощь при смерти");
                    System.out.println("Начисленная сумма: " + materialDeath());
                    break;
                default:
                    System.out.println("Ошибка! Неверный выбор");
            }
        }

    }

    private static double salary() throws IOException {
        double income = inputIncome("Введите сумму \"на руки\": ");
        return calculateN(income);
    }

    private static double calculateN(double income) {
        income += (double)((int)Math.round(income * 100.0D / 87.0D * 0.13D));
        return income;
    }

    private static double materialBirth() throws IOException {
        double income = inputIncome("Введите сумму \"на руки\": ");
        return calculateNDFL(income);
    }

    private static double calculateNDFL(double income) {
        if (income > 50000.0D) {
            income += (double)((int)Math.round((income - 50000.0D) * 100.0D / 87.0D * 0.13D));
        }

        return income;
    }

    private static double materialDeath() throws IOException {
        return inputIncome("Введите сумму \"на руки\": ");
    }

    private static double materialTax() throws IOException {
        double mathelp = inputIncome("Введите сумму материальной помощи, которая была начислена в текущем календарном году. Если не начислялась, введите ноль.");
        double income = inputIncome("Введите сумму \"на руки\": ");
        return calculateMathelp(income, mathelp);
    }

    private static double calculateMathelp(double income, double mathelp) {
        if (mathelp > 4000.0D) {
            mathelp = 4000.0D;
        }

        if (income >= 4000.0D - mathelp) {
            income = income - (double)((int)Math.round(4000.0D - mathelp)) * 0.13D + (double)((int)Math.round((income - (double)((int)Math.round(4000.0D - mathelp)) * 0.13D) * 100.0D / 87.0D * 0.13D));
        }

        return income;
    }

    private static double inputIncome(String text) throws IOException {
        while(true) {
            System.out.println(text);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            double income = Double.parseDouble(reader.readLine());
            if (checkSum(income)) {
                return income;
            }

            System.out.println("Введите сумму больше нуля и меньше 500000");
        }
    }

    private static boolean checkSum(double income) {
        return income >= 0.0D && income <= 500000.0D;
    }
}
