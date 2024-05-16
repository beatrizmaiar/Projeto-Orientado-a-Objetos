import java.net.*;
import java.io.*;

//interface remota
interface RemoteInterface {
    String toUpper(String input) throws RemoteException;
}

//implementação do objeto remoto
class ObjetoRemoto implements RemoteInterface {
    public String toUpper(String input) throws RemoteException {
        return input.toUpperCase();
    }
}

//interface para o Stub
interface StubInterface {
    String toUpper(String input);
}

//stub
class Stub implements StubInterface {
    private RemoteInterface remoteObject;

    public Stub(InetAddress address, int port) {
        try {
            Socket socket = new Socket(address, port);
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject("RemoteObject");
            InputStream in = socket.getInputStream();
            ObjectInputStream objIn = new ObjectInputStream(in);
            remoteObject = (RemoteInterface) objIn.readObject();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String toUpper(String input) {
        try {
            return remoteObject.toUpper(input);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}

//skeleton
class Skeleton {
    private RemoteInterface remoteObject;

    public Skeleton(RemoteInterface remoteObject) {
        this.remoteObject = remoteObject;
    }

    public void servico() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream in = socket.getInputStream();
                ObjectInputStream objIn = new ObjectInputStream(in);
                String methodName = (String) objIn.readObject();
                if (methodName.equals("toUpper")) {
                    String input = (String) objIn.readObject();
                    String result = remoteObject.toUpper(input);
                    OutputStream out = socket.getOutputStream();
                    ObjectOutputStream objOut = new ObjectOutputStream(out);
                    objOut.writeObject(result);
                }
                socket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

//exception customizada para RemoteException
class RemoteException extends Exception {
    public RemoteException(String message) {
        super(message);
    }
}

//cliente
public class Client {
    public static void main(String[] args) {

        ObjetoRemoto objetoRemoto = new ObjetoRemoto();

        Skeleton skeleton = new Skeleton(objetoRemoto);
        Thread skeletonThread = new Thread(skeleton::servico);
        skeletonThread.start();

        Stub stub = new Stub(InetAddress.getLoopbackAddress(), 12345);

        String result = stub.toUpper("hello");
        System.out.println("Resultado: " + result);
    }
}

