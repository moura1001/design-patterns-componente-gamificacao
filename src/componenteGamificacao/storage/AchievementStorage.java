package componenteGamificacao.storage;

import java.util.List;

import componenteGamificacao.model.Achievement;

public interface AchievementStorage {
	
	void addAchievement(String user, Achievement a);
	
	List<Achievement> getAchievements(String user);
	
	Achievement getAchievement(String user, String achievementName);

}
