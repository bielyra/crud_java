package com.testandocrud.cadastro_usuario.business;

import com.testandocrud.cadastro_usuario.infraestrutura.entitys.Usuario;
import com.testandocrud.cadastro_usuario.infraestrutura.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    //Primeiro metodo: salvar
    public void salvarUsuario(Usuario usuario) {
        repository.saveAndFlush(usuario); //salva e fecha a conexão com o BD
    }
    //Segundo metodo: Buscar
    public Usuario buscarUsuarioPorEmail(String email){
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado!")
        );
    }
    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }
    public void atualizarUsuarioPorId(Integer id, Usuario usuario) {
        Usuario usuarioEntity = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario não encontrado"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() :
                        usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() :
                        usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .build();

        repository.saveAndFlush(usuarioAtualizado);
    }

}

