public class Parede extends Celula_Mundo {
    Parede(int X, int Y) {
        Permeavel = false;
        PosicaoX = X;
        PosicaoY = Y;
        Sprite = new Imagem("Sprites\\Mapa\\Parede.png", PosicaoX, PosicaoY);
    }
    void Iteracao() {}
}