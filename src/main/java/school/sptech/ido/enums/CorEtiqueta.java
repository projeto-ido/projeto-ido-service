package school.sptech.ido.enums;

import lombok.Getter;

public enum CorEtiqueta {
    BRANCO("#FFF"),
    PRETO("#000"),
    AZUL("#5D84C2"),
    AMARELO("#FFCA6D"),
    LARANJA("#ff8c00"),
    ROXO("#b264d9"),
    VERDE("#51BDAB");

    @Getter private final String hexadecimal;

    CorEtiqueta(String hexadecimal) {
        this.hexadecimal = hexadecimal;
    }
}
