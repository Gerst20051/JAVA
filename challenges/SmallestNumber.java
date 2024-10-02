import java.util.*;

// [$]> javac SmallestNumber.java
// [$]> java SmallestNumber

class SmallestNumber {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 3, 6, 4, 1, 2}) == 5);
        System.out.println(solution(new int[]{1, 2, 3}) == 4);
        System.out.println(solution(new int[]{-1, -3}) == 1);
    }

    public static int solution(int[] arr) {
        Arrays.sort(arr);
        int smallest = 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == smallest) {
                smallest++;
            }
        }
        return smallest;
    }
}
