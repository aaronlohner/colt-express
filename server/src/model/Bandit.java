package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

// Start of user code for imports
// End of user code

/**
 * Bandit class definition. Generated by the TouchCORE code generator.
 */
public class Bandit implements SerializableSFSType {

	public boolean getsAnotherAction;
	public boolean playedThisTurn;
	transient public Character banditName;
	public String banditNameAsString; //FOR NETWORKING
	public TrainUnit position;
	transient public Hostage hostage;
	public String hostageAsString; //FOR NETWORKING
	public ArrayList<Loot> loot = new ArrayList<Loot>();
	public ArrayList<BulletCard> bullets = new ArrayList<BulletCard>();
	public ArrayList<Card> deck = new ArrayList<Card>(); //CONVENTION FOR DECK: POSITION DECK.SIZE() IS TOP OF DECK, POSITION 0 IS BOTTOM OF DECK
	public ArrayList<Card> hand = new ArrayList<Card>();
	public ArrayList<Card> discardPile = new ArrayList<Card>();
	public ActionCard toResolve = null;

	//--EMPTY CONSTRUCTOR FOR SERIALIZATION--
	public Bandit() { }
	
	public Bandit(Character c) {
		this.banditName = c;
		this.banditNameAsString = c.toString();
		this.getsAnotherAction = false;
		this.playedThisTurn = false;
		this.position = null;
		this.hostage = null;
	}

	public Character getCharacter(){
		return this.banditName;
	}

	public void setGetsAnotherAction(boolean anotherAction) {
		this.getsAnotherAction = anotherAction;
	}

	public boolean getGetsAnotherAction() {
		return this.getsAnotherAction;
	}

	public boolean getPlayedThisTurn() {
		return this.playedThisTurn;
	}

	public void setPlayedThisTurn(boolean played) {
		this.playedThisTurn = played;
	}

	public TrainUnit getPosition() {
		return this.position;
	}

	public void setPosition(TrainUnit newObject) {
		this.position = newObject;
	}

	public boolean addLootAt(int index, Loot a) {
		boolean contains = loot.contains(a);
		if (contains) {
			return false;
		}
		loot.add(index, a);
		return true;
	}

	public void removeLootAt(int index) {
		if (this.loot.size() > index) {
			this.loot.remove(index);
		}
	}

	public Loot getLootAt(int index) {
		if (this.loot.size() > index){
			Loot a = this.loot.get(index);
			return a;
		}
		return null;
	}

	public void addLoot(Loot a) {
		boolean contains = this.loot.contains(a);
		if (contains) {
			return;
		}
		this.loot.add(a);
	}

	public void removeLoot(Loot a) {
		if (this.loot.contains(a)) {
			loot.remove(a);
		}
	}

	public boolean containsLoot(Loot a) {
		boolean contains = this.loot.contains(a);
		return contains;
	}

	public int sizeOfLoot() {
		int size = this.loot.size();
		return size;
	}

	public ArrayList<Loot> getLoot() {
		return this.loot;
	}

	public void addDeckAt(int index, Card a) {
		boolean contains = this.deck.contains(a);
		if (contains) {
			return;
		}
		this.deck.add(index, a);
	}

	public Card removeDeckAt(int index) {
		assert this.deck.size() > index;
		return this.deck.remove(index);
	}

	public Card getDeckAt(int index) {
		if (this.deck.size() > index) {
			return this.deck.get(index);
		}
		return null;
	}

	public void addDeck(Card a) {
		this.deck.add(a);
	}

	public void removeDeck(Card a) {
		this.deck.remove(a);
	}

	public boolean containsDeck(Card a) {
		boolean contains = this.deck.contains(a);
		return contains;
	}

	public int sizeOfDeck() {
		int size = this.deck.size();
		return size;
	}

	public ArrayList<Card> getDeck() {
		return this.deck;
	}

	public void addHandAt(int index, Card a) {
		boolean contains = hand.contains(a);
		if (contains) {
			return;
		}
		hand.add(index, a);
	}

	public void removeHandAt(int index) {
		if (this.hand.size() > index){
			this.hand.remove(index);
		}
	}

	public Card getHandAt(int index) {
		if (this.hand.size() > index) {
			Card a = this.hand.get(index);
			return a;
		}
		return null;
	}

	public void addHand(Card a) {
		this.hand.add(a);
	}

	public void removeHand(Card a) {
		this.hand.remove(a);
	}

