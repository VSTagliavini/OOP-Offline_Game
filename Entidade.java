import java.awt.Graphics;
import java.awt.Image;

abstract class Entidade extends Atomo {
    public int Vida, Item, ID;
    public boolean Permeavel;
    void Movimentacao(int x, int y) {
        PosicaoX += x;
        PosicaoY += y;
        Sprite.x += 48*x;
        Sprite.y += 48*y;
    }
}