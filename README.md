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
> Coloque um link para os slides da apresentação final do projeto.

# Diagramas

## Diagrama Geral da Arquitetura do Jogo

> Apresente um diagrama geral da arquitetura do jogo. O formato é livre. A escolha de um ou mais estilos arquiteturais será considerado um diferencial.

~~~java
// Recorte do seu código
Class Jogo extends Game {
…
public void create () {
		…
		this.setScreen(new MainMenu(this));
    …
}
public void render() {
  super.render();
  jogar();
}
public void jogar() {
  tabuleiro.iniciaRodada(…);
  …
  tabuleiro.jogaInstante(…);
  …
  tabuleiro.finalizaRodada(…);
 }
~~~
