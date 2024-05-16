import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(dadosRio dadosRio);
}

interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

//dados do rio
class dadosRio {
    private double temperature;
    private double pH;
    private double atmosphericPressure;

    public dadosRio(double temperature, double pH, double atmosphericPressure) {
        this.temperature = temperature;
        this.pH = pH;
        this.atmosphericPressure = atmosphericPressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getPH() {
        return pH;
    }

    public double getAtmosphericPressure() {
        return atmosphericPressure;
    }
}

//rio da Amazônia
class AmazonRiver implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private dadosRio dadosRio;

    public AmazonRiver() {
        //dados do rio
        this.dadosRio = new dadosRio(0.0, 7.0, 1013.0);
    }

    public void setdadosRio(double temperature, double pH, double atmosphericPressure) {
        this.dadosRio = new dadosRio(temperature, pH, atmosphericPressure);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(dadosRio);
        }
    }
}

//faculdade federal
class FederalUniversity implements Observer {
    private String name;

    public FederalUniversity(String name) {
        this.name = name;
    }

    @Override
    public void update(dadosRio dadosRio) {
        System.out.println(name + " received updated river data:");
        System.out.println("Temperature: " + dadosRio.getTemperature());
        System.out.println("pH: " + dadosRio.getPH());
        System.out.println("Atmospheric Pressure: " + dadosRio.getAtmosphericPressure());
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        //criação dos rios
        AmazonRiver rioNegro = new AmazonRiver();
        AmazonRiver rioSolimoes = new AmazonRiver();
        AmazonRiver rioMadeira = new AmazonRiver();

        //criação das faculdades
        FederalUniversity ufAmazonas = new FederalUniversity("Universidade Federal do Amazonas (UFAM)");
        FederalUniversity ufPara = new FederalUniversity("Universidade Federal do Pará (UFPA)");
        FederalUniversity ufAcre = new FederalUniversity("Universidade Federal do Acre (UFAC)");
        FederalUniversity ufRondonia = new FederalUniversity("Universidade Federal de Rondônia (UNIR)");

        rioNegro.addObserver(ufAmazonas);
        rioSolimoes.addObserver(ufPara);
        rioMadeira.addObserver(ufAmazonas);
        rioMadeira.addObserver(ufAcre);
        rioMadeira.addObserver(ufRondonia);
      
        rioNegro.setdadosRio(26.0, 7.8, 1012.0);
        rioSolimoes.setdadosRio(27.0, 6.8, 1012.2);
        rioMadeira.setdadosRio(24.5, 7.0, 1013.3);
    }
}