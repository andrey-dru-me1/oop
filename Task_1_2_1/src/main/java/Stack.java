import java.util.Arrays;

public class Stack {

    Object[] stack;

    public Stack() {
        stack = new Object[0];
    }

    public void push(Object value) {
        stack = Arrays.copyOf(stack, stack.length + 1);
        stack[stack.length - 1] = value;
    }

    public void pushStack(Object[] arr) {
        int prevLen = stack.length;
        stack = Arrays.copyOf(stack, stack.length + arr.length);
        System.arraycopy(arr, 0, stack, prevLen, stack.length - prevLen);
    }

    public Object pop() {
        Object res = stack[stack.length - 1];
        stack = Arrays.copyOf(stack, stack.length - 1);
        return res;
    }

    public Object[] popStack(int cnt) {
        Object[] arr = new Object[cnt];
        System.arraycopy(stack, stack.length - cnt, arr, 0, cnt);
        stack = Arrays.copyOf(stack, stack.length - cnt);
        return arr;
    }

    public int count() {
        return stack.length;
    }

}
