using model;

using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

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

public class GameBoard : MonoBehaviour
{

	/*
	Frontend team:
	-attach choosecharacter strings to characters (attach character strings from
		 DisplayRemainingCharacters() to characters in the scene 
		 so that when the characters are clicked, CharacterChoice(character) is called that passes the chosen character to the server. This
		 can be done by attaching scripts to each character game object, similar to how it will work for gameobjects on the game board)
	-Assign all gameobjects in dictionary upon update game state call
	-implement prompt method in Gamemanager (i.e. set action and clickable global variables)
	-write scripts attached to each game object that checks if it is clickable, if so, checks action and calls action on clicked item
	-assign locations on game board to each gameobject (should be in attached scripts in as a global variable that is reassigned every
	   time updategamestate() is called, checks updated gm instance for new item's position)
	-get login and other scripts --done
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

	// LIST OF ALL GAME OBJECTS HERE
    public GameObject cheyenne;
	public GameObject belle; 
	public GameObject tuco; 
	public GameObject doc; 
	public GameObject ghost; 
	public GameObject django; 
	public GameObject marshal;
	
	public GameObject gem1; 
	public GameObject gem2; 
	public GameObject gem3; 
	public GameObject gem4;
	public GameObject gem5;

	/* For all the action cards */
	// public GameObject cardA; 
	// public GameObject cardB; 
	// public GameObject cardC; 
	// public GameObject cardD;
	// public GameObject cardE;
	// public GameObject cardF; 
	// public GameObject cardG;

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

	// public Text promptDrawCardsOrPlayCardMsg;
	// public GameObject tuco;
	// public GameObject doc; 
	// public GameObject django; 
    
    public static Dictionary<GameObject, object> objects = new Dictionary<GameObject, object>();

	// public Dictionary<T, GameObject> objects = new Dictionary<T, GameObject>();
	// NOTE: INITIALIZE THE DICTIONARY FOR EVERY OBJECT HERE FIRST,
	// ** THE DICTIONARIES ARE INITIALIZED(CLEARED) IN Start() ** 
	// E.G. objects.Add(cheyenne, null), objects.Add(tuco, null), ...
	// This way, update game state will simply be able to overwrite the values in the dictionary
	// whenever it is called by the server


	public static ArrayList clickable = new ArrayList();
	public static string action = "";


    public Text announcement;

	public Text cardAText; 
	public Text cardBText;
	public Text cardCText; 
	public Text cardDText;
	public Text cardEText;
	public Text cardFText;

	public Text cardNewAText;
	public Text cardNewABext;
	public Text cardNewCText;

	public GameObject CardNewA; 
	public GameObject CardNewB; 
	public GameObject CardNewC; 
	public GameObject CardNewD; 
	public GameObject CardNewE; 
	public GameObject CardNewF; 

	public GameObject playerE;


