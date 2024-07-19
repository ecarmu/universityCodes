package week8.movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SeleniumTest {

    public ArrayList<String> movies;

    @Test
    void storeData() {
    }

    @Test
    void locateData() {
    }

    @Test
    void connect() {
    }

    @Test
    void quit() {
    }

    @Test
    void getMovieNames() {
    }

    void executeSelenium(){
        Selenium selenium = new Selenium();
        selenium.connect();
        selenium.locateData();

        System.out.println(selenium.getMovieNames());

        movies = new ArrayList<>();

        Collections.copy(selenium.getMovieNames(), movies);
    }



    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple");
    }


    private static final Pattern SPECIAL_CHARACTERS_PATTERN = Pattern.compile("[^a-zA-Z]");
    @Test
    public void testMovieNames(ArrayList<String> movieList) {
             // Test movie names in each list

            for (String movieName : movieList) {
                // Test for empty or null movie names
                assertFalse(movieName == null || movieName.isEmpty(), "Movie name should not be null or empty");

                // Test for special characters or numbers in movie names
                assertFalse(SPECIAL_CHARACTERS_PATTERN.matcher(movieName).find() || containsNumber(movieName),
                        "Movie name should not contain special characters or numbers");

                // Test for movie names starting with a capital letter and ending with a letter
                assertTrue(movieName.matches("^[A-Z][a-zA-Z]*[a-z]$"),
                        "Movie name should start with a capital letter and end with a letter");
            }
    }

    private boolean containsNumber(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }




    public static void main(String[] args) {
        SeleniumTest seleniumTest = new SeleniumTest();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        seleniumTest.testMovieNames(strings);
        seleniumTest.executeSelenium();





    }
}