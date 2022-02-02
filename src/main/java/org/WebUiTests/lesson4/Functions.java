package org.WebUiTests.lesson4;

public class Functions {
    public static boolean isPrime(Integer number) {
        //1, 2, 3, 5, 7, 11 ...- простые
        //4, 6, 8 ... - не явялеются простыми

        if (number <= 0) return false;
        if (number <= 3) return true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static boolean isPalindrome(String word) {
        //1, 11, 123321, 1235321 - палиндром
        //23 - не является палиндромом

        if (word.length() < 2) return true;
        if (word.charAt(0) != word.charAt(word.length() - 1)) return false;

        return isPalindrome(word.substring(1, word.length() - 1));

        //123321
        //2332
        //33
        //"" - true
    }
}
