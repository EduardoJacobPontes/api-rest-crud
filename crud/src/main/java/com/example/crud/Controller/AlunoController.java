package com.example.crud.Controller;

import com.example.crud.Dao.AlunoDao;
import com.example.crud.model.Aluno;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoDao dao = new AlunoDao();

    // CREATE
    @PostMapping
    public String salvar(@RequestBody Aluno aluno) throws SQLException {
        dao.salvar(aluno);
        return "Aluno criado";
    }

    // READ
    @GetMapping
    public List<Aluno> listar() throws SQLException {
        return dao.listar();
    }

    // UPDATE (IMPORTANTE PARA NOTA COMPLETA)
    @PutMapping("/{matricula}")
    public String atualizar(@PathVariable String matricula,
                            @RequestBody Aluno aluno) throws SQLException {
        dao.atualizar(matricula, aluno);
        return "Aluno atualizado";
    }

    // DELETE
    @DeleteMapping("/{matricula}")
    public String deletar(@PathVariable String matricula) throws SQLException {
        dao.deletar(matricula);
        return "Aluno removido";
    }

    @GetMapping("/{matricula}")
    public Aluno buscar(@PathVariable String matricula) throws SQLException {
        return dao.buscarPorMatricula(matricula);
    }
}
