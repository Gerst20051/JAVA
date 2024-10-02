// [$]> javac ArrayExample.java
// [$]> java ArrayExample

public class ArrayExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        printArray(numbers);
    }

    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.println(num);
        }
    }
}
