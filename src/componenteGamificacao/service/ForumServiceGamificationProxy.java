package componenteGamificacao.service;

import componenteGamificacao.model.Badge;
import componenteGamificacao.model.Points;
import componenteGamificacao.storage.AchievementStorage;
import componenteGamificacao.storage.AchievementStorageFactory;

public class ForumServiceGamificationProxy implements ForumService {
	
	private ForumService forumServiceReal;
	
	public ForumServiceGamificationProxy(ForumService forumService) {
		forumServiceReal = forumService;
	}

	@Override
	public void addTopic(String user, String topic) {
		forumServiceReal.addTopic(user, topic);
		
		AchievementStorage storage = AchievementStorageFactory.getAchievementStorage();
		storage.addAchievement(user, new Points("CREATION", user, 5));
		storage.addAchievement(user, new Badge("I CAN TALK", user));
	}

	@Override
	public void addComment(String user, String topic, String comment) {
		forumServiceReal.addComment(user, topic, comment);
		
		AchievementStorage storage = AchievementStorageFactory.getAchievementStorage();
		storage.addAchievement(user, new Points("PARTICIPATION", user, 3));
		storage.addAchievement(user, new Badge("LET ME ADD", user));
	}

	@Override
	public void likeTopic(String user, String topic, String topicUser) {
		forumServiceReal.likeTopic(user, topic, topicUser);
		
		AchievementStorage storage = AchievementStorageFactory.getAchievementStorage();
		storage.addAchievement(user, new Points("CREATION", user, 1));
	}

	@Override
	public void likeComment(String user, String topic, String comment, String commentUser) {
		forumServiceReal.likeComment(user, topic, comment, commentUser);
		
		AchievementStorage storage = AchievementStorageFactory.getAchievementStorage();
		storage.addAchievement(user, new Points("PARTICIPATION", user, 1));
	}

}
