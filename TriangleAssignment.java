/*  Hamzah Saeed
*   Ms. Karasinska
*   2022-11-22
*   Assignment 3 - Triangle assignment
*   Program Description - The calculator will prompt the user on whether they would like to add integer side lengths (3,4,5) or coordinates (0, 0, 3, 0, 3, 4) depending on what they are using it for.
*   Both modes will calculate the area, perimeter, type of triangle (acute, obtuse, right and equilateral, isosceles, scalene), degrees of each angle and whether the triangle exists or not. The coordinates
*   have a bonus calculation of calculating the median of the triangle. The user is prompted throughout the program on how to enter their code and how the program will work.
*
*   Data Dictionary:
*   side1 - holds the first integer side length that the user inputs
*   side2 - holds the second integer side length that the user inputs
*   side3 - holds the third integer side length that the user inputs
*   x1 - holds the first x-coordinate that the user inputs
*   y1 - holds the first y-coordinate that the user inputs
*   x2 - holds the second x-coordinate that the user inputs
*   y2 - holds the second y-coordinate that the user inputs
*   x3 - holds the third x-coordinate that the user inputs
*   y3 - holds the third y-coordinate that the user inputs
*   sideC - Represents side c on the coordinate triangle, is the side opposite to angle C
*   sideA - Represents side a on the coordinate triangle, is the side opposite to angle A
*   sideB - Represents side b on the coordinate triangle, is the side opposite to angle B
*   userInput - users input at the start of the code to determine whether to enter integer side lengths or coordinates
 */



public class TriangleAssignment{
    //Main method allows the user to choose what type of input they would like to enter and prints out all the statements
    public static void main(String[] args){
        int side1, side2, side3;    //Declares the variables side1, side2 and side3 which are integer side lengths
        int x1, y1, x2, y2, x3, y3; //Declares the variables x1, x2, x3 and y1, y2 and y3 which are each coordinates on a plane
        double sideC, sideA, sideB;

        char userInput; //Stores the user input on whether they would like to use coordinates or integer side lengths
        System.out.println("Welcome to Hamzah's Triangle Calculator. You can choose whether you would like to enter integer side lengths or coordinates to calculate the type of triangle it is, the area of the triangle, the perimeter of the triange and much, much more!");
        do{ //Do loop that ensures they only choose a prompt that exists - 'A' or 'B'
            System.out.println("Would you like to input coordinates or integer values - Entering coordinates allows you to calculate the centroid - 'A' for coordinates and 'B' for integers");
            userInput = In.getChar();   //Stores whatever the user enters
        }while(userInput != 'A' && userInput !='B');    //Checks whether the user entered a valid option


        if(userInput=='B'){ //If the user chose to do integer side lengths everything inside this if statement will run
            do {    //Do loop that loops until the user provides 0 0 0
                System.out.println("Provide 3 side lengths - 0 0 0 to terminate");
                side1 = In.getInt();    //Stores length of side1
                side2 = In.getInt();    //Stores length of side2
                side3 = In.getInt();    //Stores length of side3
                System.out.print("Side a is "+side1 + " units, side b is " + side2 + "units and side c is " + side3+"units - ");    //Shows the user what they entered

                if (isFinished(side1, side2, side3)) {  //If they entered 0 0 0 it will let them know the program has ended
                    System.out.println(" Program was terminated by the user.");
                } else if (valid(side1, side2, side3)) {    //If the program has not finished and it is a valid triangle it will print a variety of triangle details
                    System.out.println(" Triangle possible: " + triangleSide(side1, side2, side3) + " and " + triangleAngle(side1, side2, side3) + ".");    //Prints the type of triangle it is based on side lengths
                    degrees(side1, side2, side3);   //Calls on a method that will print the actual degrees of each angle
                    System.out.println("The area of triangle ABC is: " + area(side1, side2, side3) + " units squared and the perimeter of triangle ABC is: " + perimeter(side1, side2, side3)+" units");   //Prints the area and the perimeter
                } else {    //If the triangle doesn't exist let the user know
                    System.out.println(" Triangle cannot be formed.");
                }
            } while (!isFinished(side1, side2, side3)); //Program loops while the user has not entered 0 0 0
        } else{ //Runs if the user decided to use coordinates
            do{
                System.out.println("Provide 6 coordinates - 3 x coordinates and 3 y coordinates - 0 0 0 0 0 0 to terminate");
                x1 = In.getInt();   //Stores coordinates of x1
                y1 = In.getInt();   //Stores coordinates of y1
                x2 = In.getInt();   //Stores coordinates of x2
                y2 = In.getInt();   //Stores coordinates of y2
                x3 = In.getInt();   //Stores coordinates of x3
                y3 = In.getInt();   //Stores coordinates of y3
                System.out.println("("+x1+", "+y1+"), ("+x2+", "+y2+"), ("+x3+", "+y3+")"); //Shows the user the coordinates they entered
                sideA = sideLength(x3, y3, x2, y2);    //Computes the length of line segment A
                sideB = sideLength(x1, y1, x3, y3);    //Computes the length of line segment B
                sideC = sideLength(x2, y2, x1, y1);    //Computes the length of line segment C
                if(isFinished(x1, y1, x2, y2, x3, y3)){ //Checks if the user has entered 0 0 0 0 0 0 and ended the program
                    System.out.println(" Program terminated by the user");
                } else if(valid(sideC, sideA, sideB)){   //If program has not ended and it is a valid triangle the following details will print
                    System.out.println("Triangle possible: " + triangleSide(sideA, sideB, sideC) + " and " + triangleAngle(sideA, sideB, sideC) + ".");   //Prints the type of triangle it is based on the side lengths computed by the computer using the coordinates
                    System.out.println("Line A is: "+sideA+" units\nLine B is: "+sideB+" units\nLine C is: "+sideC+" units"); //Prints the length of the line segments - The variables inside the print statement have a scope of at least the entire class
                    degrees(sideA, sideB, sideC);    //Prints out the angles of the triangle in degrees
                    centroid(x1, y1, x2, y2, x3, y3);   //Prints the coordinates of the centroid of the triangle (Very middle of the triangle)
                    System.out.println("The area of triangle ABC is: " + area(sideA, sideB, sideC) + " units squared and the perimeter of triangle ABC is: " + perimeter(sideA, sideB, sideC)+" units");   //Prints the area and the perimeter
                } else {    //If the program is not finished and the triangle does not exist it will let the user know
                    System.out.println("This triangle does not exist");
                }
            }while(!isFinished(x1, y1, x2, y2, x3, y3));    //Loop will continue while input is not 0 0 0 0 0 0
        }
    }






