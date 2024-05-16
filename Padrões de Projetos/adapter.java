// Interface comum para notificação
interface INotificador {
    void enviar(String mensagem, String destinatario);
}

// Implementação para notificação por e-mail
class NotificadorEmail implements INotificador {
    public void enviar(String mensagem, String destinatario) {
        System.out.println("Enviando e-mail para " + destinatario + ": " + mensagem);
    }
}

// Adaptador para notificação por SMS
class AdaptadorSMS implements INotificador {
    public void enviar(String mensagem, String destinatario) {
        System.out.println("Enviando SMS para " + destinatario + ": " + mensagem);
    }
}

// Adaptador para notificação push
class AdaptadorPush implements INotificador {
    public void enviar(String mensagem, String destinatario) {
        System.out.println("Enviando notificação push para " + destinatario + ": " + mensagem);
    }
}

// Classe Main para demonstração
public class Main {
    public static void main(String[] args) {
        INotificador notificadorEmail = new NotificadorEmail();
        INotificador adaptadorSMS = new AdaptadorSMS();
        INotificador adaptadorPush = new AdaptadorPush();
      
        notificadorEmail.enviar("Este é um e-mail de teste.", "usuario@example.com");
        adaptadorSMS.enviar("Este é um SMS de teste.", "+1234567890");
        adaptadorPush.enviar("Esta é uma notificação push de teste.", "dispositivo123");
    }
}
