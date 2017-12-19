package test;
 
import java.io.*;
import java.util.Random;
 
public class createTestFiles {
     
    /*
     * Creates CSV files comprised of integer values to use as test data for sort algorithms
     * Can create random, sorted and reverse sorted data
     */
     
    //define the number of integers per line of the CSV file
    private static final int NUMBER_PER_LINE = 150;
     
    public static void main(String[] args) throws IOException {
        /*
         * Program can be run from the terminal. Program Takes in 3 arguments
         * args[0] - file name (must end in .csv)
         * args[1] - number of integers
         * args[2] - type of data set. Must be either 'random', 'sorted' or 'reverse'
         */
         
        //ensure arguments are provided
        if(args[0] == null || args[1] == null || args[2] == null) {
            System.err.println("Invalid arguments. Quitting...");
            return;
        }
        //check that filename ends with .csv and that the args[1] is an integer
        if(args[0].endsWith(".csv") && isInteger(args[1])) {
            //finally, check that args[2] is one of the three accepted strings
            if(args[2].equals("random") || args[2].equals("reverse") || args[2].equals("sorted")) {
                int n = Integer.parseInt(args[1]);
                PrintWriter writer = new PrintWriter(new FileWriter(System.getProperty("user.dir") + "/" + args[0]));
                //random number generator used to create random integers
                Random numGenerator = new Random();
                //find number of lines required and remained of integers for the last line
                int numLines = n / NUMBER_PER_LINE;
                int remainder = n % NUMBER_PER_LINE;
                 
                //---RANDOM DATA---
                if(args[2].equals("random")) { 
                    //for each line print out the specified number of random integers           
                    for(int i=0; i < numLines; i++) {
                        for(int j=0; j < NUMBER_PER_LINE; j++) {
                            if(j == NUMBER_PER_LINE - 1) {          
                                    //for last element in a line, don't include the comma
                                    writer.print(numGenerator.nextInt());
                            } else {
                                writer.print(numGenerator.nextInt() + ",");
                            }
                            //new line
                            writer.println("");
                        }           
                    }
                     
                    //print remainder of values on last line
                    for(int i=0; i < remainder; i++) {
                        if(i == (remainder - 1)) 
                            writer.print(numGenerator.nextInt());
                        else
                            writer.print(numGenerator.nextInt() + ",");
                    }
                }
                //---REVERSE DATA---
                if(args[2].equals("reverse")) {
                    //create a CSV all on one line with descending integers
                    for(int i=n; i>0; i--) {
                        if(i == 1) {
                            writer.print(1);
                        } else {
                            writer.print(i +",");
                        }
                    }
                }
                //MAKE SORTED FILE      
                if(args[2].equals("sorted")) {
                    //create a csv all on one line with ascending integers
                    for(int i=1; i<=n; i++) {
                        if(i == n) {
                            writer.print(i);
                        } else {
                            writer.print(i +",");
                        }
                    }
                }
                writer.close();
                System.out.println("Done!");
            } else {
                System.err.println("Cannot create CSV test file with those parameters");
                return;
            }
        }
    }
     
    private static boolean isInteger(String str) {
        //method to determine if a certain string can be converted into an integer
        try{
            Integer.parseInt(str);
        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
         
}