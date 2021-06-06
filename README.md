# Black Jack Game

This is a java command line application that simulates a black jack game. The player faces a dealer 
who reacts via computer logic. The game begins with cards being dealt to the player and then the dealer. 
Both of the players cards are shown and only one of the dealers. The player's turn happens first where a menu will appear 
that allows the player to choose the move they will make. After the player's turn, the dealers turn is automated by game logic where the dealer will hit if the value of the hand is less than 17 or stay if it is 17 or greater.

### Hand Values
A standard 52 card deck withh 4 suits and 13 ranks are used. The following are there values:
* 2,3,4,5,6,7,8,9 and 10 are worth their face value.
* Jack, Queen, King are worth 10.
* Ace is set as High worth 11. 

### Win Condition
The round winner will be decided by the hands largest value. If the player's or dealer's hand value is greater than 21 then this is considered a bust and the corresponding player loses. If a player or dealer's hand is exactly 21, this is a black jack and the corresponding player wins. A win condition check is performed after the cards are dealt to see if the player or dealer were dealt blackjack. 

### How to use

Start the application. After the welcome menu, the player should enter their name into the console. The deck will then be shuffled and hands dealt to the player and dealer. The players hand and one of the dealers cards will be shown. During the players turn a menu will appear. Enter the number of the move you would like to make based on your current hand:
1. Hit (Adds a card to your hand)
2. Stay (Finish the round with your current hand value)

After the player's turn, the dealers turn is automated. The results of the round as well as all the cards for both players will be displayed at the end. 

A question if you would like to play another round will pop up after the results of the round. Enter Yes or No into the command line to continue playing the game. The game will continue with rounds until the user enters no to end the game.

Once all the rounds have finished, the final results from all rounds will be displayed.


### Technologies used

* Java
* Eclipse
* Object Oriented Programming Principles: Abstraction, Polymorphism, Encapsulation, Inheritance
* Enumerates
* Abstract Classes
* Game Logic

### Lessons learned

This projects focuses on object oriented programming encapsulation and keeping data private. The player class is never allowed to interact with the Deck and can only touch his blackjack hand. In addition, deck, card, etc classes are generalized so that they could be used for other games than black jack. Classes specific to blackjack inherit from the general card game classes as well as the blackjack dealer inheriting from the player class. 

