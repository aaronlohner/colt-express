using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
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

using model;

public class ChooseCharacter : MonoBehaviour
{
    // debugging variables
    public Text selected;
    public static string debugText;
    public Button button;

    private static bool alreadyCalled = false;

    //SAVE THE CHOSEN CHARACTER IN THIS STRING SO IT CAN BE USED BY GAMEMANAGER
    public string character;

    // BOOLEANS FOR CHARACTER AVAILABILITY
    public bool BelleIsAvailable; 
    public bool CheyenneIsAvailable; 
    public bool TucoIsAvailable; 
    public bool DjangoIsAvailable; 
    public bool DocIsAvailable; 
    public bool GhostIsAvailable; 

    // Start is called before the first frame update
    void Start()
    {
            BelleIsAvailable = true;
            CheyenneIsAvailable = true; 
            TucoIsAvailable = true; 
            DjangoIsAvailable = true; 
            DocIsAvailable = true; 

            // rend = GetComponent<Renderer>();
            // name = this.GameObject;
            debugText = "";
            selected.text = "";

        // Initialize SFS2X client. This can be done in an earlier scene instead
		SmartFox sfs = new SmartFox();
        // For C# serialization
		DefaultSFSDataSerializer.RunningAssembly = Assembly.GetExecutingAssembly();
        SFS.setSFS(sfs);
        SFS.Connect();
    }

    // Update is called once per frame
    void Update()
    {
        if (SFS.IsConnected() && !alreadyCalled) {
            alreadyCalled = true;
            Invoke("EnterChooseCharacterScene",2);
            /*
            THIS LISTENER CAN BE REMOVED ONCE THE CHARACTERS THEMSELVES CAN BE CLICKED
            */
            // button.onClick.AddListener(CharacterChoice);
            //EnterChooseCharacterScene();
        }
        if (SFS.IsConnected()) {
			SFS.ProcessEvents();
		}

        if (Input.GetMouseButtonDown(0)){
            //string name =  EventSystem.current.currentSelectedGameObject.name;
            //  Debug.Log(name + "ahh"); 
            //selected.text = "Your bandit is: " + name;
            // CALLS CharacterChoice AND PASSES IN THE CHOSEN CHARACTER AS A STRING
             string name =  EventSystem.current.currentSelectedGameObject.name;
             // Debug.Log(name + " ahh"); 
             selected.text = "Your bandit is: " + name;
             CharacterChoice(name);

             // SET AVAILABILITY OF THE CHOSEN CHARACTER TO FALSE 
             if(name == "Belle"){
                BelleIsAvailable = false; 
             }else if(name == "Cheyenne"){
                CheyenneIsAvailable = false; 
             }else if(name == "Doc"){
                DocIsAvailable = false; 
             }else if(name == "Tuco"){
                TucoIsAvailable = false; 
             }else if(name == "Ghost"){
                GhostIsAvailable = false; 
             }else if(name == "Django"){
                DjangoIsAvailable = false; 
             }

            // uncomment the following to check if the code in DisplayRemainingCharacters is working
            // var foundButtonObjects = FindObjectsOfType<Button>();
            // foreach(Button btn in foundButtonObjects){
            //     // Debug.Log(btn);
            //     if(btn.interactable == true){
            //         Debug.Log(btn.name);
            //     }
            // }

             // DisplayRemainingCharacters(evt);
        }

        // for debugging
        if (SFS.moreText) {
            debugText += SFS.debugText;
            SFS.moreText = false;
        }
        if (debugText != selected.text) {
            selected.text = debugText;
        }
    }


    void OnMouseEnter()
 	{
 
    //   string objectName = gameObject.name;
    //   Debug.Log(objectName);
    //  startcolor = rend.material.color;
    //  rend.material.color = Color.grey;
     // Debug.Log(this.GameObject.name);
 	}

    void OnMouseExit()
 	{
 
 	}

 	void OnMouseDown()
 	{

 	}

    public static void trace(string msg) {
		debugText += (debugText != "" ? "\n" : "") + msg;
	}

	public void EnterChooseCharacterScene() {
		ISFSObject obj = SFSObject.NewInstance();
        ExtensionRequest req = new ExtensionRequest("gm.enterChooseCharacterScene",obj);
        SFS.Send(req);
        trace("Sent enter scene message");
	}



    // private void CharacterChoice(/*string chosen*/) {
    //     ISFSObject obj = SFSObject.NewInstance();
	// 	obj.PutUtfString("chosenCharacter", "BELLE");//hardcoded for now, replace "BELLE" with "chosen"
    //     ExtensionRequest req = new ExtensionRequest("gm.chosenCharacter",obj);
    //     SFS.Send(req);
    //     trace("chose Belle");
    // }
    private void CharacterChoice(string chosenCharacter) {
        ISFSObject obj = SFSObject.NewInstance();
		obj.PutUtfString("chosenCharacter", chosenCharacter);//hardcoded for now, replace "BELLE" with "chosen"
        ExtensionRequest req = new ExtensionRequest("gm.chosenCharacter",obj);
        SFS.Send(req);
        trace("chose"+chosenCharacter);
    }

	public static void DisplayRemainingCharacters(BaseEvent evt) {
		ISFSObject responseParams = (SFSObject)evt.Params["params"];
        try {
            ISFSArray a = responseParams.GetSFSArray("characterList");
            int size = responseParams.GetSFSArray("characterList").Size();
            trace("Characters to choose from: ");
            for (int i = 0; i < size; i++) {
                string banditName = (string)a.GetUtfString(i);
                /*GET CHARACTER STRINGS HERE*/
                if (banditName == "TUCO") {
                    // the tuco game object becomes clicked somehow (e.g. same way how it can be done for gameboard, by assigning a global
                    // variable and then having scripts attached to each game object check if that global var is assigned, if so render 
                    // the game object clicked)

                    // ?? SET TUCO TO CLICKED ?? 
                    // RETURN ALL THE CHARACTERS THAT HAVE NOT BEEN CLICKED ?? 


                }
                 trace((string)a.GetUtfString(i));
            }

            //  ** print the names of all character buttons that have not been clicked 
            var foundButtonObjects = FindObjectsOfType<Button>();
            foreach(Button btn in foundButtonObjects){
                // Debug.Log(btn);
                if(btn.interactable == true){
                    trace(btn.name);
                }
            }

        } catch (Exception e) {
            SceneManager.LoadScene("GameBoard");
        }
	}


}