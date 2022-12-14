import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MainTest {

    private static Stream<Arguments> methodForTests() {
        return Stream.of(
                Arguments.of(
                        new ArrayList<>(Arrays.asList(3, 2, 1, 4, 5)),
                        new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5))
                ),
                Arguments.of(
                        new ArrayList<>(List.of()),
                        new ArrayList<>(List.of())
                ),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0)),
                        new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0))
                ),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(3, -2, 1, 4, -5)),
                        new ArrayList<>(Arrays.asList(-5, -2, 1, 3, 4))
                ),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(354154616, 2, 354154636, -4, -35456831)),
                        new ArrayList<>(Arrays.asList(-35456831, -4, 2, 354154616, 354154636))
                ),
                Arguments.of(
                        new ArrayList<>(
                                Arrays.asList(Integer.MAX_VALUE, Integer.MIN_VALUE, -4, 5, 0)
                        ),
                        new ArrayList<>(
                                Arrays.asList(Integer.MIN_VALUE, -4, 0, 5, Integer.MAX_VALUE)
                        )
                ),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(-3, -2, -1, 0, 1, 2, 3)),
                        new ArrayList<>(Arrays.asList(-3, -2, -1, 0, 1, 2, 3))
                ),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(3, 2, 1, 0, -1, -2, -3)),
                        new ArrayList<>(Arrays.asList(-3, -2, -1, 0, 1, 2, 3))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("methodForTests")
    void testExample(ArrayList<Integer> input, ArrayList<Integer> expected) {
        Assertions.assertEquals(Main.sort(input), expected);
    }

    @Test
    void testMain() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{"3", "2", "1", "4", "5"});

        Assertions.assertEquals(out.toString().trim(), "[1, 2, 3, 4, 5]");
    }
}