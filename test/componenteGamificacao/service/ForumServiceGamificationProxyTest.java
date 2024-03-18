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
	private ForumService forumService;

	@BeforeEach
	void setUp() {
		AchievementStorageFactory.setAchievementStorage(new MemoryAchievementStorage());
		achievementStorage = AchievementStorageFactory.getAchievementStorage();
		forumService = new ForumServiceGamificationProxy(new ForumServiceMock());
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
	
	@Test
	void aoCurtirUmComentarioDeveAdicionar1PontoDoTipoParticipation() {
		forumService.likeComment("moura", "Random Tópico", "Random Comentário", "randomUser");
		List<Achievement> achievements = achievementStorage.getAchievements("moura");
		assertEquals(1, achievements.size());
		
		Achievement achievement = achievementStorage.getAchievement("moura", "PARTICIPATION");
		assertNotNull(achievement);
		assertInstanceOf(Points.class, achievement);
		Points points = (Points) achievement;
		assertEquals(1, points.getQuantidadePontos());
	}
	
	@Test
	void aoAdicionarVariosTopicosDeveAcumularOsPontosDoUsuario() {
		forumService.addTopic("moura", "Meu primeiro tópico");
		forumService.addTopic("moura", "Meu segundo tópico");
		forumService.addTopic("moura", "Meu terceiro tópico");
		List<Achievement> achievements = achievementStorage.getAchievements("moura");
		assertEquals(2, achievements.size());
		
		Achievement achievement = achievementStorage.getAchievement("moura", "CREATION");
		assertNotNull(achievement);
		assertInstanceOf(Points.class, achievement);
		Points points = (Points) achievement;
		assertEquals(15, points.getQuantidadePontos());
		
		achievement = achievementStorage.getAchievement("moura", "I CAN TALK");
		assertNotNull(achievement);
		assertInstanceOf(Badge.class, achievement);
	}
	
	@Test
	void deveGanharTodosOsBadgesRelacionadosAAlgumaDeSuasInteracoesNoForum() {
		forumService.addTopic("moura", "Meu primeiro tópico");
		forumService.addComment("moura", "Tópico aleatório", "Meu primeiro comentário");
		forumService.addTopic("moura", "Meu segundo tópico");
		forumService.likeTopic("moura", "Random Tópico", "randomUser");
		forumService.likeComment("moura", "Random Tópico", "Random Comentário", "randomUser");
		List<Achievement> achievements = achievementStorage.getAchievements("moura");
		assertEquals(4, achievements.size());
		
		Achievement achievement = achievementStorage.getAchievement("moura", "CREATION");
		assertNotNull(achievement);
		assertInstanceOf(Points.class, achievement);
		Points points = (Points) achievement;
		assertEquals(11, points.getQuantidadePontos());
		
		achievement = achievementStorage.getAchievement("moura", "PARTICIPATION");
		assertNotNull(achievement);
		assertInstanceOf(Points.class, achievement);
		points = (Points) achievement;
		assertEquals(4, points.getQuantidadePontos());
		
		achievement = achievementStorage.getAchievement("moura", "I CAN TALK");
		assertNotNull(achievement);
		assertInstanceOf(Badge.class, achievement);
		
		achievement = achievementStorage.getAchievement("moura", "LET ME ADD");
		assertNotNull(achievement);
		assertInstanceOf(Badge.class, achievement);
	}
	
	@Test
	void naoDeveAdicionarAchievementsCasoOcorraAlgumaExcecao() {
		forumService = new ForumServiceGamificationProxy(new ForumServiceMockLancaExcecao());
		
		RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
			forumService.addTopic("moura", "Meu primeiro tópico");
		});
		assertEquals("Erro ao adicionar tópico", thrown.getMessage());

		List<Achievement> achievements = achievementStorage.getAchievements("moura");
		assertEquals(0, achievements.size());
	}
	
	@Test
	void aoAcumular100PontosDoTipoCreationOUsuarioDeveReceberABadgeInventor() {
		achievementStorage.addAchievement("moura", new Points("CREATION", "moura", 99));
		forumService.likeTopic("moura", "Random Tópico", "randomUser");
		List<Achievement> achievements = achievementStorage.getAchievements("moura");
		assertEquals(2, achievements.size());
		
		Achievement achievement = achievementStorage.getAchievement("moura", "CREATION");
		assertNotNull(achievement);
		assertInstanceOf(Points.class, achievement);
		Points points = (Points) achievement;
		assertEquals(100, points.getQuantidadePontos());
		
		achievement = achievementStorage.getAchievement("moura", "INVENTOR");
		assertNotNull(achievement);
		assertInstanceOf(Badge.class, achievement);
	}
	
	@Test
	void aoAcumular100PontosDoTipoParticipationOUsuarioDeveReceberABadgePartOfTheCommunity() {
		achievementStorage.addAchievement("moura", new Points("PARTICIPATION", "moura", 99));
		forumService.addComment("moura", "Tópico aleatório", "Meu primeiro comentário");
		List<Achievement> achievements = achievementStorage.getAchievements("moura");
		assertEquals(3, achievements.size());
		
		Achievement achievement = achievementStorage.getAchievement("moura", "PARTICIPATION");
		assertNotNull(achievement);
		assertInstanceOf(Points.class, achievement);
		Points points = (Points) achievement;
		assertEquals(102, points.getQuantidadePontos());
		
		achievement = achievementStorage.getAchievement("moura", "LET ME ADD");
		assertNotNull(achievement);
		assertInstanceOf(Badge.class, achievement);
		
		achievement = achievementStorage.getAchievement("moura", "PART OF THE COMMUNITY");
		assertNotNull(achievement);
		assertInstanceOf(Badge.class, achievement);
	}

}
