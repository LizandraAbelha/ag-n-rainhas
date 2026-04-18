public class Main {
    public static void main(String[] args) {
        int[] cenariosDeTeste = {4, 6, 8, 10};  
        int tamanhoPopulacao = 100; 
        int tamanhoElite = 10;      
        int limiteGeracoes = 10000; 

        System.out.println("=== INICIANDO BATERIA DE TESTES (AG1) ===");
        System.out.println("Limite máximo: " + limiteGeracoes + " gerações por teste.");
        System.out.println("---------------------------------------------------");

        for (int numRainhas : cenariosDeTeste) {
            System.out.println("\n-> Iniciando teste para " + numRainhas + " Rainhas...");
            
            Factory factory = new IndNRainhasFactory(numRainhas);
            Ag ag = new Ag();
            
            long tempoInicio = System.currentTimeMillis();
            
            Individuo campeao = ag.executar(factory, tamanhoPopulacao, tamanhoElite, limiteGeracoes);
            
            long tempoFim = System.currentTimeMillis();

            System.out.println("Resultado Final : " + campeao.toString());
            System.out.println("Tempo decorrido : " + (tempoFim - tempoInicio) + " ms");
            System.out.println("---------------------------------------------------");
        }
    }
}