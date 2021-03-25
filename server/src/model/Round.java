package model;

import java.util.ArrayList;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

// Start of user code for imports
// End of user code

/**
 * Round class definition.
 * Generated by the TouchCORE code generator.
 */
public class Round implements SerializableSFSType {
    
	transient public RoundType roundType;
	
	//= round.toString(); -- Not sure if this will work, may have to be done assigned after round is assigned
	public String roundTypeAsString; //FOR NETWORKING
	
	public Turn currentTurn;
	public int turnCounter = 1; //Tracks the current turn
	public ArrayList<Turn> turns = new ArrayList<Turn>();
    

	//--EMPTY CONSTRUCTOR FOR SERIALIZATION--
    public Round() {  }
    
    public Round(RoundType Rt) {
    	this.roundType = Rt;
    	this.roundTypeAsString = Rt.toString();
    }
    
    public void addTurn(Turn a) {
        if (this.turns.contains(a)){
            return;
        }
        this.turns.add(a);
    }

    public void addTurnsAt(int index, Turn a) {
        this.turns.add(index, a);
    }

    public void removeTurnsAt(int index) {
        int size = this.turns.size();
        if (index<size) {
            this.turns.remove(index);
        }
    }

    public Turn getTurnAt(int index) {
		if (index < this.turns.size()) {
			return this.turns.get(index);
		}
		return null;
	}

    public void removeTurn(Turn a) {
        if (this.turns.contains(a)){
            this.turns.remove(a);
        }
    }

    public boolean containsTurns(Turn a) {
        boolean contains = this.turns.contains(a);
        return contains;
    }

    public int sizeOfTurns() {
        int size = this.turns.size();
        return size;
    }

    public ArrayList<Turn> getTurns() {
        return this.turns;
    }

    public Turn getCurrentTurn() {
        return this.currentTurn;
    }

    public void setCurrentTurn(Turn newObject) {
        this.currentTurn = newObject;
    }
    
    
    public boolean hasNextTurn() {
    	if(turnCounter < turns.size()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public Turn getNextTurn() {
        return turns.get(turnCounter+1);
    }
    
    public void setNextTurn() {
    	this.turnCounter++;
    	this.currentTurn = this.turns.get(turnCounter);
    }
    
    
    public int getTurnCounter() {
    	return this.turnCounter;
    }
    //SHOULD NOT BE USED, AUTO INCREMENTED WITH SETNEXTTURN
    public void setTurnCounter(int i) {
    	this.turnCounter = i;
    }
    
}
