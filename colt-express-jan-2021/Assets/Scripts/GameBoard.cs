using model;

using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using UnityEngine.EventSystems;

using Sfs2X;
using Sfs2X.Logging;
using Sfs2X.Util;
using Sfs2X.Core;
using Sfs2X.Entities;
using Sfs2X.Entities.Data;
using Sfs2X.Requests;
using System.Reflection;
using Sfs2X.Protocol.Serialization;

using System.Collections;
// using System.Random;
using Random=System.Random;
// using UnityEngine.Color;
using UnityEngine.EventSystems;

public class GameBoard : MonoBehaviour
{

	/*
	Frontend team:
	-attach choosecharacter strings to characters (attach character strings from
		 DisplayRemainingCharacters() to characters in the scene 
		 so that when the characters are clicked, CharacterChoice(character) is called that passes the chosen character to the server. This
		 can be done by attaching scripts to each character game object, similar to how it will work for gameobjects on the game board)
	
	-Assign all gameobjects in dictionary upon update game state call --loots
	
	-implement prompt method in Gamemanager (i.e. set action and clickable global variables) --done // makeallclickable() and update()
	
	-write scripts attached to each game object that checks if it is clickable, if so, checks action and calls action on clicked item
	
	-assign locations on game board to each gameobject (should be in attached scripts in as a global variable that is reassigned every
	   time updategamestate() is called, checks updated gm instance for new item's position)
	*/

	//debug variables
	public static Text debugText;
	public static string debugTextString;
    public Button button;
	public Button extension;
	public Button chooseChar;

	public GameObject canvas;

	public Text Round;
	//public GameObject exit;
	public Text exitText;
  
	
    public Bandit b;

	public static GameManager gm;

	// public Card[] currDeck = gm.currentBandit.deck;
	// public Card[] currHand = gm.currentBandit.hand; 

	// LIST OF ALL GAME OBJECTS HERE
    public Button cheyenne;
	public Button belle; 
	public Button tuco; 
	public Button doc; 
	public Button ghost; 
	public Button django; 
	public Button marshal;
	
	public Button gem1; 
	public Button gem2; 
	public Button gem3; 
	public Button gem4;
	public Button gem5;
	public Button gem6;

	public Button ghoLoot;

	public GameObject cardA; 
	public GameObject cardB; 
	public GameObject cardC; 
	public GameObject cardD;
	public GameObject cardE;
	public GameObject cardF; 
	public GameObject cardG;

	public GameObject bulletCard;

	// propmpt messages 
	public Text promptDrawCardsOrPlayCardMsg;
	public Text promptChooseLoot; 
	public Text promptPunchTarget; 
    
    // public static Dictionary<GameObject, object> objects = new Dictionary<GameObject, object>();
	public static Dictionary<Button, object> objects = new Dictionary<Button, object>();

	// public Dictionary<T, GameObject> objects = new Dictionary<T, GameObject>();
	// NOTE: INITIALIZE THE DICTIONARY FOR EVERY OBJECT HERE FIRST,
	// ** THE DICTIONARIES ARE INITIALIZED(CLEARED) IN Start() ** 
	// E.G. objects.Add(cheyenne, null), objects.Add(tuco, null), ...
	// This way, update game state will simply be able to overwrite the values in the dictionary
	// whenever it is called by the server

	public static string action = ""; // i.e. PUNCH, SHOOT etc. 

    public Text announcement;

	public Text cardAText; 
	public Text cardBText;
	public Text cardCText; 
	public Text cardDText;
	public Text cardEText;
	public Text cardFText;

	public Text cardNewABext;
	public Text cardNewCText;

	public GameObject CardNewA; 
	public GameObject CardNewB; 
	public GameObject CardNewC; 
	public GameObject CardNewD; 
	public GameObject CardNewE; 
	public GameObject CardNewF; 

	public GameObject BelleCard1; 
	public GameObject BelleCard2; 
	public GameObject BelleCard3; 
	public GameObject BelleCard4; 
	public GameObject BelleCard5; 
	public GameObject BelleCard6; 

