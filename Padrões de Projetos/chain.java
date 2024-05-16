import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//handler
interface Handler {
    void setNext(Handler next);
    void handleRequest(char character);
    void printStatistics();
}

//concrete Handler for space
class SpaceHandler implements Handler {
    private Handler next;
    private int spaceCount;

    public SpaceHandler() {
        spaceCount = 0;
    }

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(char character) {
        if (character == ' ') {
            spaceCount++;
        } else if (next != null) {
            next.handleRequest(character);
        }
    }

    @Override
    public void printStatistics() {
        System.out.println("Número de espaços: " + spaceCount);
    }
}

//concrete Handler para a letra a
class AHandler implements Handler {
    private Handler next;
    private int aCount;

    public AHandler() {
        aCount = 0;
    }

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(char character) {
        if (Character.toLowerCase(character) == 'a') {
            aCount++;
        } else if (next != null) {
            next.handleRequest(character);
        }
    }

    @Override
    public void printStatistics() {
        System.out.println("Número de letras 'a': " + aCount);
    }
}

//concrete Handler para o ponto
class DotHandler implements Handler {
    private Handler next;
    private int dotCount;

    public DotHandler() {
        dotCount = 0;
    }

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(char character) {
        if (character == '.') {
            dotCount++;
        } else if (next != null) {
            next.handleRequest(character);
        }
    }

    @Override
    public void printStatistics() {
        System.out.println("Número de pontos: " + dotCount);
    }
}

//client
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, forneça o caminho para um arquivo de texto como argumento.");
            return;
        }

        //criação da cadeia de responsabilidade
        Handler spaceHandler = new SpaceHandler();
        Handler aHandler = new AHandler();
        Handler dotHandler = new DotHandler();

        spaceHandler.setNext(aHandler);
        aHandler.setNext(dotHandler);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    spaceHandler.handleRequest(c);
                }
            }
            reader.close();

            spaceHandler.printStatistics();
            aHandler.printStatistics();
            dotHandler.printStatistics();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}

