package ru.nsu.fit.melnikov.oop.Task_1_2_1;

import java.util.Arrays;

/**
 * A black box where you can only put elements on its top (push)
 * or extract them from the top with pop.
 */
public class Stack<T> {

    private T[] stack;
    private int size;

    @SuppressWarnings("unchecked")
    public Stack() {
        stack = (T[]) (new Object[1]);
        size = 0;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Stack)) {
            return false;
        }

        Object[] cmp = ((Stack<?>) o).stack;
        return Arrays.equals(stack, 0, size - 1, cmp, 0, size - 1);
    }

    /**
     * Appends element to the end of stack.
     *
     * @param value The value to add to the stack
     */
    public void push(T value) {
        if (size + 1 > stack.length) {
            stack = Arrays.copyOf(stack, stack.length * 2);
        }
        stack[size++] = value;
    }

    /**
     * Appends the toPush stack to the end of current stack.
     *
     * @param toPush Stack of elements to append in current stack
     */
    public void pushStack(Stack<T> toPush) {
        if (size + toPush.size > stack.length) {
            stack = Arrays.copyOf(stack, (size + toPush.size) * 2);
        }
        System.arraycopy(toPush.stack, 0, stack, size, toPush.size);
        size += toPush.size;
    }

    /**
     * Returns the latest element and removes the one from the stack.
     *
     * @return The latest element
     */
    public T pop() {
        return stack[--size];
    }

    /**
     * Returns a stack of the cnt elements from the end of stack.
     *
     * @param cnt Count of elements to pop
     * @return Stack contained extracted elements
     */
    public Stack<T> popStack(int cnt) {

        Stack<T> res = new Stack<>();   //creates new stack

        res.stack = Arrays.copyOf(res.stack, cnt);  //gives required length

        System.arraycopy(stack, size - cnt, res.stack, 0, cnt); //copies stack array to res array
        res.size = cnt; //gives required stack size
        size -= cnt;    //"removes" elements from current stack

        return res;
    }

    /**
     * Returns count of elements in the stack.
     *
     * @return Count of elements
     */
    public int count() {
        return size;
    }

}
