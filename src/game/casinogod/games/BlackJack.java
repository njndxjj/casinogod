package game.casinogod.games;

import com.casinogod.battle.BattleInfo;
import com.casinogod.cards.CardDeck;


public class BlackJack {
	
	static int cardsForEachPlayer = 52;
	
	private static void startGame(BattleInfo batInfo, long [] playerList)
	{
		int [] cardDeck = CardDeck.shuffleCards(6,false);
		
		int playerCount = playerList.length;
		
		int [][] playerCards = new int[playerCount + 1][cardsForEachPlayer]; //Add one batch cards for dealer
		
//		for(int i = 0; i < cardsForEachPlayer; i++)
//		{
//			for(int j = 0; j < playerCount + 1; j++)
//			{
//				playerCards[j][i] = cardDeck[i * playerCount + j];
//			}
//		}
		//Dealer cards
//		batInfo.setDealerCards(playerCards[playerCount]);		
		batInfo.setDealerCards(null);
//		PlayerInfo playerInfo = null;
		
//		for(int i = 0; i< playerCount; i++)
//		{
//			//Player cards
//			playerInfo = BattleFactory.getPlayerInfo(playerList[i]);
//			playerInfo.setCardsInDeck(playerCards[i]);
//			BattleFactory.updatePlayerInfo(playerInfo);
//			playerInfo = null;
//		}
		
	}
	
	public static void startGame(BattleInfo batInfo)
	{
		int playerCount = 1;
		if(batInfo.getBattlePlayerUserIDList() != null)
		{
			playerCount += batInfo.getBattlePlayerUserIDList().length;
		}
		
		long [] playerList = new long[playerCount];
		
		playerList[0] = batInfo.getBattleOwnerUserID();
		
		for(int i = 1; i < playerCount; i++)
		{
			playerList[i] = batInfo.getBattlePlayerUserIDList()[i-1];
		}
		startGame(batInfo, playerList);
	}

}
