package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import com.smartfoxserver.v2.annotations.Instantiation;
import com.smartfoxserver.v2.annotations.Instantiation.InstantiationMode;
import com.smartfoxserver.v2.annotations.MultiHandler;

//@Instantiation(InstantiationMode.SINGLE_INSTANCE)
//@MultiHandler
public class GameManager /*extends BaseClientRequestHandler */implements SerializableSFSType {

	transient public static GameManager singleton;
	transient public GameStatus gameStatus;
	public String strGameStatus;
	public Round currentRound;
	public Bandit currentBandit;
	public ArrayList<Round> rounds = new ArrayList<Round>();
	public Marshal marshalInstance;
	public PlayedPile playedPileInstance;
	public TrainUnit[][] train;
	public TrainUnit stagecoach;
	public ArrayList<Bandit> bandits = new ArrayList<Bandit>();
	transient public HashMap<Bandit, User> banditmap = new HashMap<Bandit, User>();
	

	//Bandit b = new Bandit(Character.CHEYENNE);
	
	/*@Override
	public void handleClientRequest(User sender, ISFSObject params) {
		//String command = params.getUtfString(SFSExtension.MULTIHANDLER_REQUEST_ID);
		
		ISFSObject gameState = SFSObject.newInstance();
		
//		if(truecommand.equals("test")) {
			bandits.add(b);
//			//ISFSArray banditsArray = SFSArray.newInstance();
//			//gameState.putClass("bandits", bandits);
//		}
		gameState.putUtfString("testStr", "someData");
		ColtExtension parentExt = (ColtExtension)getParentExtension();
		Zone zone = parentExt.getParentZone();
		//parentExt.send("updateGameState", gameState, (List<User>) zone.getUserList());
		parentExt.send("updateGameState", gameState, sender);
		
	}*/
	
	// SOME OF THESE FIELDS SHOULD BE AUTOMATICALLY INITIALIZED, NOT PASSED AS PARAMS
	private GameManager(ArrayList<Bandit> bandits, Bandit currentBandit, ArrayList<Round> rounds, Round currentRound,
			GameStatus status, TrainUnit[][] trainUnits) {

		this.bandits = bandits;
		this.currentBandit = currentBandit;
		this.rounds = rounds;
		this.currentRound = currentRound;
		this.gameStatus = status;
		this.marshalInstance = Marshal.getInstance();
		this.playedPileInstance = PlayedPile.getInstance();

		//this.train = TrainUnit.createTrain(this.bandits.size());
		//this.stagecoach = TrainUnit.createStagecoach();
	}
	
	public GameManager() {};

	public static GameManager getInstance() {
		GameManager gm = null;
		if (singleton == null) {
			gm = new GameManager();/*new ArrayList<Bandit>(), null, new ArrayList<Round>(), null, GameStatus.SETUP,
					TrainUnit.createTrain(0));*/
		} else {
			gm = singleton;
		}
		return gm;
	}

	/**
	 * --ROUND METHODS--
	 */
	public Round getCurrentRound() {
		return this.currentRound;
	}

	public void setCurrentRound(Round newObject) {
		this.currentRound = newObject;
	}

	public Round getRoundAt(int index) {
		return this.rounds.get(index);
	}

	public void addRound(Round a) {
		int size = rounds.size();
		/*
		 * if (size == maximum) { return false; }
		 */
		this.rounds.add(a);
	}

	public void removeRound(Round a) {
		if (this.rounds.contains(a)) {
			this.rounds.remove(a);
		}
	}

	public boolean roundsContains(Round a) {
		boolean contains = rounds.contains(a);
		return contains;
	}

	public int sizeOfRounds() {
		int size = rounds.size();
		return size;
	}

	public ArrayList<Round> getRounds() {
		return this.rounds;
	}

	/**
	 * --GAME STATUS METHODS--
	 */
	public void setGameStatus(GameStatus newStatus) {
		this.gameStatus = newStatus;
		this.strGameStatus = gameStatus.toString();
	}

	public GameStatus getGameStatus() {
		return this.gameStatus;
	}