	public GameObject playerE;

	public GameObject BelleBulletCard1; 
	public GameObject BelleBulletCard2;
	public GameObject BelleBulletCard3;
	public GameObject BelleBulletCard4;
	public GameObject BelleBulletCard5;
	public GameObject BelleBulletCard6;      

	public GameObject BelleActionMove; 
	public GameObject BelleActionChangeFloor; 
	public GameObject BelleActionPunch; 
	public GameObject BelleActionShoot; 

	public Text clickableGOsText;
	public Text currentRound; 
	public Text currentBandit; 

	private List<Button> goNeutralBulletCards;

	// a list of bullet cards for each and every bandit 
	private List<Button> goBELLEBulletCards; 
	private List<Button> goCHEYENNEBulletCards; 
	private List<Button> goDOCBulletCards; 
	private List<Button> goTUCOBulletCards; 
	private List<Button> goDJANGOBulletCards; 
	private List<Button> goGHOSTBulletCards; 

	// a list of action cards for each and every bandit's hand 
	private List<Button> goBELLEHand; 
	private List<Button> goCHEYENNEHand; 
	private List<Button> goDOCHand; 
	private List<Button> goGHOSTHand; 
	private List<Button> goTUCOHand; 
	private List<Button> goDJANGOHand; 

	private List<GameObject> clickableGOs; 
	public List<object> clickableObjects; 

    private List<float> cartZeroTop = new List<float>() {840.5F,878.4F,-364.9F};
    private List<float> cartZeroBtm = new List<float>() {786.1F, 813.5F, -364.9F};

    private List<float> cartOneTop = new List<float>() {1025.7F, 889.4F, -364.9F};
    private List<float> cartOneBtm = new List<float>() {1027.9F, 806.4F, -364.9F};

    private List<float> cartTwoTop = new List<float>() {1265.4F, 894.7F, -364.9F};
    private List<float> cartTwoBtm = new List<float>() {1279.8F, 817.7F, -364.9F};

    private List<float> cartLocoTop = new List<float>() {1410.5F, 893.4F, -364.9F};
    private List<float> cartLocoBtm = new List<float>() {1390.0F, 824.9F, -364.9F};

    private List<float> iconPosition = new List<float>() {1285.9F, 1121.9F, -364.9F};
	private List<float> gemPosition = new List<float>() {1224.1F, 1077.2F, -364.9F};

	public static string punchedBandit; 

    void Start(){
		// var clickableObjects = gm.calculateShoot();
		setAllNonClickable();

		/* DUMMY BANDITS FOR TESTING PURPOSES */
		Bandit b1 = new Bandit("GHOST");
		Bandit b2 = new Bandit("BELLE");
		Bandit b3 = new Bandit("CHEYENNE");	

		ArrayList banditsArr = new ArrayList(); 
		banditsArr.Add(b1); 
		banditsArr.Add(b2); 
		banditsArr.Add(b3); 

		/* @TEST makeShootPossibilitiesClickable*/
		buttonToObject.Add(ghost, b1);

		ArrayList shootArr = new ArrayList(); 
		shootArr.Add(b1);
		makeShootPossibilitiesClickable(shootArr);
	
		/* @OUTPUT now only GHOST is clickable 🎉*/

		/* @TEST makePunchPossibilitiesClickable */
		var selectedBanditName = EventSystem.current.currentSelectedGameObject;
         if (selectedBanditName != null)
             promptPunchTarget.text = "ahh" + selectedBanditName.name;
         else
             promptPunchTarget.text = "ahh NULLL POINTERR";
		// promptPunchTarget.text = "ahh" + selectedBanditName;
		promptPunchTarget.text = "ahh" + selectedBanditName.name;
		 

		string selectedPunchBandit = makePunchPossibilitiesClickable(shootArr);
		Debug.Log("YOU PUNCHED " + selectedPunchBandit);
		promptPunchTarget.text = selectedPunchBandit + "IS PUNCHED";

		
		SFS.setGameBoard();

		exitText.text ="";
		doesItWork.text = "";
		//Invoke("LeaveRoom",5);
		/*if (SFS.getSFS() == null) {
            // Initialize SFS2X client. This can be done in an earlier scene instead
            SmartFox sfs = new SmartFox();
            // For C# serialization
            DefaultSFSDataSerializer.RunningAssembly = Assembly.GetExecutingAssembly();
            SFS.setSFS(sfs);
        }
        if (!SFS.IsConnected()) {
            SFS.Connect("test");
        }*/
		EnterGameBoardScene();
    }

