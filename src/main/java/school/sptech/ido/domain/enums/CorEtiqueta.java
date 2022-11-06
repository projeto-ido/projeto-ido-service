package school.sptech.ido.domain.enums;


public enum CorEtiqueta {
    BRANCO("#FFF"),
    PRETO("#000"),
    AZUL("#5D84C2"),
    AMARELO("#FFCA6D"),
    LARANJA("#ff8c00"),
    ROXO("#b264d9"),
    VERDE("#51BDAB");

    private final String hexadecimal;

    CorEtiqueta(String hexadecimal) {
        this.hexadecimal = hexadecimal;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }
}
