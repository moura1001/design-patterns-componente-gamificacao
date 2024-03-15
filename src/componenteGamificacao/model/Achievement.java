package componenteGamificacao.model;

public abstract class Achievement {
	
	protected String name;

	protected Achievement(String name) {
		this.name = name;
	}
	
	public abstract void adicionar();

}
