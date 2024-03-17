package componenteGamificacao.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import componenteGamificacao.model.Achievement;
import componenteGamificacao.service.achievement.AchievementObserver;

public class MemoryAchievementStorage implements AchievementStorage {
	
	private static final Map<String, List<Achievement>> usuarios = new HashMap<>(10);

	@Override
	public void addAchievement(String user, Achievement a) {
		
		if (usuarios.containsKey(user)) {
			
			List<Achievement> achievements = usuarios.get(user);
			Achievement achievement = getAchievement(user, a.getName());
			
			if (achievement != null)
				achievement.adicionar(a);
			else {
				for (AchievementObserver observador : AchievementObserver.OBSERVADORES) {
					a.adicionarObservador(observador);
				}
				
				achievements.add(a);
			}

		} else {
			for (AchievementObserver observador : AchievementObserver.OBSERVADORES) {
				a.adicionarObservador(observador);
			}
			
			List<Achievement> achievements = new ArrayList<Achievement>(5);
			achievements.add(a);
			usuarios.put(user, achievements);
		}
	}

	@Override
	public List<Achievement> getAchievements(String user) {
		return usuarios.getOrDefault(user, new ArrayList<>(0));
	}

	@Override
	public Achievement getAchievement(String user, String achievementName) {
		List<Achievement> achievements = usuarios.get(user);
		Optional<Achievement> opt = achievements.stream()
				.filter(achievment -> achievment.getName().equals(achievementName)).findAny();
		
		if (opt.isPresent())
			return opt.get();
		else
			return null;
	}

}
