package componenteGamificacao.service.achievement;

import componenteGamificacao.model.Achievement;
import componenteGamificacao.model.Badge;
import componenteGamificacao.model.Points;
import componenteGamificacao.storage.AchievementStorage;
import componenteGamificacao.storage.AchievementStorageFactory;

public class ObservadorBadgeInventor implements AchievementObserver {

	@Override
	public void achievementUpdate(String user, Achievement a) {
		Points achievement = (Points) a;
		if (achievement.getQuantidadePontos() >= 100) {
			AchievementStorage storage = AchievementStorageFactory.getAchievementStorage();
			storage.addAchievement(a.getUser(), new Badge("INVENTOR", a.getUser()));
			a.removerObservador();
		}
	}

	@Override
	public boolean deveAdicionarObservador(Achievement a) {
		if (a instanceof Points && "CREATION".equals(a.getName()))
			return true;
		
		return false;
	}

}
