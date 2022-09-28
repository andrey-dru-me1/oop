import java.util.Arrays;

/**
 * A black box where you can only put elements on its top (push)
 * or extract them from the top with pop.
 */
public class Stack<T> {

    private T[] stack;

    @SuppressWarnings("unchecked")
    public Stack() {
        stack = (T[]) (new Object[0]);
    }

    /**
     * Appends element to the ecd of stack.
     *
     * @param value The value to add to the stack
     */
    public void push(T value) {
        stack = Arrays.copyOf(stack, stack.length + 1);
        stack[stack.length - 1] = value;
    }

    /**
     * Appends the array to the end of stack.
     *
     * @param arr Array of elements to append in the stack
     */
    public void pushStack(T[] arr) {
        int prevLen = stack.length;
        stack = Arrays.copyOf(stack, stack.length + arr.length);
        System.arraycopy(arr, 0, stack, prevLen, stack.length - prevLen);
    }

    /**
     * Returns the latest element and removes the one from the stack.
     *
     * @return The latest element
     */
    public T pop() {
        T res = stack[stack.length - 1];
        stack = Arrays.copyOf(stack, stack.length - 1);
        return res;
    }

    /**
     * Returns an array of the cnt elements from the end of stack.
     *
     * @param cnt Count of elements to pop
     * @return Array of extracted elements
     */
    public T[] popStack(int cnt) {
        @SuppressWarnings("unchecked") T[] arr = (T[]) (new Object[cnt]);
        System.arraycopy(stack, stack.length - cnt, arr, 0, cnt);
        stack = Arrays.copyOf(stack, stack.length - cnt);
        return arr;
    }

    /**
     * Returns count of elements in the stack.
     *
     * @return Count of elements
     */
    public int count() {
        return stack.length;
    }

}
