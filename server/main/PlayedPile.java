package main;

import java.util.ArrayList;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

// Start of user code for imports
// End of user code

/**
 * PlayedPile class definition.
 * Generated by the TouchCORE code generator.
 */
public class PlayedPile implements SerializableSFSType {
    
    protected static PlayedPile instance;
    protected ArrayList<ActionCard> playedCards = new ArrayList<ActionCard>();
    
    public PlayedPile() { }
    
    public void addPlayedCardsAt(int index, ActionCard a) {
        boolean contains = playedCards.contains(a);
        if (contains) {
            return;
        }
        this.playedCards.add(index, a);
    }

    public void removePlayedCardsAt(int index) {
        if (this.playedCards.size() > index){
            playedCards.remove(index);
        }
    }

    public ActionCard getPlayedCardsAt(int index) {
        if (this.playedCards.size() > index) {
            return this.playedCards.get(index);
        }
    }

    public void addPlayedCards(ActionCard a) {
        boolean contains = this.playedCards.contains(a);
        if (contains) {
            return;
        }
        this.playedCards.add(a);
    }

    public void removePlayedCards(ActionCard a) {
        if (this.playedCards.contains(a)){
            playedCards.remove(a);
        }
    }

    public boolean containsPlayedCards(ActionCard a) {
        boolean contains = this.playedCards.contains(a);
        return contains;
    }

    public int sizeOfPlayedCards() {
        int size = this.playedCards.size();
        return size;
    }

    public ArrayList<ActionCard> getPlayedCards() {
        return this.playedCards;
    }

    public static PlayedPile getInstance() {
		if (instance == null) {
			instance = new PlayedPile();
		}
		return instance;
	}
}
