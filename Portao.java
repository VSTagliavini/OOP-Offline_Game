public class Portao extends Celula_Mundo {
    Portao(int X, int Y) {
        Permeavel = false;
        PosicaoX = X;
        PosicaoY = Y;
        Sprite = new Imagem("Sprites\\Mapa\\Portao_Fechado.png", PosicaoX, PosicaoY);
    }
    public void Abre_Fecha() {
        Permeavel = !Permeavel;
        if (Permeavel) Sprite.Caminho_Incursao = "Sprites\\Mapa\\Porta_Aberta.png";
        else Sprite.Caminho_Incursao = "Sprites\\Mapa\\Portao_Fechado.png";
    }
}