	public boolean containsHand(Card a) {
		boolean contains = this.hand.contains(a);
		return contains;
	}

	public int sizeOfHand() {
		int size = this.hand.size();
		return size;
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public void addDiscardPileAt(int index, Card a) {
		boolean contains = this.discardPile.contains(a);
		if (contains) {
			return;
		}
		this.discardPile.add(index, a);
	}

	public void removeDiscardPileAt(int index) {
		if (this.discardPile.size() > index) {
			discardPile.remove(index);
		}
	}

	public Card getDiscardPileAt(int index) {
		if (this.discardPile.size() > index){
			Card associated = discardPile.get(index);
			return associated;
		}
		else {
			return null;
		}
	}

	public void addDiscardPile(Card a) {
		boolean contains = this.discardPile.contains(a);
		if (!contains) {
			this.discardPile.add(a);
		}
	}

	public void removeDiscardPile(Card a) {
		if (this.discardPile.contains(a)){
			this.discardPile.remove(a);
		}
	}

	public boolean containsDiscardPile(Card a) {
		boolean contains = this.discardPile.contains(a);
		return contains;
	}

	public int sizeOfDiscardPile() {
		int size = this.discardPile.size();
		return size;
	}

	public ArrayList<Card> getDiscardPile() {
		return this.discardPile;
	}

	public Hostage getHostage() {
		return this.hostage;
	}

	public void setHostage(Hostage hostage) {
		this.hostage = hostage;
	}
	
	public String getHostageAsString() {
		return this.hostageAsString;
	}
	public void setHostageAsString(String hostage) {
		this.hostageAsString = hostage;
	}
	
	public ActionCard getToResolve() {
		return this.toResolve;
	}
	public void setToResolve(ActionCard ac) {
		this.toResolve = ac;
	}

	public void createStartingCards() {

		ActionCard acMove1 = new ActionCard(ActionType.MOVE);
		ActionCard acMove2 = new ActionCard(ActionType.MOVE);
		ActionCard acChangeFloor1 = new ActionCard(ActionType.CHANGEFLOOR);
		ActionCard acChangeFloor2 = new ActionCard(ActionType.CHANGEFLOOR);
		ActionCard acMarshal = new ActionCard(ActionType.MARSHAL);
		ActionCard acPunch = new ActionCard(ActionType.PUNCH);
		ActionCard acRob1 = new ActionCard(ActionType.ROB);
		ActionCard acRob2 = new ActionCard(ActionType.ROB);
		ActionCard acShoot1 = new ActionCard(ActionType.SHOOT);
		ActionCard acShoot2 = new ActionCard(ActionType.SHOOT);

		this.deck.add(acMove1);
		this.deck.add(acMove2);
		this.deck.add(acChangeFloor1);
		this.deck.add(acChangeFloor2);
		this.deck.add(acMarshal);
		this.deck.add(acPunch);
		this.deck.add(acRob1);
		this.deck.add(acRob2);
		this.deck.add(acShoot1);
		this.deck.add(acShoot2);

		for (Card c : this.deck) {
			c.setBelongsTo(this);
		}

	}
	
	public void createHand() {
		Collections.shuffle(this.deck, new Random(System.currentTimeMillis()));
		for (int i =0; i<6; i++) {
			Card c = this.deck.get(0);
			this.hand.add(c);
			//this.deck.remove(c);
		}
	}

	public void createBulletCards() {

		BulletCard bc1 = new BulletCard();
		BulletCard bc2 = new BulletCard();
		BulletCard bc3 = new BulletCard();
		BulletCard bc4 = new BulletCard();
		BulletCard bc5 = new BulletCard();
		BulletCard bc6 = new BulletCard();

		this.bullets.add(bc1);
		this.bullets.add(bc2);
		this.bullets.add(bc3);
		this.bullets.add(bc4);
		this.bullets.add(bc5);
		this.bullets.add(bc6);

		for (BulletCard bc : this.bullets) {
			bc.setBelongsTo(this);
		}

	}

	public BulletCard removeTopBullet() {
		return this.bullets.remove(this.bullets.size()-1);
	}
	
	public boolean bulletsIsEmpty() {
		return this.bullets.isEmpty();
	}
	
	public void createStartingPurse() {
		Money startingPurse = new Money(MoneyType.PURSE, 250);
		this.loot.add(startingPurse);
	}

}
