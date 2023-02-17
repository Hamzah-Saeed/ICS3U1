/*
 * Hamzah Saeed
 * Ms. Karasinska
 * ISU - President
 * 2023-01-20
 *
 * Program Description: This program is based after the card game called president, in this game the player must play a card
 *                      of the same value or higher than the player before them played, as well as the same amount of cards.
 *                      if the same value and same amount is played, the player who just played that can play whatever they would
 *                      like. If a player plays the same amount in 2's, it also allows the player to play whatever they like but
 *                      the value is not considered. The game features the use of multiple methods that include, the AI/Bots,
 *                      a method to shuffle and deal the deck, ranking the cards and more.
 *
 * Data Dictionary:
 * inc = Static Integer that the user uses to hold the increments at which the text is printed during the game
 * playerChoice = String that stores what the user wants to do - settings, help, play, quit
 * cards[] = 1D array that stores all cards in a deck excluding jacks, will be used as the base to shuffle and deal cards
 *
 */





import java.util.*; //Imports the Java.util package that is used for 2 main parts - ArrayList data structure and the Collections class
import java.util.concurrent.TimeUnit; //Imports the TimeUnit package so when increments are used, I can put them into seconds

public class President {  //Name of our java class

    static int inc = 1; //stores the standard increment that text will print at - 1, static because it is used in multiple methods.

    public static void main(String[] args) {  //Main method
        System.out.println("Welcome to Muhammad Hamzah Saeed's ICS 3U1 ISU Project: Text-based president!");
        System.out.println("Good Luck and Enjoy!\n");
        String playerChoice;  //Variable that holds what the user chooses to do in the next few lines, conditional for the do-while loop
        do {  //opens do-while loop
            System.out.println("\nType 'settings' to adjust the increments text is printed (base is 1 seconds)");
            System.out.println("Type 'play' to start playing president");
            System.out.println("Type 'help' to learn how to play");
            System.out.println("Type 'exit' to quit the game");
            playerChoice = In.getString();  //Initializes what the play chooses from the earlier prompts ^^^
            while (!playerChoice.equalsIgnoreCase("settings") && !playerChoice.equalsIgnoreCase("help")     //While the user enters an invalid input, keep prompting until a correct one is given
                    && !playerChoice.equalsIgnoreCase("play") && !playerChoice.equalsIgnoreCase("exit")) {
                System.out.println("Please enter a valid input - settings, help, play, exit");  //prompt
                playerChoice = In.getString();  //initalizes their choice
            } //closes while loop
            if (playerChoice.equalsIgnoreCase("settings")) {  //if the user decides to go into the settings0
                System.out.println("\nWhat would you like the text increments for the game (0-3 seconds)?");  //prompt
                inc = In.getInt();  //initializes the increments to a different value
                while (inc > 3 || inc < 0) {  //while the increment isn't in the values provided in the prompt, keep asking, any longer increments would result in a VERY slow game
                    System.out.println("Increments must be between 0-3 seconds, input only the number."); //Prompt that they provided an incorrect value
                    inc = In.getInt();  //initialize the increments to a different value
                } //closes the while loop
            } else if (playerChoice.equalsIgnoreCase("help")) { //if the player chooses to go into help and learn about the game
                //Long prompt explaining the game
                System.out.println("\nPresident is a card game where the player must play a higher or same value card than the previous players card.\nThe values of these cards from lowest to highest are: 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace, 2\nYou must play the same amount of cards as the previous player, for example if he plays 2, 2's, I must play 2 cards on top of that\nIf you play the same value card, or 2's equivalent to the previous amount of cards played, the current play burns\nA play may also burn when all 4 players choose to skip or cannot play\nWhen a play is burned, it means that a random player is chosen and they can play whatever valued card, and whatever amount of that card\nThe final goal of the game is to lose all your cards and be declared the winner, or in this game's terms, the President\n                             \nThis game implements the use of 1-3 bots to act as other players, they have a certain level of intelligence when they play\nWhen playing a numbered card, only type the number [3 of Clubs ---> '3']\nWhen playing a worded card, only type the first word [King of Hearts ---> 'King']\n3 of Clubs starts the first play\n");
            } else if(playerChoice.equalsIgnoreCase("play")){ //If the user decides to begin playing the game

                String[] cards = {"Ace of Diamonds", "Ace of Spades", "Ace of Hearts", "Ace of Clubs",    //Initializes the 1D String Array with all cards in a deck excluding jokers
                        "2 of Diamonds", "2 of Spades", "2 of Hearts", "2 of Clubs",
                        "3 of Diamonds", "3 of Spades", "3 of Hearts", "3 of Clubs",
                        "4 of Diamonds", "4 of Spades", "4 of Hearts", "4 of Clubs",
                        "5 of Diamonds", "5 of Spades", "5 of Hearts", "5 of Clubs",
                        "6 of Diamonds", "6 of Spades", "6 of Hearts", "6 of Clubs",
                        "7 of Diamonds", "7 of Spades", "7 of Hearts", "7 of Clubs",
                        "8 of Diamonds", "8 of Spades", "8 of Hearts", "8 of Clubs",
                        "9 of Diamonds", "9 of Spades", "9 of Hearts", "9 of Clubs",
                        "10 of Diamonds", "10 of Spades", "10 of Hearts", "10 of Clubs",
                        "Jack of Diamonds", "Jack of Spades", "Jack of Hearts", "Jack of Clubs",
                        "Queen of Diamonds", "Queen of Spades", "Queen of Hearts", "Queen of Clubs",
                        "King of Diamonds", "King of Spades", "King of Hearts", "King of Clubs"};


                cards = shuffle(cards); //call on the method shuffle to shuffle the deck of cards, then put the shuffled values back into cards
                presidentCardGame(cards); //Play the president game
            }//closes else if statement if the user plays the game
        } while (!playerChoice.equalsIgnoreCase("exit")); //Closes do-while loop, ending the game, before a final prompt
        System.out.println("Thanks for playing Hamzah's Card Game, feel free to come back!"); //exit message
    } //Closes main method


