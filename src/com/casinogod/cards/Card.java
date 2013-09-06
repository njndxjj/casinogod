package com.casinogod.cards;

public class Card {
	
	private CardNumber cardNumber = CardNumber.Ace;
	private CardType cardType = CardType.Clubs;
	public CardNumber getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(CardNumber cardNumber) {
		this.cardNumber = cardNumber;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
	public static Card getCardFromIndex(int cardIndex)
	{
		if(cardIndex < 0)
		{
			return null;
		}
		
		int index = cardIndex % 54;
		Card card = new Card();
		switch(index % 13)
		{
		case 0:
			card.cardNumber = CardNumber.Ace;
			break;
		case 1:
			card.cardNumber = CardNumber.Two;
			break;
		case 2:
			card.cardNumber = CardNumber.Three;
			break;
		case 3:
			card.cardNumber = CardNumber.Four;
			break;
		case 4:
			card.cardNumber = CardNumber.Five;
			break;
		case 5:
			card.cardNumber = CardNumber.Six;
			break;
		case 6:
			card.cardNumber = CardNumber.Seven;
			break;
		case 7:
			card.cardNumber = CardNumber.Eight;
			break;
		case 8:
			card.cardNumber = CardNumber.Nine;
			break;
		case 9:
			card.cardNumber = CardNumber.Ten;
			break;
		case 10:
			card.cardNumber = CardNumber.Jack;
			break;
		case 11:
			card.cardNumber = CardNumber.Queen;
			break;
		case 12:
			card.cardNumber = CardNumber.King;
			break;
		}
		
		switch( (int)(index / 13))
		{
		case 0:
			card.cardType = CardType.Clubs;
			break;
		case 1:
			card.cardType = CardType.Diamonds;
			break;
		case 2:
			card.cardType = CardType.Hearts;
			break;
		case 3:
			card.cardType = CardType.Spades;
			break;
		}
		
		return card;
	}

}
