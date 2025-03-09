package br.com.iaragames.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.iaragames.beans.Jogo;
import java.util.List;

@Repository
public interface JogoDao extends CrudRepository<Jogo, Integer> {
    List<Jogo> findByNomeContaining(String nome);
}
