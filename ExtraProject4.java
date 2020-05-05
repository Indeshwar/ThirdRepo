import java.io.*;
import java.util.*;
public class ExtraProject4{
   public static void main(String[] args){
      displayFile();
   
      diplayValidRecord();
      
      diplayProfit();
      
      displayGameResult();
      
      problem5();
   }
   
   //this method display all the valid records
   //and throws and handles an exception if the file does not exist.
   public static void displayFile(){
      //create an object of class File that takes path of file as parameter
      File file = new File("problem1.txt");
      
      try{
         Scanner sc = new Scanner(file);
         int numOfValid = 0;
         int numOfInvalid = 0;
         
         while(sc.hasNext()){
            String[] data = sc.nextLine().split(",");
            boolean b = true;
            
            //checking valid name that does not contain any digit or space
            char ch;
            if(b == true){
               for(int i = 0; i < data[0].length(); i++){
               ch = data[0].charAt(i);
                  if(ch == ' ' || (ch  >= '0' && ch <= '9')){
                     b = false;
                     break;
                  }
               }
            }
            
            //count the number of character '@'
            int count = 0;
            for(int i = 0; i < data[1].length(); i++){
               if(data[1].charAt(i) == '@'){
                  count++;
               }
            }
            
            //check valid email that contain  only one '@' character
            if(b == true){
               if(count != 1){
                  b = false;
               }
            }
            
            //Checking valid marital status that contains only one character 'M' or 'S'
            if(b == true){
               if(data[2].length() != 1){
                  b = false;
               }else{
                  ch = data[2].charAt(0);
                  if(ch == 'M' || ch == 'S'){
                     b = true;
                  }else{
                     b = false;
                  }
               }
            }
            
            //checking valid client Id which has positive number and length 6
            if(b == true){
               if(data[3].length() == 6 && Integer.parseInt(data[3]) > 0){
                  b = true;
               }else{
                  b = false;
               }
            }
            
            if(b == true){//print the information of a client if all the information is valid
               System.out.println("Name: " + data[0] + ", Email: " + data[1] + ", Marital status: " + data[2] + ", ID: " + data[3]);
               numOfValid++;
            }else{
               System.out.println("invalid record");
               numOfInvalid++;
            }
         }
         sc.close();//closing the file
         System.out.println("Number of valid record " + numOfValid);
         System.out.println("Number of invalid record " + numOfInvalid);
         System.out.println();

      //if file not found, it handle the exception 
      }catch(FileNotFoundException ex){
         System.out.println("File not found");
      }
      
    }
     
      //this method display a valid line that includes only digits 
      public static void diplayValidRecord(){
         File file = new File("problem2.txt");
         
         try{
            Scanner sc = new Scanner(file);
            
            int numOfValid = 0, numOfInvalid = 0;
            int sum = 0;
            while(sc.hasNext()){
               //read each line
               String s = sc.nextLine();
               boolean b = false;
               char ch;
               //this loop check whether all the characters of a line is digit or not 
               for(int i = 0; i < s.length(); i++){
                  ch = s.charAt(i);
                  if(ch >= '0' && ch <= '9'){
                     b = true;
                  }else{
                     b = false;
                     break;
                  }
               }
               
               //print the valid record if the all the characters are digits
               if(b == true){
                  System.out.println("valid record");
                  numOfValid++;//count record
                  sum += Integer.parseInt(s);//add the all the valid number
               }else{
                  System.out.println("invalid record");
                  numOfInvalid++;
               }
            }
            sc.close();//closing the file
            System.out.println("valid record: " + numOfValid + ", invalid record: " + numOfInvalid);
            System.out.println("Sum = " + sum);
            System.out.println();
         }catch(FileNotFoundException ex){
            System.out.println("File not found");
         }
      }
      
