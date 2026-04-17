import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Ag {
    private Random gerador = new Random();

    public Individuo executar(Factory factory, int n, int elite, int qtdGeracoes) {
        List<Individuo> popIni = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            popIni.add(factory.getInstance());
        }

        for (int g = 0; g < qtdGeracoes; g++) {
   
            List<Individuo> filhos = new ArrayList<>();
            List<Individuo> auxList = new ArrayList<>(popIni);
            Collections.shuffle(auxList); 

            for (int i = 0; i < auxList.size() - 1; i += 2) {
                Individuo pai1 = auxList.get(i);
                Individuo pai2 = auxList.get(i + 1);
                filhos.addAll(pai1.recombinar(pai2));
            }


            List<Individuo> mutantes = new ArrayList<>();
            for (Individuo ind : popIni) {
                mutantes.add(ind.mutar());
            }


            List<Individuo> join = new ArrayList<>();
            join.addAll(popIni);
            join.addAll(filhos);
            join.addAll(mutantes);

            join.sort(Comparator.comparingDouble(Individuo::getAvaliacao));

            List<Individuo> novaPop = new ArrayList<>();

  
            for (int i = 0; i < elite; i++) {
                novaPop.add(join.get(i));
            }

            List<Individuo> roletaList = aplicarRoleta(join, n - elite);
            novaPop.addAll(roletaList);

            popIni = novaPop; 

            Individuo melhorDaGeracao = popIni.get(0);
            System.out.println("Geração " + g + " | Melhor: " + melhorDaGeracao);


            if (melhorDaGeracao.getAvaliacao() == 0) {
                System.out.println("SOLUÇÃO PERFEITA ENCONTRADA!");
                break;
            }
        }

        popIni.sort(Comparator.comparingDouble(Individuo::getAvaliacao));
        return popIni.get(0);
    }

    private List<Individuo> aplicarRoleta(List<Individuo> join, int qtdSelecionar) {
        List<Individuo> selecionados = new ArrayList<>();
        
        
        double somaAvaliacoes = 0;
        for (Individuo ind : join) {

            somaAvaliacoes += 1.0 / (ind.getAvaliacao() + 1.0); 
        }

        for (int i = 0; i < qtdSelecionar; i++) {
            double valorSorteado = gerador.nextDouble() * somaAvaliacoes;
            double somaParcial = 0;

            for (Individuo ind : join) {
                somaParcial += 1.0 / (ind.getAvaliacao() + 1.0);
                if (somaParcial >= valorSorteado) {
                    selecionados.add(ind);
                    break;
                }
            }
        }
        return selecionados;
    }
}