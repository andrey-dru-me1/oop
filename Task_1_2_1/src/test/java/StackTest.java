import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

class StackTest {

    @Test
    void testInts() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(7);
        stack.pushStack(stack);
        Assertions.assertEquals(stack.pop(), 7);

        Stack<Integer> toCompare = new Stack<>();
        toCompare.push(7);
        toCompare.push(2);
        Assertions.assertEquals(stack.popStack(2), toCompare);

        Assertions.assertEquals(stack.count(), 1);

        Object totest = new Object();
        Assertions.assertNotEquals(stack, totest);
    }

    @Test
    void testStrings() {
        Stack<String> stack = new Stack<>();

        Stack<String> newStack = new Stack<>();
        newStack.push("abrakadabra");
        newStack.push("nothing");
        newStack.push("Is");
        newStack.push("HeRe");

        stack.pushStack(newStack);
        stack.push("");

        Stack<String> toCompare = new Stack<>();
        toCompare.push("Is");
        toCompare.push("HeRe");
        toCompare.push("");
        Assertions.assertEquals(stack.popStack(3), toCompare);

        Assertions.assertEquals(stack.count(), 2);
        Assertions.assertEquals(stack.pop(), "nothing");
    }

    @Test
    void testNewObject() {

        class MyObj {

            final Integer num;
            final String[] strings;
            final Double dnum;

            MyObj(Integer num, String[] strings, Double dnum) {
                this.num = num;
                this.strings = strings;
                this.dnum = dnum;
            }

            @Override
            public boolean equals(Object inObj) {
                if (!(inObj instanceof MyObj)) return false;
                MyObj obj = (MyObj) inObj;
                return (Arrays.equals(this.strings, obj.strings)) &&
                        (Objects.equals(Double.valueOf(this.num), obj.dnum)) &&
                        (Objects.equals(Double.valueOf(obj.num), this.dnum));
            }
        }

        MyObj obj1 = new MyObj(17, new String[]{"I", "like", "formalin"}, 27.0);
        MyObj obj2 = new MyObj(17, new String[]{"I", "like", "formalin"}, 27.0);
        MyObj obj3 = new MyObj(17, new String[]{"I", "hate", "formalin"}, 27.0);
        MyObj obj4 = new MyObj(27, new String[]{"I", "like", "formalin"}, 17.0);

        Stack<MyObj> stack = new Stack<>();
        stack.push(obj1);   //1
        stack.push(obj2);   //1, 2
        stack.push(obj3);   //1, 2, 3

        Assertions.assertEquals(stack.count(), 3);
        Assertions.assertNotEquals(stack.pop(), obj4);  //1, 2
        Assertions.assertEquals(stack.count(), 2);

        stack.push(obj4);   //1, 2, 4
        Assertions.assertEquals(stack.count(), 3);

        Stack<MyObj> toCompare = new Stack<>();
        toCompare.push(obj4);
        toCompare.push(obj2);
        Assertions.assertEquals(stack.popStack(2), toCompare);   //1
        Assertions.assertEquals(stack.count(), 1);

    }

}