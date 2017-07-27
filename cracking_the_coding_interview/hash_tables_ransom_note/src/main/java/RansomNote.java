import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class RansomNote {
    List<String> magazine = new LinkedList<>();
    List<String> note = new LinkedList<>();

    public RansomNote(List<String> magazine, List<String> note) {
        this.magazine = new ArrayList<>(magazine);
        this.note = new ArrayList<>(note);
        magazine.sort(Comparator.naturalOrder());
        note.sort(Comparator.naturalOrder());
    }

    boolean possibleToCreateNote() {
        if (note.size() > magazine.size()) {
            return false;
        }
        for (String word : note) {
            if (magazine.contains(word)) {
                magazine.remove(word);
            } else {
                return false;
            }
        }
        return true;
    }

    int indexOfWordInMagazine(String word, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return -1;
        }
        int middleIndex = ((endIndex - startIndex) / 2) + startIndex;
        String middle = magazine.get(middleIndex);
        if (middle.equals(word)) {
            return middleIndex;
        } else if (middle.compareTo(word) > 0) {
            return indexOfWordInMagazine(word, startIndex, middleIndex);
        } else {
            return indexOfWordInMagazine(word, middleIndex, endIndex);
        }
    }

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {Arrays.asList("give", "me", "one", "grand", "today", "night"),
                        Arrays.asList("give", "one", "grand", "today"), true},
                {Arrays.asList("two", "times", "three", "is", "not", "four"),
                        Arrays.asList("two", "times", "two", "is", "four"), false}
        };
    }

    @Test(dataProvider = "data")
    public static void test(List<String> magazine, List<String> note, boolean expected) {
        assertEquals(new RansomNote(magazine, note).possibleToCreateNote(), expected);
    }
}
