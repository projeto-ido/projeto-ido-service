package school.sptech.ido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import school.sptech.ido.domain.model.Usuario;
import school.sptech.ido.repository.UsuarioRepository;
import school.sptech.ido.repository.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    private List<UsuarioEntity> usuarios = usuarioRepository.findAll();



    public ResponseEntity<Void> desabilitarNotificacao(Integer idusuario){
//        if(usuarios.stream().anyMatch(usuarioEntity -> usuarioEntity.getIdUsuario().equals(idusuario))){
        Optional<UsuarioEntity> usuarioOptional = usuarios.stream().filter(usuarioEntity -> usuarioEntity.getIdUsuario().equals(idusuario)).findAny();

        if (usuarioOptional.isPresent()){
            usuarios.remove(usuarioOptional.get());
            return ResponseEntity.status(200).build();
        }
//        }
        return ResponseEntity.status(404).build();
    }
}
