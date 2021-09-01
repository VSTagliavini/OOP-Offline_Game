public class Chao extends Celula_Mundo {
    Chao(int X, int Y) {
        Permeavel = true;
        PosicaoX = X;
        PosicaoY = Y;
        Sprite = new Imagem("Sprites\\Mapa\\Chao.png", PosicaoX, PosicaoY);
    }
}