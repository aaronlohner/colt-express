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
    public class Turn : SerializableSFSType {
    
        public TurnType turnType;
        
        public string turnTypeAsString;
        
        //  FOR NETWORKING
        // --EMPTY CONSTRUCTOR FOR SERIALIZATION--
        public Turn() {}
        
        // turnType
        public TurnType getTurnType() {
            return this.turnType;
        }
        
        public void setTurnType(TurnType p) {
            this.turnType = p;
        }
        
        // turnTypeAsString
        public string getTurnTypeAsString() {
            return this.turnTypeAsString;
        }
        
        public void setTurnTypeAsString(string s) {
            this.turnTypeAsString = s;
        }
    }
}