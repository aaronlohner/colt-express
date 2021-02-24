package main;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

// Start of user code for imports
// End of user code

/**
 * Money class definition.
 * Generated by the TouchCORE code generator.
 */
public class Money extends Loot implements SerializableSFSType {
    
    protected int value;
    protected MoneyType moneyType;
    
    public Money() { }

    public setValue(int v){
        this.value=v;
    }

    public getValue(){
        return this.value;
    }

    public setMoneyType(MoneyType p){
        this.moneyType = p;
    }

    public getMoneyType(){
        return this.moneyType;
    }
    
}