    //the method shuffle, shuffles the deck of cards and returns them into a 1D array in main - does not deal cards
    public static String[] shuffle(String[] arr) {
        ArrayList<String> cards = new ArrayList<String>();  //Creates an ArrayList that stores the cards from main so the Collections class can be used
        Collections.addAll(cards, arr);  //https://www.geeksforgeeks.org/arraylist-array-conversion-java-toarray-methods/ - Adds all elements from the parameter arr to cards ArrayList
        Collections.shuffle(cards);      //https://www.geeksforgeeks.org/shuffle-or-randomize-a-list-in-java/ - Shuffles all the cards in cards
        String[] shuffledCards = new String[52];  //Creates a new
        for (int i = 0; i < shuffledCards.length; i++) { //assign each value at i from 1-52 of our 1D array to the cooresponding value at i from 1-52 of our ArrayList
            shuffledCards[i] = cards.get(i); //Assigns the value from the ArrayList to the array
        } //closes for loop
        return shuffledCards; //returns the 1D array that we assigned values to - shuffled deck
    }


    //the method presidentCardGame goes through the process of the entire game
    //it does not return anything but instead just prints the winner
    //the reason we used a method instead of main was so we could easily break out of our loops with 'return;'
    public static void presidentCardGame(String[] cards) { //cards holds the array of shuffled carads


        System.out.println("\nHow many bots would you like to play against?\nThere must be at least 1 and a maximum of 3!");
        int bots = In.getInt(); //holds the value of the amount of bots the player wants to play against


        while (bots != 1 && bots != 2 && bots != 3) { //if the player enters an invalid amount of bots, prompt them to re-enter
            //if more bots are desired the while loop could hold more values, but the process of giving out all the cards
            //becomes more difficult due to the uneven division of cards - 52/5=50 R2
            System.out.println("You may only have up to a maximum of 3 bots and a minimum of 1.");
            bots = In.getInt(); //Initializes bots to a correct value
        }

        ArrayList<ArrayList<String>> decks = new ArrayList(); //a 2D ArrayList to store the 2-4 different decks
        //I chose an ArrayList as with it we can use multiple other methods, mainly the .remove() and isClear() method
        //I use .remove() to remove played cards from the deck, and isClear() to see if they could actually play

        int indexS = 1; //used to indicate the starting value of the range of cards placed in the deck
        int indexO = 52/(bots+1); //used to indicate the ending value of the range of cards placed in the deck
        //bots+1=numberOfPlayers, if 3 bots are chosen there are technically 4 players

        if(bots==2) //if there are 2 bots or 3 players, 52 is not divisibale equally so we manually set the first value
            indexO = 18; //indexO is initalized to 18 - 18+17+17=52 - additionally ensures that the AI have a slightly better chance against the player, making up for their limited intelligence

        for(int i = 0; i<bots+1; i++){ //loops until a deck for each player has not been created
            decks.add(sortCards(dealPresCards(indexS, indexO, cards))); //adds a new sorted deck on a new row of the ArrayList of decks
            //uses the starting range, ending rage, and the deck of cards to select from
            int temp; //temp value to store an increment
            temp = indexO - indexS + 1; //ensures that increment is consistent, I add 1 because without it we would have repeating cards

            if(i==0 && bots==2) //if it is the first run and bots was 3, it means indexO starts at 18 and the increment would be 1 longer than it should had we used the other formula, so we add 1
                temp=16; //temp ---> 18-2 = 16, initializes temp
            indexS = indexO+1; //1 higher than indexO to make sure we dont repeat the same value
            indexO += temp; //Adds the increment to get the next end range, initializes indexO
        } //closes for loop
        System.out.println("Your cards are: " + decks.get(0)+"\n"); //prints out the players deck, which is always the first row
        try { //try the following code
            TimeUnit.SECONDS.sleep(inc); //pauses before moving on because we are starting the game
        } catch (InterruptedException e) { //if an error occurs do the following
            Thread.currentThread().interrupt(); //keeps the program going instead of giving an error - https://dzone.com/articles/why-do-we-need-threadcurrentthreadinterrupt-in-int
        }    //closes try and catch statment
        int turn = 0;    //creates and initalizes the run value which is used in the next few lines, ensures that the first turn doesn't repeat
        boolean winner = false;    //creates variable winner which isn't changed but is required for my for loop to run
        int nextPlayer;    //stores a variable which is nextPlayer used in a for loop, holds the next player who plays after the initial first play
        String prevCard = "";    //stores the previous card played
        int prevCardCount = 1;    //stores the previous amount of cards played - defaults at 1
        int burnedPrevPlayer = 0; //stores the player who begins if the entire play is burned due to skipping
        boolean skippedBurn = false; //default sets skipped burn to false, meaning the play has not been skipped due to burns

        while (!winner) { //while there is no winner continue
            ArrayList<Integer> index = new ArrayList<Integer>();  //creates an ArrayList called index that will store the indicies of what cards the AI or player chooses to play

            int prevPlayer = 0; //prevPlayer will default as 0 at the start because the for loop always ends on the fourth players move, so when 1 is added to this value and assigned to next player, player 1 starts
            if(skippedBurn) { //if the play was burned due to skips, do this code
                prevPlayer = burnedPrevPlayer;//prevPlayer is equal to the person that caused everyone to burn, assigned later
                skippedBurn = false;  //set skipped burn back to false to repeat this from happening every time
            }   //closes if statement
            int skip = 0;   //set skip back to zero to make sure that this value isn't being constantly added to
            if (turn == 0) {   //if this it the first turn in the game, do the following
                for(int i = 0; i<=bots; i++){   //go through all the ArrayList decks of each player/AI
                    if(decks.get(i).contains("3 of Clubs")){   //if the deck at row i, has a 3 of Clubs, do the followng
                        char playerNum = String.valueOf(i+1).charAt(0);  //set the char playerNum to the current player so the method can print the player that is playing first
                        index = validateFirstCard(decks.get(i), playerNum);   //set the index to the locations of the cards in the players deck which they want to play
                        turn++;   //add one to turn to ensure we do not continue looking for a 3 of Clubs that does not exist
                        prevPlayer = i+1;   //prevPlayer becomes the first player ---> player that is currently playing
                        prevCard = decks.get(i).get(index.get(0)); //the prev card is the card the prevPlayer played in his deck at the index of the first index of index, cards are ranked on the first number, so the other words in the card game do not matter
                        prevCardCount = index.size();   //the amount of cards played previously is equal to the index size because it holds the locations of all the cards played previously
                        for(int j = 0; j<index.size(); j++){ //iteriate through every value of index
                            int tempInd = index.get(0);
                            decks.get(i).remove(tempInd);   //remove the card, at the row of the player at the index of 0 until we have gone through all of index. We can keep removing index(0) because when we remove 1, all cards afterwards move left 1.
                        } //closes for loop
                        if(prevPlayer == 1)
                            System.out.println("Your updated deck is: " + decks.get(0) + "!\n");
                    }   //closes if statement
                }   //closes for loop
            }   //closes if statement

            index.clear();   //clears index because all the cards have already been removed, and we have to re-assign the values
            nextPlayer = prevPlayer+1;   //nextPlayer is 1 higher because prevPlayer has already played, nextt player's turn
            if(nextPlayer==bots+2) //if the next player is 2 higher than the amount of bots (this player does not exist because bots+1 is the total amount of players)
                nextPlayer = 1;  //set next player to 1

            for(int i = nextPlayer; i<=bots+1; i++) {     //iteriate through each players deck, starting from the next player
                if (i != 1) {            //if it isn't the actual players turn
                    index = aiMoves(decks.get(i-1), prevCard, prevCardCount, i);                 //set the index to what the ai wants to play
                    if (!index.isEmpty()) {              //if index is empty because the AI could not play, skip this part (error if not)
                        int tempInd = index.get(0);   //tempInd holds the index of the first index of the card that th computer wanted to play
                        if(cardRanks(prevCard) == cardRanks(decks.get(i-1).get(tempInd)) || cardRanks(decks.get(i-1).get(tempInd))==15) {          //If these following cards were played, the plays is burneds
                            String burnCard;
                            do {            //do this while a condition is or isn't met
                                for (int j = 0; j < index.size(); j++) {   //iteriate through every value of index
                                    decks.get(i-1).remove(tempInd); //remove the card, at the row of the player at the index of 0 until we have gone through all of index.
                                } //close the foor loop
                                if(winner(decks.get(i-1), i)) {  //check if the player has won after the cards were removed
                                    winner = true; //sets winner to true;
                                    return; //breaks out of the method
                                }//closes if statement
                                try {
                                    TimeUnit.SECONDS.sleep(inc);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                                System.out.println("Player " + i + " burned the play. They repeat their turn."); //prompt
                                index.clear(); //clears index to make sure it is empty
                                prevCard = "0";   //previous card is 0 because play is empty
                                prevCardCount = 0;   //previous amount of cards is 0
                                index = aiMoves(decks.get(i - 1), prevCard, prevCardCount, i);
                                tempInd = index.get(0);
                            } while (cardRanks(decks.get(i-1).get(tempInd))==15);  //while card played is 2
                        }
                        prevCard = decks.get(i-1).get(tempInd);
                        prevCardCount = index.size();
                        for (int j = 0; j < index.size(); j++) {
                            decks.get(i-1).remove(tempInd); //https://iq.opengenus.org/2d-array-list-java/
                        }

                        //if index is empty
                    }else{
                        if(winner(decks.get(i-1), i)){//method winner
                            return;
                        }
                        skip += 1; //add one to skip
                    }

                    index.clear(); //clear index
                }else{ //if player is 1
                    String burnCard = "";
                    index = pMove(decks.get(0), prevCard, prevCardCount); //see pMove and put in index
                    if(!index.isEmpty()){ //if index is empty
                        int tempInd = index.get(0);
                        if(cardRanks(prevCard) == cardRanks(decks.get(0).get(tempInd)) || cardRanks(decks.get(0).get(tempInd))==15){ //if a burn is possible
                            for (int j = 0; j < index.size(); j++) { //removes card from deck
                                decks.get(0).remove(tempInd); //https://iq.opengenus.org/2d-array-list-java/
                            }
                            if(winner(decks.get(0), i)) { //if they won
                                return;
                            }
                            System.out.println("Your updated deck is: " + decks.get(0) + "!\n");
                            do {
                                burnCard = "";  //re-sets value of burn everytime
                                System.out.println("Player 1 burned the play. They repeat their turn.");
                                index.clear();  //clears index
                                prevCard = "0"; //pre
                                prevCardCount = 0;
                                index = pMove(decks.get(0), prevCard, prevCardCount);

                                tempInd = index.get(0);
                                burnCard = decks.get(0).get(tempInd);

                                for(int j = 0; j<index.size(); j++){
                                    decks.get(0).remove(tempInd);
                                }
                                if(winner(decks.get(0), i)) { //if they won
                                    return;
                                }
                                System.out.println("Your updated deck is: " + decks.get(0) + "!\n");

                            }while(cardRanks(burnCard)==15);  //while it is 2
                            prevCard = burnCard;   //previously played card
                            prevCardCount = index.size(); //previous amount of played cards
                        }else {
                            prevCard = decks.get(0).get(tempInd);   //previously played card
                            prevCardCount = index.size(); //previous amount of played cards
                            for (int j = 0; j < index.size(); j++) {
                                decks.get(0).remove(tempInd);
                            }
                            if (winner(decks.get(0), i)) {
                                winner = true;
                                return;
                            }
                            System.out.println("Your updated deck is: "+decks.get(0)+"!\n");
                        }
                    }else{  //if the index is empty
                        skip+=1;
                    }
                }//closes for loop
                index.clear();

                try {
                    TimeUnit.SECONDS.sleep(inc);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
            //if every player has skipped in a row
            if(skip == bots+1){
                burnedPrevPlayer = skippedBurn(bots); //provides a random player who starts
                skippedBurn = true; //sets as true for start of while loop
                prevCard = "0";
                prevCardCount = 0;
            }
            skip = 0; //defaults skip to 0
        } //closes while loop
    } //closes method


    //this method deals out the president cards using a range and the deck of already shuffled cards
    public static ArrayList<String> dealPresCards(int a, int b, String[] cards) { //parameter an is the start of the range, parameter 2 is the end, cards[] is the array of cards
        ArrayList<String> hand = new ArrayList<String>(); //Arraylist holding the hand that will be returned
        for(int i = a - 1; i < b; i++) {
            hand.add(cards[i]);//adds the card at [i] to hand
        }
        return hand;
    }

    //this method holds the users move (that isn't the first ever move) as well as error checking
    public static ArrayList<Integer> pMove(ArrayList<String> deck, String pCard, int pCards) {  //parameters: deck holds the players hand, pCards holds the previously played card, pCards hold the amount of previously played cards
        ArrayList<Integer> index = new ArrayList<Integer>();  //ArrayList that holds the indices of the cards the player wants to play
        System.out.println("It is your turn! What card would you like to play? (Type SKIP to skip your turn)");
        boolean validCard = false;  //checks if play is valid
        String play = ""; //holds the users play

        if(pCards!=0) { //if card previous play was not a burn
            while (!validCard) {
                index.clear();
                play = In.getString();
                if (play.equalsIgnoreCase("skip")) {
                    System.out.println("Player 1 skipped\n");
                    return index;
                }
                index.clear();
                for (int i = 0; i < deck.size(); i++) {
                    if (play.equalsIgnoreCase(deck.get(i).substring(0, deck.get(i).indexOf(' ')))) {  //if the card at i == the players move, add it to the index and set as true
                        index.add(i);
                        validCard = true;
                    }
                }

                //conditions to check if card is still valid
                if (!validCard) {
                    System.out.println("The card you play must be in your deck");
                } else if (index.size() < pCards) { //not enough cards
                    System.out.println("You do not have enough " + play + "'s, you need " + pCards + " of one card");
                    validCard = false;
                } else if (cardRanks(deck.get(index.get(0))) < cardRanks(pCard)) {  //to low of a value
                    System.out.println("The card you played must be a higher value than the previous card played: " + pCard);
                    validCard = false;
                } else {   //confirms
                    System.out.println("Are you sure you want to play " + pCards + ", " + play + "'s (yes or no)");
                    String confirm = In.getString();
                    while (!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no")) {   //keep prompting if invalid input
                        System.out.println("Are you sure you want to play " + pCards + ", " + play + "'s (yes or no)");
                        confirm = In.getString();
                    }
                    if (confirm.equalsIgnoreCase("no")) {  //allow them to re-enter by sending them to the start
                        System.out.println("Re-Enter the card you DO want to play");
                        validCard = false;
                    }else{
                        for (int i = pCards; i<index.size(); i++){
                            index.remove(i); //removes extra of that card
                        }
                        return index;
                    }
                }
            }
            while (index.size() != pCards) { //while the size of the index, is not equal to the amount of previously played cards, it means you are playing too much
                index.remove(0);  //remove the extra cards
            }
            System.out.println("Player 1 plays " + play + ", " + index.size() + " times\n");
            return index;
        }else { //if it is a burn play
            while (!validCard) {
                int cardsPlayed = 0;  //stores amount of cards player wants to play
                play = In.getString();
                for (int i = 0; i < deck.size(); i++) {
                    if (play.equalsIgnoreCase(deck.get(i).substring(0, deck.get(i).indexOf(' ')))) {  //confirms user has the cards he wants to play
                        index.add(i); //adds index to index<>
                        validCard = true; //moves on to conditions
                    }
                }

                if (!validCard) {
                    System.out.println("The card you play must be in your deck");
                }else{
                    while (cardsPlayed<1 || cardsPlayed>index.size()){  //more cards than 0, and less than or equal to the number of those cards he has
                        System.out.println("How many cards do you want to play?");
                        cardsPlayed = In.getInt();  //resets until it is a correct value
                    }
                    System.out.println("Player 1 plays "+play+", "+cardsPlayed+" times\n");
                    for (int i = cardsPlayed; i<index.size(); i++){
                        index.remove(i);  //removes any extra of the card(s)
                    }
                    return index;
                }
            }
        }
        index.clear();
        return index;
    }


    //sorts the hands of each player
    public static ArrayList<String> sortCards(ArrayList<String> cards) {
        int[] values = new int[cards.size()]; //sets up a array called values
        for (int i = 0; i < cards.size(); i++) {
            values[i] = cardRanks(cards.get(i));  //assigns a value to each card
        }
        for (int i = 0; i < values.length; i++) {
            //sorts each card by comparing values
            for (int j = i + 1; j < values.length; j++) {
                int temp; //stores value
                String temp2;  //stores card
                if (values[i] > values[j]) {  //if it isn't correctly sorted
                    temp = values[i]; //initializes temp
                    values[i] = values[j];  //resets to make order it by values
                    values[j] = temp; //puts the removed value into J
                    temp2 = cards.get(i); //initializes temp2
                    cards.set(i, cards.get(j)); //sets the index of i to j
                    cards.set(j, temp2);  //sets the index of j to i
                }
            }
        }

        return cards;
    }


    //decides how the AI will play and stores the card it plays into an array of the indices
    public static ArrayList<Integer> aiMoves(ArrayList<String> deck, String prevCard, int prevNum, int prevPlayer){ //deck<> holds the hand of card, prevCard is the previous card played, prevNum is the amount of the previous card played, prevPlayer is the player who is playing
        ArrayList<Integer> index = new ArrayList<Integer>();  //creates ArrayList that will hold the indices
        if(prevNum == 0 ){  //if it is a burn
            index.add(0); //computer will always play the lowest possible card which is always at index of 0
            for(int i = 1; i<4; i++){ //starts ahead of the first card because it has been already been added - i<4---> there can only be 4 of the same card
                try{  //if deck has no index of i
                    cardRanks(deck.get(i));
                }catch(Exception e){
                    return index;  //break out of the for loop by returning index
                }
                if(cardRanks(deck.get(index.get(0))) == cardRanks(deck.get(i))){  //checks if cards afterwards are the same card
                    index.add(i);
                }else{  //if it is not the same return the value
                    System.out.println("Player "+prevPlayer+" plays "+deck.get(index.get(0)).substring(0, deck.get(0).indexOf(' '))+", "+index.size()+" times\n");
                    return index;
                }
            }
        }else if(prevNum == 1){ //if previous number of cards played were 1
            for(int i = 0; i<deck.size(); i++){
                if(cardRanks(deck.get(i))>=cardRanks(prevCard)){ //checks for the lowest possible card that can be played and returns its index
                    System.out.println("Player "+prevPlayer+" plays "+deck.get(i).substring(0, deck.get(i).indexOf(' '))+", 1 time\n");
                    index.add(i);
                    return index;
                }
            }
        }else{  //anything higher than 1 card
            for(int i = 0; i<deck.size(); i++){

                if(cardRanks(deck.get(i))>=cardRanks(prevCard)){  //checks for the lowest possible rank
                    index.add(i);
                    for(int j = 1; j<=prevNum-1; j++){  //starts at 1 because lowest has already been added, goes to the max cards that can be played ---> same as last amount
                        try{  //try this code --- IndexOutOfBounds because deck.get(i+j) will not exist if you are at the end of the code
                            if(cardRanks(deck.get(i))==cardRanks(deck.get(i+j))){
                                index.add(i+j);
                            }
                        }catch(Exception e){  //if it has gotten that far it means the player can't play and returns an empty index
                            System.out.println("Player "+prevPlayer+" could not play.\n");
                            index.clear();
                            return index;
                        }
                        if(index.size()==prevNum){  //same amount of cards as previously played means the player can play
                            System.out.println("Player "+prevPlayer+" plays "+deck.get(i).substring(0, deck.get(i).indexOf(' '))+", "+index.size()+" times\n");
                            return index;
                        }
                    }
                    index.clear();  //clears index if none of these work so an empty index can be returned
                }
            }
        }
        System.out.println("Player "+prevPlayer+" could not play\n");
        return index;
    }


    //method that goes through the first turn of the game
    public static ArrayList<Integer> validateFirstCard(ArrayList<String> deck, char playerNum){ //deck<> is the player who starts hand, playerNum is the player that is playing
        String play = ""; //creates variable play
        String prevCard;  //creates variable prevCard
        int prevCardCount = 1;  //player must play 1 or more card
        int cardCount = 0;  //amount of cards player plays
        ArrayList<Integer> index = new ArrayList<Integer>();  //holds the indicies of the cards played
        if(playerNum=='1') {  //if the first player is playing
            System.out.println("Player 1 starts because they have the 3 of Clubs\n");
            do {  //continues until a valid card is played
                System.out.println("What card would you like to play?");
                play = In.getString();
                for (int i = 0; i < deck.size(); i++) {
                    if (play.equalsIgnoreCase(deck.get(i).substring(0, deck.get(i).indexOf(' ')))) {  //if they actually have the card they play, add it to the index
                        cardCount += 1; //breaks out of the do-while loop
                        index.add(i);
                    }
                }
            } while (cardCount == 0);
            prevCard = play;  //initializes prevCard
            if (cardCount > 1) {  //if there is more than 1 of the cards that the player would like to play
                System.out.println("How many " + prevCard + "'s would you like to play?");
                prevCardCount = In.getInt();
                while (prevCardCount > cardCount || prevCardCount < 1) {  //while the amount they want to play is invalid
                    prevCardCount = In.getInt();
                }
            }
            System.out.println("You played " + prevCard + ", " + prevCardCount + " times\n");
            while(index.size()!=prevCardCount){ //removes extra cards
                index.remove(0);
            }
            return index; //returns
        }else{  //if the player isn't 1
            System.out.println("Player "+playerNum+" starts because they have the 3 of Clubs");
            for (int i = 0; i < 4; i++) { //AI will only play the lowest number of 3 at the start, which is within the first 4 indices
                if (cardRanks(deck.get(i))==3) {
                    cardCount += 1;
                    index.add(i);
                    play = "3 of Clubs";  //play will always be a 3
                }
            }
            prevCardCount = cardCount;  //initializes prevCardCount
            prevCard = play;  //initializes prevCard
            System.out.println("Player "+playerNum+" plays " + prevCard.substring(0, prevCard.indexOf(' ')) + ", " + prevCardCount + " times\n");
        }
        return index; //returns
    }


    //provides a card with a rank that is often being compared to sort decks and see if a play is valid
    public static int cardRanks(String cards) { //parameter is the string of the card entered
        if(cards.length()==0){  //empty string
            return 0;
        }
        if (cards.charAt(0) == '2') { //highest value card
            return 15;
        } else if ((cards.charAt(0) < 58 && cards.charAt(0) > 48) && cards.charAt(1) == ' ') {  //ASCII ---> 3-9, excluding 10
            return  Integer.parseInt(String.valueOf(cards.charAt(0)));  //literally assigned to their number --> 3=3
        } else if (cards.charAt(0) == '1' && cards.charAt(1) == '0') {  //the number 10
            return  10;
        } else if (cards.charAt(0) == 'J' || cards.charAt(0) == 'j') {  //Jack
            return  11;
        } else if (cards.charAt(0) == 'Q' || cards.charAt(0) == 'q') {  //Queen
            return  12;
        } else if (cards.charAt(0) == 'K' || cards.charAt(0) == 'k') {  //King
            return  13;
        } else if(cards.charAt(0)=='a' || cards.charAt(0)=='A'){  //Ace
            return 14;
        }else{  //any other card
            return 0;
        }
    }

    //prints the winner
    public static boolean winner(ArrayList<String> deck, int player){ //deck is the players hand, player is the player
        if (deck.isEmpty()){  //if the deck is empty, the player has gotten rid of all their cards
            System.out.println("Player "+player+" has gotten rid of all their cards!\nPlayer "+player+" wins!");
            return true;
        }
        return false; //the deck wasn't empty
    }


    //generates a random player who gets chosen to play first
    public static int skippedBurn(int bots) { //amount of bots playing
        System.out.println("Every player skipped so the entire play is burned...");

        try { //short pause
            TimeUnit.SECONDS.sleep(inc);  //https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Generating a random player to start...");
        int player = (int)(Math.random() * (bots+1))+1; //depending on the bots the number goes from 0-(bots+1)

        try { //pauses to replicate thinking
            TimeUnit.SECONDS.sleep(inc);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Player "+player+" was chosen");
        return player-1;
    }


} //closes class