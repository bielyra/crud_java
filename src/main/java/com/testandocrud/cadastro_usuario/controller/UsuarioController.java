package com.testandocrud.cadastro_usuario.controller;


import com.testandocrud.cadastro_usuario.business.UsuarioService;
import com.testandocrud.cadastro_usuario.business.dto.UsuarioDto;
import com.testandocrud.cadastro_usuario.infraestrutura.entitys.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> salvarUsuario(@RequestBody UsuarioDto dto){
        log.info("Salvando usuario: {}", dto.getNome());
        return ResponseEntity.ok(usuarioService.salvarUsuario(dto));
    }

    //TODO - listarTodos
    //TODO - buscarPorFiltro
    //TODO - buscarPorId

    @GetMapping
    public ResponseEntity<Page<UsuarioDto>> listarTodos(Pageable pageable){
        return ResponseEntity.ok(usuarioService.listarTodos(pageable));
    }

    @GetMapping(value = "email")
    public ResponseEntity<UsuarioDto> buscarUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam("email") String email){
        usuarioService.deletarUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<Void> atualizarUsuarioPorId(@RequestParam Integer id,
                                                      @RequestBody Usuario usuario){
        usuarioService.atualizarUsuarioPorId(id, usuario);
        return ResponseEntity.ok().build();
    }
}
