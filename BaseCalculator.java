/*
* Hamzah Saeed
* Ms. Karasinska
* Assignment 4 - Base Calculator
* 2022-12-21
* Program Description - The program is a base calculator that has the user enter 5 pieces of information: an input base,
* a mathematical equation in that input base (10 + 10), supporting +, -, *, / and %, and lastly an output base in which
* the final answer is in. Each piece of information is split with a space so the computer is able to tell what it needs
* to do. The computer converts the math equation in whatever input base it is in to a decimal (base 10) system, does the
* math equation and converts it to the output base.
*
* Data Dictionary:
* start = holds the users decision on whether they would like to begin or stop the program
* inLine = holds the users string input which is validated through a method
* array[] = holds the pieces of information from the inline being split from the spaces, holds the string version of all
*         these pieces of information in an array
* arr[] = converts all the string integers in the equation to integers for the computer to do math quicker, stored in an
*         array. It also stores the 2 mathematical equation numbers before and after they are converted to decimal
* sum = stores the sum of the mathematical equation after converting to decimal numbers
* remainder = stores the remainder of the two math numbers in the equation if we are doing division
 */

public class BaseCalculator{
//Main method validates the user input, confirms whether the use wants to continue, and prints the end result
    public static void main(String[] args){
        String start;   //Variable that will store the users decision on whether to start or not
        System.out.println("Welcome to Hamzah's different base calculator\nEnter a starting base, a math equation and an output base in the following format: 10 9 + 8 7\n10 is the base the numbers in the math equation are in, 9 + 7 is the math equation and 7 is the output base\nEnsure that each number and operator has a space after it (other than the last output base)");
        System.out.println("Your base must be within the inclusive range of 2-10\nThe supported math operations are: +, -, *, /, %");
        do {    //Loops the main program until the user decides to stop the program
            System.out.println("Enter (1) to use the calculator\nEnter (stop) to end the program");
            start = In.getString(); //Initializes the variable that stores the users decision on whether they want to start or not
            if (!start.equals("1") && !start.equalsIgnoreCase("stop")){   //If neither options are chosen code in curly brackets occurs
                do{ //Loops user input until they enter a correct input
                    System.out.println("Invalid Input. Re-Enter."); //Prompts the user
                    start = In.getString(); //Initializes the start variable that holds the users decision to start or not
                }while(!start.equals("1") && !start.equalsIgnoreCase("stop"));    //Loop continues until proper conditions are met - stop or 1
            }
            if(start.equals("1")){  //If the user decided to start the main program will run (anything in curly brackets)
                String inLine = stringInput();  //The variable 'inLine' stores the users input after validating if it is a correct input or not
                String[] array = charInputs(inLine);    //array is an array that stores the users inputs as strings after being split up, it is 5 long (index of 4)
                int[] arr = new int[5]; //arr is an array that stores the users inputs as integers after being split up, it is 5 long (index of 4) with the index of 2 being null
                array[2] = opCheck(array[2]);   //Checks whether the user has entered a valid operator and re-initializes where the operator is stored if it is not valid
                arr[0] = inputChecker(array[0]); //Converts the string input of the input base as an integer in the arr[] array, also checks whether it is a valid base though the inputChecker method
                arr[4] = inputChecker(array[4]); //Converts the string input of the output base as an integer in the arr[] array, also checks whether it is a valid base though the inputChecker method
                array[1] = mathIntCheck(array[1], arr[0]);  //Checks whether the 1st number in the math problem is a valid number (no digits higher than base), if it is not re-initializes where it is stored with another string
                array[3] = mathIntCheck(array[3], arr[0]);  //Checks whether the 2nd number in the math problem is a valid number (no digits higher than base), if it is not re-initializes where it is stored with another string
                while(array[2].equals("-") && Integer.parseInt(array[1]) - Integer.parseInt(array[3])<0){   //while loop occurs if in a subtraction case a negative number is returned
                    System.out.println("The math equation provided returns a negative number, the returned number must be positive, please re-enter both of them.");    //informs the user
                    System.out.println("Number 1:");    //prompts user to enter new number
                    array[1] = In.getString();  //stores a VALID number into array[1]
                    System.out.println("Number 2"); //prompts user to enter a second new number
                    array[3] = In.getString();  //stores another VALID number into array[3]
                    array[1] = mathIntCheck(array[1], arr[0]);  //Checks whether the 1st number in the math problem is a valid number (no digits higher than base), if it is not re-initializes where it is stored with another string
                    array[3] = mathIntCheck(array[3], arr[0]);  //Checks whether the 2nd number in the math problem is a valid number (no digits higher than base), if it is not re-initializes where it is stored with another string
                }   //closes loop
                arr[1] = mathNumConversion(arr[0], array[1]);   //Converts the 1st number in the math problem to a decimal number through a method
                arr[3] = mathNumConversion(arr[0], array[3]);   //Converts the 2nd number in the math problem to a decimal number through a method
                System.out.println(array[1] + " (base " + arr[0] + ") " + array[2] + " " + array[3] + " (base " + arr[0] + ")");    //Prints out the math equation in a base 10 (decimal) system
                int answer = decimalMath(arr[1], arr[3], array[2]);    //creates a variable and initializes that variable to store the answer of the math problem
                System.out.print("= "+toOutputBase(arr[4], answer));    //prints the final output line
                if(array[2].equals("/")) {  //If the operator is division we must find the output base remainder
                    int remainder = arr[1]%arr[3];  //Initializes the value of the remainder
                    System.out.print(" R "+toOutputBase(arr[4], remainder)+" ");    //Prints out the remainder after converting the base with the toOutputBase method
                }
                System.out.println("(base  "+arr[4]+")");   //Prints out the base the line was in
            }
        }while(!start.equalsIgnoreCase("stop"));  //While the stop line is not entered, the program will continue
        System.out.println("You have ended the program\nHave an excellent rest of your day!");  //Goodbye message
    }


