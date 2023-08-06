import java.util.*;

public class PandemicGame {
    public static void main(String[] args) {
    	// Change these to effect difficulty
    	final int actionsPerTurn = 4;
    	final int MaxOutbreaks = 5;
    	final int StartingHandSize = 4;
    	final String startingCity = "Atlanta";
    	// Creating the objects based on classes i predefined
    	PandemicCures cures = new PandemicCures();
        PandemicMap map = new PandemicMap();
        PandemicCity cities = new PandemicCity();
        
        boolean gameEnd = false;
        int OutbreakCount = 0;
        
        //Deck and hand set up
        LinkedList<String[]> PlayerCardDeck = new LinkedList<String[]>();
        LinkedList<String[]> PlayerDiscardDeck = new LinkedList<String[]>();
        LinkedList<String[]> InfectionCardDeck = new LinkedList<String[]>();
        LinkedList<String[]> InfectionDiscardDeck = new LinkedList<String[]>();
        
        PandemicPlayer humanPlayer = new PandemicPlayer(StartingHandSize,actionsPerTurn, startingCity);
        PandemicAI AIPlayer = new PandemicAI(StartingHandSize,actionsPerTurn, startingCity);
        deckSetup(StartingHandSize, gameEnd, PlayerCardDeck,InfectionCardDeck,AIPlayer.getHand(),humanPlayer.getHand());
        
        //There are some blank print lines, to make the UI look a little better
    	System.out.println();
        System.out.println("You are currently in " + humanPlayer.getCurrentCity() + ".");
        System.out.println("You have " + humanPlayer.getActionsLeft() + " actions left.");
        Scanner scanner = new Scanner(System.in);
        
        //Game loop
        while (!gameEnd) {
            humanTurn(humanPlayer, map, cures,  gameEnd,  cities, PlayerCardDeck,PlayerDiscardDeck, scanner);
            humanPlayer.setActionsLeft(actionsPerTurn);
            DrawPlayerCard(humanPlayer.getHand(), PlayerCardDeck, gameEnd);
            DrawPlayerCard(humanPlayer.getHand(), PlayerCardDeck, gameEnd);
            for(int i =0; i<10;i++) {
            City infectionTarget = drawInfection(InfectionCardDeck, InfectionDiscardDeck, cities);
            infectCity(infectionTarget, map, OutbreakCount, MaxOutbreaks, cities, gameEnd);
            }
            //aiTurn();
        }
	}
    private static void humanTurn(PandemicPlayer humanPlayer, PandemicMap map, PandemicCures cures, boolean gameEnd, PandemicCity cities, LinkedList<String[]> PlayerCardDeck,LinkedList<String[]> PlayerDiscardDeck, Scanner scanner) {
        while (humanPlayer.getActionsLeft() > 0) {
        	System.out.println();
            System.out.print("What do you want to do? ");
            String action = scanner.nextLine().toLowerCase();
            if (action.equals("move")) {
                move(humanPlayer, map, scanner);
            } else if (action.equals("show hand")) {
                showHand(humanPlayer.getHand());
            } else if (action.equals("build research station")) {
                buildResearchStation(humanPlayer, cities);
            } else if (action.equals("cure disease")) {
                researchStationCheck(humanPlayer, cities, cures, PlayerDiscardDeck, gameEnd, scanner);
            } else if (action.equals("pass")) {
                pass(humanPlayer);
            } else {
                System.out.println("Invalid action. Try again.");
            }

            System.out.println("You are currently in " + humanPlayer.getCurrentCity() + ".");
            System.out.println("You have " + humanPlayer.getActionsLeft() + " actions left.");
        }
    }

    private static void pass(PandemicPlayer humanPlayer) {
    	System.out.println("Passing the turn...");
        humanPlayer.setActionsLeft(0);
		
	}
    
   // Basic ferry/drive, that compares current location with possible locations in the hashmap 
    private static void move(PandemicPlayer humanPlayer, PandemicMap map, Scanner scanner) {
        System.out.print("Where do you want to move? ");
        System.out.println("Available Places to move to are " + map.getCityConnections(humanPlayer.getCurrentCity()));
        String destination = scanner.nextLine();
        if (map.getCityConnections(humanPlayer.getCurrentCity()).contains(destination)) {
            System.out.println("Moving to " + destination + "...");
            humanPlayer.setCurrentCity(destination);;
            humanPlayer.decreaseActionsLeft();;
        } else {
            System.out.println("You can't move to " + destination + " from " + humanPlayer.getCurrentCity() + ".");
            System.out.println("Available Places to move to are " + map.getCityConnections(humanPlayer.getCurrentCity()));
        }
    }