	public static void testSerial() {
		// ISFSObject obj = SFSObject.NewInstance();
		// ExtensionRequest req = new ExtensionRequest("gm.testSerial",obj);
		// SFS.Send(req);
		// //EnterGameBoardScene();
		// exitText.text = ""; 
		// clickableGOsText.text = "";
		// // init clickables should be called on update
		// initClickables();
		// exitText.text =""; 
    }

	// makeAllClickable makes all clickable objects clickable 
	public void setAllNonClickable(){
		Button[] allButtons = UnityEngine.Object.FindObjectsOfType<Button>();
		foreach(Button aBtn in allButtons){
			aBtn.interactable = false; 
		}
	}

	public void buttonClicked(Button btn){
		promptPunchTarget.text = btn.name + "IS CLICKED"; 
		punchedBandit = btn.name;
		// btn.interactable = false;
	}



	// THIS IS THE FIRST METHOD CALLED FOR RECEIVING NEW GAME STATE
    public void UpdateGameState(BaseEvent evt) {
        Debug.Log("updategamestate called");
        
        ISFSObject responseParams = (SFSObject)evt.Params["params"];
		gm = (GameManager)responseParams.GetClass("gm");
		
		// REASSIGN ALL GAME OBJECTS USING DICTIONARY
		ArrayList banditsArray = gm.bandits;
		foreach (Bandit b in banditsArray) {
            if (b.characterAsString == "CHEYENNE") {
				objects[cheyenne] = b;
                trace("Cheyenne added!");
            }
			if (b.characterAsString == "BELLE") {
                objects[belle] = b;
                trace("Belle added!");
            }
			if (b.characterAsString == "TUCO") {
                objects[tuco] = b;
                trace("Tuco added!");
            }
			if (b.characterAsString == "DOC") {
                objects[doc] = b;
                trace("Doc added!");
            }
			// if (b.characterAsString == "GHOST") {
            //     objects[ghost] = b;
            //     trace("Ghost added!");
            // }
			if (b.characterAsString == "DJANGO") {
                objects[django] = b;
                trace("Django added!");
            }
		}

		ArrayList lootArray = gm.loots;
		foreach (Loot l in lootArray) {
            if (l.getBelongsTo().getCharacter() == gem1.transform.parent.name.ToUpper()) {
				objects[gem1] = l;
                trace("Gem 1 added!");
            }
            if (l.getBelongsTo().getCharacter() == gem2.transform.parent.name.ToUpper()) {
				objects[gem2] = l;
                trace("Gem 2 added!");
            }
            if (l.getBelongsTo().getCharacter() == gem3.transform.parent.name.ToUpper()) {
				objects[gem3] = l;
                trace("Gem 3 added!");
            }
            if (l.getBelongsTo().getCharacter() == gem4.transform.parent.name.ToUpper()) {
				objects[gem4] = l;
                trace("Gem 4 added!");
            }
            if (l.getBelongsTo().getCharacter() == gem5.transform.parent.name.ToUpper()) {
				objects[gem5] = l;
                trace("Gem 5 added!");
            }
			if (l.getBelongsTo().getCharacter() == gem6.transform.parent.name.ToUpper()) {
				objects[gem6] = l;
                trace("Gem 6 added!");
            }
			// check if a loot belongs to TrainUnit and assign it 
		}

		// map the 13 neutral bullet cards
		ArrayList neuturalBulletCards = gm.neutralBulletCard; 
		for(int i=1; i<14; i++){
			Button goBulletCard = goNeutralBulletCards[i];
			objects[goBulletCard] = neuturalBulletCards[i];
		}

		// // map each bandit's bullet cards 
		// ArrayList bulletCards = currentBandit.getBulletCards();
		// // @TODO: please add a getBulletCards() mthod in Bandit that returns a list of all bullet cards of the bandit
		// if(currentBandit.characterAsString == "BELLE"){
		// 	foreach(BulletCard currBC in bulletCards){
		// 		int bulletSize = currBC.sizeOfBullet
		// 		objects[goBelleBulletCards[bulletSize]] = currBC; 
		// 	}
		// }
		
		// @TODO: add a method getBulletSize() in BulletCard that returns the number of bullets indicated on the card
		var currBandit = gm.currentBandit; 
		var bBullets = currBandit.getBulletCards(); 
		for(int i=0; i<bBullets.Count; i++){
			BulletCard currBC = (BulletCard)bBullets[i];
			mapBulletCards(b.characterAsString, currBC.getSize(), currBC);
		}

		foreach (Bandit b in banditsArray) {
         ArrayList bHand = b.getHand();
         String bName = b.getCharacter();
		 
			if(bName == "BELLE"){
				for(int i=0; i<b.sizeOfHand(); i++){
				ActionCard a = (ActionCard)bHand[i];
				string actionName = a.getActionTypeAsString();
				mapActionCards(goBELLEHand, actionName, a, bName);
				}	
		 	}else if(bName == "CHEYENNE"){
				for(int i=0; i<b.sizeOfHand(); i++){
				ActionCard a = (ActionCard)bHand[i];
				string actionName = a.getActionTypeAsString();
				mapActionCards(goCHEYENNEHand, actionName, a, bName);
				}	
		 	}else if(bName == "DOC"){
				for(int i=0; i<b.sizeOfHand(); i++){
				ActionCard a = (ActionCard)bHand[i];
				string actionName = a.getActionTypeAsString();
				mapActionCards(goDOCHand, actionName, a, bName);
				}	
		 	}else if(bName == "TUCO"){
				for(int i=0; i<b.sizeOfHand(); i++){
				ActionCard a = (ActionCard)bHand[i];
				string actionName = a.getActionTypeAsString();
				mapActionCards(goTUCOHand, actionName, a, bName);
				}	
		 	}else if(bName == "DJANGO"){
				for(int i=0; i<b.sizeOfHand(); i++){
				ActionCard a = (ActionCard)bHand[i];
				string actionName = a.getActionTypeAsString();
				mapActionCards(goDJANGOHand, actionName, a, bName);
				}	
		 	}else if(bName == "GHOST"){
				for(int i=0; i<b.sizeOfHand(); i++){
				ActionCard a = (ActionCard)bHand[i];
				string actionName = a.getActionTypeAsString();
				mapActionCards(goGHOSTHand, actionName, a, bName);
				}	
			}
		}

		
			gm.playTurn();

		// assign currRound and currPlayer 
		currentRound.text += gm.currentRound.roundTypeAsString; 
		currentBandit.text += gm.currentBandit.characterAsString; 
		Invoke("GoToChat",2);

    }


