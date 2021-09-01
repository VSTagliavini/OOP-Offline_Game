public class Chave extends Item {
    Chave(int X, int Y, int ID) {
        this.ID = ID;
        PosicaoX = X;
        PosicaoY = Y;
        Sprite = new Imagem("Sprites\\Items\\Chave.png", PosicaoX, PosicaoY);
    }
    
}
