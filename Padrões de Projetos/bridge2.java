import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

//interface para implementação da fila usando List
interface ListQueueImplementor {
    void queue(Object o);
    Object dequeue();
    int size();
    boolean isEmpty();
}

//implementação de fila usando ArrayList
class ArrayListQueueImplementor implements ListQueueImplementor {
    private List<Object> list;

    public ArrayListQueueImplementor() {
        this.list = new ArrayList<>();
    }

    @Override
    public void queue(Object o) {
        list.add(o);
    }

    @Override
    public Object dequeue() {
        if (!isEmpty()) {
            return list.remove(0);
        }
        return null;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}

//implementação de fila usando Vector
class VectorQueueImplementor implements ListQueueImplementor {
    private List<Object> list;

    public VectorQueueImplementor() {
        this.list = new Vector<>();
    }

    @Override
    public void queue(Object o) {
        list.add(o);
    }

    @Override
    public Object dequeue() {
        if (!isEmpty()) {
            return list.remove(0);
        }
        return null;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}

//abstração da fila
abstract class Queue {
    protected ListQueueImplementor implementor;

    public Queue(ListQueueImplementor implementor) {
        this.implementor = implementor;
    }

    public void queue(Object o) {
        implementor.queue(o);
    }

    public Object dequeue() {
        return implementor.dequeue();
    }

    public int size() {
        return implementor.size();
    }

    public boolean isEmpty() {
        return implementor.isEmpty();
    }
}

//fila FIFO
class FIFOQueue extends Queue {
    public FIFOQueue(ListQueueImplementor implementor) {
        super(implementor);
    }
}

