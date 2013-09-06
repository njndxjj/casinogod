package game.casinogod.user;

import com.casinogod.utility.DataStore;

import net.sf.json.JSONObject;

public class UserFactory {
	private static String userIDPrefix = "UserID:";
    public static UserInfo authUser(String userLogin, String userPwd) 
    {
	   UserInfo uInfo = new UserInfo();
	   
	   DataStore.waitingBattle.put(userIDPrefix + uInfo.getUserID(),  
				JSONObject.fromObject(uInfo).toString());
	   
	   return uInfo;
   }
}
