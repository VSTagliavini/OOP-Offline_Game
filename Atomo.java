import java.io.IOException;
import java.awt.Graphics;

public class Atomo {
    boolean Permeavel;
    int PosicaoX, PosicaoY;
    public Imagem Sprite;
    public void draw(Graphics g, int X, int Y) throws IOException {
        Sprite.draw(g, 1);
    }
    void Interacao(Personagem_Controlavel Personagem) {}
}
