import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Anagrams {
    int numberNeeded(String a, String b) {
        Map<Character, Integer> charMapA = createCharMap(a);
        Map<Character, Integer> charMapB = createCharMap(b);

        Map<Character, Integer> subtractedCharMaps = new HashMap<>();

        for (Character cA : charMapA.keySet()) {
            subtractedCharMaps.put(cA, charMapA.get(cA));
        }
        for (Character cB : charMapB.keySet()) {
            if (subtractedCharMaps.containsKey(cB)) {
                subtractedCharMaps.put(cB, abs(subtractedCharMaps.get(cB) - charMapB.get(cB)));
            } else {
                subtractedCharMaps.put(cB, charMapB.get(cB));
            }
        }

        return subtractedCharMaps.values().stream().reduce((i1, i2) -> i1 + i2).get();
    }

    private int abs(int val) {
        return val >= 0 ? val : val * -1;
    }

    private Map<Character, Integer> createCharMap(String a) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (Character c : a.toCharArray()) {
            if (!charMap.containsKey(c)) {
                charMap.put(c, 0);
            }
            charMap.put(c, charMap.get(c) + 1);
        }
        return charMap;
    }

    @Test(dataProvider = "anagramData")
    public static void test(String a, String b, int distance) {
        assertEquals(new Anagrams().numberNeeded(a, b), distance);
    }

    @DataProvider
    public Object[][] anagramData() {
        return new Object[][] {
                {"bacdc", "dcbac", 0},
                {"cde", "abc", 4}
        };
    }
}
