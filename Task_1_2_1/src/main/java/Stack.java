import java.util.Arrays;

public class Stack {

    int[] stack;

    public Stack() {
        stack = new int[0];
    }

    public void push(int value) {
        stack = Arrays.copyOf(stack, stack.length + 1);
        stack[stack.length - 1] = value;
    }

    public void pushStack(int[] arr) {
        int prevLen = stack.length;
        stack = Arrays.copyOf(stack, stack.length + arr.length);
        System.arraycopy(arr, 0, stack, prevLen, stack.length - prevLen);
    }

    public int pop() {
        int res = stack[stack.length - 1];
        stack = Arrays.copyOf(stack, stack.length - 1);
        return res;
    }

    public int[] popStack(int cnt) {
        int[] arr = new int[cnt];
        System.arraycopy(stack, stack.length - cnt, arr, 0, cnt);
        stack = Arrays.copyOf(stack, stack.length - cnt);
        return arr;
    }

    public int count() {
        return stack.length;
    }

}
