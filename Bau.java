public class Bau extends Celula_Mundo {
    int ID;
    Item Inventario;
    Bau(int X, int Y, int ID, Item Inventario) {
        Permeavel = false;
        PosicaoX = X;
        PosicaoY = Y;
        this.ID = ID;
        this.Inventario = Inventario;
        Sprite = new Imagem("Sprites\\Mapa\\Bau_Fechado.png", PosicaoX, PosicaoY);
    }
    public void Interacao(Personagem_Controlavel Personagem) {
        if (Personagem.Inventario == null) return;
        if (ID == Personagem.Inventario.ID) {
            if (Inventario != null) Personagem.MudaPontuacao(10);
            Sprite.Caminho_Incursao = "Sprites\\Mapa\\Bau_Aberto.png";
            Personagem.Inventario = Inventario;
            Inventario = null;
        }
    }
}