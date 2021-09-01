public class Espada extends Item {
    Espada(int X, int Y) {
        ID = 0;
        PosicaoX = X;
        PosicaoY = Y;
        Sprite = new Imagem("Sprites\\Items\\Espada.png", PosicaoX, PosicaoY);
    }
}