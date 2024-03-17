package componenteGamificacao.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import componenteGamificacao.model.Achievement;
import componenteGamificacao.model.Badge;
import componenteGamificacao.model.Points;
import componenteGamificacao.storage.AchievementStorage;
import componenteGamificacao.storage.AchievementStorageFactory;
import componenteGamificacao.storage.MemoryAchievementStorage;

class ForumServiceGamificationProxyTest {
	
	private AchievementStorage achievementStorage;
	private ForumService forumService = new ForumServiceGamificationProxy(); 

	@BeforeEach
	void setUp() {
		AchievementStorageFactory.setAchievementStorage(new MemoryAchievementStorage());
		achievementStorage = AchievementStorageFactory.getAchievementStorage();
	}

	@Test
	void aoAdicionarUmTopicoPelaPrimeiraVezDeveGanharOBadgeICanTalk() {
		forumService.addTopic("moura", "Meu primeiro tópico");
		List<Achievement> achievements = achievementStorage.getAchievements("moura");
		assertEquals(2, achievements.size());
		
		Achievement achievement = achievementStorage.getAchievement("moura", "CREATION");
		assertNotNull(achievement);
		assertInstanceOf(Points.class, achievement);
		Points points = (Points) achievement;
		assertEquals(5, points.getQuantidadePontos());
		
		achievement = achievementStorage.getAchievement("moura", "I CAN TALK");
		assertNotNull(achievement);
		assertInstanceOf(Badge.class, achievement);
	}
	
	@Test
	void aoAdicionarUmComentarioPelaPrimeiraVezDeveGanharOBadgeLetMeAdd() {
		forumService.addComment("moura", "Tópico aleatório", "Meu primeiro comentário");
		List<Achievement> achievements = achievementStorage.getAchievements("moura");
		assertEquals(2, achievements.size());
		
		Achievement achievement = achievementStorage.getAchievement("moura", "PARTICIPATION");
		assertNotNull(achievement);
		assertInstanceOf(Points.class, achievement);
		Points points = (Points) achievement;
		assertEquals(3, points.getQuantidadePontos());
		
		achievement = achievementStorage.getAchievement("moura", "LET ME ADD");
		assertNotNull(achievement);
		assertInstanceOf(Badge.class, achievement);
	}
	
	@Test
	void aoCurtirUmTopicoDeveAdicionar1PontoDoTipoCreation() {
		forumService.likeTopic("moura", "Random Tópico", "randomUser");
		List<Achievement> achievements = achievementStorage.getAchievements("moura");
		assertEquals(1, achievements.size());
		
		Achievement achievement = achievementStorage.getAchievement("moura", "CREATION");
		assertNotNull(achievement);
		assertInstanceOf(Points.class, achievement);
		Points points = (Points) achievement;
		assertEquals(1, points.getQuantidadePontos());
	}

}
