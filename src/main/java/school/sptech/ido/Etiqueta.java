package school.sptech.ido;

public class Etiqueta implements Comparable<Etiqueta> {

    private int id;
    private String titulo;
    private CorEtiqueta cor;

    @Override
    public int compareTo(Etiqueta etiqueta) {
        return this.titulo.compareTo(etiqueta.getTitulo());
    }

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
