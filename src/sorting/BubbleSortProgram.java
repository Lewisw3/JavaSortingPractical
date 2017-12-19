package sorting;
 
public class BubbleSortProgram {
     
    public static void main(String[] args) {
    	/*
    	 * Bubble sort program that takes in a file name and performs the bubble sort
    	 */
        System.out.println("Sorting Array...");
        BubbleSort sorter = new BubbleSort();
        int[] array = sorter.readFile(args[0]);
        array = sorter.bubbleSort(array);
        //double checks the list is actually sorted
        System.out.println("Is list sorted? " + sorter.isSorted(array));
         
    }
 
}