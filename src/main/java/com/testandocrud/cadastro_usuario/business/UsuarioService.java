package com.testandocrud.cadastro_usuario.business;

import com.testandocrud.cadastro_usuario.business.dto.UsuarioDto;
import com.testandocrud.cadastro_usuario.business.mapper.UsuarioMapper;
import com.testandocrud.cadastro_usuario.infraestrutura.entitys.Usuario;
import com.testandocrud.cadastro_usuario.infraestrutura.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioDto salvarUsuario(UsuarioDto dto) {
        Usuario usuario = repository.save(mapper.toEntity(dto));
        return mapper.toDto(usuario);
    }

    //TODO - descobrir outros modos de listar paginado
    public Page<UsuarioDto> listarTodos(Pageable pageable) {
        Page<Usuario> usuarios = repository.findAll(pageable);
        List<UsuarioDto> dtos = mapper.toDtoList(usuarios.getContent());
        return new PageImpl<>(dtos, pageable, usuarios.getTotalElements());
//        return repository.findAll(pageable).map(mapper::toDto);
    }

    public UsuarioDto buscarUsuarioPorEmail(String email){
        return mapper.toDto(repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado!")
        ));
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

