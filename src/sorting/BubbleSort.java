package sorting;
 
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
  
public class BubbleSort extends SortAlgorithm {
     
    /*
     * Class implements the bubble sort and includes instrumentation to monitor performance
     * Class inherits from SortAlgorithm class. Class outputs the results of a sort to a csv file
     */
     
    public int[] bubbleSort(int[] array) {
        /*
         * method implements bubble sort algorithm. Includes with instrumentation code.
         * this is optimized since the n-th pass finds the n-th largest element and puts it into its final place
         * The inner loop only therefore only needs to go as far as n-1th element, and not the end of the array
         */
         
        //start algorithm timer
        long startTime = System.nanoTime();
        //create an array to monitor the elapsed time for each bubble (or pass)
        String[] passTimes = new String[array.length+5];
        //contains a flag that returns false when no more comparisons are made and list is sorted
        boolean flag = true;
        int temp;
        int lastElement = array.length;
        long numSwaps = 0, numPasses = 0, numComparisons = 0;
        while(flag) {
            numPasses++;
            flag = false;
            // start time for a individual pass
            long passStart = System.nanoTime();
            for(int i = 1; i < lastElement; i++) {
                numComparisons++;
                if(array[i-1] > array[i]) {
                    //swap values
                    numSwaps++;
                    temp = array[i-1];
                    array[i-1] = array[i];
                    array[i] = temp; 
                    flag = true;
                }
            }
            //calculate time for a pass 
            long passTime = System.nanoTime() - passStart;
            passTimes[(int) numPasses] = numPasses + "," + Long.toString(passTime);
            //decrease last element
            lastElement--;
        }
        long elapsedTime = System.nanoTime() - startTime;
        generateResultsCSV(passTimes, array, elapsedTime, numSwaps, numPasses, numComparisons);
        return array;
    }
     
  private void generateResultsCSV(String[] passTimes, int[] array, long elapsedTime, long numSwaps, long numPasses, long numComparisons) {
    //outputs the performance data for to a csv file in the current directory
      PrintWriter writer = null;
      String filePath = System.getProperty("user.dir") + "/bubble" + new Date() + ".csv";
      try {
          //output macro data about algorithm
          writer = new PrintWriter(new FileWriter(filePath));
          writer.println("Array Length," + array.length);
          writer.println("Elapsed Time," + elapsedTime);
          writer.println("Number of Swaps," + numSwaps);
          writer.println("Number of Passes," + numPasses);
          writer.println("Number of Comparisons," + numComparisons);
          //output the individual bubble (passes) times
          for(int i=1; i < passTimes.length; i++) {
              if(passTimes[i] != null) 
                  writer.println(passTimes[i]);
               writer.flush();
          }
          writer.close();
          System.out.println("Success! New results file create at " + filePath);
      } catch(IOException e) {
          System.err.println("Failed to generate results");
          e.printStackTrace();
      }
       
  }
     
     
     
}