/* 
 This program was written
 by Devin Dougherty
 on 02/10/2021
 for the COSC 445W-01 class.
 Title: "Woods Simulation"
 Folder: "Homework1"
 Dependencies: Point.java
 This program generates a grid based on user input and
 simulates two entities wandering in random directions until
 they end up on the same cell of the grid. MOVES ARE NOT
 SHOWN WHEN MULTIPLE SIMULATIONS ARE RUN CONSECUTIVELY.
 Credit: Dr. Simon's point class from 'Java with Data Structures'.
*/

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Main {

  public static Boolean friendFound(Point p, Point p2) { // Returns true/false when both friends are on the same coordinate
    if ((p.getx() == p2.getx()) && (p.gety() == p2.gety())) // Compare X,Y coordinates between friends
      return true;
    else
      return false;
  }

  public static Point friendMove(Random r, Integer b, Integer a, Point p, Integer t) { // Move each friend 1 unit in a random direction (including diagonals)
    int direction = r.nextInt(8); // Choose a direction 1-7 at random
    int x = p.getx(); // Store x before modification
    int y = p.gety(); // Store y before modification

    if (direction == 0 && y != a - 1) // NORTH
    {
      if (t == 1) {
        System.out.println("moved North.");
      } // Direction does not print for multiple runs
      p.sety(y += 1);
    } else if (direction == 1 && y != 0) // SOUTH
    {
      if (t == 1) {
        System.out.println("moved South.");
      }
      p.sety(y -= 1);
    } else if (direction == 2 && x != b - 1) // EAST
    {
      if (t == 1) {
        System.out.println("moved East.");
      }
      p.setx(x += 1);
    } else if (direction == 3 && x != 0) // WEST
    {
      if (t == 1) {
        System.out.println("moved West.");
      }
      p.setx(x -= 1);
    } else if (direction == 4 && (y != a - 1 && x != 0)) // NORTH WEST
    {
      if (t == 1) {
        System.out.println("moved North-West.");
      }
      p.sety(y += 1);
      p.setx(x -= 1);
    } else if (direction == 5 && (y != 0 && x != 0)) // SOUTH WEST
    {
      if (t == 1) {
        System.out.println("moved South-West.");
      }
      p.sety(y -= 1);
      p.setx(x -= 1);
    } else if (direction == 6 && (y != a - 1 && x != b - 1)) // NORTH EAST
    {
      if (t == 1) {
        System.out.println("moved North-East.");
      }
      p.sety(y += 1);
      p.setx(x += 1);
    } else if (direction == 7 && (y != 0 && x != b - 1)) // SOUTH EAST
    {
      if (t == 1) {
        System.out.println("moved South-East.");
      }
      p.sety(y -= 1);
      p.setx(x += 1);
    } else {
      if (t == 1) {
        System.out.println("found a dead end.");
      } // If friend could not move there must have been a dead end
    }

    return p; // Return new Point object
  }

  public static void printGrid(Integer b, Integer a, Point p, Point p2) { // Visual representation of the friends' movement

    ArrayList<ArrayList<Integer>> list = new ArrayList<>(); // Declare 2D array

    for (int i = 0; i < b; i++) { // Build 2D array structure (Assign values)
      list.add(new ArrayList<>());
      for (int j = 0; j < a; j++) { // Place '1' where friends are located and '0' where they are not
        if ((i == p.getx() && j == p.gety()) || (i == p2.getx() && j == p2.gety()))
          list.get(i).add(1);
        else
          list.get(i).add(0);
      }
    }

    System.out.println();
    for (int i = 0; i < list.size(); i++) { // Print 2D array
      for (int j = 0; j < list.get(i).size(); j++) {
        System.out.print(list.get(i).get(j) + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) { // Main driver code

    Random r = new Random(); // Random int used for random friend movement
    Scanner sc = new Scanner(System.in); // Scanner for user input

    System.out.println("Welcome to Devin Dougherty's 'Lost in the woods' simulation!");
    System.out.println(
        "Pat and Chris are lost in a forest and will attempt to find each other by moving in random directions.");
    System.out.println("You will determine the dimentions of the forest by inputting the amount of rows and columns.");

    while (true) {
      ArrayList<Integer> counts = new ArrayList<Integer>(); // Used to keep track of the outputs of multiple simulations
      int a = 0, b = 0, t = 0; // a = COLUMNS, b = ROWS, T = # OF SIMULATIONS

      while (true) { // User specifies how many rows and columns the forest should have
        System.out.println("Enter the amount of ROWS the forest should have (MIN=2,MAX=50):");
        while (true) { // User input B
          String input = sc.next();
          try {
            b = Integer.parseInt(input);
            break;
          } catch (NumberFormatException ne) {
            System.out.println("Please enter a numerical value from 2-50.");
          }
        }

        System.out.println("Enter the amount of COLUMNS the forest should have (MIN=2,MAX=50):");
        while (true) { // User input A
          String input = sc.next();
          try {
            a = Integer.parseInt(input);
            break;
          } catch (NumberFormatException ne) {
            System.out.println("Please enter a numerical value from 2-50.");
          }
        }

        if (!(a >= 2 && a <= 50) || !(b >= 2 && b <= 50)) { // Check for valid inputs (2 <= X <=50)
          System.out.println("Those were not valid dimentions. Valid inputs range from 2 to 50.");
        } else {
          break;
        } // Break loop if valid input
      }

      while (true) { // User specifies how many times the simulation should run
        System.out.println("Enter the amount of times the simulation should run:");

        while (true) { // User input T
          String input = sc.next();
          try {
            t = Integer.parseInt(input);
            break;
          } catch (NumberFormatException ne) {
            System.out.println("Please enter a numerical value.");
          }
        }

        if (t == 0) { // Check for valid inputs (X <= 1)
          System.out.println("The simulation should run at least 1 time.");
        } else {
          break;
        } // Break loop if valid input
      }

      int count = 0; // Tracks the number of iterations for each simulation
      for (int j = 0; j < t; j++) { // Runs the simulation T times depending on user input
        Point f1 = new Point(0, 0); // Pat starts at left top
        Point f2 = new Point(b - 1, a - 1); // Chris starts at right bottom

        if (t == 1) {
          printGrid(b, a, f1, f2);
        } // Grid does not print for multiple runs
        count = 0; // Count starts at zero for new simulation

        while (!friendFound(f1, f2) || count > 999999) { // Loop will continue until friendFound or until count == 1,000,000
          if (t == 1) {
            System.out.print("Pat ");
          } // Name does not print for multiple runs
          f1 = friendMove(r, b, a, f1, t); // Move friend depending on grid layout (Pat)
          if (t == 1) {
            System.out.print("Chris ");
          } // Name does not print for multiple runs
          f2 = friendMove(r, b, a, f2, t); // Move friend depending on grid layout (Chris)
          if (t == 1) {
            printGrid(b, a, f1, f2);
          } // Grid does not print for multiple runs
          count++; // Increment count after each iteration
        }
        counts.add(count); // Add count to array for later calculations
      }

      if (t == 1) { // Ending output differs when multiple runs have been specified
        System.out.println("Pat and Chris found each other after " + count + " time unit(s)");
        System.out.println();
      } else {
        float avg = 0; // Used to store the average count value
        for (int i = 0; i < counts.size(); i++) { // Get each count in array
          avg += counts.get(i); // Add counts together
          System.out.println("Run " + (i + 1) + ": " + counts.get(i) + " time unit(s)");
        }
        avg = avg / counts.size(); // Divide final count by array size to find true average
        System.out.println("Average: " + avg + " time unit(s)");
        int max = Collections.max(counts); // Find maximum value in count array
        System.out.println("Maximum: " + max + " time unit(s)");
        int min = Collections.min(counts); // Find minimum value in count array
        System.out.println("Minimum: " + min + " time unit(s)");
        System.out.println();
      }
    }
  }
}