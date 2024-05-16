Atualizações nos atalhos do teclado … Em quinta-feira, 1 de agosto de 2024, os atalhos de teclado do Drive serão atualizados para oferecer a navegação por letras iniciais.Saiba mais
import java.util.ArrayList;
import java.util.List;

public class MatriculaFacade {
    private Escola escola;

    public MatriculaFacade() {
        this.escola = new Escola();
    }

    public void matricular(String nomeAluno, String codCurso) {
        Curso curso = escola.getCurso(codCurso);
        if (curso == null) {
            throw new IllegalArgumentException("Curso não encontrado");
        }

        Aluno aluno = new Aluno(nomeAluno);
        curso.addAluno(aluno);
    }

    public List<String> exibirStatus(String nomeTurma) {
        Turma turma = new Turma(nomeTurma);
        List<String> status = new ArrayList<>();

        Curso curso = turma.getCurso();
        if (curso != null) {
            status.add("Curso: " + curso.getNome());
            Aluno[] alunos = turma.getAlunos();
            for (Aluno aluno : alunos) {
                status.add("Aluno: " + aluno.getNome());
            }
        } else {
            status.add("Turma não encontrada");
        }

        return status;
    }
}

class MatriculaGUI {
    private MatriculaFacade facade;

    public MatriculaGUI(MatriculaFacade facade) {
        this.facade = facade;
    }

    public void matricular(String nomeAluno, String codCurso) {
        facade.matricular(nomeAluno, codCurso);
    }

    public List<String> exibirStatus(String nomeTurma) {
        return facade.exibirStatus(nomeTurma);
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        MatriculaFacade facade = new MatriculaFacade();
        MatriculaGUI gui = new MatriculaGUI(facade);

        gui.matricular("João", "POO2");
        List<String> status = gui.exibirStatus("Turma POO2");

        for (String info : status) {
            System.out.println(info);
        }
    }
}

class Curso {
    private String nome;
    private List<Aluno> alunos;

    public Curso(String nome) {
        this.nome = nome;
        this.alunos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }
}

class Turma {
    private String nome;
    private Curso curso;

    public Turma(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public Aluno[] getAlunos() {
        return new Aluno[0];
    }
}

class Aluno {
    private String nome;

    public Aluno(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

class Escola {
    public Curso getCurso(String codCurso) {
        return null;
    }
}
