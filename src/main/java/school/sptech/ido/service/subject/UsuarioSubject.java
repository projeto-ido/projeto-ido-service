package school.sptech.ido.service.subject;

import school.sptech.ido.domain.model.Usuario;
import school.sptech.ido.repository.UsuarioRepository;
import school.sptech.ido.service.observer.ApplicationObsever;

import java.time.LocalDate;

public class UsuarioSubject {

    private Usuario usuario;

    private ApplicationObsever observer;


    public UsuarioSubject(Usuario usuario, LocalDate dtAtual) {
        this.usuario = usuario;
        observer = new ApplicationObsever(dtAtual);
    }






    public Usuario getUsuario() {
        return usuario;
    }


}
