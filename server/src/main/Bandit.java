package main;

import java.util.ArrayList;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class Bandit implements SerializableSFSType {
    
    protected boolean getsAnotherAction;
    protected boolean playedThisTurn;
    protected Character banditName;
    protected TrainUnit position;
    protected Hostage hostage;
    protected ArrayList<Loot> loot = new ArrayList<Loot>();
    protected ArrayList<ActionCard> hand = new ArrayList<ActionCard>();;
    protected ArrayList<ActionCard> discardPile = new ArrayList<ActionCard>();;
    
    public Bandit() { }
    
    public void setGetsAnotherAction(boolean anotherAction) {
        /* TODO: No message view defined */
    }

    public boolean getGetsAnotherAction() {
        /* TODO: No message view defined */
        return false;
    }

    public boolean getPlayedThisTurn() {
        /* TODO: No message view defined */
        return false;
    }

    public boolean setPlayedThisTurn(boolean played) {
        /* TODO: No message view defined */
        return false;
    }

    TrainUnit getPosition() {
        return this.position;
    }

    boolean setPosition(TrainUnit newObject) {
        this.position = newObject;
        return true;
    }

    boolean addLootAt(int index, Loot a) {
        boolean contains = loot.contains(a);
        if (contains) {
            return false;
        }
        loot.add(index, a);
        return true;
    }

    boolean removeLootAt(int index) {
        Loot removedElement = loot.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    Loot getLootAt(int index) {
        Loot associated = loot.get(index);
        return associated;
    }

    boolean addLoot(Loot a) {
        boolean contains = loot.contains(a);
        if (contains) {
            return false;
        }
        boolean added = loot.add(a);
        return added;
    }

    boolean removeLoot(Loot a) {
        boolean removed = loot.remove(a);
        return removed;
    }

    boolean containsLoot(Loot a) {
        boolean contains = loot.contains(a);
        return contains;
    }

    int sizeOfLoot() {
        int size = loot.size();
        return size;
    }

    ArrayList<Loot> getLoot() {
        return this.loot;
    }

    boolean addHandAt(int index, ActionCard a) {
        boolean contains = hand.contains(a);
        if (contains) {
            return false;
        }
        hand.add(index, a);
        return true;
    }

    boolean removeHandAt(int index) {
        ActionCard removedElement = hand.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    ActionCard getHandAt(int index) {
        ActionCard associated = hand.get(index);
        return associated;
    }

    boolean addHand(ActionCard a) {
        boolean contains = hand.contains(a);
        if (contains) {
            return false;
        }
        boolean added = hand.add(a);
        return added;
    }

    boolean removeHand(ActionCard a) {
        boolean removed = hand.remove(a);
        return removed;
    }

    boolean containsHand(ActionCard a) {
        boolean contains = hand.contains(a);
        return contains;
    }

    int sizeOfHand() {
        int size = hand.size();
        return size;
    }

    ArrayList<ActionCard> getHand() {
        return this.hand;
    }

    boolean addDiscardPileAt(int index, ActionCard a) {
        boolean contains = discardPile.contains(a);
        if (contains) {
            return false;
        }
        discardPile.add(index, a);
        return true;
    }

    boolean removeDiscardPileAt(int index) {
        ActionCard removedElement = discardPile.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    ActionCard getDiscardPileAt(int index) {
        ActionCard associated = discardPile.get(index);
        return associated;
    }

    boolean addDiscardPile(ActionCard a) {
        boolean contains = discardPile.contains(a);
        if (contains) {
            return false;
        }
        boolean added = discardPile.add(a);
        return added;
    }

    boolean removeDiscardPile(ActionCard a) {
        boolean removed = discardPile.remove(a);
        return removed;
    }

    boolean containsDiscardPile(ActionCard a) {
        boolean contains = discardPile.contains(a);
        return contains;
    }

    int sizeOfDiscardPile() {
        int size = discardPile.size();
        return size;
    }

    ArrayList<ActionCard> getDiscardPile() {
        return this.discardPile;
    }

    Hostage getHostage() {
        return this.hostage;
    }

    boolean setHostage(Hostage newObject) {
        this.hostage = newObject;
        return true;
    }
}
