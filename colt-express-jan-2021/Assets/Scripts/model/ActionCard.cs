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
    public class ActionCard : Card, SerializableSFSType
    {
       
        public string actionTypeAsString;
        public bool saveForNextRound;      
        public bool faceDown;
        
        // --EMPTY CONSTRUCTOR FOR SERIALIZATION--
        public ActionCard() {}
        
        public ActionCard(string action, string belongsTo) {
            this.actionTypeAsString = action;
            this.belongsToAsString = belongsTo;
            this.saveForNextRound = false;
            this.faceDown = false;
        }
        
        // actionTypeAsString
        public string getActionTypeAsString() {
            return this.actionTypeAsString;
        }
        
        public void setActionTypeAsString(string action) {
            this.actionTypeAsString = action;
        }
        
        // saveForNextRound
        public bool getSaveForNextRound() {
            return this.saveForNextRound;
        }
        
        public void setSaveForNextRound(bool b) {
            this.saveForNextRound = b;
        }
        
        // faceDown
        public bool getFaceDown() {
            return this.faceDown;
        }
        
        public void setFaceDown(bool b) {
            this.faceDown = b;
        }
    }
}
