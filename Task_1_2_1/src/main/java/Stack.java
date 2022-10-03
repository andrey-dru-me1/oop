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

    public Stack(Stack<T> another) {
        this.stack = another.stack;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object toCompare) {
        if (!(toCompare instanceof Stack)) return false;
        Stack<T> obj = (Stack<T>) toCompare;
        return Arrays.equals(stack, obj.stack);
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
     * Appends the toPush stack to the end of current stack.
     *
     * @param toPush Stack of elements to append in current stack
     */
    public void pushStack(Stack<T> toPush) {
        Stack<T> toPushCopy = new Stack<>(toPush);

        Stack<T> toPushRevert = new Stack<>();
        while (toPushCopy.count() > 0) {
            toPushRevert.push(toPushCopy.pop());
        }

        while (toPushRevert.count() > 0) {
            this.push(toPushRevert.pop());
        }
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
     * Returns a stack of the cnt elements from the end of stack.
     *
     * @param cnt Count of elements to pop
     * @return Stack contained extracted elements
     */
    public Stack<T> popStack(int cnt) {
        Stack<T> resRevert = new Stack<>();

        for (int i = 0; i < cnt; i++) {
            resRevert.push(this.pop());
        }

        Stack<T> res = new Stack<>();
        while (resRevert.count() > 0) {
            res.push(resRevert.pop());
        }

        return res;
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
