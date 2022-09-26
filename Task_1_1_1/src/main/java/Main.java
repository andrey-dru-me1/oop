import java.util.ArrayList;
import java.util.List;

/**
 * Contains start method main and method sort.
 */
public class Main {

    /**
     * Scans the array of integer values from the prompt and call a sort method for it.
     *
     * @param args Integer arguments from the prompt. The array.
     */
    public static void main(String[] args) {

        List<Integer> intArgs = new ArrayList<>();
        for (String i : args) {
            intArgs.add(Integer.parseInt(i));
        }

        System.out.println(sort(intArgs));

    }

    /**
     * Sorts the input list by heapsort.
     *
     * @param input The list to sort.
     * @return The sorted list.
     */
    public static List<Integer> sort(List<Integer> input) {

        BinHeap nums = new BinHeap();   //Creates binary heap for heapsort

        while (!input.isEmpty()) {
            nums.add(input.remove(0));   //Adds all input numbers to the binary heap
        }

        List<Integer> res = new ArrayList<>();  //Creates the variable where sorted list
        //will be contained

        while (nums.size() > 0) {
            res.add(nums.pop()); //Adds elements to the res list in right order
        }

        return res;

    }

}
