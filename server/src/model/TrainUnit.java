package model;

import java.util.ArrayList;
import java.util.HashSet;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

import model.Bandit;

// Start of user code for imports
// End of user code

/**
 * TrainUnit class definition.
 * Generated by the TouchCORE code generator.
 */
public class TrainUnit implements SerializableSFSType {
    
	transient public static int trainLength;
	transient public static TrainUnit[][] train;
	transient public static TrainUnit[] stagecoach;
	
    public CarType carType;
    public String carTypeAsString;
    
    public TrainUnit above = null;
    public TrainUnit below = null;
    public TrainUnit left = null;
    public TrainUnit right = null;
    public TrainUnit beside = null; //Used for stagecoach and it's adjacent car ONLY.
    
    public boolean isMarshalHere = false;
    public HashSet<Bandit> banditsHere = new HashSet<Bandit>();
    public HashSet<Loot> lootHere = new HashSet<Loot>();
    public HashSet<Horse> horsesHere = new HashSet<Horse>();
    
    //--EMPTY CONSTRUCTOR FOR SERIALIZATION--
    public TrainUnit() {}
    
    private TrainUnit(CarType carType) {
    	this.carType = carType;
    	//TODO: createGraphic()
    }
    
    /**
     * 
     * @param numberOfBandits
     *           the number of train cars to create is based on the number of bandits playing the game
     * @return a 2D array laid out in the following configuration:
     *           train[0][i] = roof units
     *           train[1][i] = cabin units
     *           where i=0 is the caboose and i=number of cars is the locomotive
     * Does NOT contain the stagecoach. The GameManger must separately call createStagecoach().
     */
    public static TrainUnit[][] createTrain(int numberOfBandits){
    	//Create one car for each player, +1 to account for the locomotive
    	final int trainLength = numberOfBandits + 1;
    	
    	TrainUnit[][] train = new TrainUnit[2][trainLength];
    	TrainUnit locoCabin = new TrainUnit(CarType.LocomotiveCabin);
    	TrainUnit locoRoof = new TrainUnit(CarType.LocomotiveRoof);
    	
    	//Create locomotive
    	locoCabin.above = locoRoof;
    	locoCabin.isMarshalHere = true;
    	locoRoof.below = locoCabin;
    	
    	//TODO: Add locomotive to array
    	
    	//TODO: Create rest of the cars, associate with each other, add to array
    	
    	//TODO: Associate locomotive to front car
    	
    	TrainUnit.trainLength = trainLength;
    	TrainUnit.train = train;
    	
    	return train;
    }
    
    /**
     * 
     * @return a 2D array laid out in the following configuration:
     *           stagecoach[0] = roof
     *           stagecoach[1] = cabin;
     */
    public static TrainUnit[] createStagecoach() {
    	TrainUnit[] stagecoach = new TrainUnit[2];
    	
    	TrainUnit cabin = new TrainUnit(CarType.StagecoachCabin);
    	TrainUnit roof = new TrainUnit(CarType.StagecoachRoof);
    	
    	stagecoach[0] = roof;
    	stagecoach[1] = cabin;
    	
    	return stagecoach;
    }


    
    /**
     * TRAIN UNIT METHODS
     */
    
    //trainLength
    public static int getTrainLength() {
    	return TrainUnit.trainLength;
    }
    public static void setTrainLength(int length) {
    	TrainUnit.trainLength = length;
    }
    
    //train
    public static TrainUnit[][] getTrain(){
    	return TrainUnit.train;
    }
    
    //stagecoach
    public static TrainUnit[] getStagecoach() {
    	return TrainUnit.stagecoach;
    }
    
    public TrainUnit getAbove() {
        return this.above;
    }
    public void setAbove(TrainUnit otherTrainUnit) {
    	this.above = otherTrainUnit;
    	otherTrainUnit.below = this;
    }
   
    public TrainUnit getBelow() {
        return this.below;
    }
    public void setBelow(TrainUnit otherTrainUnit) {
    	this.below = otherTrainUnit;
    	otherTrainUnit.above = this;
    }
    
