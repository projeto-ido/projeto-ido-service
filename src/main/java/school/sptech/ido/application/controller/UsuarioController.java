package school.sptech.ido.application.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.controller.dto.*;
import school.sptech.ido.application.controller.dto.Request.AtualizacaoSenhaDto;
import school.sptech.ido.application.controller.dto.Response.UsuarioAtualizadoResDto;
import school.sptech.ido.application.controller.dto.Response.UsuarioDto;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;
import school.sptech.ido.service.UsuarioService;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;


@Tag(name = "Usuário", description = "Responsável por gerir os usuários dessa aplicação.")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;

    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Não há usuários cadastrados.", content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(responseCode = "200", description = "Usuários encontrados."),
            @ApiResponse(responseCode = "201", description = "Usuários cadastrado."),
            @ApiResponse(responseCode = "401", description = "O conteúdo não é autorizado."),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado nenhum usuário.")
    })

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
        List<UsuarioDto> usuarios = usuarioRepository.findAll().stream().map(UsuarioDto::new).collect(Collectors.toList());
        return usuarios.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok().body(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDto usuario) {
        return ResponseEntity.status(201).body(new UsuarioDto(usuarioRepository.save(new UsuarioEntity(usuario))));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioAtualizadoResDto> atualizarUsuario(
        @PathVariable Integer idUsuario,
        @RequestBody @Valid UsuarioAtualizadoDto usuarioAtualizadoDto
    ) {

        if (usuarioRepository.existsById(idUsuario)){
            Optional<UsuarioEntity> usuarioOpt = this.getUsuarioEntity(idUsuario);

            if(usuarioOpt.isPresent()){
                UsuarioEntity usuarioEntity = usuarioOpt.get();

                usuarioEntity.setNome(usuarioAtualizadoDto.getNome());
                usuarioEntity.setApelido(usuarioAtualizadoDto.getApelido());
                usuarioEntity.setEmail(usuarioAtualizadoDto.getEmail());
                usuarioEntity.setTelefone(usuarioAtualizadoDto.getTelefone());
                usuarioEntity.setBiografia(usuarioAtualizadoDto.getBiografia());
                usuarioEntity.setImagemBiografia(Base64.getDecoder().decode(usuarioAtualizadoDto.getImagemBiografia()));
                usuarioEntity.setImagemPerfil(Base64.getDecoder().decode(usuarioAtualizadoDto.getImagemPerfil()));

                return ResponseEntity.ok().body(new UsuarioAtualizadoResDto(usuarioRepository.save(usuarioEntity)));
            }
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{idUsuario}/nivel/{novoNivel}")
    public ResponseEntity<UsuarioDto> atualizarNivel(
        @PathVariable Integer idUsuario,
        @PathVariable Integer novoNivel
    ) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()){
            UsuarioEntity usuarioEncontrado = usuario.get();
            usuarioEncontrado.setNivel(novoNivel);
            return ResponseEntity.ok().body(new UsuarioDto(usuarioRepository.save(usuarioEncontrado)));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer idUsuario) {

        if (usuarioRepository.existsById(idUsuario)){
            usuarioRepository.deleteById(idUsuario);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> logar(@RequestBody @Valid UsuarioLoginDto usuarioLoginDto) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmailAndSenha(
            usuarioLoginDto.getEmail(),
            usuarioLoginDto.getSenha()
        );

        if (usuario.isPresent()){
            UsuarioEntity usuarioLogado = usuario.get();
            usuarioLogado.setAutenticado(true);
            return ResponseEntity.ok().body(new UsuarioDto(usuarioRepository.save(usuarioLogado)));
        }

        return ResponseEntity.status(401).build();
    }

    @GetMapping("/{idUsuario}/logoff")
    public ResponseEntity<Void> deslogar(@PathVariable Integer idUsuario) {

        Optional<UsuarioEntity> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()){
            UsuarioEntity usuarioDeslogado = usuario.get();
            if (usuarioDeslogado.getAutenticado()){
                usuarioDeslogado.setAutenticado(false);
                usuarioRepository.save(usuarioDeslogado);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.status(404).build();
    }

    public Boolean isUsuarioAutenticado(Integer idUsuario){

        Optional<UsuarioEntity> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()){
            UsuarioEntity usuarioEncontrado = usuario.get();
            return usuarioEncontrado.getAutenticado();
        }

        return false;
    }

    public UsuarioDto getUsuarioDto(Integer idUsuario){

        Optional<UsuarioDto> usuario = usuarioRepository.getusuarioDto(idUsuario);

        if (usuario.isPresent()){
            return usuario.get();
        }

        return null;
    }


    @PostMapping("/notificacao/desabilita/{id}")
    public ResponseEntity<UsuarioDto> desabilitarNotificacao(@PathVariable Integer id){
        return usuarioService.removerSubject(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/notificacao/habilita/{id}")
    public ResponseEntity<Void> habilitarNotificacao(@PathVariable Integer id){
        if (usuarioService.adicionarSubject(id))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/notificacao/envio")
    public ResponseEntity<Void> teste(){
        usuarioService.verificarData(LocalDateTime.now());

        return ResponseEntity.status(200).build();
    }

    private Optional<UsuarioEntity> getUsuarioEntity(int idUsuario){
        return usuarioRepository.findById(idUsuario);
    }

}
