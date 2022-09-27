import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(2);
        stack.push(7);
        stack.pushStack(new Integer[]{4, 8});
        System.out.println(stack.pop());
        System.out.println(Arrays.toString(stack.popStack(2)));
        System.out.println(stack.count());
    }

}
