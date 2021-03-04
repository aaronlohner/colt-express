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
    public class Whiskey : SerializableSFSType
    {

        public string strWhiskeyStatus; //public WhiskeyStatus whiskeyStatus;
        public string strWhiskeyType; //public WhiskeyType whiskeyType;

        public Whiskey() { }

    }
}