	private String[] logMessages = {
		"Angry Marshal Round! 1 Standard turns, 1 Tunnel turn, and 1 Switching turn\nIt is now Ghost's turn to play a card or draw 3 cards.", //0
		"Standard Turn: Ghost played a MOVE card\nIt is now Cheyenne's turn to play a card or draw 3 cards.",
		"Standard Turn: Cheyenne played a CHANGEFLOOR card\nIt is now Django's turn to play a card or draw 3 cards.",
		"Standard Turn: Django chose to draw cards\nNext turn!\nIt is now Ghost's turn to play a card or draw 3 cards.",
		"Tunnel Turn: Ghost played an action card which is hidden\nIt is now Cheyenne's turn to play a card or draw 3 cards.", //4
		"Tunnel Turn: Cheyenne played an action card which is hidden\nIt is now Django's turn to play a card or draw 3 cards.",
		"Tunnel Turn: Django played an action card which is hidden\nSwitching Turn Player Order: Django, Cheyenne, Ghost\nIt is now Django's turn to play a card or draw 3 cards.",
		"Switching Turn: Django played a SHOOT card\nIt is now Cheyenne's turn to play a card or draw 3 cards.",
		"Switching Turn: Cheyenne chose to draw cards\nIt is now Ghost's turn to play a card or draw 3 cards.", 
		"Switching Turn: Ghost chose to draw cards\nTime for Stealin!\nGhost's card will now be resolved",//9
		"Stealin, Resolving Move: Ghost moved to the adjacent car\nCheyenne's card will now be resolved",
		"Stealin, Resolving ChangeFloor: Cheyenne moved to the top of the car\nTime for Ghost to choose to pick one loot",
		"Stealin, Resolving Rob: Ghost chooses one gem to add to his loot\nCheyenne's card will now be resolved",
		"Stealin, Resolving MoveMarshal: Cheyenne moved the Marshal\nDjango's card will now be resolved", 
		"Stealin, Resolving Punch: Django choose to punch Ghost, who drops his loot\nTime for Django to choose where to punch Ghost to",//14
		"Punch: Django chooses to punch Ghost to the last train car\nTime for Django to choose who to shoot",
		"Stealin, Resolving Shoot: Django shoots Ghost\nNew Round, SpeedingUp! 1 SpeedingUp turn. New Player Order: Cheyenne, Django, Ghost\nIt is now Cheyenne's turn to play a card or draw 3 cards.",
		"SpeedingUp Turn 1 (Cheyenne): Cheyenne played a MOVE card\nIt is now Cheyenne's turn to play a card or draw 3 cards.", 
		"SpeedingUp Turn 2 (Cheyenne): Cheyenne chose to draw cards\nIt is now Django's turn to play a card or draw 3 cards.",
		"SpeedingUp Turn 1 (Django): Django played a CHANGEFLOOR card\nIt is now Django's turn to play a card or draw 3 cards.",//19
		"SpeedingUp Turn 2 (Django): Django chose to draw cards\nIt is now Ghost's turn to play a card or draw 3 cards.",
		"SpeedingUp Turn 1 (Ghost): Ghost chose to draw cards\nIt is now Ghost's turn to play a card or draw 3 cards.",
		"SpeedingUp Turn 2 (Ghost): Ghost played a CHANGEFLOOR card\nStealin Time!\nCheyenne's card will now be resolved",
		"Stealin, Resolving Move: Cheyenne moves to the adjacent train car\nDjango's card will now be resolved", //24
		"Stealin, Resolving ChangeFloor: Django is moved to the top of the car\nGhost's card will now be resolved",
		"Stealin, Resolving ChangeFloor: Ghost is moved to the top of the car\nCalculating Scores",
		"Results: Game has ended. ADD SCORES Django is the winner!", //27
		"..."
		};

    //private static SmartFox sfs = SFS.sfs;
    // private static string defaultHost = SFS.defaultHost;// = "127.0.0.1"; //"13.90.26.131"; // 
    //private static int defaultTcpPort = SFS.defaultTcpPort;// = 9933;			// Default TCP port
    //private static string zone = SFS.zone;// = "MergedExt"; //"ColtExpress"; //"NewZone"; //"BasicExamples";// "MyExt";
    private List<float> cartZeroTop = new List<float>() {840.5F,878.4F,-364.9F};
    private List<float> cartZeroBtm = new List<float>() {786.1F, 813.5F, -364.9F};

    private List<float> cartOneTop = new List<float>() {1025.7F, 889.4F, -364.9F};
    private List<float> cartOneBtm = new List<float>() {1027.9F, 806.4F, -364.9F};

    private List<float> cartTwoTop = new List<float>() {1265.4F, 894.7F, -364.9F};
    private List<float> cartTwoBtm = new List<float>() {1279.8F, 817.7F, -364.9F};

    private List<float> cartLocoTop = new List<float>() {1410.5F, 893.4F, -364.9F};
    private List<float> cartLocoBtm = new List<float>() {1390.0F, 824.9F, -364.9F};

    private List<float> iconPosition = new List<float>() {1285.9F, 1121.9F, -364.9F};

