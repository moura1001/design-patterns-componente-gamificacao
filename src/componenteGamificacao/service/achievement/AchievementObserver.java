package componenteGamificacao.service.achievement;

import java.util.List;

import componenteGamificacao.model.Achievement;

public interface AchievementObserver {
	
	public final List<AchievementObserver> OBSERVADORES = List.of(
		new ObservadorBadgeInventor(),
		new ObservadorBadgePartOfTheCommunity()
	);
	
	void achievementUpdate(String user, Achievement a);
	
	boolean deveAdicionarObservador(Achievement a);

}
