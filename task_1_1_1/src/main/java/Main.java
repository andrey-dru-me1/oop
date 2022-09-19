import java.util.Scanner;

public class Main {

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
