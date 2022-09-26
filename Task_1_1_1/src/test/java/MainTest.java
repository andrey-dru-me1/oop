import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void main() {

        Assertions.assertEquals(
                Main.sort(new ArrayList<>(Arrays.asList(3, 2, 1, 4, 5))),
                new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5))
        );

        Assertions.assertEquals(
                Main.sort(new ArrayList<>(List.of())),
                new ArrayList<>(List.of())
        );

        Assertions.assertEquals(
                Main.sort(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0))),
                new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0))
        );

        Assertions.assertEquals(
                Main.sort(new ArrayList<>(Arrays.asList(3, -2, 1, 4, -5))),
                new ArrayList<>(Arrays.asList(-5, -2, 1, 3, 4))
        );

        Assertions.assertEquals(
                Main.sort(new ArrayList<>(Arrays.asList(354154616, 2, 354154636, -4, -35456831))),
                new ArrayList<>(Arrays.asList(-35456831, -4, 2, 354154616, 354154636))
        );

    }
}