	public void mapActionCards(List<Button> goHand, string actionName, Card c, string banditName){
		foreach(Button g in goHand){
			string goName = banditName + g.name;
			if(actionName == goName.ToUpper()){
            	objects[g] = c;
			}
		}
	}

	public void mapBulletCards(string bName, int buSize, BulletCard bc){
		if(bName == "BELLE"){
			objects[goBELLEBulletCards[buSize]] = bc;
		}else if(bName == "CHEYENNE"){
			objects[goCHEYENNEBulletCards[buSize]] = bc;
		}else if(bName == "DOC"){
			objects[goDOCBulletCards[buSize]] = bc;
		}else if(bName == "TUCO"){
			objects[goTUCOBulletCards[buSize]] = bc;
		}else if(bName == "DJANGO"){
			objects[goDJANGOBulletCards[buSize]] = bc;
		}else if(bName == "GHOST"){
			objects[goGHOSTBulletCards[buSize]] = bc;
		}
	}

	public void LeaveRoom() {
        SFS.LeaveRoom();
    }

	public void playCard(GameObject selectedCard){
		// draws 3 cards randomly and put in the hand
		Destroy(selectedCard);
	}

	/* Map all Buttons to their GM objects counterparts */
	public void mapAll(){
		
	}

	/* makeShootPossibilitiesClickable makes all possibilities clickable */
	public static void makeShootPossibilitiesClickable(ArrayList possibilities){
		Debug.Log("HELLO FROM makeShootPossibilitiesClickable");

		foreach(Bandit b in possibilities){
			foreach(Button oneBtn in buttonToObject.Keys){
				if(b.characterAsString == oneBtn.name.ToUpper()){
					oneBtn.interactable = true; 
				}
			}
		}
	}