    void Start(){
		initCards();
		// set extra cards to false 
		CardNewA.SetActive(false);
		CardNewB.SetActive(false);
		CardNewC.SetActive(false);
		CardNewD.SetActive(false);
		CardNewE.SetActive(false);
		CardNewF.SetActive(false);

		announcement.text = "";
		Round.text = "ROUND 1:\n-Standard turn\n-Tunnel turn\n-Switching turn";
		SFS.setGameBoard();

		// announcement.text = "The current round is an Angry Marshal Round and the current turn is a Tunnel Turn!";
		// drawnCard1.text="MOVE";
		// SFS.setGameBoard(this);
		Debug.Log(SFS.step);
		announcement.text = logMessages[SFS.step];
		//debugTextString = "";
                //debugText.text = "";
		gem2.SetActive(false);

		//exit.SetActive(false);
		exitText.text ="";

		//SendNewGameState();
		// ** THE DICTIONARIES ARE INITIALIZED(CLEARED) IN Start() ** 
		// Bandits
		// objects.Add(cheyenne, "null");
		// objects.Add(belle, "null");
		/*objects.Add(tuco, "null");
		objects.Add(doc, "null");
		objects.Add(ghost, "null");
		objects.Add(django, "null");
		// Loot
		objects.Add(gem1, "null");
		objects.Add(gem2, "null");
		objects.Add(gem3, "null");
		objects.Add(gem4, "null");
		objects.Add(gem5, "null");*/
		// Cards
		// objects.Add(cardA, "null");
		// objects.Add(cardB, "null");
		// objects.Add(cardC, "null");
		// objects.Add(cardD, "null");
		// objects.Add(cardE, "null");

		//EnterGameBoardScene();

		//Invoke("LeaveRoom",5);

    }

	public void LeaveRoom() {
        SFS.LeaveRoom();
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
		return;
	}

	public void MouseDown() {
		SFS.step += 1;
		int step = SFS.step;
		ISFSObject obj = SFSObject.NewInstance();
		obj.PutInt("step", step);
		ExtensionRequest req = new ExtensionRequest("gm.nextAction",obj);
		SFS.Send(req);
		//executeHardCoded(step);
	}

	// public void drawCards(string char, int step) {
	// 	if(char == ChooseCharacter.character) {
	// 		// make three cards appear
	// 		Destroy(cardA);
	// 	}
	// 	return; 
	// }
	public void drawCards(/*string currentChar*/){
		// make three cards appear 
		// if(ChooseCharacter.character == currentChar){
			CardNewA.SetActive(true);
			CardNewB.SetActive(true);
			CardNewC.SetActive(true);
		// }
	}

	public void drawCardsSecond(/*string currentChar*/){
		// make three cards appear 
		// if(ChooseCharacter.character == currentChar){
			CardNewD.SetActive(true);
			CardNewE.SetActive(true);
			CardNewF.SetActive(true);
		// }
	}

