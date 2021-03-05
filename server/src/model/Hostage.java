package model;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import java.util.Optional;

// Start of user code for imports
// End of user code

/**
 * Hostage class definition.
 * Generated by the TouchCORE code generator.
 */
public class Hostage implements SerializableSFSType {
    
    transient public HostageType hostageType;
    public String hostageTypeAsString; ////FOR NETWORKING
    public Optional<Bandit> capturedBy;
    
  //--EMPTY CONSTRUCTOR FOR SERIALIZATION--
    public Hostage() { }
    
    /**
     * --GETTERS AND SETTERS--
     */
    
    //hostageType
    public HostageType getHostageType(){
        return this.hostageType;
    }

    public void setHostageType(HostageType hostage) {
        this.hostageType=hostage;
    }

    //hostageTypeAsString
    public String getHostageTypeAsString() {
    	return this.hostageTypeAsString;
    }
    public void setHostageTypeAsString(String hostage) {
    	this.hostageTypeAsString = hostage;
    }
    
    //capturedBy
    public Bandit getCapturedBy() {
        return this.capturedBy.get();
    }

    public void setCapturedBy(Bandit capturedBy) {
        this.capturedBy = Optional.ofNullable(capturedBy);
    }
    
}
