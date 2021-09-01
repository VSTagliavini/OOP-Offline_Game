import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

public class Imagem {
    public int x, y;
    public String Caminho_Incursao;

    Imagem() {
        this(null, 0, 0);
    }
    public Imagem(String Caminho_Incursao, int x, int y) {
        this.Caminho_Incursao = Caminho_Incursao;
        this.x = x;
        this.y = y;
    }

    public Image getImage() throws IOException {
        return ImageIO.read(new File(Caminho_Incursao));
    }

    public void draw(Graphics g, int inversao) throws IOException {
        if (inversao < 0) g.drawImage(this.getImage(), (this.x+1)*48, this.y*48, -48, 48, null);
        else g.drawImage(this.getImage(), this.x*48, this.y*48, 48, 48, null);
    }
}
