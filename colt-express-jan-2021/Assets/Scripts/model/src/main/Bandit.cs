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
public class Bandit : MonoBehaviour, SerializableSFSType
{

    public bool getsAnotherAction;
    public bool playedThisTurn;
    public Character banditName;
    public TrainUnit position;
    public Hostage hostage;
    public ArrayList loot = new ArrayList();
    public ArrayList hand = new ArrayList();
    public ArrayList discardPile = new ArrayList();

    public Bandit() { }

}
