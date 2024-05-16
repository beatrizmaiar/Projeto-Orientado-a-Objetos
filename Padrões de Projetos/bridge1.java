import java.util.LinkedList;
import java.util.List;

//classe base Queue
class Queue {
    protected List<Object> list;

    public Queue() {
        list = new LinkedList<>();
    }

    public Queue(List<Object> list) {
        this.list = list;
    }

    public Object dequeue() {
        if (!isEmpty()) {
            return list.remove(0);
        }
        return null;
    }

    public void enqueue(Object o) {
        list.add(o);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}

//subclasse FIFOQueue usando Bridge
class FIFOQueue extends Queue {
    public FIFOQueue() {
        super();
    }

    public FIFOQueue(List<Object> list) {
        super(list);
    }
}

//subclasse ArrayListQueue
class ArrayListQueue extends Queue {
    public ArrayListQueue() {
        super();
    }
}

//subclasse VectorQueue
class VectorQueue extends Queue {
    public VectorQueue() {
        super();
    }
}

