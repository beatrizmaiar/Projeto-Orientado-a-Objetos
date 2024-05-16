Atualizações nos atalhos do teclado … Em quinta-feira, 1 de agosto de 2024, os atalhos de teclado do Drive serão atualizados para oferecer a navegação por letras iniciais.Saiba mais
import java.util.List;

interface Publicacao {
    void adicionarPublicacao(Publicacao publicacao);
    void removerPublicacao(Publicacao publicacao);
    List<Publicacao> getPublicacoes();
    String getTitulo();
}

class Artigo implements Publicacao {
    private String titulo;

    public Artigo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public void adicionarPublicacao(Publicacao publicacao) {
    }

    @Override
    public void removerPublicacao(Publicacao publicacao) {
    }

    @Override
    public List<Publicacao> getPublicacoes() {

        return null;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }
}

class Jornal implements Publicacao {
    private List<Publicacao> publicacoes;
    private String titulo;

    public Jornal(String titulo, List<Publicacao> publicacoes) {
        this.titulo = titulo;
        this.publicacoes = publicacoes;
    }

    @Override
    public void adicionarPublicacao(Publicacao publicacao) {
        publicacoes.add(publicacao);
    }

    @Override
    public void removerPublicacao(Publicacao publicacao) {
        publicacoes.remove(publicacao);
    }

    @Override
    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }
}

class Caderno implements Publicacao {
    private List<Publicacao> publicacoes;
    private String titulo;

    public Caderno(String titulo, List<Publicacao> publicacoes) {
        this.titulo = titulo;
        this.publicacoes = publicacoes;
    }

    @Override
    public void adicionarPublicacao(Publicacao publicacao) {
        publicacoes.add(publicacao);
    }

    @Override
    public void removerPublicacao(Publicacao publicacao) {
        publicacoes.remove(publicacao);
    }

    @Override
    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }
}

class Revista implements Publicacao {
    private List<Publicacao> publicacoes;
    private String titulo;

    public Revista(String titulo, List<Publicacao> publicacoes) {
        this.titulo = titulo;
        this.publicacoes = publicacoes;
    }

    @Override
    public void adicionarPublicacao(Publicacao publicacao) {
        publicacoes.add(publicacao);
    }

    @Override
    public void removerPublicacao(Publicacao publicacao) {
        publicacoes.remove(publicacao);
    }

    @Override
    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }
}

public class Main {
    public static void main(String[] args) {
        Artigo artigo1 = new Artigo("Artigo 1");
        Artigo artigo2 = new Artigo("Artigo 2");

        Jornal jornal = new Jornal("Jornal A", List.of(artigo1, artigo2));
        Caderno caderno = new Caderno("Caderno X", List.of(artigo1, artigo2));
        Revista revista = new Revista("Revista Z", List.of(artigo1, artigo2));

        System.out.println("Jornal: " + jornal.getTitulo());
        imprimirPublicacoes(jornal);

        System.out.println("\nCaderno: " + caderno.getTitulo());
        imprimirPublicacoes(caderno);

        System.out.println("\nRevista: " + revista.getTitulo());
        imprimirPublicacoes(revista);
    }

    public static void imprimirPublicacoes(Publicacao publicacao) {
        for (Publicacao p : publicacao.getPublicacoes()) {
            System.out.println("- " + p.getTitulo());
        }
    }
}