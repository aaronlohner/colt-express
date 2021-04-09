package model;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

// Start of user code for imports
// End of user code

/**
 * Money class definition.
 * Generated by the TouchCORE code generator.
 */
public class Money extends Loot implements SerializableSFSType {
    
    public int value;
    transient public MoneyType moneyType;
    public String moneyTypeAsString; //FOR NETWORKING
    
  //--EMPTY CONSTRUCTOR FOR SERIALIZATION--
    public Money() { }
    
	public Money(MoneyType mt, int value) {
		this.moneyType = mt;
		this.value = value;
	}
    
	/**
	 * 18 purses worth:
	 * 2x $500
	 * 2x $450
	 * 2x $400
	 * 2x $350
	 * 2x $300
	 * 8x $250
	 * 
	 * 6 jewels worth:
	 * 6x $500
	 * 
	 * 2 strongboxes worth:
	 * 2x $1000
	 */
	
	/**
	 * --GETTERS AND SETTERS--
	 */
	
	//value
    public void setValue(int v){
        this.value=v;
    }
    public int getValue(){
        return this.value;
    }

    //moneyType
    public void setMoneyType(MoneyType p){
        this.moneyType = p;
    }
    public MoneyType getMoneyType(){
        return this.moneyType;
    }
    
    //moneyTypeAsString
    public String getMoneyTypeAsString() {
    	return this.moneyTypeAsString;
    }
    public void setMoneyTypeAsString(String s) {
    	this.moneyTypeAsString = s;
    }
}
