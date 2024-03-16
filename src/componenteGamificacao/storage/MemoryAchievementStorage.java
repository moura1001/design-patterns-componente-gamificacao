package componenteGamificacao.storage;

import java.util.List;

import componenteGamificacao.model.Achievement;
import componenteGamificacao.service.achievement.AchievementObserver;

public class MemoryAchievementStorage implements AchievementStorage {
	
	MemoryAchievementStorage() {}

	@Override
	public void addAchievement(String user, Achievement a) {
		for (AchievementObserver observador : AchievementObserver.OBSERVADORES) {
			a.adicionarObservador(observador);
		}
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