    //Checks whether the program is finished for integer side lengths - Will return true if the 3 parameters are 0 0 0
    public static boolean isFinished(int a, int b, int c){
        return a==0 && b==0 && c==0;
    }

    //Checks whether the triangle is Equilateral, Isosceles or Scalene
    public static String triangleSide(double a, double b, double c){
        if(a == b && b == c){   //If all side lengths are equal it is an equilateral triangle
            return "Equilateral";
        } else if (a == b || b==c || a==c){     //If at least 1 side length is equal to another it will return isosceles - Cannot be all 3 are equal because the first statement already checks that
            return "Isosceles";
        } else {
            return "Scalene";   //The only remaining option is Scalene
        }
    }
    //Returns the type of triangle it is based on the angles that the triangle has
    public static String triangleAngle(double a, double b, double c){
        double biggest = Math.sqrt(Math.pow(min(a,b,c), 2) + Math.pow(mid(a, b, c), 2));    //Initializes a variable called biggest which is the c^2 in a^2 + b^2 = c^2
        if(biggest == max(a,b,c)){  //If the max value is equal to the variable biggest, it means that it follows pythagorean theorem and thus is a right triangle
            return "Right";
        } else if(biggest<max(a, b, c)) {   //If the max value is large then the variable biggest, it means that it is an obtuse triangle
            return "Obtuse";
        } else {    //The only other option is an acute triangle
            return "Acute";
        }
    }

    /*Confirms whether the inputted side lengths are valid triangles or not - Takes in both the side lengths computed with coordinates and the integer side lengths
    * which is why the parameters are doubles (often times side lengths computed from coordinates will have a decimal value)
    */
    public static boolean valid(double a, double b, double c){
        return !(min(a, b, c) <= 0) && !(min(a, b, c) + mid(a, b, c) <= max(a, b, c));  //Will return true if the smallest side length isn't negative or 0 and if the smallest value plus the value in the middle aren't equal to the largest value (hypotenuse) as then the triangle will not exist
    }



    //Will return the larger value from 2 variables which it will then compare to 1 other value in the overloaded max method, it is double because it may take decimal values from the coordinate side lengths
    public static double max (double a, double b) {
        if(a<b){    //If a is less than b, b is the larger value so return b - Can be replaced with Math.max(a, b)
            return b;   //returns the value of b
        }else{  //Only other possibility is that a is the larger value or the same value, both cases will end up working
            return a;   //Returns the value of a
        }
    }

    //Will return the smaller value from 2 double variables which it will then compare to 1 other value in the overloaded min method, it is double because it may take decimal values from the coordinate side lengths
    public static double min (double a, double b) {
        if(b<a){    //if b is less than a, b is the smaller value
            return b;   //returns the value of b
        }else{  //only other case is a is equal to or less than b, both cases work
            return a;   //returns the value of a
        }
    }

