package componenteGamificacao.model;

import java.util.Objects;

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
		return user;
	}
	
	public abstract void adicionar(Achievement a);
	
	public void adicionarObservador(AchievementObserver observador) {
		if (observador == null)
			return;
		
		if (observador.deveAdicionarObservador(this)) {
			
			this.observador = observador;
		}
	}
	
	public void removerObservador() {
		observador = null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Achievement other = (Achievement) obj;
		return Objects.equals(name, other.name) && Objects.equals(user, other.user);
	}

}
