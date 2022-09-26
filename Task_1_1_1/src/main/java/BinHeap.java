import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BinHeap class represents the binary heap variable type.
 * The class has inner variable contains binary heap itself
 * and some methods to work with the one.
 */
public class BinHeap {

    /**
     * The variable contains the binary heap (which is a binary tree) in a list.
     * <p>It has invariant that every son is always more or equal to his parent.</p>
     */
    private final List<Integer> binHeap;

    /**
     * Constructor of the BinHeap class initializes inner variable and sets it to the empty list.
     */
    public BinHeap() {
        binHeap = new ArrayList<>();
    }

    /**
     * Returns the element at the specified position of the binary heap.
     *
     * @param i Index of the element to return
     * @return The element at the specified position of the binary heap
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size())
     */
    public Integer get(int i) {
        return binHeap.get(i);
    }

    /**
     * Returns the number of elements in this list. If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     *
     * @return the number of elements in this list
     */
    public Integer size() {
        return binHeap.size();
    }

    /**
     * The siftUp function moves the element under index i up to
     * the root of the binary tree binHeap saving the invariant that
     * every son is more or equal to his parent.
     * <p>The whole route of the i-th element becomes a subtree of the one.</p>
     *
     * @param i The index of the element to sift up
     */
    private void siftUp(int i) {
        for (int j = i; j > 0 && this.get(j) < this.get((j - 1) / 2); j = (j - 1) / 2) {
            Collections.swap(binHeap, j, (j - 1) / 2);
        }
    }

    /**
     * The siftDown function moves the root element down the binary tree
     * saving invariant that every son is greater or equal to his parent
     * and that every leaf in the tree has no less depth than the whole
     * tree height minus one.
     */
    private void siftDown() {
        Collections.swap(binHeap, 0, binHeap.size() - 1); //swaps root and the last element
        binHeap.remove(binHeap.size() - 1);            //removes the last elem which used to be root

        //calculates the minimum for sons of the root
        int min;                                                                //creates helper variable

        //Sift-down algorithm itself
        for (int j = 0; j * 2 + 1 < binHeap.size(); j = min) {

            if (j * 2 + 2 >= binHeap.size()) min = j * 2 + 1;                                //if there is only one son
            else
                min = (binHeap.get(j * 2 + 1) < binHeap.get(j * 2 + 2)) ? j * 2 + 1 : j * 2 + 2;   //if there are both of sons

            if (binHeap.get(j) <= binHeap.get(min)) break;   //stops iterations when the value gets its position

            Collections.swap(binHeap, j, min);  //swaps current elem which used to be the last with one of his sons

        }
    }

    /**
     * Add the value to the tree using siftUp
     *
     * @param value Value that will add to the tree
     */
    public void add(int value) {
        binHeap.add(value);
        siftUp(binHeap.size() - 1);
    }

    /**
     * Returns the minimum in the binary tree, removes it from the tree
     * and remake the tree without its root element.
     *
     * @return The minimum of the elements in the binary tree
     */
    public int pop() {
        int result = binHeap.get(0);
        this.siftDown();
        return result;
    }

}
