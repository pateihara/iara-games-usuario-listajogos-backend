package br.com.iaragames.dao;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.com.iaragames.beans.Usuario;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
    List<Usuario> findByNomeContainingAndEmailContaining(String nome, String email);
    Usuario findByEmailAndSenha(String email, String senha);
}

