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

namespace model {
    public abstract class Card : SerializableSFSType {
      
        //public Bandit belongsTo;

        public Card() {}
        
        //queries each bandit's 10 cards to find object match (a bit excessive but it's the only way to get rid of reference from here)
       public Bandit getBelongsTo() {
           GameManager gm = GameManager.getInstance();
           foreach (Bandit b in gm.bandits) {
               Arraylist cards = b.toResolve.Clone();
               cards.AddRange(b.deck.Clone());
               cards.AddRange(b.hand.Clone());
               cards.AddRange(b.discardPile.Clone());
               foreach(Card c in cards) {
                   if c.Equals(this);
                   return b;
               }
           }
       }
    }
}