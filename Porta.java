public class Porta extends Celula_Mundo {
    int ID;
    Porta(int X, int Y, int ID) {
        Permeavel = false;
        PosicaoX = X;
        PosicaoY = Y;
        this.ID = ID;
        Sprite = new Imagem("Sprites\\Mapa\\Porta_Fechada.png", PosicaoX, PosicaoY);
    }
    public void Interacao(Personagem_Controlavel Personagem) {
        if (Personagem.Inventario == null) return;
        if (ID == Personagem.Inventario.ID) {
            if (!Permeavel) Personagem.MudaPontuacao(10);
            Permeavel = true;
            Sprite.Caminho_Incursao = "Sprites\\Mapa\\Porta_Aberta.png";
            Personagem.Inventario = null;
        }
    }
}