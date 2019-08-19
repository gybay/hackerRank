import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        int a_l = a.length(),
            b_l = b.length();
        boolean[][] dp_arr = new boolean[a_l + 1][b_l + 1]; // all items are false as def
        dp_arr[0][0] = true;

        // initiliaze first column
        boolean isThereUp = false;
        for(int i = 1; i <= a_l; i++){
            if(( isThereUp // we have already seen an uppercase
                || a.charAt(i-1) <= 90 && a.charAt(i-1) >= 65) // it is uppercase 
                ){
                dp_arr[i][0]=false;
            } else 
                dp_arr[i][0] = true;
        }

        // fill the rest
        for(int i = 1; i <= a_l; i++){ // what we have
            for(int j = 1; j <= b_l; j++){ // what we want
                // same char, transfer the cross value
                if(a.charAt(i-1) == b.charAt(j-1)) { 
                    dp_arr[i][j] = dp_arr[i-1][j-1];
                }
                // lower case of b, we can change 
                else if ((int)a.charAt(i-1) - 32 == (int)b.charAt(j-1)) {
                    dp_arr[i][j] = dp_arr[i-1][j-1] || dp_arr[i-1][j];
                } 
                // an uppercase, nothing to do
                else if (a.charAt(i-1) <= 90 && a.charAt(i-1) >= 65){
                    dp_arr[i][j] = false;
                } else { // lower case non equal char, remove
                    dp_arr[i][j] = dp_arr[i-1][j];
                }
            }
        }

        return dp_arr[a_l][b_l] ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
