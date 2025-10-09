import java.util.ArrayList;
import java.util.LinkedList;

class Operation {

    void ArrayInsertion(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        long start = System.currentTimeMillis(); // time before insert
        for (int i = 0; i < n; i++) {
            list.add(i); // insert element at end
        }
        long end = System.currentTimeMillis();   // time after insert
        System.out.println("ArrayList insert " + n + " items: " + (end - start) + " ms");
    }

    // method to test LinkedList insertion
    void LinkedInsertion(int n) {
        LinkedList<Integer> list = new LinkedList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("LinkedList insert " + n + " items: " + (end - start) + " ms");
    }

    // method to test ArrayList deletion
    void ArrayDeletion(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            list.remove(list.size() / 2); // remove from middle
        }
        long end = System.currentTimeMillis();
        System.out.println("ArrayList delete " + n + " items: " + (end - start) + " ms");
    }

    // method to test LinkedList deletion
    void LinkedDeletion(int n) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            list.remove(list.size() / 2); // remove from middle
        }
        long end = System.currentTimeMillis();
        System.out.println("LinkedList delete " + n + " items: " + (end - start) + " ms");
    }


}

public class Array_Linked_Comparison {
    public static void main(String[] args) {
        Operation a1 = new Operation(); // create object of class A

        // test with 10k, 50k, 100k
        int[] test = {10000, 50000, 100000};
        for (int n : test) {
            System.out.println("\nTesting " + n + " elements :");
            a1.ArrayInsertion(n);
            a1.LinkedInsertion(n);
            a1.ArrayDeletion(n);
            a1.LinkedDeletion(n);
        }
    }
}