	public void executeHardCoded(int step) {
		Debug.Log(SFS.step);
		announcement.text += "\n";

		if(step % 4 == 0){
			announcement.text = ""; 
		}
		announcement.text += logMessages[SFS.step];

		switch(step) {
			case 0:
				//round,turn info
				//"Angry Marshal Round! 1 Standard turns, 1 Tunnel turn, and 1 Switching turn",
				//Its yyy's turn to play a card or draw 3 cards.
				break;
			case 1: 
				// yyyy played a ___ card / yyy chose to draw 3 cards
				//"Standard Turn: Ghost played a MOVE card",
				//Its xxx's turn to play a card or draw 3 cards.
				break;
			case 2:
				//"Standard Turn: Cheyenne played a CHANGEFLOOR card",
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
					playCard(cardD);
				}
				break;
			case 5:
				//"Tunnel Turn: Cheyenne played an action card which is hidden",
				if(ChooseCharacter.character == "CHEYENNE"){
					playCard(cardD);
				}
				break;
			case 6:
				//"Tunnel Turn: Django played an action card which is hidden",
				if(ChooseCharacter.character == "DJANGO"){
					playCard(cardD);
				}
				break;
			case 7:
				//"Switching Turn Player Order: Django, Cheyenne, Ghost",
				break;
			case 7:
				//"Switching Turn: Django played a SHOOT card",
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
				drawCards();
				//"Switching Turn: Ghost chose to draw cards",
				break;
			case 10:
				//"Stealin, Resolving Move: Ghost moved to the adjacent car",
				ghost.transform.position = new Vector3 (cartOneBtm[0] - 1F, cartOneBtm[1], cartOneBtm[2]);
                		ghost.transform.position += ghost.transform.forward * Time.deltaTime * 5f;
			        // cheyenne.transform.position = new Vector3 (cartZeroTop[0], cartZeroTop[1], cartZeroTop[2]);
                    		// cheyenne.transform.position += cheyenne.transform.forward * Time.deltaTime * 5f;
				break;
			case 11:
				//"Stealin, Resolving ChangeFloor: Cheyenne moved to the top of the car",
				cheyenne.transform.position = new Vector3 (cartZeroTop[0] + 5F, cartZeroTop[1], cartZeroTop[2]);
                		cheyenne.transform.position += cheyenne.transform.forward * Time.deltaTime * 5f;
			        // Destroy(gem3);
				break;
			case 12:
				//"Stealin, Resolving Rob: Ghost chooses one gem to add to his loot",
				// TODO: GHOST CLICK ON GEM 3 
				Destroy(gem3);
				break;
			case 13:
				//"Stealin, Resolving MoveMarshal: Cheyenne moved the Marshal",
				marshal.transform.position = new Vector3 (cartTwoBtm[0], cartTwoBtm[1], cartTwoBtm[2]);
                		marshal.transform.position += marshal.transform.forward * Time.deltaTime * 5f;
				break;
			case 14:
				// "Stealin, Resolving Punch: Django choose to punch Ghost, who drops his loot",
				gem2.SetActive(true);
				break;
			case 15:
				//"Punch: Django chooses to punch Ghost to the last train car",
				punch(); 
				break;
			case 16:
				// "Stealin, Resolving Shoot: Django shoots Ghost",// "New Round, SpeedingUp! 1 SpeedingUp turn",
			   	shoot();
				Round.text = "ROUND 2:\n-SpeedingUp turn";
				break;
			case 17:	
				// "SpeedingUp Turn 1 (Cheyenne): Cheyenne played a MOVE card",  
				break;
			case 20:
				drawCards();
				// "SpeedingUp Turn 2 (Cheyenne): Cheyenne chose to draw cards",
				break;
			case 19:
				// "SpeedingUp Turn 1 (Django): Django played a CHANGEFLOOR card", 
				break;
			case 22:
				drawCards();
				// "SpeedingUp Turn 2 (Django): Django chose to draw cards",
				break;
			case 21:
				// "SpeedingUp Turn 1 (Ghost): Ghost chose to draw cards",
				break;
			case 24:
				drawCards();
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
			        ghost.transform.position = new Vector3 (cartOneBtm[0] - 1F, cartOneBtm[1], cartOneBtm[2]);
                    		ghost.transform.position += ghost.transform.forward * Time.deltaTime * 5f;
				break;
			case 26: 
				// "Results: Game has ended. ADD SCORES Django is the winner!" 
				break;
			case 27:
				Debug.Log("Leaving room");
				LeaveRoom();
				break; 
				
		}
    }


	public void rob(){
		gem3.SetActive(false);
		Destroy(gem3);
	}

	public void punch(){
		// Django punches Ghost, Ghost is punched back to last train car and with 
		// his initial purse is left in the second last train car. 
		// move ghost to the last train car
		// check if the obj being clicked on is the loot/bandit that we want to move 
		Debug.Log("GHOST IS PUNCHED");
        float posX = cartZeroBtm[0]; 
        float posY = cartZeroBtm[1]; 
        float posZ = cartZeroBtm[2]; 
        ghost.transform.position = new Vector3 (posX, posY, posZ);
        ghost.transform.position += ghost.transform.forward * Time.deltaTime * 5f; // can be any float number
		// shoot();  
	}

	public void shoot(){
		// Django punches Ghost, Ghost is punched back to last train car and with 
		// his initial purse is left in the second last train car. 
		// move ghost to the last train car
		// check if the obj being clicked on is the loot/bandit that we want to move 
		// Debug.Log("GHOST IS SHOT");
        // float posX = cartZeroBtm[0]; 
        // float posY = cartZeroBtm[1]; 
        // float posZ = cartZeroBtm[2]; 
        // ghost.transform.position = new Vector3 (posX, posY, posZ);
        // ghost.transform.position += ghost.transform.forward * Time.deltaTime * 5f; // can be any float number
		// gem2.SetActive(true);
		Debug.Log("GHOST IS SHOT");
		bulletCard.transform.position = new Vector3 (iconPosition[0], iconPosition[1], iconPosition[2]);
        bulletCard.transform.position += bulletCard.transform.forward * Time.deltaTime * 2f;
	}

	// public void OnMouseDown(){
	// 	MouseDown();
	// 	Debug.Log("Clicked");
	// }


    // Update is called once per frame
    void Update()
    {
        if (SFS.IsConnected()) {
			SFS.ProcessEvents();
		}

		if (Input.GetMouseButtonDown(0)){
			MouseDown();
			Debug.Log("Clicked");
		}

		/*if (SFS.debugText != debugText.text) {
            debugText.text = SFS.debugText;
        }

		// for debugging
		if (SFS.moreText) {
            debugTextString += SFS.debugText;
            SFS.moreText = false;
        }
        if (debugTextString != debugText.text) {
            debugText.text = debugTextString;
        }*/
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

	// THIS IS THE FIRST METHOD CALLED FOR RECEIVING NEW GAME STATE
    public void UpdateGameState(BaseEvent evt) {
        Debug.Log("updategamestate called");
        
        ISFSObject responseParams = (SFSObject)evt.Params["params"];
		gm = (GameManager)responseParams.GetClass("gm");
		
		// REASSIGN ALL GAME OBJECTS USING DICTIONARY
		ArrayList banditsArray = gm.bandits;
		//ArrayList banditsArray = new ArrayList();
		foreach (Bandit b in banditsArray) {
            if (b.banditNameAsString == "CHEYENNE") {
				objects[cheyenne] = b;
                trace("Cheyenne added!");
            }
			if (b.banditNameAsString == "BELLE") {
                objects[belle] = b;
                trace("Belle added!");
            }
			if (b.banditNameAsString == "TUCO") {
                objects[tuco] = b;
                trace("Tuco added!");
            }
			if (b.banditNameAsString == "DOC") {
                objects[doc] = b;
                trace("Doc added!");
            }
			if (b.banditNameAsString == "GHOST") {
                objects[ghost] = b;
                trace("Ghost added!");
            }
			if (b.banditNameAsString == "DJANGO") {
                objects[django] = b;
                trace("Django added!");
            }
		}
		Debug.Log("bandits array size: " + banditsArray.Count);
		ArrayList cards = new ArrayList();
		foreach (Bandit ba in banditsArray) {
			Debug.Log(ba.banditNameAsString +" "+ ChooseCharacter.character);
			if(ba.banditNameAsString == ChooseCharacter.character) {
				ArrayList hand = b.hand;
				Debug.Log("adding cards");
				objects[cardA] = hand[0];
				objects[cardB] = hand[1];
				objects[cardC] = hand[2];
				objects[cardD] = hand[3];
				objects[cardE] = hand[4];
			}
		}

			gm.playTurn();
    }

	/*private void ChooseCharacter() {
        ISFSObject obj = SFSObject.NewInstance();
		obj.PutUtfString("chosenCharacter", "TUCO");
        ExtensionRequest req = new ExtensionRequest("gm.chosenCharacter",obj);
        SFS.Send(req);
        trace("chose Tuco");
    }*/

    public static void trace(string msg) {
	//	debugText.text += (debugText.text != "" ? "\n" : "") + msg;
	}

	public void GoToWaitingRoom(){
		Invoke("GoToWaitingRoom2",5);
	}

	void GoToWaitingRoom2(){
		SceneManager.LoadScene("WaitingRoom");
	}


    void OnApplicationQuit() {
		ChooseCharacter.RemoveLaunchedSession();
		// Always disconnect before quitting
		SFS.Disconnect();
	}

}
