using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

using Sfs2X;
using Sfs2X.Logging;
using Sfs2X.Util;
using Sfs2X.Core;
using Sfs2X.Entities;
using Sfs2X.Entities.Data;
using Sfs2X.Requests;
using System.Reflection;
using Sfs2X.Protocol.Serialization;

using model;

public class GameBoard : MonoBehaviour
{

	/*
	Frontend team:
	-attach choosecharacter strings to characters (attach character strings from
		 DisplayRemainingCharacters() to characters in the scene so that when the characters are clicked,
		 CharacterChoice(character) is called that passes the chosen character to the server. This
		 can be done by attaching scripts to each character game object, similar to how it will work for gameobjects on the game board)
	-Assign all gameobjects in dictionary upon update game state call
	-implement prompt method in Gamemanager (i.e. set action and clickable global variables)
	-write scripts attached to each game object that checks if it is clickable, if so, checks action and calls action on clicked item
	-assign locations on game board to each gameobject (should be in attached scripts in as a global variable that is reassigned every
	   time updategamestate() is called, checks updated gm instance for new item's position)
	-get login and other scripts
	*/

	//debug variables
	public Text debugText;
	public static string debugTextString;
    public Button button;
	public Button extension;
	public Button chooseChar;
    public Text buttonLabel;
    public Bandit b;

	public static GameManager gm;

	// LIST OF ALL GAME OBJECTS HERE
    public GameObject cheyenne;

    
    
    //public Dictionary<GameObject, T> objects = new Dictionary<GameObject, T>();
	//NOTE: INITIALIZE THE DICTIONARY FOR EVERY OBJECT HERE FIRST,
	//	E.G. objects.Add(cheyenne, null), objects.Add(tuco, null), ...
	// This way, update game state will simply be able to overwrite the values in the dictionary
	// whenever it is called by the server

	public static ArrayList clickable = new ArrayList();
	public static string action = "";


    //private static SmartFox sfs = SFS.sfs;
   // private static string defaultHost = SFS.defaultHost;// = "127.0.0.1"; //"13.90.26.131"; // 
	//private static int defaultTcpPort = SFS.defaultTcpPort;// = 9933;			// Default TCP port
    //private static string zone = SFS.zone;// = "MergedExt"; //"ColtExpress"; //"NewZone"; //"BasicExamples";// "MyExt";

    void Start()
    {
		debugTextString = "";
        debugText.text = "";
		SendNewGameState();

    }

    // Update is called once per frame
    void Update()
    {
        if (SFS.IsConnected()) {
			SFS.ProcessEvents();
		}

		if (SFS.debugText != debugText.text) {
            debugText.text = SFS.debugText;
        }

		// for debugging
		if (SFS.moreText) {
            debugTextString += SFS.debugText;
            SFS.moreText = false;
        }
        if (debugTextString != debugText.text) {
            debugText.text = debugTextString;
        }
    }

	public static void SendNewGameState() {
		ISFSObject obj = SFSObject.NewInstance();
		
		// testing purposes
		gm = new GameManager();
		ArrayList bandits = new ArrayList();
		Bandit doc = new Bandit();
		TrainUnit position = new TrainUnit();
		position.carTypeAsString = "LocomotiveRoof";
		doc.position = position;
		bandits.Add(doc);
		gm.bandits = bandits;
		

		obj.PutClass("gm", gm);
        ExtensionRequest req = new ExtensionRequest("gm.newGameState",obj);
        SFS.Send(req);
        trace("sent game state");
	}

	// THIS IS THE FIRST METHOD CALLED FOR RECEIVING NEW GAME STATE
    public static void UpdateGameState(BaseEvent evt) {
        trace("updategamestate called");
        
        ISFSObject responseParams = (SFSObject)evt.Params["params"];
		gm = (GameManager)responseParams.GetClass("gm");
		
		// assign new gm to all attached scripts here
		loot.setGame(gm);

		gm.PlayTurn();

    }

	private void ChooseCharacter() {
        ISFSObject obj = SFSObject.NewInstance();
		obj.PutUtfString("chosenCharacter", "TUCO");
        ExtensionRequest req = new ExtensionRequest("gm.chosenCharacter",obj);
        SFS.Send(req);
        trace("chose Tuco");
    }

	public static void trace(string msg) {
		debugTextString += (debugTextString != "" ? "\n" : "") + msg;
	}

	void OnApplicationQuit() {
		// Always disconnect before quitting
		SFS.Disconnect();
	}

   /* private void Test() {
        buttonLabel.text = "CONNECT+ENTER";
        button.onClick.AddListener(OnButtonClick);
		extension.onClick.AddListener(SendNewGameState);//EnterChooseCharacterScene);
		chooseChar.onClick.AddListener(ChooseCharacter);
    }


    private void trace(string msg) {
		debugText.text += (debugText.text != "" ? "\n" : "") + msg;
	}*/


}
