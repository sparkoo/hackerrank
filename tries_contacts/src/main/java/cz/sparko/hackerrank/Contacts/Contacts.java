package cz.sparko.hackerrank.Contacts;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Contacts {
    Map<Character, List<String>> contacts = new HashMap<>();

    long op(String op, String contact) {
        if ("add".equals(op)) {
            add(contact);
            return 0;
        } else if ("find".equals(op)) {
            return find(contact);
        } else {
            throw new UnsupportedOperationException("unsupported operation [" + op + "]");
        }
    }

    void add(String contact) {
        Character firstChar = contact.charAt(0);
        if (!contacts.containsKey(firstChar)) {
            contacts.put(firstChar, new LinkedList<>());
        }
        contacts.get(firstChar).add(contact);
    }

    long find(String contact) {
        long count = 0;
        Character firstChar = contact.charAt(0);
        if (contacts.containsKey(firstChar)) {
            for (String c : contacts.get(firstChar)) {
                if (c.startsWith(contact)) {
                    count++;
                }
            }
        } else {
            return 0;
        }

        return count;
    }

    @Test
    public static void test() {
        Contacts contacts = new Contacts();
        contacts.op("add", "hack");
        contacts.op("add", "hackerrank");
        assertEquals(contacts.op("find", "hac"), 2);
        assertEquals(contacts.op("find", "hak"), 0);
    }
}