	/**
	 * --TRAIN UNIT METHODS--
	 */
	/*public void addTrainUnitsAt(int index, TrainUnit a) {
		boolean contains = this.trainUnits.contains(a);
		if (contains) {
			return;
		}
		trainUnits.add(index, a);
	}

	public void removeTrainUnitsAt(int index) {
		if (this.trainUnits.size >= index) {
			this.trainUnits.remove(index);
		}
	}

	public TrainUnit getTrainUnitsAt(int index) {
		if (this.trainUnits.size >= index) {
			return this.trainUnits.get(index);
		}
	}

	public void addTrainUnit(TrainUnit a) {
		this.trainUnits.add(a);
	}

	public void removeTrainUnits(TrainUnit a) {
		if (this.trainUnits.contains(a)) {
			this.trainUnits.remove(a);
		}
	}

	public boolean trainUnitsContain(TrainUnit a) {
		boolean contains = trainUnits.contains(a);
		return contains;
	}

	public int sizeOfTrainUnits() {
		int size = this.trainUnits.size();
		return size;
	}

	public ArrayList<TrainUnit> getTrainUnits() {
		return this.trainUnits;
	}*/

	/**
	 * --BANDIT METHODS--
	 */
	public Bandit getCurrentBandit() {
		return this.currentBandit;
	}

	public void setCurrentBandit(Bandit newObject) {
		this.currentBandit = newObject;
	}

	/*
	 * boolean addBanditsAt(int index, Bandit a) { int size = bandits.size(); if
	 * (size == maximum) { return false; } bandits.add(index, a); return true; }
	 */

	public void removeBanditsAt(int index) {
		if (this.bandits.size() >= index) {
			this.bandits.remove(index);
		}
	}

	public Bandit getBanditsAt(int index) {
		if (this.bandits.size() >= index) {
			return this.bandits.get(index);
		}
		return null;
	}

	public void addBandit(Bandit a) {
		bandits.add(a);
	}

	public void removeBandits(Bandit a) {
		if (this.bandits.contains(a)) {
			this.bandits.remove(a);
		}
	}

	public boolean containsBandits(Bandit a) {
		boolean contains = this.bandits.contains(a);
		return contains;
	}

	public int sizeOfBandits() {
		int size = this.bandits.size();
		return size;
	}

	public ArrayList<Bandit> getBandits() {
		return this.bandits;
	}

	/**
	 * --GAME MANAGER METHODS--
	 */

	boolean allPlayersChosen(int numPlayers) {
		// TO DO
		if(numPlayers == bandits.size()) {
			return true;
		}
		// for all players, return if they are all associated with a bandit or not.
		return false;
	}

	int getNumOfPlayers() {
		// TO DO
		// Here to get number of players
		return 3;
	}
	
	//void chosenCharacter(int playerId, Character c) {
	public Bandit chosenCharacter(User player, Character c, int numPlayers) {
		Bandit newBandit = new Bandit(c);
		this.bandits.add(newBandit);
		this.banditmap.put(newBandit, player);
		// TO DO.
		// Here to associate playerId with newBandit.
		boolean ready = this.allPlayersChosen(numPlayers);
		if (!ready) {
			System.out.println("Not all players are ready!");
		} else {
			int numP = this.getNumOfPlayers();
			for (int i = 0; i < numP; i++) {
				// TO DO
				// Here create new TrainUnit object
				// this.addTrainUnits(new TrainUnit());
			}

			// I COMMENTED MOST OF THIS OUT BC IT CAUSES A SERIALIZATION ERROR SINCE THE FIELDS OF THE OBJECTS BEING CREATED
			// ARE NOT ALL PUBLIC -- Aaron
			
			/*for (Bandit b : this.bandits) {
				b.createStartingCards();
				b.createBulletCards();
				b.createStartingPurse();
			}

			// TO DO
			// set up positions for bandits correspondingly

			Marshal marshal = new Marshal();

			Money strongbox = new Money(MoneyType.STRONGBOX, 1000);*/

			// TO DO
			// set up the positions for marshal and strongbox

			// for (TrainUnit tu : this.trainUnits) {
			// TO DO
			// algorithm for adding loots randomly to each train unit that is created
			// Money l = new Money();
			// tu.addLootInCabin(l);
			// }

			this.setGameStatus(GameStatus.SCHEMIN);
			//this.setCurrentRound(this.rounds.get(0));
			// set waiting for input to be true;
		}
		return newBandit;
	}

