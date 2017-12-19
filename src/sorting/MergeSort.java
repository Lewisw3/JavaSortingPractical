package sorting;
 
public class MergeSort extends SortAlgorithm {
	
    /*
     * Class implements the merge sort and includes instrumentation to monitor performance
     * Class inherits from SortAlgorithm class. Class outputs the results of a sort to a csv file
     */
	
   private int numSplits = 0;;
   private String[] splitTimes; 
   private long startTime;
   
   public int[] mergeSort(int[] array) {
	   //method that recursively implements the merge sort 
       int length = array.length;
       // base case
       if (length <= 1) {
           return array;
       } else {
    	   //split in half
           int[] left = split(array, length/2, true);
           int[] right = split(array, length/2, false);
           numSplits++;
           splitTimes[numSplits] = numSplits + "," + (System.nanoTime() - this.getStartTime());
           //recursively call merge sort for each half
           left = mergeSort(left);
           right = mergeSort(right);
           //merge together and return sorted array
           return merge(left, right);     
       }
   }
 
   private int[] merge(int[] left, int[] right) {
	   // method that merges together two ordered sublists
       int[] mergedArray = new int[left.length + right.length];
       int leftIndex = 0, rightIndex = 0, mergedIndex = 0;
        
       while(leftIndex < left.length && rightIndex < right.length) {
           if(left[leftIndex] <= right[rightIndex]) {
               //add left element
               mergedArray[mergedIndex] = left[leftIndex];
               leftIndex++;
           } else {
               //add right element
               mergedArray[mergedIndex] = right[rightIndex];
               rightIndex++;
           }
           mergedIndex++;
       }
 
       // once one the sublists is empty
       if(leftIndex == left.length) {
           // if left array is empty then append right array
           while(rightIndex < right.length) {
               mergedArray[mergedIndex] = right[rightIndex];
               rightIndex++;
               mergedIndex++;
           }
       } else {
           // if right list is empty
           while(leftIndex < left.length) {
               mergedArray[mergedIndex] = left[leftIndex];
               leftIndex++;
               mergedIndex++;
           }
       }
       return mergedArray;
   }
     
   private int[] split(int[] array, int partition, boolean isLeft) {
	   //splits the list into a given partition
	   if(isLeft) {
		   //for the left list
           int[] left = new int[partition];
           for(int i = 0; i < partition; i++) {
               left[i] = array[i];
           }
           return left;
       } else {
    	   //for the right list
           int[] right = new int[array.length - partition];
           int j = 0;
           for(int i = partition; i < array.length; i++) {
               right[j] = array[i];
               j++;
           }
           return right;
       }
   }
    
   public void setSplitArray(int size) {
	   // Initialize split times array
	   splitTimes = new String[size+5];
   }
   
   public int getSplits() {
	   return numSplits;
   }
   
   public String[] getSplitTimes() {
	   //return split times array
	   return this.splitTimes;
   }
   
   public long getStartTime() {
	   return this.startTime;
   }
   
   public void setStartTime(long time) {
	   startTime = time;
   }
}