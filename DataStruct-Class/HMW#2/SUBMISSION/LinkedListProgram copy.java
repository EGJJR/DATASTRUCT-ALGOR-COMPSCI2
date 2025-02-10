/*
LINKED-LIST PROGRAM

Author: Edd Joseph
Date: 07/17/2023
Class: MET CS342
Issues: None Known
 
Description:

/*
 * LinkedListProgram implements a menu-driven program for manipulating a LinkedList in Java.
 * 
 * It utilizes the built-in Java LinkedList class to store a heterogeneous list of objects.
 * 
 * The main() method:
 * - Creates a Scanner for user input
 * - Instantiates an empty LinkedList<Object>
 * 
 * It then enters a loop offering the user options:
 * 
 * 1. Add to List
 *   - Prompts for an int or String input
 *   - Adds the input to the end of the list
 *   
 * 2. Delete last element from List
 *   - Calls removeLast() to delete the end of the list
 *   - Handles empty list case
 *   
 * 3. Display the List 
 *   - Prints the list
 *   - Handles empty list case
 *   
 * 4. Exit Program
 * 
 * After each operation, it loops back to show the menu.
 * 
 * This demonstrates using a LinkedList, generics, user input, and menus in Java.
 */




import java.util.Scanner;
import java.util.LinkedList;

public class LinkedListProgram<T> {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LinkedList<Object> list = new LinkedList<>();
       

        int choice = 0;
        // This method is used as a menu choice
        //if choice is 
        while (choice != 4) {
            System.out.println("1. Add to List");
            System.out.println("2. Delete last element from List");
            System.out.println("3. Display the List");
            System.out.println("4. Exit Program");
            System.out.print("Please enter choice: ");
            choice = input.nextInt();
            // This method is called when the user clicks on the choice.
            switch (choice) {
                case 1:
                    System.out.print("Enter data (Integer or String) to add: ");
                    // Add a number or string to the list.
                    if(input.hasNextInt()){
                        int num = input.nextInt();
                        list.add(num);
                    } else{ 
                        String str = input.next();
                        list.add(str); }
                        break;

                case 2:
                    // Removes the last element from the list.
                    if (!list.isEmpty()) {
                        list.removeLast();
                        System.out.println("Last element deleted");
                    } else {
                        System.out.println("List is empty");
                    }
                    break;

                case 3:
                     // Prints the list to console or if list is empty  then it declares so
                     if (!list.isEmpty()) {
                        System.out.println("List: " + list);
                    } else {
                        System.out.println("List is empty");
                    }
                    break;

                case 4:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}