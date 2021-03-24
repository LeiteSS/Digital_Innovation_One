package lab.aulaDIO;

import lab.aulaDIO.entity.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public List<Aluno> list() {
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM aluno;";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String estado = rs.getString("estado");

                alunos.add(new Aluno(
                        id,
                        nome,
                        idade,
                        estado
                ));
            }
        } catch (SQLException e) {
            System.out.println("Listagem de Alunos Falhou!");
            e.printStackTrace();
        }

        return alunos;
    }

    public Aluno getById(int id) {
        Aluno aluno = new Aluno();

        try (Connection conn = ConnectionFactory.getConnection()) {
//            ?: representa o parâmetro dado
            String sql = "SELECT * FROM aluno WHERE id = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Listagem de Alunos Falhou");
            e.printStackTrace();
        }

        return aluno;
    }

    public void create(Aluno aluno) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO aluno(nome, idade, estado) VALUES(?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
//            parameteIndex: representa o primeiro ? no comando sql
            stmt.setString(1, aluno.getNome());
//            parameteIndex: representa o segundo ? no comando sql
            stmt.setInt(2, aluno.getIdade());
//            parameteIndex: representa o terceiro ? no comando sql
            stmt.setString(3, aluno.getEstado());
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Inserção Bem Sucedida!. Foi adicionada " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Inserção FALHOU!");
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "DELETE FROM aluno WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Aluno Excluido Com Sucesso! Foi deletado " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Exclusão Falhou!");
            e.printStackTrace();
        }
    }

    public void update(Aluno aluno) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
//            parameteIndex: representa o primeiro ? no comando sql
            stmt.setString(1, aluno.getNome());
//            parameteIndex: representa o segundo ? no comando sql
            stmt.setInt(2, aluno.getIdade());
//            parameteIndex: representa o terceiro ? no comando sql
            stmt.setString(3, aluno.getEstado());
            stmt.setInt(4, aluno.getId());
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Atualização Bem Sucedida! Foi mudada " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Atualização FALHOU!");
            e.printStackTrace();
        }
    }
}
