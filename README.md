# Projeto Extinction

# Descrição Resumida do Projeto/Jogo

Em nosso jogo, temos quatro espécies diferentes que lutam entre si em um tabuleiro para obter comida. Cada espécie possui três atributos indo de 0 a 10, sendo eles: velocidade, inteligência e força. Para jogar, o jogador escolhe uma das quatro espécies e seleciona seus atributos, que juntos devem somar dez pontos. Seu objetivo é que no final do jogo apenas sua espécie sobreviva e as outras três sejam extintas.

# Equipe
* Bernardo Panka Archegas - 246970
* Luiz Henrique Yuji Delgado Oda - 247255

# Arquivo Executável do Jogo

> Coloque aqui um link para download do arquivo `jar` ou equivalente para execução do seu jogo.

# Slides do Projeto

## Slides da Prévia

[Slides](https://docs.google.com/presentation/d/1jbVQLHXSmoIzbXC1I0b6tI3u2GO_QiLP6dFZnplVF5k/edit#slide=id.g12eddf9782c_0_10)

## Slides da Apresentação Final

[Slides](https://docs.google.com/presentation/d/1HpqCt5N_N9NJoHFYNiT1HZXO-wRYFXCIIAiSLM9TnwM/edit#slide=id.g12029d56449_0_15)

# Diagramas

## Diagrama Geral da Arquitetura do Jogo

> Apresente um diagrama geral da arquitetura do jogo. O formato é livre. A escolha de um ou mais estilos arquiteturais será considerado um diferencial.

~~~java
Class Jogo extends Game {
	…
	
	// Demonstracao de como a classe Jogo interage com a interface grafica e o model
	public void create () {
	
		// Interface grafica
		…
		this.setScreen(new TelaInicio(this));
		…
	}
	public void render() {
		// loop que roda a cada frame
		
		// Interface grafica
		super.render();
		
		// Model
		jogar();
	}
	public void jogar() {
	
		// Funcao que chama tudo que precisa para que o facade tabuleiro jogue as rodadas
		
		// Model
		tabuleiro.iniciaRodada(…);
		…
		tabuleiro.jogaInstante(…);
		…
		tabuleiro.encerraRodada(…);
	}
	…
}
~~~
~~~java
// Funcionamento da interface grafica
Class TelaQualquer implements Screen {
	Jogo game;
	…
	public void render() {
		…
		// Ela renderiza a tela e, quando precisar mudar de tela
		// a classe Jogo apaga a instancia da tela atual
		// e setta a screen para a nova tela
		this.dispose();
		game.setScreen(new OutraTelaQualquer(game));
		…
	}
	…
}
~~~
~~~java
Class Tabuleiro {
	// Facade
	Celula[][] campo;
	…
	public void iniciaRodada(…) {
		// Inicializa as comidas e coloca as criaturas no canto do tabuleiro
		…
		campo[i][j].resetaComida();
		ArrayList<Especie> temp = this.getCriaturas();
		for(Especie i : temp) {
			i.settarPosicaoInicial();
			this.adicionaCriatura(i);
		}
	}
	public void jogaInstante(…) {
		// Roda um frame da rodada
		// Itera por todas as criaturas e move as que precisam mover (depende da velocidade)
		…
		ArrayList<Especie> temp = this.getCriaturas();
		for(Especie i : temp) {
			…
			this.mover(i);
		}
	}
	public void mover(…) {
		// Move a criatura
		// Primeiro pega o campo de visao da criatura (baseado na inteligencia)
		// Se existe uma comida no campo de visao, 
		// ele pega a comida mais proxima e anda na direcao dela
		// Se nao tiver comida, ela ve se pode matar alguem 
		// (se tem alguem mais fraco ao seu redor)
		// Se nao tiver comida nem morte, 
		// ela anda random com um incentivo a ir ao meio do tabuleiro
		…
		this.campo[x][y].removeCriatura(criatura);
		this.campo[novoX][novoY].adicionaCriatura(criatura);
	}
	public void encerraRodada() {
		// Tira todo mundo das suas posicoes
		// Mata e reproduz as criaturas baseado na quantidade de comida
		// Reinicia as comidas no tabuleiro
		…
		campo[i][j].encerraRodada();
	}
	…
}
~~~
~~~java
public Class Estatisticas {
	// Singleton
	…
	private static Estatisticas estatisticas = null;
	…
	private Estatisticas(…) {
		…
	}
	public static Estatisticas getStats(…) {
		if(estatisticas == null) {
			estatisticas = new Estatisticas(…);
		}
		estatisticas.atualizaStats();
		return estatisticas;
	}
	private void atualizaStats() {
		// Atualiza as estatisticas que sao mostradas 
		// para o usuario durante o jogo
		…
	}
	…
}
~~~