    //This method checks whether the string is valid through a variety of checks including: doubleSpaces, too many spaces (can only have 4 or else spaces are at start or end)
    //checking if non-supported characters are added (mostly letters)
    public static String stringInput(){
        String a;   //Stores the users input
        boolean doubleSpace;    //Stores true or false on whether there iss a double space in the string
        int spaceCount; //Stores the amount of strings in the string, there may only be 4
        boolean allInt; //Checks to see whether only supported characters are in the input
        do{ //Loop continues until valid string is added
            doubleSpace = false;    //at the start of each loop doubleSpace will be false
            spaceCount = 0; //at the start of each loop amount of spaces resets to 0
            allInt = true;  //at the start of each loop the boolean to check whether all the characters are supported sets as true
            System.out.println("Enter your equation:"); //Prompts user to enter equation
            a = In.getString(); //Initializes a variable with input
            for(int i = 0; i<a.length()-1; i++){        //loop to go through each character and see whether any of the previous conditions are violated
                if(a.charAt(i)==' ' && a.charAt(i+1)==' ') {    //If a space has another space after it double space sets as true
                    doubleSpace = true; //Initializes doubleSpace as true as it is a double space
                }   //Closes if statement
                if(a.charAt(i)==' '){   //If there is a space add one to spaceCount
                    spaceCount += 1;    //Adds 1 to spaceCount
                }   //Closes if statment
                if(a.charAt(i)!=' ' && a.charAt(i)!='1' && a.charAt(i)!='2' && a.charAt(i)!='3' && a.charAt(i)!='4' && a.charAt(i)!='5' && a.charAt(i)!='6' && a.charAt(i)!='7' && a.charAt(i)!='8' && a.charAt(i)!='9' && a.charAt(i)!='0' && a.charAt(i)!='+' && a.charAt(i)!='-' && a.charAt(i)!='*' && a.charAt(i)!='/' && a.charAt(i)!='%'){   //If a non-supported character is in the input, input it invalid
                    allInt = false; //Initializes allInt to false
                }   //Closes if statement
            }   //Closes for loop
            if(a.charAt(0)==' ' || a.charAt(a.length()-1)==' ' || doubleSpace || spaceCount!=4 || !allInt){ //If any conditions are violated print next statement
                System.out.println("You have entered an invalid input\nYou cannot start or end your problem with a space, you also cannot have doubles spaces in your problem, your input cannot have letters or other unsupported characters. You must have a space between each 'input' - input base, number 1, math operator, number 2 and output base.");   //Tells user an invalid input was provided
            }   //Closes if statement
        }while(a.charAt(0)==' ' || a.charAt(a.length()-1)==' ' || doubleSpace || spaceCount!=4 || !allInt); //If any conditions are violated loops again
        return a;   //If all conditions are met return the correct String
    }   //close method