    //Command to show you your hand and colour of the cards in it
    private static void showHand(LinkedList<String[]> HumanHand) {
        for (String[] card : HumanHand) {
            System.out.println(card[0] + " Which is coloured: " + card[1]);
        }
    }

    //Changes no research station, into true, for the tile you are on
    private static void buildResearchStation(PandemicPlayer humanPlayer,PandemicCity cities) {
        City currentCityObject = cities.findCity(humanPlayer.getCurrentCity());
        if (!currentCityObject.hasResearchStation()) {
            currentCityObject.buildReseachStation();
            System.out.print("Research station constructed in " + humanPlayer.getCurrentCity());
        } else {
            System.out.print("There is already a research station in " + humanPlayer.getCurrentCity());
        }
    }
    
    //Checks to see if you are on a research station, to be able to cure
    static void researchStationCheck(PandemicPlayer humanPlayer, PandemicCity cities, PandemicCures cures, LinkedList<String[]> PlayerDiscardDeck, boolean gameEnd, Scanner scanner) {
    	City CurrentCity = cities.findCity(humanPlayer.getCurrentCity());
    	if(CurrentCity.hasResearchStation() == true) {
        	System.out.print("What colour would you like to cure ");
			String colour = scanner.nextLine().toLowerCase();
            if((colour.equals("blue") && !cures.blueCure) || (colour.equals("red") && !cures.redCure) || (colour.equals("yellow") && !cures.yellowCure) || (colour.equals("black") && !cures.blackCure)) {
                cureDisease(humanPlayer ,colour, PlayerDiscardDeck, cures, gameEnd);
            }
            else if(!colour.equals("blue") && !colour.equals("red") && !colour.equals("yellow") && !colour.equals("black")) {
                System.out.println("Invalid colour.");
            }
            else {
                System.out.println(colour + " is already cured.");
            }
    }
    	else {
    		System.out.println(humanPlayer.getCurrentCity() +" has no research station ");
    	}
    }
    
    // Initilizes the decks and shuffles them.
    static LinkedList<String[]> DeckInitiliazation(LinkedList<String[]> Deck){
    	String[][] cities = {
  			  {"Atlanta", "blue"},
  			  {"Chicago", "blue"},
  			  {"Essen", "blue"},
  			  {"London", "blue"},
  			  {"Madrid", "blue"},
  			  {"Milan", "blue"},
  			  {"Montreal", "blue"},
  			  {"New York", "blue"},
  			  {"Paris", "blue"},
  			  {"San Francisco", "blue"},
  			  {"St. Petersburg", "blue"},
  			  {"Washington", "blue"},
  			  {"Bangkok", "red"},
  			  {"Beijing", "red"},
  			  {"Ho Chi Minh City", "red"},
  			  {"Hong Kong", "red"},
  			  {"Jakarta", "red"},
  			  {"Manila", "red"},
  			  {"Osaka", "red"},
  			  {"Seoul", "red"},
  			  {"Shanghai", "red"},
  			  {"Sydney", "red"},
  			  {"Taipei", "red"},
  			  {"Tokyo", "red"},
  			  {"Algiers", "black"},
  			  {"Baghdad", "black"},
  			  {"Cairo", "black"},
  			  {"Chennai", "black"},
  			  {"Delhi", "black"},
  			  {"Istanbul", "black"},
  			  {"Karachi", "black"},
  			  {"Kolkata", "black"},
  			  {"Moscow", "black"},
  			  {"Mumbai", "black"},
  			  {"Riyadh", "black"},
  			  {"Tehran", "black"},
  			  {"Bogota", "yellow"},
  			  {"Buenos Aires", "yellow"},
  			  {"Johannesburg", "yellow"},
  			  {"Khartoum", "yellow"},
  			  {"Kinshasa", "yellow"},
  			  {"Lagos", "yellow"},
  			  {"Lima", "yellow"},
  			  {"Los Angeles", "yellow"},
  			  {"Mexico City", "yellow"},
  			  {"Miami", "yellow"},
  			  {"Santiago", "yellow"},
  			  {"Sao Paulo", "yellow"}
  			};
    	for (String[] city : cities) {
    		Deck.add(new String[] {city[0], city[1]});
    	}

    	// Collections is part of java utils library
    	Collections.shuffle(Deck);
		return Deck;
    	
    }
    
    // Every turn, draws 2 cards
    static void DrawPlayerCard(LinkedList<String[]> Hand, LinkedList<String[]> PlayerCardDeck, boolean gameEnd) {
    	if(PlayerCardDeck.size() == 0) {
    		System.out.print("No player cards left, You lose. ");
    		gameEnd = true;
    		System.exit(0);
    	}
    	String[] TopCard = PlayerCardDeck.peekFirst();
    	Hand.add(TopCard);
    	PlayerCardDeck.removeFirst();
    }
    
