package main;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public abstract class Card implements SerializableSFSType {
	
	protected Bandit belongsTo;
	
	// NEEDED FOR SERIALIZATION
	public Card() { }
	
    Bandit getBelongsTo() {
        return this.belongsTo;
    }

    boolean setBelongsTo(Bandit newObject) {
        this.belongsTo = newObject;
        return true;
    }
}