	/* makePunchPossibilitiesClickable makes all possibilities clickable AND returns the clicked Bandit's name as a string */
	public static string makePunchPossibilitiesClickable(ArrayList possibilities){
		foreach(Bandit b in possibilities){
			foreach(Button oneBtn in buttonToObject.Keys){
				if(b.characterAsString == oneBtn.name.ToUpper()){
					oneBtn.interactable = true; 
				}
			}
		}

		// user clicks on one of the highlighted bandits 
		while(punchedBandit is null){
			makePunchPossibilitiesClickable(possibilities);
		}	

		Debug.Log("PASSED BACK TO GM");
		return punchedBandit; 
	}


    // Update is called once per frame
    void Update()
    {

		// var selectedBanditName = EventSystem.current.currentSelectedGameObject;
        //  if (selectedBanditName != null)
        //      promptPunchTarget.text = "ahh" + selectedBanditName.name;
        //  else
        //      promptPunchTarget.text = "ahh NULLL POINTERR";
		

        if (SFS.IsConnected()) {
			SFS.ProcessEvents();
		}

		if (Input.GetMouseButtonDown(0)){
			// MouseDown();
			Debug.Log("Clicked");
			works = false;
			Debug.Log("currentbandit on mouse: "+ gm.currentBandit.getCharacter());
			if(gm != null && gm.currentBandit.getCharacter() == ChooseCharacter.character) {
				Debug.Log("ending my turn");
				Bandit b = (Bandit) gm.bandits[0];
				if (b.getCharacter() == gm.currentBandit.getCharacter()) {
					gm.currentBandit = (Bandit) gm.bandits[1];
				} else {
					gm.currentBandit = (Bandit) gm.bandits[0];
				}
				SendNewGameState();
				//gm.endOfTurn();
			}
		}

		if(works) {
			doesItWork.text = "it works!";
		} else {
			doesItWork.text = "";
		}

		/*if (SFS.debugText != debugText.text) {
            debugText.text = SFS.debugText;
        }
        clickableGOsText.text += "==== NOW GHOST IS SET TO NONACTIVE ===";
        // ghost.SetActive(false);
        foreach(GameObject go in allObjects){
            if(go.activeSelf == true){
                clickableGOsText.text += go.name;
            }
        }

		// calculateShoot() returns an arraylist of all possible clickable objects 
		// map those objects to gameobjects
		// make them clickable
		// var possibilities = gm.calculateShoot(); 
		// clickableObjects = gm.calculateShoot(); 
		// foreach (KeyValuePair onePair in objects){
		// 	if(clickableObjects.Contains(onePair.value)){
		// 		// make the GO clickable 
		// 		onePair.key.SetActive(true);
		// 	}
		// }
    }

	public void initCards(){
		// draws 6 cards randomly and put in the hand
		cardAText.text = "MOVE";
		cardBText.text = "ROB";
		cardCText.text = "MARSHAL"; 
		cardDText.text = "CHANGE FLOOR";
		cardEText.text = "SHOOT"; 
		cardFText.text = "PUNCH"; 
		return;
	}

	public void playCard(GameObject selectedCard){
		// draws 3 cards randomly and put in the hand
		Destroy(selectedCard);
	}

	public void MouseDown() {
		SFS.step += 1;
		int step = SFS.step;
		ISFSObject obj = SFSObject.NewInstance();
		obj.PutInt("step", step);
		ExtensionRequest req = new ExtensionRequest("gm.nextAction",obj);
		SFS.Send(req);
		//executeHardCoded(step);
		if (SFS.step == 27){
			LeaveRoom();
		}
	}

	public void drawCards(/*string currentChar*/){
		CardNewA.SetActive(true);
		CardNewB.SetActive(true);
		CardNewC.SetActive(true);
	}

