import java.util.ArrayList;
import java.util.List;

//observer
interface Observer {
    void update(RiverData riverData);
}

//observavel
interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

//dados do rio
class RiverData {
    private double temperature;
    private double pH;
    private double atmosphericPressure;

    public RiverData(double temperature, double pH, double atmosphericPressure) {
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
    private RiverData riverData;

    public AmazonRiver() {
        this.riverData = new RiverData(0.0, 7.0, 1013.0);
    }

    public void setRiverData(double temperature, double pH, double atmosphericPressure) {
        this.riverData = new RiverData(temperature, pH, atmosphericPressure);
        notifyObservers();
    }

    public RiverData getRiverData() {
        return riverData;
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
            observer.update(riverData);
        }
    }
}

//faculdades federais
class FederalUniversity implements Observer {
    private String name;

    public FederalUniversity(String name) {
        this.name = name;
    }

    @Override
    public void update(RiverData riverData) {
        System.out.println(name + " received updated river data:");
        System.out.println("Temperature: " + riverData.getTemperature());
        System.out.println("pH: " + riverData.getPH());
        System.out.println("Atmospheric Pressure: " + riverData.getAtmosphericPressure());
        System.out.println();
    }
}

//mediator
class RiverMediator {
    private List<AmazonRiver> rivers = new ArrayList<>();

    public void addRiver(AmazonRiver river) {
        rivers.add(river);
    }

    public void checkTemperatureAlert(double newTemperature) {
        double totalTemperatureChange = 0;
        for (AmazonRiver river : rivers) {
            totalTemperatureChange += Math.abs(newTemperature - river.getRiverData().getTemperature());
        }
        if (totalTemperatureChange >= 10) {
            System.out.println("ALERTA: mudança significante de temperatura no rio");
        }
    }

    public void checkPHAlert(double newPH) {
        for (AmazonRiver river : rivers) {
            double deltaPH = Math.abs(newPH - river.getRiverData().getPH());
            if (deltaPH >= 5 ) {
                System.out.println("ALERTA: mudança significante do PH no Rio");
                break; // Só emite um alerta se a mudança for detectada em pelo menos um rio
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        RiverMediator mediator = new RiverMediator();

        //rios da Amazônia
        AmazonRiver rioNegro = new AmazonRiver();
        AmazonRiver rioSolimoes = new AmazonRiver();
        AmazonRiver rioMadeira = new AmazonRiver();

        //adicionando os rios ao Mediador
        mediator.addRiver(rioNegro);
        mediator.addRiver(rioSolimoes);
        mediator.addRiver(rioMadeira);

        //criação das faculdades federais
        FederalUniversity ufAmazonas = new FederalUniversity("Universidade Federal do Amazonas (UFAM)");
        FederalUniversity ufPara = new FederalUniversity("Universidade Federal do Pará (UFPA)");
        FederalUniversity ufAcre = new FederalUniversity("Universidade Federal do Acre (UFAC)");
        FederalUniversity ufRondonia = new FederalUniversity("Universidade Federal de Rondônia (UNIR)");

        //adicionando as faculdades como observadores dos rios
        rioNegro.addObserver(ufAmazonas);
        rioSolimoes.addObserver(ufPara);
        rioMadeira.addObserver(ufAmazonas);
        rioMadeira.addObserver(ufAcre);
        rioMadeira.addObserver(ufRondonia);

        //simulação de alteração nos dados do rio
        rioNegro.setRiverData(25.5, 6.8, 1012.5);
        rioSolimoes.setRiverData(26.0, 6.9, 1013.2);
        rioMadeira.setRiverData(25.7, 7.1, 1013.8); //aumento de temperatura
        mediator.checkTemperatureAlert(25.7);
        mediator.checkPHAlert(7.1); //alteração no pH
    }
}

