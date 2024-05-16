Atualizações nos atalhos do teclado … Em quinta-feira, 1 de agosto de 2024, os atalhos de teclado do Drive serão atualizados para oferecer a navegação por letras iniciais.Saiba mais
import java.util.ArrayList;
import java.util.List;

//componente
interface Participante {
    int getAssento();
}

//folha participante
class Individuo implements Participante {
    private int assento;

    public Individuo(int assento) {
        this.assento = assento;
    }

    @Override
    public int getAssento() {
        return assento;
    }
}

//folha instituição
class Instituicao implements Participante {
    private List<Individuo> membros = new ArrayList<>();

    public void adicionarMembro(Individuo individuo) {
        membros.add(individuo);
    }

    @Override
    public int getAssento() {
        int totalAssentos = 0;
        for (Individuo individuo : membros) {
            totalAssentos += individuo.getAssento();
        }
        return totalAssentos;
    }
}

//composite
class Congresso {
    private List<Participante> participantes = new ArrayList<>();

    public void adicionarParticipante(Participante participante) {
        participantes.add(participante);
    }

    public int totalParticipantes() {
        return participantes.size();
    }

    public int totalAssentos() {
        int totalAssentos = 0;
        for (Participante participante : participantes) {
            totalAssentos += participante.getAssento();
        }
        return totalAssentos;
    }
}

//exemplo
public class Main {
    public static void main(String[] args) {
        Congresso congresso = new Congresso();

        Individuo individuo1 = new Individuo(1);
        Individuo individuo2 = new Individuo(1);
        Instituicao instituicao = new Instituicao();
        instituicao.adicionarMembro(new Individuo(2));
        instituicao.adicionarMembro(new Individuo(2));

        congresso.adicionarParticipante(individuo1);
        congresso.adicionarParticipante(individuo2);
        congresso.adicionarParticipante(instituicao);

        System.out.println("Total de participantes: " + congresso.totalParticipantes());
        System.out.println("Total de assentos: " + congresso.totalAssentos());
    }
}