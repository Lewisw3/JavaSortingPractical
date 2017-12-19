package sorting;
 
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
 
public abstract class SortAlgorithm {
     
    /*
     * Abstract superclass that implements common methods between bubble sort and merge sort
     */
     
    protected int[] readFile(String filePath) {
        /*
         *  method to read a csv file and convert it into an array of integers
         *  Initially file is read into an ArrayList to utilize its ability to dynamically resize
         */
         
        ArrayList<Integer> data = new ArrayList<Integer>();
        BufferedReader reader = null;
        StringTokenizer tokenizer = null;
        String strLine = "";
         
        try{
            reader = new BufferedReader(new FileReader(filePath));
            //iterate through each line of the csv file
            while((strLine = reader.readLine()) != null) {
                //intialise a string tokenizer to tokenize the current line
                tokenizer = new StringTokenizer(strLine, ",");
                while(tokenizer.hasMoreTokens()) {
                    //add current token to array list
                    data.add(Integer.parseInt(tokenizer.nextToken()));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot parse CSV file. Try a different file. " + e.getMessage());
            System.exit(0);
        }       
        return convertListToIntArray(data);
    }
     
    private int[] convertListToIntArray(ArrayList<Integer> list) {
        //converts the array list into an array of integers
        int[] array = new int[list.size()];
        //iterate through list and array 
        Iterator<Integer> iterator = list.iterator();
        for(int i=0; i < array.length; i++) {
            array[i] = iterator.next().intValue();
        }
        return array;
    }
     
    protected void printArray(int[] array) {
        //print all the elements in a given array
        for(int element : array) {
            System.out.print(element + " ");
        }
        System.out.println("");
    }
     
    protected boolean isSorted(int[] array) {
        //returns true if all the elements in the array are sorted in ascending order
        for(int i=0; i < array.length - 1; i++) {
            if(array[i] > array[i+1]) {
                return false;
            }
        }
        return true;
    }
     
     
}