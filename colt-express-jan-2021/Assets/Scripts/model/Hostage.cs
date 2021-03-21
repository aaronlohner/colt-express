using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Sfs2X;
using Sfs2X.Logging;
using Sfs2X.Util;
using Sfs2X.Core;
using Sfs2X.Entities;
using Sfs2X.Entities.Data;
using Sfs2X.Protocol.Serialization;


//The following code is executed right after creating the SmartFox object:
// using System.Reflection;
//        DefaultSFSDataSerializer.RunningAssembly = Assembly.GetExecutingAssembly();
namespace model {
    public class Hostage : SerializableSFSType {
    
        public string hostageTypeAsString;
        public Bandit capturedBy = null;
        
        // --EMPTY CONSTRUCTOR FOR SERIALIZATION--
        public Hostage() {}
        
        public Hostage(string hostageTypeAsString){
            this.hostageTypeAsString = hostageTypeAsString;
        }
        
        // hostageTypeAsString
        public string getHostageTypeAsString() {
            return this.hostageTypeAsString;
        }
        
        public void setHostageTypeAsString(string hostage) {
            if(hostage.equals("POODLE") ||
            hostage.equals("MINISTER") ||
            hostage.equals("TEACHER") ||
            hostage.equals("ZEALOT") ||
            hostage.equals("OLDLADY") ||
            hostage.equals("POKERPLAYER") ||
            hostage.equals("PHOTOGRAPHER")){
                this.hostageTypeAsString = hostage;
            }
            else{
                Debug.Log("INVALID HOSTAGE TYPE SET");
            }
        }
        
        // capturedBy
        public Bandit getCapturedBy() {
            //return this.capturedBy.value;
            return this.capturedBy;
        }
        
        public void setCapturedBy(Bandit capturedBy) {
            if(this.capturedBy == null){
                this.capturedBy = capturedBy;
            }
            else{
                Debug.Log("THIS HOSTAGE HAS ALREADY BEEN CAPTURED BY A BANDIT");
            }
        }
    }
}
