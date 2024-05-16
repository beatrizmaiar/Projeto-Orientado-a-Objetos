Atualizações nos atalhos do teclado … Em quinta-feira, 1 de agosto de 2024, os atalhos de teclado do Drive serão atualizados para oferecer a navegação por letras iniciais.Saiba mais
import java.util.ArrayList;
import java.util.List;

interface Publicacao {
    String toString();
}

class Artigo implements Publicacao {
    private String nome;
    private String[] autores;

    public Artigo(String nome, String[] autores) {
        this.nome = nome;
        this.autores = autores;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Artigo: " + nome);
        sb.append("\nAutores:");
        for (String autor : autores) {
            sb.append("\n- ").append(autor);
        }
        return sb.toString();
    }
}

class Colecao {
    private List<Publicacao> publicacoes = new ArrayList<>();
    private String nome;

    public Colecao(String nome) {
        this.nome = nome;
    }

    public void addPublicacao(Publicacao publicacao) {
        publicacoes.add(publicacao);
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Coleção: " + nome);
        for (Publicacao publicacao : publicacoes) {
            sb.append("\n").append(publicacao.toString());
        }
        return sb.toString();
    }
}

public class Teste {
    public static void main(String[] args) {
        //criando artigos
        Artigo artigo1 = new Artigo("Artigo 1", new String[]{"Autor A", "Autor B"});
        Artigo artigo2 = new Artigo("Artigo 2", new String[]{"Autor C"});

        //criando coleção
        Colecao colecao = new Colecao("Minha Coleção");
        colecao.addPublicacao(artigo1);
        colecao.addPublicacao(artigo2);

        //imprimindo o número de publicações e artigos
        System.out.println("Número de publicações: " + colecao.getPublicacoes().size());
        int numArtigos = 0;
        for (Publicacao publicacao : colecao.getPublicacoes()) {
            if (publicacao instanceof Artigo) {
                numArtigos++;
            }
        }
        System.out.println("Número de artigos: " + numArtigos);

        //imprimindo o conteúdo de toString() de cada publicação
        System.out.println(colecao.toString());
    }
}