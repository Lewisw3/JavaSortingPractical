package sorting;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class MergeSortProgram {
	
	/*
	 * Program that implements the merge sort algorithm
	 */
	
	public static void main(String[] args) {
		System.out.println("Sorting list...");
		MergeSort sorter = new MergeSort();
		//read in CSV file
		int[] array = sorter.readFile(args[0]);
		//set instrumentation array
		sorter.setSplitArray(array.length);
		long startTime = System.nanoTime();
		sorter.setStartTime(startTime);
		array = sorter.mergeSort(array);
		long elapsedTime = System.nanoTime() - startTime;
		generateResult(elapsedTime, array.length, sorter.getSplits(), sorter.getSplitTimes());
		
		System.out.println("Is list sorted? " + sorter.isSorted(array));

		
	}
	
	public static void generateResult(long elapsedTime, int n, int splits, String[] splitTimes) {
		//method that creates outputs the performance data to a CSV file
		 PrintWriter writer = null;
	      String filePath = System.getProperty("user.dir") + "/merge" + new Date() + ".csv";
	      try {
	          //output macro data about algorithm
	          writer = new PrintWriter(new FileWriter(filePath));
	          writer.println("Array size," + n);
	          writer.println("Elapsed time," + elapsedTime);
	          writer.println("Number of splits," + splits);
	          for(int i=0; i<splitTimes.length;i++) {
	        	  if(splitTimes[i] != null) {
	        		  writer.println(splitTimes[i]);
	        	  }
	          }
	          writer.close();
	          System.out.println("Success! New results file create at " + filePath);
	      } catch(IOException e) {
	          System.err.println("Failed to generate results");
	          e.printStackTrace();
	      }
	}

}
