package componenteGamificacao.model;

public class Points extends Achievement {
	
	private int quantidadePontos;

	public Points(String name, String user) {
		super(name, user);
		quantidadePontos = 0;
	}
	
	public int getQuantidadePontos() {
		return quantidadePontos;
	}

	@Override
	public void adicionar() {
		quantidadePontos++;
		
		if (observador != null) {
			observador.achievementUpdate(user, this);
		}
	}

}