	public void drawCardsSecond(/*string currentChar*/){
		CardNewD.SetActive(true);
		CardNewE.SetActive(true);
		CardNewF.SetActive(true);
	}

	public void drawThreeCards(){
		Debug.Log("DRAW THREE CARDS IS CALLED");
		// 3 cards from the current player's deck are added to the player's hand 
		var currDeck = gm.currentBandit.deck;
		Random rand = new Random();
		List<Card> threeRandomCards = new List<Card>(); 

		for(int i=0; i<2; i++){
			Card randomCard = (Card)currDeck[rand.Next(currDeck.Count)];
			threeRandomCards.Insert(i, randomCard); 
			currDeck.Remove(randomCard); 
		}
		var currHand = gm.currentBandit.hand; 
		currHand.AddRange(threeRandomCards);
	}

	public void executeHardCoded(int step) {
		announcement.text += "\n";

		if(step % 3 == 0){
			announcement.text = ""; 
		}

		switch(step) {
			case 0:
				//round,turn info
				//"Angry Marshal Round! 1 Standard turns, 1 Tunnel turn, and 1 Switching turn",
				//Its yyy's turn to play a card or draw 3 cards.
				break;
			case 1: 
				if(ChooseCharacter.character == "GHOST"){
					playCard(cardA);
				}
				break;
			case 2:
				//"Standard Turn: Cheyenne played a CHANGEFLOOR card",
				if(ChooseCharacter.character == "CHEYENNE"){
					playCard(cardD);
				}
				break;
			case 3:
				//Standard Turn: Django chose to draw cards",
				// drawCards("DJANGO", step);
				// DRAW CARDS 
				if(ChooseCharacter.character == "DJANGO"){
					drawCards(); 
				}
				break;
			case 4:
				//"Tunnel Turn: Ghost played an action card which is hidden",
				if(ChooseCharacter.character == "GHOST"){
					playCard(cardB);
				}
				break;
			case 5:
				//"Tunnel Turn: Cheyenne played an action card which is hidden",
				if(ChooseCharacter.character == "CHEYENNE"){
					playCard(cardC);
				}
				break;
			case 6:
				//"Tunnel Turn: Django played an action card which is hidden",
				if(ChooseCharacter.character == "DJANGO"){
					playCard(cardF);
				}
				break;
			case 7:
				//"Switching Turn: ",
				if(ChooseCharacter.character == "GHOST"){
					drawCards();
				}
				//"Switching Turn: Ghost chose to draw cards",
				break;
			case 8:
				if(ChooseCharacter.character == "DJANGO"){
					playCard(cardE);
				}
				break;
			case 9:
				if(ChooseCharacter.character == "CHEYENNE"){
					drawCards();
				}
				//"Switching Turn: Cheyenne chose to draw cards",
				break;
			case 10:
				//"Stealin, Resolving Move: Ghost moved to the adjacent car",
				ghost.transform.position = new Vector3 (cartOneBtm[0] - 1F, cartOneBtm[1], cartOneBtm[2]);
                		ghost.transform.position += ghost.transform.forward * Time.deltaTime * 5f;
				break;
			case 11:
				//"Stealin, Resolving ChangeFloor: Cheyenne moved to the top of the car",
				cheyenne.transform.position = new Vector3 (cartZeroTop[0] + 5F, cartZeroTop[1], cartZeroTop[2]);
                		cheyenne.transform.position += cheyenne.transform.forward * Time.deltaTime * 5f;
			        // Destroy(gem3);
				break;
			case 12:
				//"Stealin, Resolving Rob: Ghost chooses one gem to add to his loot"
				Destroy(gem3);
				// gem4.SetActive(true);
				break;
			case 13:
				//"Stealin, Resolving MoveMarshal: Cheyenne moved the Marshal",
				marshal.transform.position = new Vector3 (cartTwoBtm[0], cartTwoBtm[1], cartTwoBtm[2]);
                		marshal.transform.position += marshal.transform.forward * Time.deltaTime * 5f;
				break;
			case 14:
				// "Stealin, Resolving Punch: Django must punch Ghost,Time for Django to choose which loot to force Ghost to drop"
				// gem2.SetActive(true); //purse appears
				Destroy(ghoLoot);
				break;
			case 15:
				//"Stealin, Resolving Punch: Django chose the loot.\nTime for Django to choose where to punch Ghost to\n",//15
				//"Punch: Django chooses to punch Ghost to the last train car",
				punch(); //moves ghost
				break;
			case 16:
			//"Punch: Django chooses to punch Ghost to the last train car\nTime for Django to choose who to shoot\n",
			// "Stealin, Resolving Shoot: Django shoots Ghost",// "New Round, SpeedingUp! 1 SpeedingUp turn",
			   	shoot();
				Round.text = "ROUND 2:\n-SpeedingUp turn";
				if(ChooseCharacter.character == "DJANGO"){
					Destroy(cardA);
				}
				else if(ChooseCharacter.character == "CHEYENNE"){
					Destroy(CardNewC);
				} else if(ChooseCharacter.character == "GHOST") {
					Destroy(cardE);
				}
				break;
			case 17:	
				// "SpeedingUp Turn 1 (Cheyenne): Cheyenne played a MOVE card",  
				if(ChooseCharacter.character == "CHEYENNE"){
					playCard(cardA);
				}
				break;
			case 18:
				// "SpeedingUp Turn 2 (Cheyenne): Cheyenne chose to draw cards",
				if(ChooseCharacter.character == "CHEYENNE"){
					drawCardsSecond();
				}
				break;
			case 19:
				if(ChooseCharacter.character == "DJANGO"){
							playCard(CardNewB);
				}
				// "SpeedingUp Turn 1 (Django): Django played a CHANGEFLOOR card", 
				break;
			case 20:
				if(ChooseCharacter.character == "DJANGO"){
					drawCardsSecond();
				}
				// "SpeedingUp Turn 2 (Django): Django chose to draw cards",
				break;
			case 21:
				if(ChooseCharacter.character == "GHOST"){
						drawCardsSecond();
				}
				// "SpeedingUp Turn 1 (Ghost): Ghost chose to draw cards",
				break;
			case 22:
				if(ChooseCharacter.character == "GHOST"){
					playCard(CardNewB);
				}
				// "SpeedingUp Turn 2 (Ghost): Ghost played a CHANGEFLOOR card",
				break;
			case 23:
				// "Stealin, Resolving Move: Cheyenne moves to the adjacent train car",
			        cheyenne.transform.position = new Vector3 (cartOneTop[0] + 5F, cartOneTop[1], cartOneTop[2]);
                   		cheyenne.transform.position += cheyenne.transform.forward * Time.deltaTime * 5f;	
				break;
			case 24:
				// "Stealin, Resolving ChangeFloor: Django is moved to the top of the car",
			        django.transform.position = new Vector3 (cartOneTop[0] - 5F, cartOneTop[1], cartOneTop[2]);
                    		django.transform.position += django.transform.forward * Time.deltaTime * 10f;	
				break;
			case 25:
				// "Stealin, Resolving ChangeFloor: Ghost is moved to the top of the car",
			        ghost.transform.position = new Vector3 (cartZeroTop[0] - 1F, cartZeroTop[1], cartZeroTop[2]);
                    		ghost.transform.position += ghost.transform.forward * Time.deltaTime * 5f;
				break;
			case 26: 
				// "Results: Game has ended. ADD SCORES Django is the winner!" 
				break;
			case 27:
				Debug.Log("Leaving room");
				//LeaveRoom();
				break;
		}
    }


