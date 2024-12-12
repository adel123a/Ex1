/**
 * This class represents a simple solution for Ex1.
 * As defined here: https://docs.google.com/document/d/1AJ9wtnL1qdEs4DAKqBlO1bXCM6r6GJ_J/r/edit/edit
 * In this assignment, we will design a number formatting converter and calculator.
 * In general, we will use Strings as numbers over basis of binary till Hexa.
 * [2-16], 10-16 are represented by A,B,..G.
 * The general representation of the numbers is as a String with the following format:
 * <number><b><base> e.g., “135bA” (i.e., “135”, as 10 is the default base), “100111b2”, “12345b6”,”012b5”, “123bG”, “EFbG”.
 * The following are NOT in the format (not a valid number):
 * “b2”, “0b1”, “123b”, “1234b11”, “3b3”, “-3b5”, “3 b4”, “GbG”, "", null,
 * You should implement the following static functions:
 */
public class Ex1 {
    //פונקצית עזר שלי
    // This function convert string to a numeric value based on it content ( numbers/ letters)
    public static int converted(String s)
    {
        // if conatain only digits
        if (s.matches("\\d+"))
        {
            // if it digit - convert to integer
            return Integer.parseInt(s);
        }
        else if (s.matches("[A-G]+"))
        {
            // If it's all letters from A to G, convert ti uts corresponding numbers
            int result = 0;
            for (int i = 0; i < s.length(); i++)
            {
                char letter = s.charAt(i);
                int letterResult = (letter - 'A') + 10;  // convert A to 10...
                result = result * 10 + letterResult;  // construct the number
            }
            return result;
        }
        else  //return -1 if input dont match the condition
            return -1;
    }

    /**
     * פונקצית עזר שלי
     * This static function -split a given string into number and base
     * @param a String representing a number
     * @return array with the number in index 0 and base in index 1
     **/
    public static String[] split(String s)
    {
        String[] spilted = null;
        if (s.isEmpty() || !s.contains("b") || (s.contains(" ")) )
        {
            return null; // invalid input
        }
        spilted = s.split("b"); // spilt string 2 parts number and base
        if (spilted.length != 2 || spilted[0].isEmpty() || spilted[1].isEmpty())
        {
            return null;
        }
        return spilted; // Return array with number and base
    }
        /**
         * Convert the given number (num) to a decimal- base10 representation (as int).
         * It the given number is not in a valid format returns -1.
         * @param num a String representing a number in basis [2,16]
         * @return - int representing a number
         */

        public static int number2Int (String num)
        {
            int decimalNum = 0;  // This will store the final decimal result
            String [] array = split(num);
            if (array != null) { //הוספתי בגלל הנאל
                String baseS = array[1];
                int base = Integer.parseInt(baseS);
                for (int i = 0; i < array[0].length(); i++) {
                    char c = array[0].charAt(i);  // Get the current character
                    // Convert the character to its numeric value using the converted function
                    int digitValue = converted(String.valueOf(c));  // Convert char to String and use converted function
                    if (digitValue == -1) {
                        return -1;  // Return -1 for invalid character in the number part
                    }
                    decimalNum += digitValue * Math.pow(base, array[0].length() - 1 - i);
                }
                return decimalNum;  // Return the final result
            }
            return -1; // הוספתי בגלל הנאל
        }


        /**
         * This static function checks if the given String (g) is in a valid "number" format.
         * @param a a String representing a number
         * @return true iff the given String is in a number format
         */
        public static boolean isNumber(String a) {
            {
                if (a.isEmpty()) {
                    return false; // Invalid
                }
                String[] divided = Ex1.split(a);
                if (divided == null) {
                    return false; // Invalid
                }
                String number = divided[0];
                // Convert base to int
                int baseInt = Ex1.converted(divided[1]);
                if (baseInt == -1)  //  invalid
                    return false;
                else if (baseInt < 2 || baseInt > 16) {
                    return false; // Invalid
                }
                // check each digit in number part
                char[] chars = number.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char d = chars[i]; // current char in number
                    if (Character.isDigit(d)) {
                        if (Character.getNumericValue(d) >= baseInt) {
                            return false; // Invalid
                        }
                    } else if (d >= 'A' && d <= 'G') // od string contains letters not digits
                    {
                        if ((d - 'A' + 10) >= baseInt) {
                            return false; // Invalid
                        }
                    } else {
                        return false; // Invalid
                    }
                }
                return true;
            }
        }

        /**
         * Calculate the number representation (in basis base)
         * of the given natural number (represented as an integer).
         * If num<0 or base is not in [2,16] the function should return "" (the empty String).
         * @param num the natural number (include 0).
         * @param base the basis [2,16]
         * @return a String representing a number (in base) equals to num, or an empty String (in case of wrong input).
         */
        public static String int2Number(int num, int base)
            {
                /// note - in this function i didnt use number2int function. check again if i can use it or there is no need
                // Validate input
                if (base < 2 || base > 16) {
                    return ""; // Invalid base
                }
                String ans = ""; // empty string
                while (num > 0) {
                    int remainder = num % base;
                    // convert the remainder to char
                    char digit;
                    if (remainder < 10) {
                        digit = (char) ('0' + remainder); // Digits 0-9
                    } else {
                        digit = (char) ('A' + (remainder - 10)); // Letters A-F
                    }
                    //
                    ans = digit + ans; // add to beggingng
                    num = num / base; //
                }
                return ans;
            }

        /**
         * Checks if the two numbers have the same value.
         * @param n1 first number
         * @param n2 second number
         * @return true iff the two numbers have the same values.
         */
        public static boolean equals(String n1, String n2) {
            boolean ans = true;
            int n1Base10= number2Int(n1);
            int n2Base10= number2Int(n2);
            if (n1Base10 != n2Base10) {
                ans = false;
            }
            return ans;
        }

        /**
         * This static function search for the array index with the largest number (in value).
         * In case there are more than one maximum - returns the first index.
         * Note: you can assume that the array is not null and is not empty, yet it may contain null or none-valid numbers (with value -1).
         * @param arr an array of numbers
         * @return the index in the array in with the largest number (in value).
         *
         */
        public static int maxIndex(String[] arr) {
            {
                int ans = 0;
                for (int i=0; i < arr.length ;i++)
                {
                    if (number2Int(arr[i]) > number2Int(arr[ans]))
                    {
                        ans = i;
                    }
                }
                return ans;
            }
        }
}
