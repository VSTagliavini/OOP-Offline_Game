import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

class JogoBase extends JFrame {
  int X = 16, Y = 9, Z = 5;
  int X1, Y1;
  Atomo Mapa[][][] = new Atomo[X][Y][Z];
  Desenho des = new Desenho();
  Movimentacao M1;

  class Movimentacao extends KeyAdapter {
    Personagem_Controlavel Personagem;
    Movimentacao(Personagem_Controlavel Personagem) {
      this.Personagem = Personagem;
    }
    public void keyPressed(KeyEvent Tecla) {
      int NovoX = 0, NovoY = 0, PX = Personagem.PosicaoX, PY = Personagem.PosicaoY, MX = Personagem.Atomo_Marcado.PosicaoX, MY = Personagem.Atomo_Marcado.PosicaoY;

      if (Tecla.getKeyCode() == 69) {
        if (Mapa[MX][MY][1] != null) {
          Mapa[MX][MY][1].Interacao(Personagem);
          if (((Entidade) Mapa[MX][MY][1]).Vida <= 0) Mapa[MX][MY][1] = null;
        }
        if (Mapa[MX][MY][2] != null) {
          Personagem.dropar(PX, PY);
          Mapa[PX][PY][2] = Personagem.Inventario;
          Personagem.Inventario = (Item) Mapa[MX][MY][2];
          Mapa[MX][MY][2] = null;
        }
        if (Mapa[MX][MY][3] != null) {

          Mapa[MX][MY][3].Interacao(Personagem);
        }
        des.repaint();
        return;
      } else if (Tecla.getKeyCode() == 81) {
        if (Personagem.Inventario != null && Mapa[PX][PY][2] == null) {
          Mapa[PX][PY][2] = Personagem.Inventario;
          Personagem.dropar(PX, PY);
          Personagem.Inventario = null;
          des.repaint();
        }
      }
      Mapa[PX][PY][4] = null;
      if (Tecla.getKeyCode() == 87) {NovoX = 0; NovoY = -1;}
      else if (Tecla.getKeyCode() == 83) {NovoX = 0; NovoY = 1;}
      else if (Tecla.getKeyCode() == 68) {NovoX = 1; NovoY = 0;}
      else if (Tecla.getKeyCode() == 65) {NovoX = -1; NovoY = 0;}
      if (PX + NovoX < 0 || PX + NovoX > X-1 || PY + NovoY < 0 || PY + NovoY > Y-1) return;
      if (!Mapa[PX+NovoX][PY+NovoY][0].Permeavel || Mapa[PX+NovoX][PY+NovoY][1] != null) return;
      else if (Mapa[PX+NovoX][PY+NovoY][3] != null && !Mapa[PX+NovoX][PY+NovoY][3].Permeavel) return;
      Mapa[PX+NovoX][PY+NovoY][1] = Personagem;
      Mapa[MX][MY][4] = null;
      Mapa[PX][PY][1] = null;


      Personagem.Movimentacao(NovoX, NovoY, X, Y);
      if (Personagem.Identidade == 1) {
        X1 = PX+NovoX;
        Y1 = PY+NovoY;
      }
      Mapa[Personagem.Atomo_Marcado.PosicaoX][Personagem.Atomo_Marcado.PosicaoY][4] = Personagem.Atomo_Marcado;
      des.repaint();
    }
  }

  class Desenho extends JPanel  {
    Desenho() {
      for (int auxx = 0; auxx < X; auxx++) {
        for (int auxy = 0; auxy < Y; auxy++) {
          if (auxx == 0 || auxx == X-1 || auxy == 0 || auxy == Y-1) Mapa[auxx][auxy][0] = new Parede(auxx, auxy);
          else Mapa[auxx][auxy][0] = new Chao(auxx, auxy);
        }
      }

      Mapa[14][7][1] = new Personagem_Controlavel(2, 14, 7);
      Mapa[14][6][1] = new Personagem_Controlavel(1, 14, 6);
      Mapa[10][7][2] = new Pocao(10, 7, 1);
      Mapa[10][6][2] = new Pocao(10, 6, 2);
      for (int i = 13; i < 16; i++) Mapa[i][5][0] = new Parede(i, 5);
      for (int i = 8; i > 4; i--) Mapa[11][i][0] = new Parede(11, i);
      Mapa[12][5][3] = new Porta(12, 5, 10);
      Mapa[12][6][2] = new Chave(12, 6, 10);
      for (int i = 0; i < 6; i++) Mapa[7][i][0] = new Parede(7, i);
      Mapa[8][5][0] = new Parede(8, 5);
      Mapa[10][5][0] = new Parede(10, 5);
      Mapa[9][5][3] = new Portao(9, 5);
      Mapa[11][1][3] = new Alavanca(11, 1, (Portao) Mapa[9][5][3]);
      Mapa[11][3][2] = new Chave(11, 3, 11);
      Mapa[1][7][3] = new Bau(1, 7, 11, (Item) new Espada(1, 7));
      Mapa[1][1][3] = new Saida(1, 1);
      Mapa[5][2][1] = new Slime(5, 2);

      M1 = new Movimentacao((Personagem_Controlavel) Mapa[14][6][1]);
      
      setPreferredSize(new Dimension(X*48, Y*48+100));
    }

    public void paintComponent(Graphics g) {
      g.setColor(Color.BLACK);
      g.fillRect(0, Y*48, getWidth(), 100);
      for (int auxx = 0; auxx < X; auxx++)
        for (int auxy = 0; auxy < Y; auxy++)
          for (int auxz = 0; auxz < Z; auxz++)
            try {
              if (Mapa[auxx][auxy][auxz] != null) Mapa[auxx][auxy][auxz].draw(g, X, Y);
            } catch (IOException e) {}
    }
  }

  JogoBase() {
    super("Trabalho");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    add(des);
    addKeyListener(M1);
    pack();
    setVisible(true);
  }

  static public void main(String[] args) {
    JogoBase f = new JogoBase();
  }
}