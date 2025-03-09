package br.com.iaragames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.iaragames.beans.Usuario;
import br.com.iaragames.dao.UsuarioDao;

@RestController
@RequestMapping("/usuario") // Agora todas as rotas começam com /usuario
@CrossOrigin("*")
public class UsuarioController {
    
    @Autowired
    private UsuarioDao dao;

    // 🟢 LISTAR TODOS OS USUÁRIOS
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = (List<Usuario>) dao.findAll();
        return usuarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuarios);
    }

    // 🔍 BUSCAR UM USUÁRIO PELO ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable int id) {
        return dao.findById(id)
                  .map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // ➕ CADASTRAR UM NOVO USUÁRIO
    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = dao.save(usuario);
            return ResponseEntity.ok(novoUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // ✏️ ATUALIZAR UM USUÁRIO EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable int id, @RequestBody Usuario usuarioAtualizado) {
        return dao.findById(id).map(usuario -> {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha()); 
            dao.save(usuario);
            return ResponseEntity.ok(usuario);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 🗑️ DELETAR UM USUÁRIO
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
		if (dao.existsById(id)) {
			dao.deleteById(id);
			return ResponseEntity.noContent().build(); // ✅ Retorna 204 No Content
		} else {
			return ResponseEntity.notFound().build();  // ✅ Retorna 404 Not Found
		}
	}
}