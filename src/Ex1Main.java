import java.util.Scanner;
/**
 * Intro2CS, Ex1 - very basic "main template"
 * Make sure your implementation of this main performs as the Ex1Sol.jar solution implement all needed functions.
 *
 */
public class Ex1Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = "", num2="", quit = "quit";
        while (!num1.equals(quit) && !num2.equals(quit))
        {
            System.out.println();
            System.out.println("Ex1 class solution:");
            System.out.println("Enter a string as number#1 (or \"quit\" to end the program): ");
            num1 = sc.next();

            while (!Ex1.isNumber(num1)) {
                System.out.printf("num1= %s is number:false, value:-1\n", num1);
                System.out.printf("ERR: num1 is in the wrong format! (%s)\n", num1);
                System.out.println("Ex1 class solution:");
                System.out.println("Enter a string as number#1 (or \"quit\" to end the program):");
                num1 = sc.next();
            }
            if (num1.equals("quit"))
                break;
            int num1Base10 = Ex1.number2Int(num1);
            System.out.printf("num1= %s is number: true , value: %d\n", num1 , num1Base10);
            System.out.println("Enter a string as number#2 (or \"quit\" to end the program): ");
            num2 = sc.next();
            while (!num2.equals("quit") && !Ex1.isNumber(num2)) {
                System.out.printf("num2= %s is number:false, value:-1\n", num2);
                System.out.printf("ERR: num2 is in the wrong format! (%s)\n", num2);
                System.out.println("Enter a string as number#2 (or \"quit\" to end the program): ");
                num2 = sc.next();
            }
            if (num2.equals("quit"))
                break;
            int num2Base10 = Ex1.number2Int(num2);
            System.out.printf("num2= %s is number: true , value: %d\n", num2 , num2Base10);
            System.out.print("Enter a base for output (a number [2-16]): \n");
            int base = sc.nextInt();
            if (base < 2 || base > 16) {
                System.out.printf("ERR: wrong base, should be [2-16], got (%d)\n", base);
                break;
            }
            int sum = num1Base10 + num2Base10;
            int mul = num1Base10 * num2Base10;
            String sumInBase = Ex1.int2Number(sum, base); // sum in target base
            String mulInBase = Ex1.int2Number(mul, base); // mul in target base
            if (base==10) {
                System.out.printf("%s + %s  = %s\n", num1, num2, sumInBase);
                System.out.printf("%s * %s  = %s\n", num1, num2, mulInBase);
                String[] numbers = {num1, num2 ,sumInBase,mulInBase};
                int maxIndex = Ex1.maxIndex(numbers);
                System.out.printf("Max number over [%s,%s,%s,%s] is : %s\n" , num1, num2 ,sumInBase,mulInBase, numbers[maxIndex]);
            }
            else
            {
                System.out.printf("%s + %s  = %sb%d\n", num1, num2, sumInBase,base);
                System.out.printf("%s * %s  = %sb%d\n", num1, num2, mulInBase,base);
                String[] numbers = {num1, num2 ,sumInBase,mulInBase};
                int maxIndex = Ex1.maxIndex(numbers);
                System.out.printf("Max number over [%s,%s,%sb%d,%sb%d] is : %sb%d\n" , num1, num2 ,sumInBase,base,mulInBase,base, numbers[maxIndex],base);
            }

        }
        System.out.println("Quitting now...");
        sc.close();
    }
}