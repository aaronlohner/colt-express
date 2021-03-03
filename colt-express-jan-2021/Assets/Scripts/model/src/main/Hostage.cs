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
    public class Hostage : SerializableSFSType
    {

        public string strHostageType; //public HostageType hostageType;
        public Bandit capturedBy;
        
        public Hostage() {
            
        }
        
        Bandit getCapturedBy() {
            return this.capturedBy;
        }
        
        bool setCapturedBy(Bandit newObject) {
            this.capturedBy = newObject;
            return true;
        }

    }
}
