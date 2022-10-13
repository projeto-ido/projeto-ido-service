package school.sptech.ido.service;

import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.ido.repository.UsuarioRepository;
import school.sptech.ido.repository.entity.UsuarioEntity;
import school.sptech.ido.service.subject.UsuarioSubject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    private List<UsuarioSubject> subjects = new ArrayList();


    public UsuarioSubject desabilitarNotificacao(Integer idusuario){
//        if(usuarios.stream().anyMatch(usuarioEntity -> usuarioEntity.getIdUsuario().equals(idusuario))){
        Optional<UsuarioSubject> subjectOptional = subjects.stream().filter(usuarioSubject -> usuarioSubject.getUsuario().getIdUsuario().equals(idusuario)).findAny();
        if (subjectOptional.isPresent()){
            subjects.remove(subjectOptional.get());
            return subjectOptional.get();
        }

        return null;
//        }
    }

    public UsuarioEntity habilitarNotificacao(UsuarioEntity usuario){
        subjects.add(new UsuarioSubject(usuario));
        return usuario;
    }

    public void notificar(UsuarioEntity usuario, LocalDate dtAtual){
        UsuarioSubject subject = getSubject(usuario);
        subject.
    }

    public


    private UsuarioSubject getSubject(UsuarioEntity usuario){
        for (UsuarioSubject usuarioSubject: subjects) {
            if(usuarioSubject.getUsuario().getIdUsuario().equals(usuario.getIdUsuario()))
                return usuarioSubject;
        }
        return null;
    }


}
