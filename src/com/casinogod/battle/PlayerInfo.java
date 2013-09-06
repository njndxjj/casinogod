package com.casinogod.battle;

public class PlayerInfo {
	
	private long playerID = 0;
	private long playerBet = 0;
	private int[] cardsInDeck;
	
	public long getPlayerID() {
		return playerID;
	}
	public void setPlayerID(long playerID) {
		this.playerID = playerID;
	}
	public long getPlayerBet() {
		return playerBet;
	}
	public void setPlayerBet(long playerBet) {
		this.playerBet = playerBet;
	}
	
	public int[] getCardsInDeck() {
		return cardsInDeck;
	}
	public void setCardsInDeck(int[] cardsInDeck) {
		this.cardsInDeck = cardsInDeck;
	}
	
}
