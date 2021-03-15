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
	transient public static ArrayList<TrainUnit> stagecoach;
	transient public static ArrayList<TrainUnit> trainRoof;
	transient public static ArrayList<TrainUnit> trainCabin;
	
    transient public CarType carType;
    transient public CarFloor carFloor;
    public String carTypeAsString;
    public String carFloorAsString;
    
    public boolean isMarshalHere = false;
    //public HashSet<Bandit> banditsHere = new HashSet<Bandit>();
    public HashSet<Loot> lootHere = new HashSet<Loot>();
    public HashSet<Horse> horsesHere = new HashSet<Horse>();
    
    //--EMPTY CONSTRUCTOR FOR SERIALIZATION--
    public TrainUnit() {}
    
    private TrainUnit(CarType carType, CarFloor carFloor) {
    	this.carType = carType;
    	this.carFloor = carFloor;
    	this.carTypeAsString = carType.toString();
    	this.carFloorAsString = carFloor.toString();
    }
	
    
    /**
     * CREATE TRAIN METHODS
     * MUST BE CALLED IN THE FOLLOWING ORDER
     * 1. createTrainRoof(int numberOfBandits);
     * 2. createTrainCabin(int numberOfBandits);
     * 3. createStagecoach();
     */
    
    
	public static ArrayList<TrainUnit> createTrainRoof(int numberOfBandits) {
		if (numberOfBandits <= 1) {
			throw new IllegalArgumentException("Number of participating players must be between 2 and 6.");
		}
		GameManager gm = GameManager.getInstance();
		int i = 0; 
		ArrayList<TrainUnit> response = new ArrayList<TrainUnit>();
		for (CarType cr : CarType.values()) {
			i++;
			TrainUnit tu = new TrainUnit(cr, CarFloor.ROOF);
			response.add(tu);
			if (i == (numberOfBandits + 1)) { 
				break; 
			}
		}
		return response;
	}

	public static ArrayList<TrainUnit> createTrainCabin(int numberOfBandits) {
		if (numberOfBandits <= 1) {
			throw new IllegalArgumentException("Number of participating players must be between 2 and 6.");
		}
		GameManager gm = GameManager.getInstance();
		int i = 0; 
		ArrayList<TrainUnit> response = new ArrayList<TrainUnit>();
		for (CarType cr : CarType.values()) {
			i++;
			TrainUnit tu = new TrainUnit(cr, CarFloor.CABIN);
			response.add(tu);
			if (i == (numberOfBandits + 1)) { 
				break; 
			}
		}
		return response;
	}
    
    /**
     * 
     * @return an arraylist laid out in the following configuration:
     *           stagecoach[0] = roof
     *           stagecoach[1] = cabin;
     */
     public static ArrayList<TrainUnit> createStagecoach() {
    	 return new ArrayList<TrainUnit>();
     }


    
    /**
     * TRAIN UNIT METHODS
     */
    
    
    
    public static void moveStagecoach() { //TODO SHOTGUN MUST MOVE WITH STAGECOACH
    	return;
    	/*TrainUnit stagecoachRoof = TrainUnit.getStagecoach().get(0);
    	TrainUnit stagecoachCabin = TrainUnit.getStagecoach().get(1);
    	TrainUnit roofCurrentlyBeside = stagecoachRoof.getBeside();
    	TrainUnit cabinCurrentlyBeside = stagecoachCabin.getBeside();
    	
    	//IF NOT ALREADY BESIDE THE CABOOSE
    	if(roofCurrentlyBeside.getCarType() != CarType.CAR1 && cabinCurrentlyBeside.getCarType() != CarType.CAR1) {
    		roofCurrentlyBeside.setBeside(null);
    		cabinCurrentlyBeside.setBeside(null);
    		roofCurrentlyBeside.getLeft().setBeside(stagecoachRoof);
    		cabinCurrentlyBeside.getLeft().setBeside(stagecoachCabin);
    	}*/
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
    	GameManager gm = GameManager.getInstance();
    	gm.banditPositions.replace(b, this);
    }

    /**
     * 
     * @param b
     *           b is a non-null Bandit to be removed from this train car
     * @pre this train car must contain b
     */

    
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
