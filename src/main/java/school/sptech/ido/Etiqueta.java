package school.sptech.ido;

public class Etiqueta {

    private int id;
    private String titulo;
    private CorEtiqueta cor;

    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }

    public CorEtiqueta getCor() {
        return cor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCor(CorEtiqueta cor) {
        this.cor = cor;
    }
}
