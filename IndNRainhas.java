import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IndNRainhas implements Individuo {
    private double txMutacao = 0.3; 
    private int[] genes;
    private int qtdGenes;
    private Random gerador = new Random();

    public IndNRainhas(int qtdGenes) {
        this.qtdGenes = qtdGenes;
        this.genes = new int[qtdGenes];
        for (int i = 0; i < qtdGenes; i++) {
            this.genes[i] = gerador.nextInt(qtdGenes);
        }
    }

    public IndNRainhas(int[] genes) {
        this.qtdGenes = genes.length;
        this.genes = genes.clone(); 
    }

    @Override
    public double getAvaliacao() {
        int colisoes = 0;
        for (int i = 0; i < this.qtdGenes - 1; i++) {
            for (int j = i + 1; j < this.qtdGenes; j++) {
                if (genes[i] == genes[j] || 
                    genes[i] == genes[j] + (j - i) || 
                    genes[i] == genes[j] - (j - i)) {
                    colisoes++;
                }
            }
        }
        return colisoes;
    }

    @Override
    public boolean isMaximizacao() {
        return false; 
    }

    @Override
    public List<Individuo> recombinar(Individuo pai2) {
        IndNRainhas p2 = (IndNRainhas) pai2;
        
        int pontoCorte = gerador.nextInt(qtdGenes - 1) + 1;
        
        int[] genesFilho1 = new int[qtdGenes];
        int[] genesFilho2 = new int[qtdGenes];
        
        for (int i = 0; i < qtdGenes; i++) {
            if (i < pontoCorte) {
                genesFilho1[i] = this.genes[i];
                genesFilho2[i] = p2.genes[i];
            } else {
                genesFilho1[i] = p2.genes[i];
                genesFilho2[i] = this.genes[i];
            }
        }
        
        List<Individuo> filhos = new ArrayList<>();
        filhos.add(new IndNRainhas(genesFilho1));
        filhos.add(new IndNRainhas(genesFilho2));
        
        return filhos;
    }

    @Override
    public Individuo mutar() {
        IndNRainhas mutante = new IndNRainhas(this.genes);
        for (int i = 0; i < mutante.qtdGenes; i++) {
            if (gerador.nextDouble() <= txMutacao) {
                mutante.genes[i] = gerador.nextInt(qtdGenes);
            }
        }
        return mutante;
    }

    public int[] getGenes() {
        return genes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < qtdGenes; i++) {
            sb.append(genes[i]);
            if (i < qtdGenes - 1) sb.append(", ");
        }
        sb.append("] -> Colisoes: ").append((int) getAvaliacao());
        return sb.toString();
    }
}