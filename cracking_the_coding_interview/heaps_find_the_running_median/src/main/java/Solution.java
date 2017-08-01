import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class Solution {

    @Test
    public static void test() {
        RunningMedian runningMedian = new RunningMedian(6);
        runningMedian.insert(12);
        assertEquals(runningMedian.countStringMedian(), "12.0");

        runningMedian.insert(4);
        assertEquals(runningMedian.countStringMedian(), "8.0");

        runningMedian.insert(5);
        assertEquals(runningMedian.countStringMedian(), "5.0");

        runningMedian.insert(3);
        assertEquals(runningMedian.countStringMedian(), "4.5");

        runningMedian.insert(8);
        assertEquals(runningMedian.countStringMedian(), "5.0");

        runningMedian.insert(7);
        assertEquals(runningMedian.countStringMedian(), "6.0");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        RunningMedian runningMedian = new RunningMedian(n);
        for (int a_i = 0; a_i < n; a_i++) {
            runningMedian.insert(in.nextInt());
            System.out.println(runningMedian.countStringMedian());
        }
    }

    private static class RunningMedian {

        private final PriorityQueue<Double> maxHeap;
        private final PriorityQueue<Double> minHeap;

        public RunningMedian(int n) {
            maxHeap = new PriorityQueue<>((n / 2) + 1, Comparator.comparingDouble(d -> d));
            minHeap = new PriorityQueue<>((n / 2) + 1, Comparator.comparingDouble(d -> (Double) d).reversed());
        }

        public void insert(double e) {
            if (minHeap.isEmpty()) {
                minHeap.offer(e);
                return;
            } else {
                if (e <= minHeap.peek()) {
                    minHeap.offer(e);
                } else {
                    maxHeap.offer(e);
                }
            }
            while (Math.abs(minHeap.size() - maxHeap.size()) >= 2) {
                if (minHeap.size() > maxHeap.size()) {
                    maxHeap.offer(minHeap.poll());
                } else {
                    minHeap.offer(maxHeap.poll());
                }
            }
        }

        private double countMedian() {
            if (minHeap.isEmpty() && maxHeap.isEmpty()) {
                return 0.0;
            } else if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
                return (minHeap.peek() + maxHeap.peek()) / 2;
            } else {
                if (minHeap.size() > maxHeap.size()) {
                    return minHeap.peek();
                } else {
                    return maxHeap.peek();
                }
            }
        }

        public String countStringMedian() {
            return String.format("%.1f", countMedian());
        }
    }
}
