/* Hamzah Saeed
 * Ms. Karasinska
 * Assignment 1: Problem Statement #2
 * 2022-10-21
 * Program Description: This program will prompt the user to provide inputs for a variety of questions regarding their income and their name. Once this input is provided
 *                      it will go through a variety of if statements to stop the program if any errors are found, and if none are it will provide a formulated input that
 *                      shows the first name, last name, gross income, tax cuts, money given to charity and the net income.
 *
 * Data Dictionary:
 * familyName = Holds the user input for the family (last) name of the user
 * givenName = Holds the user input for the given (first )name of the user
 * hourlyPay = Holds the user input for the hourly pay of the user
 * hoursWorked = Holds the user input for the amount of hours worked by the user
 * grossPay = Holds the gross pay of the user, different calculations depending on whether the user has worked for more than 40 hours
 * taxBracket = Holds the tax bracket of the user, a character
 * tax = Holds the amount of money that tax cuts off from the gross income
 * netPay = Holds the amount of money after taxes and money given to charity are subtracted from the gross income
 * charity = Holds the character determining whether the user is giving to charity or not
 * charityCut = The amount of money given to charity, 0 or 20
 */


public class PayStub {  //Opens class
    public static void main(String[] args) { //Opens main




        System.out.println("What is your family (last) name?");
        String familyName = In.getString(); //Variable that stores the employee's familyName

        System.out.println("What is your given (first) name?");
        String givenName = In.getString();  //Variable that stores the employee's givenName

        System.out.println("What is your hourly rate of pay? (Only input digits, not the $ symbol - Hourly pay cannot be 0 or lower)");
        double hourlyPay = In.getDouble(); //Variable that stores the employee's hourlyPay


        if (hourlyPay <= 0) {   //Confirms that it is a valid hourly pay (anything higher than 0) and that the program will stop if it is not
            System.out.println("You have inputted an invalid hourly rate of pay. Hourly rate of pay MUST be above 0");

        } else {    // if pay is valid the rest of the program will continue to run
            System.out.println("How many hours did you work that week? Any time over 40 hours is double your hourly pay.");
            double hoursWorked = In.getDouble();  // Variable that stores the employee's hours worked
            if (hoursWorked < 0 || hoursWorked > 168) {  //Confirms if the hours worked is valid (0 or higher and 168 or less) and the program will stop if it is not
                System.out.println("You have entered an invalid input, please have 0-168 hours");

            } else { // if both pay and hours worked are valid the program will continue to run
                    double grossPay; //Variable that stores the employee's gross pay which will later be printed
                    if (hoursWorked > 40) { //Confirm whether the hours the employee has worked is over 40 and is thus able to be paid double for any time over 40 hours
                        grossPay = (hoursWorked - 40) * (hourlyPay * 2) + (40*hourlyPay);   //Initialize grossPay variable
                    } else { //Runs if the hours worked are under 40
                        grossPay = hourlyPay * hoursWorked; //Initialize grossPay variable if 40 hours or less is worked
                    }

                    System.out.println("Which letter defines the tax category you are in? (Only input ONE CAPITAL letter from the following categories) \nA. No tax deduction\nB. Tax is 10% of gross pay\nC. Tax is 20% of gross pay\nD. Tax is 29% of gross pay\nE. Tax is 35% of gross pay");
                    char taxBracket = In.getChar();  //Variable that stores the employee's tax bracket
                    if (taxBracket != 'A' && taxBracket != 'B' && taxBracket != 'C' && taxBracket != 'D' && taxBracket != 'E' && taxBracket != 'a' && taxBracket != 'b' && taxBracket != 'c' && taxBracket != 'd' && taxBracket != 'e') {   //Confirms whether the tax bracket letter that the employee has provided is valid and will stop running if it is not
                        System.out.println("You have entered an invalid tax category letter. A, B, C, D, E are the only available letters");

                    } else {    //if the hourly pay is valid, the hours worked is valid and the tax bracket is valid, the program will continue to run
                        double tax = 0; //Initialize the variable that will hold the tax bracket depending on the letter inputted
                        switch (taxBracket) {   //The switch statement goes through all the possibilities (cases) of the taxes
                        case 'A':   //The tax will remain the same as when it was initialized - 0%
                        case 'a':
                            break;
                        case 'B':
                        case 'b':
                            tax = grossPay * 0.1;   // tax divided by 100 which is then multiplied by the gross pay provides the tax
                            break;
                        case 'C':
                        case 'c':
                            tax = grossPay * 0.2;
                            break;
                        case 'D':
                        case 'd':
                            tax = grossPay * 0.29;
                            break;
                        case 'E':
                        case 'e':
                            tax = grossPay * 0.35;
                            break;


                        }

                            double netPay = grossPay - tax; //Calculates and stores the netPay before we determine whether employee has enough money to donate to charity
                            double charityCut = 0;  //Initialize the variable that stores the amount of money cut from gross income for charity


                        if (netPay >= 20) {  //Determines whether the employee has the funds to pay for charity, if not there is no prompt and we go to output
                            System.out.println("Do you want $20 of your pay to be given to the United Way Charity, put Y for yes and N for no");
                            char charity = In.getChar();    //Stores the user input for whether they want to pay $20 for charity or not
                            if (charity != 'Y' && charity != 'y' && charity != 'N' && charity != 'n') {     //Determines whether the input stored in 'charity' is a valid character or not
                                System.out.println("You have entered an invalid input - The only available inputs are Y and N");
                            } else if (charity == 'Y' || charity == 'y'){   //Determines whether the employee has chosen to pay $20 to charity or not
                                charityCut = 20;    //Initializes the variable charityCut
                                netPay -= 20;   //Removes 20 dollars from the current netPay due to $20 being given to charity
                                System.out.printf("Name: " + givenName + " " + familyName + "\n");
                                System.out.printf("Gross Pay: %.2f \n", grossPay);
                                System.out.printf("Tax: %.2f \n", tax);
                                System.out.printf("Charity: %.2f \n", charityCut);
                                System.out.printf("Net Pay: %.2f \n", netPay);

                            } else { //The else statement ensures that even if no is chosen the program will still continue to run
                                System.out.printf("Name: " + givenName + " " + familyName + "\n");
                                System.out.printf("Gross Pay: %.2f \n", grossPay);
                                System.out.printf("Tax: %.2f \n", tax);
                                System.out.printf("Charity: %.2f \n", charityCut);
                                System.out.printf("Net Pay: %.2f \n", netPay);
                                }


                        } else {    //This else statement ensures that even if the netPay is 20 or higher that the rest of the program will still continue to run
                            System.out.printf("Name: " + givenName + " " + familyName + "\n");
                            System.out.printf("Gross Pay: %.2f \n", grossPay);
                            System.out.printf("Tax: %.2f \n", tax);
                            System.out.printf("Charity: %.2f \n", charityCut);
                            System.out.printf("Net Pay: %.2f \n", netPay);
                    }


                }
            }
        }
    }
}