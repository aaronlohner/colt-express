package model;

import java.util.ArrayList;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;


/**
 * Round class definition.
 * Generated by the TouchCORE code generator.
 */
public class Round implements SerializableSFSType {
    
	transient public RoundType roundType;
	
	public String roundTypeAsString;
	
	public Turn currentTurn;
	public int turnCounter = 0; //Tracks the current turn
	public ArrayList<Turn> turns = new ArrayList<Turn>();
    

	//--EMPTY CONSTRUCTOR FOR SERIALIZATION--
    public Round() {  }
    
    public Round(RoundType rt) {
        int numOfBandits = GameManager.getInstance().bandits.size();
        assert numOfBandits >= 2 && numOfBandits <= 6;
        this.roundType = rt;
	    this.roundTypeAsString = rt.name();
        this.turnCounter = 0;
        this.turns = new ArrayList<Turn>();
        if(rt == RoundType.AngryMarshal && numOfBandits <= 4){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
            turns.add(new Turn(TurnType.SWITCHING));
        }
        else if(rt == RoundType.SwivelArm && numOfBandits <= 4){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.STANDARD));
        }
        else if(rt == RoundType.Braking && numOfBandits <= 4){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.STANDARD));
        }
        else if(rt == RoundType.TakeItAll && numOfBandits <= 4){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
            turns.add(new Turn(TurnType.SPEEDINGUP));
            turns.add(new Turn(TurnType.STANDARD));
        }
        else if(rt == RoundType.PassengersRebellion && numOfBandits <= 4){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.STANDARD));
        }
        else if(rt == RoundType.Bridge && numOfBandits <= 4){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.SPEEDINGUP));
            turns.add(new Turn(TurnType.STANDARD));
        }
        else if(rt == RoundType.Cave && numOfBandits <= 4){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
            turns.add(new Turn(TurnType.STANDARD));
        }
        else if(rt == RoundType.AngryMarshal){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.SWITCHING));
        }
        else if(rt == RoundType.SwivelArm){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
            turns.add(new Turn(TurnType.STANDARD));
        }
        else if(rt == RoundType.Braking){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.STANDARD));
        }
        else if(rt == RoundType.TakeItAll){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.SPEEDINGUP));
            turns.add(new Turn(TurnType.SWITCHING));
        }
        else if(rt == RoundType.PassengersRebellion){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.SWITCHING));
        }   
        else if(rt == RoundType.Bridge){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.SPEEDINGUP));
        }
        else if(rt == RoundType.Cave){
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
        }
        if(rt == RoundType.MarshalsRevenge) {
        	turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.STANDARD));
            turns.add(new Turn(TurnType.TUNNEL));
            turns.add(new Turn(TurnType.STANDARD));
        }
        this.currentTurn = turns.get(0);
        System.out.println("turn: " + currentTurn.turnTypeAsString);
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