	public void rob(){
		gem3.transform.position = new Vector3 (gemPosition[0], gemPosition[1], gemPosition[2]); //(1224.1, 1077.2, -364.9)
        gem3.transform.position += gem3.transform.forward * Time.deltaTime * 5f;
	}

	public void punch(){
		Debug.Log("GHOST IS PUNCHED");
        float posX = cartZeroBtm[0]; 
        float posY = cartZeroBtm[1]; 
        float posZ = cartZeroBtm[2]; 
        ghost.transform.position = new Vector3 (posX, posY, posZ);
        ghost.transform.position += ghost.transform.forward * Time.deltaTime * 5f; // can be any float number
		// shoot();  
	}

	public void shoot(){
		Debug.Log("GHOST IS SHOT");
		bulletCard.transform.position = new Vector3 (iconPosition[0], iconPosition[1], iconPosition[2]);
        bulletCard.transform.position += bulletCard.transform.forward * Time.deltaTime * 2f;
	}


    void Update(){
        if (SFS.IsConnected()) {
			SFS.ProcessEvents();
		}

		// if (Input.GetMouseButtonDown(0)){
		// 	// Debug.Log(this.gameObject.name);
		// 	var clickedObjectName = this.gameObject.name; 
		// 	// check if this obj is clickable using the List<obj>
		// 	foreach(GameObject go in clickableGOs){
		// 		if(go.name == clickedObjectName){
		// 			// curr GO is clickable
		// 			object clickableObj = objects[go];
		// 			// pass clickableObj back! 
		// 			// TODO: @Backend Team : action is the name(all caps) of the next method 
		// 			// gm.action(clickableObj);
		// 		}
		// 	}

		// }
    }

