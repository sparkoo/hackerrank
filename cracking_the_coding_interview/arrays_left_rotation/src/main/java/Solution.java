import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }

        int i = k;
        do {
            System.out.print(a[i] + " ");
            if (i >= n - 1) {
                i = 0;
            } else {
                i++;
            }
        } while(i != k);
    }
}