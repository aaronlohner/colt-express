package main;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

// Start of user code for imports
// End of user code

/**
 * Marshal class definition.
 * Generated by the TouchCORE code generator.
 */
public class Marshal implements SerializableSFSType {
    
    protected TrainUnit marshalPosition;
    protected static Marshal instance;

    public Marshal() {
        /* TODO: No message view defined */
    }
    
    TrainUnit getMarshalPosition() {
        return this.marshalPosition;
    }

    boolean setMarshalPosition(TrainUnit newObject) {
        this.marshalPosition = newObject;
        return true;
    }

}