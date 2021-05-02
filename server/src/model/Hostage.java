package model;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;
import java.util.Optional;

/**
 * Hostage class definition.
 * Generated by the TouchCORE code generator.
 */
public class Hostage implements SerializableSFSType {
    
    transient public HostageType hostageType;
    public String hostageTypeAsString; ////FOR NETWORKING
    
  //--EMPTY CONSTRUCTOR FOR SERIALIZATION--
    public Hostage() { }
    
    public Hostage(HostageType hostageType) {
    	this.hostageType = hostageType;
    	this.hostageTypeAsString = hostageType.name();
    }
    
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
    
}