	/*void Rob() {

		if (this.currentBandit.getPosition().carType == CarType.Car1Roof
				|| this.currentBandit.getPosition().carType == CarType.Car2Roof
				|| this.currentBandit.getPosition().carType == CarType.Car3Roof
				|| this.currentBandit.getPosition().carType == CarType.Car4Roof
				|| this.currentBandit.getPosition().carType == CarType.Car5Roof
				|| this.currentBandit.getPosition().carType == CarType.Car6Roof
				|| this.currentBandit.getPosition().carType == CarType.LocomotiveRoof
				|| this.currentBandit.getPosition().carType == CarType.StagecoachRoof) {
			return;
		} else {
			if (!this.currentBandit.getPosition().getLootHere().isEmpty()) {
				// ask the bandit to choose loot
				Loot l = null;
				this.currentBandit.addLoot(l);
			}
		}

	}

	void shoot() {

		ArrayList<Bandit> roofShootTarget = new ArrayList<Bandit>();
		ArrayList<Bandit> carShootTarget = new ArrayList<Bandit>();
		if (this.currentBandit.getPosition().carType == CarType.Car1Roof
				|| this.currentBandit.getPosition().carType == CarType.Car2Roof
				|| this.currentBandit.getPosition().carType == CarType.Car3Roof
				|| this.currentBandit.getPosition().carType == CarType.Car4Roof
				|| this.currentBandit.getPosition().carType == CarType.Car5Roof
				|| this.currentBandit.getPosition().carType == CarType.Car6Roof
				|| this.currentBandit.getPosition().carType == CarType.LocomotiveRoof
				|| this.currentBandit.getPosition().carType == CarType.StagecoachRoof) {
			for (Bandit b : this.bandits) {
				if (b != this.currentBandit) {
					if (b.getPosition().carType == CarType.Car1Roof || b.getPosition().carType == CarType.Car2Roof
							|| b.getPosition().carType == CarType.Car3Roof
							|| b.getPosition().carType == CarType.Car4Roof
							|| b.getPosition().carType == CarType.Car5Roof
							|| b.getPosition().carType == CarType.Car6Roof
							|| b.getPosition().carType == CarType.LocomotiveRoof
							|| b.getPosition().carType == CarType.StagecoachRoof) {
						roofShootTarget.add(b);
					}
				}
			}

			if (roofShootTarget.get(0) != null) {
				// ask the current player to choose which bandit to shoot
				Bandit b = null;

				BulletCard bc = this.currentBandit.bullets.remove(0);
				if (bc != null) {
					b.addDiscardPile(bc);
				}
			}

		} else {
			for (Bandit bl : this.currentBandit.getPosition().getLeft().banditsHere) {
				carShootTarget.add(bl);
			}
			for (Bandit br : this.currentBandit.getPosition().getRight().banditsHere) {
				carShootTarget.add(br);
			}
			if (carShootTarget.get(0) != null) {
				// ask the current player to choose which bandit to shoot
				Bandit b = null;

				BulletCard bc = this.currentBandit.bullets.remove(0);
				if (bc != null) {
					b.addDiscardPile(bc);
				}
			}
		}

	}

	void punch() {
		ArrayList<Bandit> otherBandits = new ArrayList<Bandit>();
		for (Bandit b : this.currentBandit.getPosition().getBandtsHere()) {
			if (b != this.currentBandit) {
				otherBandits.add(b);
			}
		}

		if (otherBandits.get(0) != null) {
			// ask the player to choose target
			Bandit target = null;
			if (!target.getLoot().isEmpty()) {
				// ask the player to choose 1 loot
				Loot l = null;
				target.removeLoot(l);
				this.currentBandit.addLoot(l);
			}

			// ask where to punch to
			TrainUnit tu = null;
			target.setPosition(tu);

		}

	}*/

	/**
     * @param c
     *           Card will be moved from bandit's hand to played pile and it's effect will be resolved
     *
     */
	public void playActionCard(ActionCard c){

	    //Remove card from bandit's hand
        this.currentBandit = c.getBelongsTo();
        this.currentBandit.removeHand(c);

        //Prompt playing face down
        if(currentBandit.getCharacter() == Character.GHOST && this.currentRound.getTurnCounter() == 0){
            //TODO: prompt choice;
        	//TODO: receive choice;
        }
        else if(this.currentRound.getCurrentTurn().getTurnType() == TurnType.TUNNEL) {
        	c.setFaceDown(true);
        }

        //Assign card to played pile
        PlayedPile pile = PlayedPile.getInstance();
        pile.addPlayedCards(c);
        //TODO: graphical response

    }

}
