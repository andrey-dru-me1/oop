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
        stack.pushStack(new Integer[]{4, 8});
        Assertions.assertEquals(stack.pop(), 8);
        Assertions.assertEquals(Arrays.toString(stack.popStack(2)).trim(), "[7, 4]");
        Assertions.assertEquals(stack.count(), 1);
    }

    @Test
    void testStrings() {
        Stack<String> stack = new Stack<>();
        stack.pushStack(new String[]{"abrakadabra", "nothing", "Is", "HeRe"});
        stack.push("");
        Assertions.assertArrayEquals(stack.popStack(3), new String[]{"Is", "HeRe", ""});
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
        stack.pushStack(new MyObj[]{obj1, obj2, obj3}); //1, 2, 3

        Assertions.assertEquals(stack.count(), 3);
        Assertions.assertNotEquals(stack.pop(), obj4);  //1, 2
        Assertions.assertEquals(stack.count(), 2);

        stack.push(obj4);   //1, 2, 4
        Assertions.assertEquals(stack.count(), 3);
        Assertions.assertArrayEquals(stack.popStack(2), new MyObj[]{obj4, obj2});   //1
        Assertions.assertEquals(stack.count(), 1);

    }


}