package model;

import java.util.Optional;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import java.util.ArrayList;

/**
 * Horse class definition.
 * Generated by the TouchCORE code generator.
 */
public class Horse implements SerializableSFSType {
	
	public TrainUnit adjacentTo;
	public Bandit riddenBy;
	
	//--EMPTY CONSTRUCTOR FOR SERIALIZATION--
    public Horse() { }
    
    public Horse(Bandit b) {
    	GameManager gm = GameManager.getInstance();
       	this.adjacentTo = gm.getTrainCabinAt(gm.trainLength-1);
    	this.riddenBy = b;
    }
    
    /**
     * --GETTERS AND SETTERS--
     */
    
    //adjacentTo
    public TrainUnit getAdjacentTo() {
    	return this.adjacentTo;
    }
    public void setAdjacentTo(TrainUnit adjacentTo) {
    	this.adjacentTo = adjacentTo;
    }
    
    //riddenBy
    public Bandit getRiddenBy() {
    	return this.riddenBy;
    }
    public void setRiddenBy(Bandit b) {
    	this.riddenBy = b;
    }
    
    public static ArrayList<Horse> createHorses(){
    	GameManager gm = GameManager.getInstance();
    	final int horsesToCreate = gm.getNumOfPlayers();
    	ArrayList<Horse> response = new ArrayList<Horse>();
    	for(int i=0; i<horsesToCreate; i++) {
    		Horse horse = new Horse(gm.getBandits().get(i));
    		response.add(horse);
    	}
    	return response;
    }
    
}
