package school.sptech.ido.service.subject;

import school.sptech.ido.domain.model.Usuario;
import school.sptech.ido.repository.UsuarioRepository;
import school.sptech.ido.repository.entity.UsuarioEntity;
import school.sptech.ido.service.observer.ApplicationObsever;

import java.time.LocalDate;

public class UsuarioSubject {

    private UsuarioEntity usuario;

    private ApplicationObsever observer;


    public UsuarioSubject(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public void notificar(){
        observer.
    }




    public UsuarioEntity getUsuario() {
        return usuario;
    }


}
