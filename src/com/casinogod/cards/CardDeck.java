package com.casinogod.cards;

import com.casinogod.utility.Utility;



public class CardDeck {
	
	public static int[] shuffleCards(int cardDeckCount, boolean hasJoker)
	{
		int cardNumber = 54;
		
		if(!hasJoker)
		{
			cardNumber = 52;
		}
		
		int[] cards = new int[cardNumber * cardDeckCount];
		
		int rndIndex = 0;
		int temp = 0;
		
		for(int i = 0; i < cardNumber * cardDeckCount; i++)
		{
			cards[i] = i;
		}
		
		for(int i = 0; i < cardNumber * cardDeckCount; i++)
		{
			rndIndex =  (int) (Utility.randomLong() % (cardNumber * cardDeckCount));
			
			temp = cards[Math.abs(rndIndex)];
			cards[Math.abs(rndIndex)] = cards[i];
			cards[i] = temp;
		}		
		return cards;
	}

}
