import java.io.IOException;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;

public class Personagem_Controlavel extends Entidade {
  int Identidade, Direcao;
  Atomo Atomo_Marcado = new Atomo();
  Item Inventario;
  char Pontos[] = new char[4];
  Personagem_Controlavel(int Identidade, int X_Inicial, int Y_Inicial) {
    Vida = 3;
    this.Identidade = Identidade;
    Direcao = 1;
    for (int i = 0; i < 4; i++) Pontos[i] = '0';
    PosicaoX = X_Inicial;
    PosicaoY = Y_Inicial;
    Atomo_Marcado.PosicaoX = X_Inicial;
    Atomo_Marcado.PosicaoY = Y_Inicial;
    Inventario = null;
    Sprite = new Imagem("Sprites\\Jogadores\\Jogador"+Identidade+"\\Parado_Frente.png", PosicaoX, PosicaoY);
    Atomo_Marcado.Sprite = new Imagem("Sprites\\Jogadores\\Chao_Selecionado.png", PosicaoX, PosicaoY);
  }
  public void Movimentacao(int NovoX, int NovoY, int X, int Y) {
    if (NovoX < 0) Direcao = -1;
    else if (NovoX > 0) Direcao = 1;
    if (NovoY < 0) Sprite.Caminho_Incursao = "Sprites\\Jogadores\\Jogador"+Identidade+"\\Parado_Tras.png";
    else if (NovoY > 0) Sprite.Caminho_Incursao = "Sprites\\Jogadores\\Jogador"+Identidade+"\\Parado_Frente.png";
    PosicaoX += NovoX;
    PosicaoY += NovoY;
    Sprite.x = PosicaoX;
    Sprite.y = PosicaoY;
    if (PosicaoX + NovoX > X-1 || PosicaoX + NovoX < 0 || PosicaoY + NovoY > Y-1 || PosicaoY + NovoY < 0) {
      Atomo_Marcado.PosicaoX = PosicaoX;
      Atomo_Marcado.PosicaoY = PosicaoY;
    } else {
      Atomo_Marcado.PosicaoX = PosicaoX + NovoX;
      Atomo_Marcado.PosicaoY = PosicaoY + NovoY;
    }
    Atomo_Marcado.Sprite.x = Atomo_Marcado.PosicaoX;
    Atomo_Marcado.Sprite.y = Atomo_Marcado.PosicaoY;
  }
  public void dropar(int x, int y) {
    if (Inventario == null) return;
    Inventario.PosicaoX = x;
    Inventario.PosicaoY = y;
    Inventario.Sprite.x = x;
    Inventario.Sprite.y = y;
  }
  public void draw(Graphics g, int X, int Y) throws IOException {
    Sprite.draw(g, Direcao);
    if (Identidade == 1) {
      g.setColor(Color.red);
      g.fillRect(X*24-55, Y*48+25, 50, 50);
      for (int i = 0; i < Vida; i++)
        g.fillRect(X*24-55-(i+1)*70, Y*48+45, 60, 30);
      if (Inventario != null) {
        g.drawImage(Inventario.Sprite.getImage(), X*24-55, Y*48+27, 48, 48, null);
        if (Inventario.ID > 2) {
          g.setColor(Color.white);
          g.drawChars(new char[] {(char)(Inventario.ID/10+48), (char)(Inventario.ID%10+48)}, 0, 2, X*24-35, Y*48+40);
        }
      }
      g.setColor(Color.white);
      g.drawChars(Pontos, 0, 4, X*24-93, Y*48+40);
    } else if (Identidade == 2) {
      g.setColor(Color.blue);
      g.fillRect(X*24+5, Y*48+25, 50, 50);
      for (int i = 0; i < Vida; i++)
        g.fillRect(X*24+65+i*70, Y*48+45, 60, 30);
      if (Inventario != null) {
        g.drawImage(Inventario.Sprite.getImage(), X*24+5, Y*48+27, 48, 48, null);
        if (Inventario.ID > 2) {
          g.setColor(Color.white);
          g.drawChars(new char[] {(char)(Inventario.ID/10+48), (char)(Inventario.ID%10+48)}, 0, 2, X*24+25, Y*48+40);
        }
      }
      g.setColor(Color.white);
      g.drawChars(Pontos, 0, 4, X*24+65, Y*48+40);
    }
  }
  public void Interacao(Personagem_Controlavel Personagem) {
    if (Personagem.Inventario != null)
      if (Personagem.Inventario.ID == 0) {
        Vida--;
        Personagem.MudaPontuacao(-25);
      } else if (Personagem.Inventario.ID == Identidade && Vida < 3) {
        Personagem.MudaPontuacao((3-Vida)*25);
        Vida = 3;
        Personagem.Inventario = null;
      }
  }
  public void MudaPontuacao(int Alteracao) {
    if (Alteracao > 0) {
      if (Pontos[3]-48 + Alteracao > 9) {
        Pontos[2] += (Pontos[3]-48+Alteracao)/10;
        Pontos[3] = (char) ((Pontos[3]-48+Alteracao)%10+48);
      } else Pontos[3] += Alteracao;
      for (int i = 2; i > -1; i--) {
        if (Pontos[i]-48 > 9) {
          Pontos[i-1] += (char) (Pontos[i]-48)/10;
          Pontos[i] = (char) ((Pontos[i]-48)%10+48);
        }
      }
    } else if (Alteracao < 0) {
      if ((Pontos[3]-48) + (Pontos[2]-48)*10 + (Pontos[1]-48)*100+ (Pontos[0]-48)*1000 + Alteracao <= 0) {
        System.out.println("...");
        for (int i = 0; i < 4; i++) Pontos[i] = 48;
        return;
      } 
      Pontos[3] += Alteracao;
      int i = 3;
      while (i > -1) {
        while (Pontos[i] < 48) {
          Pontos[i] += 10;
          if (i-1 > -1) Pontos[i-1]--;
        }
        i--;
      }
    }
  }
}