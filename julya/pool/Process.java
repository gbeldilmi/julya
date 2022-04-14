package julya.pool;

public interface Process<E> {
  public void process(E item) throws RuntimeException;
}
