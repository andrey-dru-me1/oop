import java.util.Scanner;

/**
 * Consists only start method main. Scans the unsorted array
 * from user, sorts it by heapsort and prints the result.
 */
public class Main {

    /**
     * Scans the unsorted array from user, sorts it by heapsort
     * and prints the result.
     *
     * @param args Arguments from the prompt. Not used in program.
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);            //Creates helper var to scan input System.in
        BinHeap nums = new BinHeap();

        System.out.println("Write down integer numbers separated by spaces and ended with dot:");

        while (in.hasNextInt()) {     //Scans next int until any non-space character
            nums.add(in.nextInt());
        }

        in.close();

        while (nums.size() > 0) {
            System.out.print(nums.pop() + " ");
        }

    }

}
