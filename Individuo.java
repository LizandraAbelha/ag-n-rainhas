import java.util.List;

public interface Individuo {
    List<Individuo> recombinar(Individuo pai2);
    Individuo mutar();
    double getAvaliacao();
    boolean isMaximizacao();
}