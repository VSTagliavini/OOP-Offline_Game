public class Alavanca extends Celula_Mundo {
    boolean Ativacao, Pontuacao;
    Portao Portao_Atrelado;
    Alavanca(int X, int Y, Portao Portao_Atrelado) {
        Pontuacao = true;
        Ativacao = false;
        Permeavel = true;
        PosicaoX = X;
        PosicaoY = Y;
        Sprite = new Imagem("Sprites\\Mapa\\Alavanca_Desativada_Teste.png", PosicaoX, PosicaoY);
        this.Portao_Atrelado = Portao_Atrelado;
    }
    void Interacao(Personagem_Controlavel Personagem) {
        Ativacao = !Ativacao;
        if (Pontuacao) {
            Personagem.MudaPontuacao(10);
            Pontuacao = false;
        }
        if (Ativacao) Sprite.Caminho_Incursao = "Sprites\\Mapa\\Alavanca_Ativada_Teste.png";
        else Sprite.Caminho_Incursao = "Sprites\\Mapa\\Alavanca_Desativada_Teste.png";
        if (Portao_Atrelado != null) Portao_Atrelado.Abre_Fecha();
    }
}