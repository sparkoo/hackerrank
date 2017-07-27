public class Solution {
    private final int n;
    private final int a[];

    public Solution(int n, int[] a) {
        this.n = n;
        this.a = a;
    }

    void bubbleSort() {
        int numberOfSwaps = 0;
        for (int i = 0; i < n; i++) {
            // Track number of elements swapped during a single array traversal

            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    swap(j, j + 1);
                    numberOfSwaps++;
                }
            }

            // If no elements were swapped during a traversal, array is sorted
            if (numberOfSwaps == 0) {
                break;
            }
        }
        System.out.println("Array is sorted in " + numberOfSwaps + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[n - 1]);
    }

    private void swap(int i1, int i2) {
        int tmp = a[i1];
        a[i1] = a[i2];
        a[i2] = tmp;
    }
}
