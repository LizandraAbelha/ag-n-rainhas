public class Main {
    public static void main(String[] args) {
        int numRainhas = 8; 
        int tamanhoPopulacao = 100; 
        int tamanhoElite = 10; 
        int numGeracoes = 500; 

        System.out.println("Iniciando Algoritmo Genético para " + numRainhas + " Rainhas...");

        Factory factory = new IndNRainhasFactory(numRainhas);
        

        Ag ag = new Ag();
        
        Individuo superCampeao = ag.executar(factory, tamanhoPopulacao, tamanhoElite, numGeracoes);

        System.out.println("\n--- RESULTADO FINAL ---");
        System.out.println("Melhor Solução Encontrada: " + superCampeao.toString());
    }
}