    public TrainUnit getRight() {
        return this.right;
    }
    public void setRight(TrainUnit otherTrainUnit) {
    	this.right = otherTrainUnit;
    	otherTrainUnit.left = this;
    }

    public TrainUnit getLeft() {
        return this.left;
    }
    public void setLeft(TrainUnit otherTrainUnit) {
    	this.left = otherTrainUnit;
    	otherTrainUnit.right = this;
    }

    public TrainUnit getBeside() {
        return this.beside;
    }
    
    public boolean isAdjacentTo(TrainUnit otherTrainUnit) {
        boolean adjacentTo = false;
        if(this.below == otherTrainUnit && otherTrainUnit.above == this) {
        	adjacentTo = true;
        }
        else if(this.above == otherTrainUnit && otherTrainUnit.below == this) {
        	adjacentTo = true;
        }
        else if(this.right == otherTrainUnit && otherTrainUnit.left == this) {
        	adjacentTo = true;
        }
        else if(this.left == otherTrainUnit && otherTrainUnit.right == this) {
        	adjacentTo = true;
        }
        else if(this.beside == otherTrainUnit && otherTrainUnit.beside == this) {
        	adjacentTo = true;
        }
        return adjacentTo;
    }

    
    
    /**
     * BANDIT METHODS
     */
    
    
    /**
     * 
     * @param b
     *           b is a non-null Bandit to be added to this train car
     * @pre this train car must not already contain b
     */
    public void addBandit(Bandit b) {
    	assert !this.banditsHere.contains(b);
        this.banditsHere.add(b);
    }

    /**
     * 
     * @param b
     *           b is a non-null Bandit to be removed from this train car
     * @pre this train car must contain b
     */
    public void removeBandit(Bandit b) {
    	assert banditsHere.contains(b);
    	this.banditsHere.remove(b);
    }

    public boolean containsBandit(Bandit b) {
        return this.banditsHere.contains(b);
    }

    public int numOfBanditsHere() {
        return this.banditsHere.size();
    }

    public HashSet<Bandit> getBanditsHere() {
        return (HashSet<Bandit>) this.banditsHere.clone();
    }

    
    /**
     * LOOT METHODS
     */
    
    
    /**
     * 
     * @param a
     */
    public void addLoot(Loot a) {
        //TODO
    }

    public void removeLoot(Loot a) {
        //TODO
    }

    boolean containsLoot(Loot a) {
        return this.lootHere.contains(a);
    }

    int numOfLootHere() {
        return this.lootHere.size();
    }

    /*public HashSet<Loot> getLootHere() {
        return this.lootHere.clone();
    }*/
    
    
    /**
     * MARSHAL METHODS
     */
    
    
    public boolean getIsMarshalHere() {
        return this.isMarshalHere;
    }

    public void setIsMarshalHere(boolean b) {
        this.isMarshalHere = b;
    }
    public void moveMarshalTo(TrainUnit dest) {
    	this.isMarshalHere = false;
    	dest.isMarshalHere = true;
    }

    /**
     * HORSE METHODS, (HAVE NOT BEEN CHECKED)
     */
    
    /*
    public boolean addHorsesAt(int index, Horse a) {
        boolean contains = horses.contains(a);
        if (contains) {
            return false;
        }
        horses.add(index, a);
        return true;
    }

    public boolean removeHorsesAt(int index) {
        Horse removedElement = horses.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    Horse getHorsesAt(int index) {
        Horse associated = horses.get(index);
        return associated;
    }

    boolean addHorses(Horse a) {
        boolean contains = horses.contains(a);
        if (contains) {
            return false;
        }
        boolean added = horses.add(a);
        return added;
    }

    boolean removeHorse(Horse a) {
        boolean removed = horses.remove(a);
        return removed;
    }

    boolean containsHorse(Horse a) {
        boolean contains = horses.contains(a);
        return contains;
    }

    int sizeOfHorses() {
        int size = horses.size();
        return size;
    }

    ArrayList<Horse> getHorses() {
        return this.horses;
    }*/
}
