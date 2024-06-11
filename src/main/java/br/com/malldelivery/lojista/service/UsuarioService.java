package br.com.malldelivery.lojista.service;


import br.com.malldelivery.lojista.exception.LojaException;
import br.com.malldelivery.lojista.model.Loja;
import br.com.malldelivery.lojista.model.Perfil;
import br.com.malldelivery.lojista.model.Usuario;
import br.com.malldelivery.lojista.repository.PerfilRepository;
import br.com.malldelivery.lojista.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    public Usuario criar(String username, String password, int idPerfil) throws LojaException {

        Optional<Perfil> optRole = this.perfilRepository.findById(idPerfil);

        if (optRole.isEmpty())
            throw new LojaException("perfil", "Perfil não encontrado");

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.getPerfis().add(optRole.get());

        this.usuarioRepository.save(usuario);

        return usuario;
    }

    public List<Usuario> obterTodos() {
        return this.usuarioRepository.findAll();
    }

    public Usuario obterUsuarioPorId(int id) {
        Optional<Usuario> optUsuario = this.usuarioRepository.findById(id);
        if (optUsuario.isEmpty())
            return null;
        return optUsuario.get();
    }
    public Usuario obterUsuarioPorUsernameAndPassword(String username, String password) {
        Optional<Usuario> optUsuario = this.usuarioRepository.findByUsernameAndPassword(username, password);
        if (optUsuario.isEmpty())
            return null;
        return optUsuario.get();
    }

}
