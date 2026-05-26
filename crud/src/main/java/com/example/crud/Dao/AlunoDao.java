package com.example.crud.Dao;

import com.example.crud.ConexaoJDBC;
import com.example.crud.model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    // CREATE
    public void salvar(Aluno aluno) throws SQLException {

        String sql = "INSERT INTO aluno (nome, matricula) VALUES (?, ?)";

        try (Connection conn = ConexaoJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());

            stmt.executeUpdate();
        }
    }

    // READ - LISTAR TODOS
    public List<Aluno> listar() throws SQLException {

        List<Aluno> lista = new ArrayList<>();

        String sql = "SELECT * FROM aluno";

        try (Connection conn = ConexaoJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno a = new Aluno(
                        rs.getString("nome"),
                        rs.getString("matricula")
                );

                lista.add(a);
            }
        }

        return lista;
    }

    // READ - BUSCAR POR MATRICULA (NOVO)
    public Aluno buscarPorMatricula(String matricula) throws SQLException {

        String sql = "SELECT * FROM aluno WHERE matricula = ?";

        try (Connection conn = ConexaoJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Aluno(
                        rs.getString("nome"),
                        rs.getString("matricula")
                );
            }

            return null;
        }
    }

    // UPDATE
    public void atualizar(String matricula, Aluno aluno) throws SQLException {

        String sql = "UPDATE aluno SET nome = ? WHERE matricula = ?";

        try (Connection conn = ConexaoJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, matricula);

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deletar(String matricula) throws SQLException {

        String sql = "DELETE FROM aluno WHERE matricula = ?";

        try (Connection conn = ConexaoJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);

            stmt.executeUpdate();
        }
    }
}
