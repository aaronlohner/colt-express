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
    
    boolean addPlayedCardsAt(int index, ActionCard a) {
        boolean contains = playedCards.contains(a);
        if (contains) {
            return false;
        }
        playedCards.add(index, a);
        return true;
    }

    boolean removePlayedCardsAt(int index) {
        ActionCard removedElement = playedCards.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    ActionCard getPlayedCardsAt(int index) {
        ActionCard associated = playedCards.get(index);
        return associated;
    }

    boolean addPlayedCards(ActionCard a) {
        boolean contains = playedCards.contains(a);
        if (contains) {
            return false;
        }
        boolean added = playedCards.add(a);
        return added;
    }

    boolean removePlayedCards(ActionCard a) {
        boolean removed = playedCards.remove(a);
        return removed;
    }

    boolean containsPlayedCards(ActionCard a) {
        boolean contains = playedCards.contains(a);
        return contains;
    }

    int sizeOfPlayedCards() {
        int size = playedCards.size();
        return size;
    }

    ArrayList<ActionCard> getPlayedCards() {
        return this.playedCards;
    }

    private PlayedPile() {
        /* TODO: No message view defined */
    }

    static GameManager getInstance() {
        /* TODO: No message view defined */
        return null;
    }
}
