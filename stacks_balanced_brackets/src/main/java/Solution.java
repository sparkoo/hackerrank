import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Solution {
    private static boolean isBalanced(String expression) {
        Deque<Character> stack = new LinkedList<>();
        Map<Character, Character> bracketsMap = new HashMap<>();
        bracketsMap.put('{', '}');
        bracketsMap.put('(', ')');
        bracketsMap.put('[', ']');
        for (char c : expression.toCharArray()) {
            if (bracketsMap.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character popped = stack.pop();
                if (c != bracketsMap.get(popped)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test(dataProvider = "data")
    public static void test(String expression, boolean expected) {
        assertEquals(isBalanced(expression), expected);
    }

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {"{[()]}", true},
                {"{[(])}", false},
                {"{{[[(())]]}}", true},
                {"}{", false},
                {"{{[[(())]]}", false}
        };
    }
}
