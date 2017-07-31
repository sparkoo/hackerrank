import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class Solution {
    public static class MyQueue<T> {
        Stack<T> stackNewestOnTop = new Stack<T>();
        Stack<T> stackOldestOnTop = new Stack<T>();

        public void enqueue(T value) { // Push onto newest stack
            stackNewestOnTop.push(value);
        }

        public T peek() {
            prepareOld();
            return stackOldestOnTop.peek();
        }

        public T dequeue() {
            prepareOld();
            return stackOldestOnTop.pop();
        }

        public void prepareOld() {
            if (stackOldestOnTop.isEmpty()) {
                while (!stackNewestOnTop.isEmpty()) {
                    stackOldestOnTop.push(stackNewestOnTop.pop());
                }
            }
        }
    }

    @Test
    public static void test() {
        MyQueue<Integer> que = new MyQueue<>();
        que.enqueue(42);
        assertEquals(que.dequeue(), new Integer(42));
        que.enqueue(14);
        assertEquals(que.peek(), new Integer(14));
        que.enqueue(28);
        assertEquals(que.peek(), new Integer(14));
        que.enqueue(60);
        que.enqueue(78);
        assertEquals(que.dequeue(), new Integer(14));
        assertEquals(que.dequeue(), new Integer(28));
        assertEquals(que.dequeue(), new Integer(60));
        assertEquals(que.peek(), new Integer(78));
        assertEquals(que.dequeue(), new Integer(78));
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}