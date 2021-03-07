package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.smartfoxserver.v2.annotations.Instantiation;
import com.smartfoxserver.v2.annotations.MultiHandler;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.extensions.SFSExtension;

import main.GameExtension;
import model.*;
import model.Character;

import com.smartfoxserver.v2.annotations.Instantiation.InstantiationMode;

@MultiHandler
public class GameMultiHandler extends BaseClientRequestHandler
{
	public void handleClientRequest(User sender, ISFSObject params) {
		String command = params.getUtfString(SFSExtension.MULTIHANDLER_REQUEST_ID);
		
		switch(command) {
		case "chooseBandit": handleChooseBandit(sender, params);
		case "begin": handleBegin();
		default: trace("Invalid command - cannot be handled by multihandler");
		}
		/*if (command.equals("chooseBandit")) {
			handleChooseBandit(sender, params);
		}
		else if (command.equals("begin")) {
			handleBegin();
		}
		else if (command.equals("")) {
			trace("");
		}*/
	}
	
	public void handleBegin() {
		//assigning null variable to indicate bandits have not been chosen
		GameExtension parentExt = (GameExtension)getParentExtension();
		Zone zone = parentExt.getParentZone();
		List<User> users = (List<User>)zone.getUserList();
		for (User user: users) {
			UserVariable assignedBandit = new SFSUserVariable("bandit", null);
			getApi().setUserVariables(user, Arrays.asList(assignedBandit));
		}
		GameManager gm = GameManager.getInstance();
		updateGameState(gm);
	}
	
	//"bandit" -> Bandit string that player chooses
	public void handleChooseBandit(User sender, ISFSObject params) {
		/* HAS COMPILER ERROR
		 * 
		 * 
		//execute choose bandit logic
		String strBandit = (String)params.getText("bandit");
		//create bandit from string info
		Character character = null;
		try {
			character = Character.valueOf(strBandit);
		} catch(IllegalArgumentException e) {
			//TODO: handle error
		}
		//call chosenCharacter
		GameExtension parentExt = (GameExtension)getParentExtension();
		Zone zone = parentExt.getParentZone();
		List<User> users = (List<User>)zone.getUserList();
		GameManager gm = GameManager.getInstance();
		Bandit b = gm.chosenCharacter(sender, character, users.size());
		//assigning bandit to user variable
		List<UserVariable> uv = new ArrayList<UserVariable>();
		UserVariable assignedBandit = new SFSUserVariable("bandit", b);
		uv.add(assignedBandit);
		getApi().setUserVariables(sender, uv);
		//if chosenCharacter changed the gameStatus (bandit selection is done), initialize game
		GameStatus status = gm.getGameStatus();
		if (status == GameStatus.SCHEMIN) {
			initializeGame();
		}
		else {
			//bandit selection ongoing, send back list of chosen bandits
			ISFSArray chosen = new SFSArray();
			for (User user: users) {
				Bandit a = (Bandit)user.getVariable("bandit");
				if (a != null) {
					chosen.addUtfString(a.banditNameAsString);
				}
			}
			//if choosing bandit phase still ongoing then send this info back
			ISFSObject gameState = SFSObject.newInstance();
			gameState.putSFSArray("chosen", chosen);
			parentExt.send("chosenBandits", gameState, users);
		}
			*/
		
	}
	
	public void initializeGame() {
		//TODO: implement
		//method for creating all non-bandit game objects and sending them to client
		//GameExtension parentExt = (GameExtension)getParentExtension();
		//Zone zone = parentExt.getParentZone();
		//List<User> users = (List<User>)zone.getUserList();
		GameManager gm = GameManager.getInstance();
		gm.initializeGame();
		updateGameState(gm);
		
	}
	
	public void updateGameState(GameManager gm) {
		//TODO: implement
	}
}