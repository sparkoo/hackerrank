import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class LinkedListCycle {
    static boolean hasCycle(Node head) {
        Map<Node, Boolean> visited = new HashMap<>();
        Node current = head;
        while (current != null) {
            if (visited.containsKey(current)) {
                return true;
            } else {
                visited.put(current, true);
                current = current.next;
            }
        }
        return false;
    }

    private static class Node {
        int data;
        Node next;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    @Test(dataProvider = "data")
    public static void test(Node head, boolean hasCycle) {
        assertEquals(hasCycle(head), hasCycle);
    }

    @DataProvider
    public static Object[][] data() {
        return new Object[][] {
                {null,false},
                {noCycleListOfLength(10), false},
                {cycleList(), true},
                {new Node(0), false},
                {minimalCycle(), true}
        };
    }

    private static Node minimalCycle() {
        Node n = new Node(0);
        n.next = n;
        return n;
    }

    @Test
    public static void testListCreation() {
        Node current = noCycleListOfLength(5);
        for (int i = 0; current != null; i++) {
            System.out.println(current.data);
            assertEquals(current.data, i);
            current = current.next;
        }
    }

    public static Node cycleList() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        n1.next = n2;
        n2.next = n3;
        n3.next = n2;

        return n1;
    }

    public static Node noCycleListOfLength(int length) {
        Node head = new Node(0);
        Node current = head;
        for (int i = 1; i < length; i++) {
            Node newNode = new Node(i);
            current.next = newNode;
            current = newNode;
        }
        return head;
    }


}
