import java.io.IOException;
import java.awt.Graphics;
import java.awt.Color;

public class Slime extends Entidade {
    Slime(int X_Inicial, int Y_Inicial) {
        Vida = 2;
        PosicaoX = X_Inicial;
        PosicaoY = Y_Inicial;
        Sprite = new Imagem("Sprites\\Inimigos\\Slime\\Slime_Chorao.png", PosicaoX, PosicaoY);
    }
    public void Interacao(Personagem_Controlavel Personagem) {
        if (Personagem.Inventario != null)
            if (Personagem.Inventario.ID == 0) {
                Vida--;
                Personagem.MudaPontuacao(5);
                if (Vida <= 0) Personagem.MudaPontuacao(10);
            }
    }
    public void draw(Graphics g, int X, int Y) throws IOException {
        Sprite.draw(g, 1);
        if (Vida < 2) {
            g.setColor(Color.white);
            g.drawChars(new char[] {'E', 'i', ' ', 'c', 'a', 'v', 'a', 'l', 'e', 'i', 'r', 'o'}, 0, 12, PosicaoX*48-50,PosicaoY*48-20);
            g.drawChars(new char[] {'C', 'u', 'i', 'd', 'a', 'd', 'o', ' ', 'c', 'o', 'm', ' ', 'o', 's'}, 0, 14, PosicaoX*48-50, PosicaoY*48-10);
            g.drawChars(new char[] {'o', 'u', 't', 'r', 'o', 's', ' ', 's', 'l', 'i', 'm', 'e', 's'}, 0, 13, PosicaoX*48-50, PosicaoY*48);
            g.drawChars(new char[] {'e', 'l', 'e', 's', ' ', 'n', 'a', 'o', ' ', 's', 'a', 'o'}, 0, 12, PosicaoX*48-50, PosicaoY*48+10);
            g.drawChars(new char[] {'p', 'a', 'c', 'i', 'f', 'i', 'c', 'o', 's'}, 0, 9, PosicaoX*48-50, PosicaoY*48+20);
            g.drawChars(new char[] {'c', 'o', 'm', 'o', ' ', 'e', 'u'}, 0, 7, PosicaoX*48-50, PosicaoY*48+30);
        }
    }
}