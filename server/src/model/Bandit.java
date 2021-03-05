package model;

import java.util.ArrayList;

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
	public Hostage hostage;
	public ArrayList<Loot> loot = new ArrayList<Loot>();
	public ArrayList<BulletCard> bullets = new ArrayList<BulletCard>();
	public ArrayList<Card> deck = new ArrayList<Card>();
	public ArrayList<Card> hand = new ArrayList<Card>();
	public ArrayList<Card> discardPile = new ArrayList<Card>();

	//--EMPTY CONSTRUCTOR FOR SERIALIZATION--
	public Bandit() { }
	
	public Bandit(Character c) {
		this.banditName = c;
		this.strBanditName = c.toString();
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

	public void removeDeckAt(int index) {
		if (this.deck.size() > index) {
			this.deck.remove(index);
		}
	}

	public Card getDeckAt(int index) {
		if (this.deck.size() > index) {
			return this.deck.get(index);
		}
		return null;
	}

	public void addDeck(Card a) {
		boolean contains = this.deck.contains(a);
		if (contains) {
			return;
		}
		this.deck.add(a);
	}

	public void removeDeck(Card a) {
		if (this.deck.contains(a)) {
			this.deck.remove(a);
		}
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
		boolean contains = this.hand.contains(a);
		if (contains) {
			return;
		}
		this.hand.add(a);
	}

	public void removeHand(Card a) {
		if (this.hand.contains(a)) {
			this.hand.remove(a);
		}
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

	public void setHostage(Hostage newObject) {
		this.hostage = newObject;
	}

	public void createStartingCards() {

		ActionCard acMove1 = new ActionCard(ActionKind.MOVE);
		ActionCard acMove2 = new ActionCard(ActionKind.MOVE);
		ActionCard acChangeFloor1 = new ActionCard(ActionKind.CHANGEFLOOR);
		ActionCard acChangeFloor2 = new ActionCard(ActionKind.CHANGEFLOOR);
		ActionCard acMarshal = new ActionCard(ActionKind.MARSHAL);
		ActionCard acPunch = new ActionCard(ActionKind.PUNCH);
		ActionCard acRob1 = new ActionCard(ActionKind.ROB);
		ActionCard acRob2 = new ActionCard(ActionKind.ROB);
		ActionCard acShoot1 = new ActionCard(ActionKind.SHOOT);
		ActionCard acShoot2 = new ActionCard(ActionKind.SHOOT);

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

	public void createStartingPurse() {
		Money startingPurse = new Money(MoneyType.PURSE, 250);
		this.loot.add(startingPurse);
	}

}