	public void EnterGameBoardScene() {
		Debug.Log("entering scene");
		ISFSObject obj = SFSObject.NewInstance();
        ExtensionRequest req = new ExtensionRequest("gm.enterGameBoardScene",obj);
        SFS.Send(req);
        trace("Sent enter scene message");
	}

	public static void SendNewGameState() {
		ISFSObject obj = SFSObject.NewInstance();
		Debug.Log("sending new game state");
		obj.PutClass("gm", gm);
        ExtensionRequest req = new ExtensionRequest("gm.newGameState",obj);
        SFS.Send(req);
        trace("sent game state");
	}

	/*private void ChooseCharacter() {
        ISFSObject obj = SFSObject.NewInstance();
		obj.PutUtfString("chosenCharacter", "TUCO");
        ExtensionRequest req = new ExtensionRequest("gm.chosenCharacter",obj);
        SFS.Send(req);
        trace("chose Tuco");
    }*/

	/* Using the prompt methods from GM */
 	// public TrainUnit moveMarshalPrompt(ArrayList possibilities)
	// public void ridePrompt(ArrayList possibilities) 
	// public void move(TrainUnit targetPosition) 
	// public TrainUnit punchPositionPrompt(Bandit b, Bandit b2)

    public static void trace(string msg) {
	//	debugText.text += (debugText.text != "" ? "\n" : "") + msg;
	}

	public void GoToWaitingRoom(){
		Invoke("GoToWaitingRoom2",5);
	}

	void GoToWaitingRoom2(){
		SceneManager.LoadScene("WaitingRoom");
	}

	public void GoToChat(){
		SceneManager.LoadScene("Chat");
	}


    void OnApplicationQuit() {
		ChooseCharacter.RemoveLaunchedSession();
		// Always disconnect before quitting
		SFS.Disconnect();
	}
}