    //Overloaded max method, finds the larger value between the max method with 2 parameters to the third parameter double 'c', it is double because it may take decimal values from the coordinate side lengths
    public static double max (double a, double b, double c){
        if(c>max(a, b)){    //if c is greater than the previous max value, c is the max value
            return c;   //returns the value of c
        } else {    //only other scenario is that the other max value is equal to or larger than c, both cases work
            return max(a, b);   //returns either a or b (larger value)
        }
    }


    //Overload min method, finds the smaller value between the min method with 2 parameters to the third parameter double 'c', it is double because it may take decimal values from the coordinate side lengths
    public static double min (double a, double b, double c){
        if(c<min(a,b)){ //if c is smaller than the previous min value, c is the min value
            return c;   //returns the value of c
        } else {    //only other scenario is that the previous min value is equal to or smaller than c, both cases work
            return min(a, b);   //returns either a or b (smaller value)
        }
    }

    //Returns the value that is neither the max or min value, it returns a double value because it may take decimal values from the coordinate side lengths
    public static double mid (double a, double b, double c){
        return a+b+c-max(a, b, c)-min(a, b, c); //Adds all 3 side lengths and the subtracts the largest and smallest side length so only the mid side length is left - returns the mid side length
    }


    //Calculates the actual degrees of each angle using cosine law and prints each value out
    public static void degrees(double a, double b, double c){
        double aDegree, bDegree, cDegree;   //Creates 3 variables that will store the degrees of their respective angle - they are named in respect to the side opposite to them
        aDegree = Math.toDegrees(Math.acos((b*b+c*c-a*a)/(2.0*b*c)));   //Calculates the angle opposite to line 'a' using cosine law for degrees, converts the value of cosine law to degrees as the Math.acos method returns a radiant
        bDegree = Math.toDegrees(Math.acos((a*a+c*c-b*b)/(2.0*a*c)));   //Calculates the angle opposite to line 'b' using cosine law for degrees, converts the value of cosine law to degrees as the Math.acos method returns a radiant
        cDegree = Math.toDegrees(Math.acos((a*a+b*b-c*c)/(2.0*a*b)));   //Calculates the angle opposite to line 'c' using cosine law for degrees, converts the value of cosine law to degrees as the Math.acos method returns a radiant

        System.out.printf("Angles are named after the side opposite to them.\nAngle A is: %.0f degrees\nAngle B is: %.0f degrees\nAngle C is: %.0f degrees\n", aDegree, bDegree, cDegree);   //Prints out each angle and links them to
    }


    //Calculates the perimeter of the triangle, parameters and the value being returned are doubles because the coordinates side lengths will often be decimals
    public static double perimeter(double a, double b, double c){
        return a+b+c;   //returns the sum of all 3 sides
    }

    //Computes the area of a triangle using Herons Formula - https://www.mathsisfun.com/geometry/herons-formula.html - Area will often times be decimal even if the parameters are decimal values or not, parameters are doubles for the coordinate side lengths
    public static double area(double a, double b, double c){
        double s; //Declares variable 's' which will hold the semi-perimeter which is just 1/2 of the perimeter
        s = perimeter(a, b, c)/2;   //Initializes the semi-perimeter into variable 's'
        return Math.sqrt(s*(s-a)*(s-b)*(s-c)); //Returns the area of the triangle
    }


    //Computes whether the user has decided to end the program, overloaded method of isFinished that checks if all inputs are 0
    public static boolean isFinished(int a, int b, int c, int d, int e, int f){
        return a==0 && b==0 && c==0 && d==0 && e==0 && f==0;    //returns true if all the inputs by the user are zero - 0 0 0 0 0 0
    }


    //Calculates the side lengths of each line segment in the triangle using the equation of a circle not centered on the origin - Source: Mr. Boyer grade 10 math class
    public static double sideLength(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));  //Returns the equation of a circle not centered on the origin
    }


    //Calculates the centroid of the triangle only if coordinates are entered by getting the average of all 3 points
    public static void centroid(int x1, int y1, int x2, int y2, int x3, int y3){
        double xCord = midPoint(x1, x2, x3);    //Stores the x-coordinate of the centroid
        double yCord = midPoint(y1, y2, y3);    //Stores the y-coordinate of the centroid
        System.out.println("The centroid is located at ("+xCord+", "+yCord+")");    //Prints out the location of the centroid when the method is called on
    }


    //Overloaded method of midpoint, it finds the average of 3 points which is used to compute the centroid
    public static double midPoint(double a, double b, double c){
        return (a+b+c)/3.0; //finds average of 3 points by adding all 3 up and dividing them by 3
    }
}