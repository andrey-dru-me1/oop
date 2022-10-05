package ru.nsu.fit.melnikov.oop.Task_1_2_1;

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

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Stack)) {
            return false;
        }

        Object[] cmp = ((Stack<?>) o).stack;
        return Arrays.equals(stack, cmp);
    }

    /**
     * Appends element to the end of stack.
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
        int prevLen = stack.length;
        stack = Arrays.copyOf(stack, stack.length + toPush.stack.length);
        System.arraycopy(toPush.stack, 0, stack, prevLen, stack.length - prevLen);
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
        Stack<T> res = new Stack<>();

        res.stack = Arrays.copyOf(res.stack, cnt);

        System.arraycopy(stack, stack.length - cnt, res.stack, 0, cnt);
        stack = Arrays.copyOf(stack, stack.length - cnt);

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
