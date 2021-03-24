package lab.aulaDIO;

import lab.aulaDIO.entity.Aluno;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();
        List<Aluno> alunos = alunoDAO.list();

        alunos.stream().forEach(System.out::println);

        Aluno alunoParaConsulta = alunoDAO.getById(3);
        //System.out.println(alunoParaConsulta);

        Aluno alunoParaInsercao = new Aluno( "Matheus", 43, "SP");

        //alunoDAO.create(alunoParaInsercao);

        //alunoDAO.delete(5);
        //alunoDAO.list().stream().forEach(System.out::println);

        Aluno alunoParaAtualizar = alunoDAO.getById(4);
        alunoParaAtualizar.setNome("Mariana");
        alunoParaAtualizar.setIdade(21);
        alunoParaAtualizar.setEstado("SP");

        alunoDAO.update(alunoParaAtualizar);
        alunoDAO.list().stream().forEach(System.out::println);
    }
}
