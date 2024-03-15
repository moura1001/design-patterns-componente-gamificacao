package componenteGamificacao.storage;

import java.util.List;

import componenteGamificacao.model.Achievement;

public class MemoryAchievementStorage implements AchievementStorage {
	
	MemoryAchievementStorage() {}

	@Override
	public void addAchievement(String user, Achievement a) {
	}

	@Override
	public List<Achievement> getAchievements(String user) {
		return null;
	}

	@Override
	public Achievement getAchievement(String user, String achievementName) {
		return null;
	}

}
