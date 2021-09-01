import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Saida extends Celula_Mundo {
    Saida(int X, int Y) {
        Permeavel = false;
        PosicaoX = X;
        PosicaoY = Y;
        Sprite = new Imagem("Sprites\\Mapa\\Saida.png", PosicaoX, PosicaoY);
    }
    public void Interacao(Personagem_Controlavel Personagem) {
        JOptionPane.showMessageDialog(new JFrame(), "Demonstracao completa.");
        System.exit(0);
    }
}