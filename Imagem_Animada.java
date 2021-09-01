import java.awt.Graphics;
import java.io.IOException;

import javax.swing.Timer;

public class Imagem_Animada extends Imagem {
    private Strings sprites[];
    private boolean animating;
    private boolean loop;
    private int timing;
    private int index;
    Timer t;

    public Imagem_Animada(String sprites[], boolean loop, int timing, int x, int y) {
        super(null, x, y);
        this.sprites = sprites;
        this.timing = timing;
        this.loop = loop;
        this.index = 0;
        t = new Timer(this.timing, e -> {
            this.index++;

            if (!this.loop && this.index == this.sprites.length) {
                this.stop();
                this.index = 0;
                return;
            }

            this.index %= this.sprites.length;
        });

    }

    public void animate(Graphics g) throws IOException {
        if (this.animating) {
            return;
        }

        this.animating = true;

        t.start();
    }

    public void stop() {
        this.animating = false;

        t.stop();
    }

    public String getPath() {
        return this.sprites[this.index];
    }

    public void draw(Graphics g, int Direcao) throws IOException {
        super.draw(g, Direcao);
    }
}
