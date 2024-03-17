package componenteGamificacao.model;

public class Points extends Achievement {
	
	private int quantidadePontos;

	public Points(String name, String user) {
		super(name, user);
		quantidadePontos = 0;
	}
	
	public Points(String name, String user, int quantidadePontos) {
		super(name, user);
		this.quantidadePontos = quantidadePontos;
	}
	
	public int getQuantidadePontos() {
		return quantidadePontos;
	}

	@Override
	public void adicionar(Achievement a) {
		if (a instanceof Points) {
			Points p = (Points) a;
			quantidadePontos += p.quantidadePontos;		
		}
		
		if (observador != null) {
			observador.achievementUpdate(user, this);
		}
	}

}
