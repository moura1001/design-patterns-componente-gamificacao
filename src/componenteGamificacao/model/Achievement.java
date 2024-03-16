package componenteGamificacao.model;

import componenteGamificacao.service.achievement.AchievementObserver;

public abstract class Achievement {
	
	protected String name;
	protected String user;
	
	protected AchievementObserver observador;

	protected Achievement(String name, String user) {
		this.name = name;
		this.user = user;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUser() {
		return name;
	}
	
	public abstract void adicionar();
	
	public void adicionarObservador(AchievementObserver observador) {
		if (observador == null)
			return;
		
		if (observador.deveAdicionarObservador(this))
			this.observador = observador;
	}
	
	public void removerObservador() {
		observador = null;
	}

}
