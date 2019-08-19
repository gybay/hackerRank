import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the candies function below.
    static long candies(int n, int[] arr) {
        long sum = 0;
        int[] cache = new int[arr.length];
        cache[0] = 1;
        for(int i = 1; i < arr.length; i++){ // forward scan
            cache[i] = arr[i] > arr[i-1] ? cache[i-1] + 1 : 1;
        }

        System.out.println("First: ");
        printArr(cache);

        for(int i = arr.length - 2; i >= 0; i--){ // backward scan
            if(arr[i] > arr[i+1] && cache[i] <= cache[i+1])
                cache[i] = cache[i+1] +1;
        }

        System.out.println("Then: ");
        printArr(cache);

        for(int i = 0; i < cache.length; i++){
            sum +=cache[i];
        }

        return sum;
    }

    private static void printArr(int[] arr){
        for(int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
   
}