      //this method read a file problem3.txt that has 24 line. The first and second line is the income and expenses, respectively
      public static void diplayProfit(){
         File file = new File("problem3.txt");
         try{
            Scanner sc = new Scanner(file);
            
            int profit = 0;
            double totalProfit = 0;
            int count = 0;
            //compute the total profit of a year
            while(sc.hasNext()){
               profit = Integer.parseInt(sc.nextLine()) - Integer.parseInt(sc.nextLine());
               totalProfit += profit;
               count++;
               
            }
            sc.close();
            System.out.println("Total Profit: " + totalProfit);
            System.out.println("Average monthly Profit: " + totalProfit/count);
            System.out.println();

         }catch(FileNotFoundException ex){
            System.out.println("File not found");
         }
      }
      
      //this method reads the content of the file “problem4.txt”
      //displays the name of each team, the number of wins, the number of draws, the number of losses, goals for, goals against, goal difference, and the number of gained points  
      public static void displayGameResult(){
         File file = new File("problem4.txt");
         
         try{
            Scanner sc = new Scanner(file);
            
            String[] names = sc.nextLine().split(",");
            int nTeam = names.length;
            int[] points = new int[nTeam];
            int[] win = new int[nTeam];
            int[] loss = new int[nTeam];
            int[] draw = new int[nTeam];
            int[] goalFor = new int[nTeam];
            int[] goalAgainst = new int[nTeam];
            
            int ind0, ind1, val0, val1;
            //ind0: stores home team's index in the array of names
            //ind1: stores away team's index in the array of names
            //val0: stores the # goals scored by home team
            //val1: stores the # goals scored by away team

            while(sc.hasNext()){
               //store each information in a array of type string that is separated by a comma
               String[] teams = sc.nextLine().split(",");
               ind0 = findingIndex(names, teams[0]);//find the index of home team
               ind1 = findingIndex(names, teams[1]);//find the index of away team
               
               //store the number of goals of home team and away team in a array that is separated by character '-'
               String[] scores = teams[2].split("-");
               val0 = Integer.parseInt(scores[0]);
               val1 = Integer.parseInt(scores[1]);
               
                
               //For Goal
               goalFor[ind0] += val0;
               goalFor[ind1] += val1;
            
               //Against Goal
               goalAgainst[ind0] += val1;
               goalAgainst[ind1] += val0;
            
               //points
               if(val0 == val1){
                  points[ind0]++;
                  points[ind1]++;
                  draw[ind0]++;
                  draw[ind1]++;
               
               }else if(val0 > val1){
                  points[ind0] += 3;
                  win[ind0]++;
                  loss[ind1]++;
               
               }else{
                  points[ind1] += 3;
                  win[ind1]++;
                  loss[ind0]++;
               }
               
            }
            sc.close();
            System.out.printf("%10s%4s%4s%4s%5s%5s%5s%8s\n", "Team", "W", "D", "L", "GF", "GA", "GD", "Points");
            for(int k = 0; k < names.length; k++){
               System.out.printf("%10s%4s%4s%4s%5s%5s%5s%8s\n", names[k], win[k], draw[k], loss[k], goalFor[k], goalAgainst[k], goalFor[k] - goalAgainst[k], points[k]);
            }


         }catch(Exception ex){
            System.out.println("File not found");        
         }
      }
      
      //this method find the index of any team 
      public static int findingIndex(String[] names, String team){
         int index = 0;
         for(int i = 0; i < names.length; i++){
            if(names[i].equals(team)){
               index = i;
               break;
            }
         }
         return index;
      }
      
      
      //this method reads a file that includes employees’ names and performance and displays each employee’s name and incentive
      public static void problem5(){
         File file = new File("problem5.txt");
         
         try{
            Scanner sc = new Scanner(file);
            
            while(sc.hasNext()){
               String[] empl =  sc.nextLine().split(",");
               char ch = empl[1].charAt(0);
               if(ch == 'E'){
                  System.out.println("Incentive of " + empl[0] + " is $" + 1000);
               }else if(ch == 'V'){
                  System.out.println("Incentive of " + empl[0] + " is $" + 500);
               }else if(ch == 'G'){
                  System.out.println("Incentive of " + empl[0] + " is $" + 350);
               }else{
                  System.out.println("Incentive of " + empl[0] + " is $" + 0);
               }
            }
            sc.close();
         }catch(FileNotFoundException ex){
            System.out.println("File not found");
         }
      }      
      
}