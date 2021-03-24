package lab.aulaDIO.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nome;

    @Column
    private int idade;

    @Column
    private String estado;

    public Aluno(int id, String nome, int idade, String estado) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.estado = estado;
    }

    public Aluno(String nome, int idade, String estado) {
        this.nome = nome;
        this.idade = idade;
        this.estado = estado;
    }

    public Aluno() { }
}
