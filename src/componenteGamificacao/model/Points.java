package componenteGamificacao.model;

public class Points extends Achievement {
	
	private int quantidadePontos;

	public Points(String name) {
		super(name);
		quantidadePontos = 0;
	}

	@Override
	public void adicionar() {
	}

}