    //this method coverts a math number in the math equation to base 10 from the input base, it is stored into an array in main
    public static int mathNumConversion(int base, String num){  //parameters are the inputBase and the num being converted
        int temp = 0; //temp is the number we will need to return
        for(int i = num.length()-1, j=0; i>=0; i--, j++){
            /*
             * When converting we start from base^0 and go up (base^0, base^1, base^2...) starting from the right side of the number
             * thus we have to start from the end of the string and work leftward, as j increases so do our powers. We must itterate
             * through each individual character
             */
            temp += (Math.pow(base, j) * (Integer.parseInt(num.substring(i, i+1))) ); //By multiplying the digit by base^j and adding to temp until there are no more digits, it has been converted
        }   //closes for loop
        return temp;  //returns the converted variable
    }

    public static int decimalMath(int a, int b, String operator){   //This method does the math operation with the 2 base 10 math numbers depending on the operator
        char mathOperator = operator.charAt(0); //Switch statements need characters thus we convert the 1 character operator String into a character
        switch (mathOperator){  //create a switch statement with our mathOperator as the parameter
            case '+':   //Runs when the character is +
                System.out.println("= "+a+" + "+b+" (base 10)"); //Prints the step-by-step process for the user
                System.out.println("= "+(a+b)+" (base 10)"); //Prints the step-by-step process for the user
                return a+b; //Returns the value, so we can convert it to the output base
            case '-':
                System.out.println("= "+a+" - "+b+" (base 10)"); //Prints the step-by-step process for the user
                System.out.println("= "+(a-b)+" (base 10)"); //Prints the step-by-step process for the user
                return a-b; //Returns the value, so we can convert it to the output base
            case '*':
                System.out.println("= "+a+" * "+b+" (base 10)"); //Prints the step-by-step process for the user
                System.out.println("= "+(a*b)+" (base 10)"); //Prints the step-by-step process for the user
                return a*b; //Returns the value, so we can convert it to the output base
            case '/':
                System.out.println("= "+a+" / "+b+" (base 10)"); //Prints the step-by-step process for the user
                System.out.println("= "+(a/b)+" R "+(a%b)+" (base 10)"); //Prints the step-by-step process for the user
                return a/b; //Returns the value, so we can convert it to the output base
            case '%':
                System.out.println("= "+a+" % "+b+" (base 10)"); //Prints the step-by-step process for the user
                System.out.println("= "+(a%b)+" (base 10)"); //Prints the step-by-step process for the user
                return a%b; //Returns the value, so we can convert it to the output base
            default:
                System.out.println("Something went wrong"); //This portion should never be reached and if it does the user should know
                return -1;  //returns a temp variable that will not damage the rest of the program
        }
    }



    public static String toOutputBase(int base, int num){   //This method converts the answer of the math equation to the output base and prints it, parameters are the output base and the final answer to the math problem in base 10
        String output = ""; //String is going to be used for printing the final output
        int quotient = num; //Initializes a variable called quotient with the answer to the math equation
        while(quotient != 0){   //Continue loop until quotient divides into 0
            num = quotient % base;  //num will equal the remainder of quotient / base
            output = num + output;  //the string output will add the remainder going from right to left (backwards)
            quotient = quotient/base;   //reinitialize quotient as quotient / base to ensure that remainder isnt always the same
        }   //closes while loops
        return output;
    }   //closes method


