public class Pocao extends Item {
    Pocao(int X, int Y, int ID) {
        this.ID = ID;
        PosicaoX = X;
        PosicaoY = Y;
        Sprite = new Imagem("Sprites\\Items\\Pocao"+ID+".png", PosicaoX, PosicaoY);
    }   
}