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
    public class Whiskey : Loot, SerializableSFSType {
    
        public string whiskeyType;
        //public string whiskeyTypeAsString;
        
        // FOR NETWORKING
        public string whiskeyStatus;
        //public string whiskeyStatusAsString;
        
        // FOR NETWORKING
        // --EMPTY CONSTRUCTOR FOR SERIALIZATION--
        public Whiskey() {}
        
        // whiskeyType
        public string getWhiskeyType() {
            return this.whiskeyType;
        }
        
        public void setWhiskeyType(string p) {
            this.whiskeyType = p;
        }
        
        // // whiskeyTypeAsString
        // public string getWhiskeyTypeAsString() {
        //     return this.whiskeyTypeAsString;
        // }
        
        // public void setWhiskeyTypeAsString(string s) {
        //     this.whiskeyTypeAsString = s;
        // }
        
        // whiskeyStatus
        public string getWhiskeyStatus() {
            return this.whiskeyStatus;
        }
        
        public void setWhiskeyStatus(string p) {
            this.whiskeyStatus = p;
        }
        
        // // whiskeyStatusAsString
        // public string getWhiskeyStatusAsString() {
        //     return this.whiskeyStatusAsString;
        // }
        
        // public void setWhiskeyStatusAsString(string s) {
        //     this.whiskeyStatusAsString = s;
        // }
    }
}