    //This method confirms that the bases are valid and if they are not prompt the user to re-enter them
    public static int inputChecker(String a) {  //Parameter is the base stored in the array[] loop - Opens method
        for (int i = 0; i<a.length(); i++) {    //opens for loop that goes through each character of the string to confirm whether the base is valid or not
            while (a.charAt(i) == ' ' || a.charAt(i) == '+' || a.charAt(i) == '-' || a.charAt(i) == '*' || a.charAt(i) == '/' || a.charAt(i) == '%' || !(a.charAt(i)>=48 && a.charAt(i)<=57) || (Integer.parseInt(a)<2 || Integer.parseInt(a)>10)) {   //while the base is not a numerical digit and is not within the numbers 2-10 inclusive, re-enter the base
                System.out.println("Your base MUST be within the range of 2 to 10 inclusive, the base you entered was : (" + a +"). Re-enter your input base"); //prompt the user
                a = In.getString(); //re-initialize a with the new base
                i=0;    //reset i to 0 so the for loop may start again
            }//closes while loop
        }//closes for loop
        return Integer.parseInt(a); //return the new base as an integer and store it into the array - arr[]
    }//closes method

    //This method confirms that the math numbers in the math equation do not exceed the base or else it is invalid
    public static String mathIntCheck(String num, int base){    //Parameters are the number that is being checked and the base - Opens method
        for (int i = 0; i < num.length(); i++) {    //opens for loop to go through each character in the number to confirm if any digits are larger than or equal to the base
            while((num.charAt(i)==' ' || !(num.charAt(i)>=48 && num.charAt(i)<=57) || Integer.parseInt(num.substring(i, i + 1)) >= base) || (Integer.parseInt(num)<0)){  //confirms whether the user entered a space, a character that isn't a numerical number and a character that is negative, which in any of those scenarios means the base is not correct
                System.out.println("Every digit in the number you entered (" + num + ") must be less than your base of: " + base + "\nYou may not have negative numbers in this program\nRe-Enter the value of your math number with NO SPACES"); //Prompts the user
                num = In.getString();   //Re-initializes the base
                i = 0;  //Sets i to 0 so it can be looped through again to confirm that the new base is also valid
            } //closes while loops
        }   //closes for loop
        return num; //returns the new base
    }   //closes method

    //This method confirms that the user has entered a valid operator or not
    public static String opCheck(String a){ //The parameter is the operator in array[2] - opens the method
        while(!(a.equals("+") || a.equals("-") || a.equals("*") || a.equals("/") || a.equals("%"))) {   //While none of the correct operators are met, continue the loops
            System.out.println("You entered an incorrect operator (" + a + ")\nRe-Enter a correct operator:");  //User prompt
            a = In.getString(); //Stores the new operator into a
        }   //Closes while loops if valid operator is given
        return a;   //returns the new operator
    }   //closes method


    //This method splits up the string by the spaces it finds, putting them into an array to use throughout the program
    public static String[] charInputs(String inLine){   //The parameter is the input line that the user provides - Opens the method
        String[] a = new String [5];    //Creates an array that will store the 5 different values the equation is made out of
        int savedSpace = 0; //Saves the last known location of the last space to split - set as 0 because we will first split from the start of our string to the first space
        for(int i = 0, j=0; i<=inLine.length(); i++){   //Loops through each character, so we know when there is a space and whe to split
            if(i==inLine.length() || inLine.charAt(i)==' '){    //if we are at the last character or hit a space we will split up to the space or last character
                a[j] = inLine.substring(savedSpace, i); //We save the string we get after splitting the string into an array
                savedSpace = i+1;   //save where the space is + 1 because we don't want to have a space in our string, no double spaces ensures that we hit a character
                j++;    //add one to j to make sure that we move on to the next index of the array
            }   //close the if statement
        }   //close the for loop
        return a;   //return the array
    } //close the method

}   //close the class