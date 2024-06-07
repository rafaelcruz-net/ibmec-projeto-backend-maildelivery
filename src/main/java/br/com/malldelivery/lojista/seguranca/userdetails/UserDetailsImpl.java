package br.com.malldelivery.lojista.seguranca.userdetails;

import br.com.malldelivery.lojista.model.Perfil;
import br.com.malldelivery.lojista.model.Usuario;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {

    private Usuario usuario;
    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }
    public UserDetailsImpl() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        for (Perfil perfil: this.usuario.getPerfis()) {
            roles.add(new SimpleGrantedAuthority(perfil.getNome()));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return this.usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return this.usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