    // Function to send cards from hand to discard pile
    static void DiscardPlayerCard(PandemicPlayer humanPlayer,LinkedList<String[]> PlayerDiscardDeck, String[] card) {
    	PlayerDiscardDeck.add(card);
    	humanPlayer.takeFromHand(card);;
    }
	
    // Infects the City and handles outbreaks. 
	public static void infectCity(City city, PandemicMap map, int OutbreakCount, int MaxOutbreaks,PandemicCity cities, boolean gameEnd) { 
		if(city.getNumDiseaseCubes() == 3) { 
			OutbreakCount++;
			if(OutbreakCount >= MaxOutbreaks) {
				System.out.print("Game Lost");
			}
			System.out.println("Outbreak in " +city.getName() + "!"); 
				for (String adjacentCities : map.getCityConnections(city.getName())) { 
					City adjacentCity= cities.findCity(adjacentCities);
					if(adjacentCity.getNumDiseaseCubes() == 3) {
						OutbreakCount++;
						System.out.println("Outbreak in " +adjacentCity.getName() + "!"); 
						if(OutbreakCount >= MaxOutbreaks) {
							gameEnd = true;
							System.out.print("Game Lost");
							System.exit(0);
						}
					}
					else {
						adjacentCity.addDiseaseCube(); 
						System.out.print(adjacentCity.getName()+ "Has been infected by outbreak, it has an infection of " + adjacentCity.getNumDiseaseCubes() );
					}
					}
		}
		else { 
			city.addDiseaseCube(); 
			System.out.print(city.getName() + " Has been infected, it has " + city.getNumDiseaseCubes()+ " infection cube ");
			}
    	System.out.println();
		}
	
	// Called from check research station and cures the colour, if possible
	static void cureDisease(PandemicPlayer humanPlayer ,String colour,LinkedList<String[]> PlayerDiscardDeck, PandemicCures cures, boolean gameEnd) {
		int colourCount = 0;
		for (String[] Card : humanPlayer.getHand()) {
			if (Card[1].equals(colour)) {
			    colourCount++;
			}
		}
		// Check if the 2nd element appears 5 times
		if (colourCount >= 5) {
		    int discardCard = 0;
			for (String[] Card : humanPlayer.getHand()) {
				if (Card[1] == colour) {
					while(discardCard <5) {
						discardCard++;
						DiscardPlayerCard(humanPlayer, PlayerDiscardDeck, Card);
						}
				    }
		} 
			System.out.println(colour + " has been cured!");
			switch(colour) {
			case "yellow":
				cures.yellowCure = true;
				break;
			case "red":
				cures.redCure = true;
				break;
			case "blue":
				cures.blueCure = true;
				break;
			case "black":
				cures.blackCure = true;
				break;
			default:
				System.out.println("Fatal Error Occured");
			}
			if(cures.redCure == true && cures.blueCure == true && cures.blackCure == true && cures.yellowCure == true ) {
				gameEnd = true;
				System.out.println("You Win!");
				System.exit(0);
			}
			humanPlayer.decreaseActionsLeft();
		}
		else {
		    // The 2nd element does not appear 5 times
		    System.out.println("There are less than 5 " +colour +" cards in your hand");
		}
	}

	//Adds cards to the deck
	public static void deckSetup(int StartingHandSize, boolean gameEnd, LinkedList<String[]> PlayerCardDeck,LinkedList<String[]> InfectionCardDeck,LinkedList<String[]> AIHand,LinkedList<String[]> HumanHand) {
	    //Deck and hand set up
	    DeckInitiliazation(PlayerCardDeck);
	    DeckInitiliazation(InfectionCardDeck);

	    for(int Dealt = 0 ; Dealt < StartingHandSize; Dealt++) {
	        DrawPlayerCard(HumanHand, PlayerCardDeck, gameEnd);
	        DrawPlayerCard(AIHand, PlayerCardDeck, gameEnd);
	    }
	}
	
	//Draws infection that goes into the infect city function
	public static City drawInfection(LinkedList<String[]> InfectionCardDeck, LinkedList<String[]> InfectionDiscardDeck, PandemicCity cities ) {
		if(InfectionCardDeck.size() == 0) {
			InfectionDiscardDeck.clear();
			DeckInitiliazation(InfectionCardDeck);
		}
    	String[] TopCard = InfectionCardDeck.peekFirst();
    	InfectionDiscardDeck.add(TopCard);
    	String Card = TopCard[0];
    	City InfectedCity = cities.findCity(Card);
    	InfectionCardDeck.removeFirst();
		return InfectedCity;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	}
	



	
