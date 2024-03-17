package componenteGamificacao.service.achievement;

import componenteGamificacao.model.Achievement;
import componenteGamificacao.model.Badge;
import componenteGamificacao.model.Points;
import componenteGamificacao.storage.AchievementStorage;
import componenteGamificacao.storage.AchievementStorageFactory;

public class ObservadorBadgePartOfTheCommunity implements AchievementObserver {

	@Override
	public void achievementUpdate(String user, Achievement a) {
		Points achievement = (Points) a;
		if (achievement.getQuantidadePontos() >= 100) {
			AchievementStorage storage = AchievementStorageFactory.getAchievementStorage();
			storage.addAchievement(a.getUser(), new Badge("PART OF THE COMMUNITY", a.getUser()));
			a.removerObservador();
		}
	}
	
	@Override
	public boolean deveAdicionarObservador(Achievement a) {
		if (a instanceof Points && "PARTICIPATION".equals(a.getName()))
			return true;
		
		return false;
	}

}
