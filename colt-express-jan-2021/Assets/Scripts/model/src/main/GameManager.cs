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

// STATIC FIELDS ARE NOT SERIALIZED
public class GameManager : SerializableSFSType
{

    public GameStatus status;
    public Round currentRound;
    public Bandit currentBandit;
    public ArrayList rounds = new ArrayList();
    public static GameManager instance;
    public static Marshal marshalInstance;
    public static PlayedPile playedPileInstance; 
    public ArrayList trainUnits = new ArrayList();
    public ArrayList bandits = new ArrayList();

    public GameManager() { }

}