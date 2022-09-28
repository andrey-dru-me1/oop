import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class StackTest {

    @Test
    void testInts() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(7);
        stack.pushStack(new Integer[]{4, 8});
        Assertions.assertEquals(stack.pop(), 8);
        Assertions.assertEquals(Arrays.toString(stack.popStack(2)).trim(), "[7, 4]");
        Assertions.assertEquals(stack.count(), 1);
    }

}