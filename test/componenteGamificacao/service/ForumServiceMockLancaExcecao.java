package componenteGamificacao.service;

public class ForumServiceMockLancaExcecao implements ForumService {

	@Override
	public void addTopic(String user, String topic) {
		throw new RuntimeException("Erro ao adicionar tópico");
	}

	@Override
	public void addComment(String user, String topic, String comment) {
		throw new RuntimeException("Erro ao adicionar comentário");
	}

	@Override
	public void likeTopic(String user, String topic, String topicUser) {
		throw new RuntimeException("Erro ao curtir tópico");
	}

	@Override
	public void likeComment(String user, String topic, String comment, String commentUser) {
		throw new RuntimeException("Erro ao curtir comentário");
	}

}
