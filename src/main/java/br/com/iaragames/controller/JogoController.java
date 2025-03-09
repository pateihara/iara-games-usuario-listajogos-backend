package br.com.iaragames.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.iaragames.beans.Jogo;
import br.com.iaragames.dao.JogoDao;

@RestController
@RequestMapping("/jogo") // Todas as rotas começam com /jogo
@CrossOrigin("*")
public class JogoController {

    @Autowired
    private JogoDao dao;

    // 🟢 LISTAR TODOS OS JOGOS
    @GetMapping
    public ResponseEntity<List<Jogo>> getAll() {
        List<Jogo> jogos = (List<Jogo>) dao.findAll();
        return jogos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(jogos);
    }

    // 🔍 BUSCAR UM JOGO PELO ID
    @GetMapping("/{id}")
    public ResponseEntity<Jogo> getJogo(@PathVariable Integer id) {
        return dao.findById(id)
                  .map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // ➕ CADASTRAR UM NOVO JOGO
    @PostMapping
    public ResponseEntity<Jogo> saveJogo(@RequestBody Jogo jogo) {
        try {
            Jogo novoJogo = dao.save(jogo);
            return ResponseEntity.ok(novoJogo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // ✏️ ATUALIZAR UM JOGO EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<Jogo> updateJogo(@PathVariable Integer id, @RequestBody Jogo jogoAtualizado) {
        return dao.findById(id).map(jogo -> {
            jogo.setNome(jogoAtualizado.getNome());
            jogo.setCategoria(jogoAtualizado.getCategoria());
            jogo.setPreco(jogoAtualizado.getPreco());
            dao.save(jogo);
            return ResponseEntity.ok(jogo);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 🗑️ DELETAR UM JOGO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJogo(@PathVariable Integer id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }
}
