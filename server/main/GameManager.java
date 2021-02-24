package main;

import java.util.ArrayList;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

// Start of user code for imports
// End of user code

/**
 * GameManager class definition.
 * Generated by the TouchCORE code generator.
 */
public class GameManager implements SerializableSFSType {
    
    protected static GameManager singleton;
    protected GameStatus gameStatus;
    protected Round currentRound;
    protected Bandit currentBandit;
    protected ArrayList<Round> rounds = new ArrayList<Round>();
    protected static GameManager instance;
    protected static Marshal marshalInstance;
    protected static PlayedPile playedPileInstance;
    protected ArrayList<TrainUnit> trainUnits = new ArrayList<TrainUnit>();
    protected ArrayList<Bandit> bandits = new ArrayList<Bandit>();
    
    public GameManager() { }
    
    public Round getCurrentRound() {
        return this.currentRound;
    }

    public void setCurrentRound(Round newObject) {
        this.currentRound = newObject;
    }

    public Round getRoundAt(index){
        return this.rounds.get(index);
    }

    public void addRound(Round a) {
        int size = rounds.size();
        /*if (size == maximum) {
            return false;
        }*/
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

    public void setGameStatus(GameStatus newStatus) {
        this.gameStatus = newStatus;
    }

    public void getGameStatus(){
        return this.gameStatus;
    }

    public void addTrainUnitsAt(int index, TrainUnit a) {
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
        if (this.trainUnits.contains(a)){
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
    }

    public Bandit getCurrentBandit() {
        return this.currentBandit;
    }

    public void setCurrentBandit(Bandit newObject) {
        this.currentBandit = newObject;
    }

    /*boolean addBanditsAt(int index, Bandit a) {
        int size = bandits.size();
        if (size == maximum) {
            return false;
        }
        bandits.add(index, a);
        return true;
    }*/

    public void removeBanditsAt(int index) {
        if (this.bandits.size()) >= index {
            this.bandits.remove(index);
        }
    }

    public Bandit getBanditsAt(int index) {
        if (this.bandits.size() >= index) {
            return this.bandits.get(index);
        }
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

    ArrayList<Bandit> getBandits() {
        return this.bandits;
    }

    public static GameManager getInstance() {
        if (singleton == null) {
            singleton = new GameManager();
        }
        return singleton;
    }
}
