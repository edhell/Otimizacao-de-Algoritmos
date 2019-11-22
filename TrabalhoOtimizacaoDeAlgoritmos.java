/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.otimizacao.de.algoritmos;

import trabalho.otimizacao.de.algoritmos.Annealing.ItemMochila;
import trabalho.otimizacao.de.algoritmos.Annealing.KnapsackData;
import trabalho.otimizacao.de.algoritmos.Annealing.KnapsackSolver;
import trabalho.otimizacao.de.algoritmos.Annealing.SAStrategy;
import trabalho.otimizacao.de.algoritmos.Annealing.SolucaoMochila;
import trabalho.otimizacao.de.algoritmos.Arvore.ArvoreGeradora;
import trabalho.otimizacao.de.algoritmos.Fluxo.FluxoMaximo;
import trabalho.otimizacao.de.algoritmos.Tabu.TabuSearch;

/**
 *
 * @author Eduardo Dumke - M95949
 * email: edhell2@gmail.com
 */
public class TrabalhoOtimizacaoDeAlgoritmos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 1) Problema do fluxo máximo
        System.out.println("Problema do fluxo máximo:\n");
        fluxo();
        
        System.out.println("####################################################################");
        
        // 2) Árvore geradora mínima
        System.out.println("Árvore geradora mínima:\n");
        arvore();
                
        System.out.println("####################################################################");
        
        // 3) Busca tabu para o problema da mochila
        System.out.println("Busca tabu (mochila):\n");
        tabu();
        
        System.out.println("####################################################################");
        
        // 4) Simulated annealing para o problema da mochila
        System.out.println("Simulated annealing (mochila):\n");
        annealing(400);
        System.out.println("####################################################################");
        
    }

    private static void fluxo() {
        FluxoMaximo fluxo = new FluxoMaximo();
        fluxo.gerarMatriz(10);
        fluxo.run();
    }

    private static void arvore() {
        ArvoreGeradora arvore = new ArvoreGeradora();
        arvore.addItem(1, 2, 1);
        arvore.addItem(1, 4, 2);
        arvore.addItem(2, 4, 4);
        arvore.addItem(2, 3, 6);
        arvore.addItem(2, 5, 7);
        arvore.addItem(4, 5, 9);
        arvore.addItem(5, 6, 4);
        arvore.addItem(3, 5, 8);
        arvore.addItem(3, 6, 5);
        
        arvore.run();
    }
    
    private static void tabu() {
        KnapsackData knapsackData = new KnapsackData(400);
        knapsackData.addItem(new ItemMochila("Mapa", 9, 150));
        knapsackData.addItem(new ItemMochila("Bussola", 13, 35));
        knapsackData.addItem(new ItemMochila("Agua", 153, 200));
        knapsackData.addItem(new ItemMochila("Lanche", 50, 160));
        knapsackData.addItem(new ItemMochila("Suco", 15, 60));
        knapsackData.addItem(new ItemMochila("Lata", 68, 45));
        knapsackData.addItem(new ItemMochila("Banana", 27, 60));
        knapsackData.addItem(new ItemMochila("Maça", 39, 40));
        knapsackData.addItem(new ItemMochila("Queijo", 23, 30));
        knapsackData.addItem(new ItemMochila("Cerveja", 52, 10));
        knapsackData.addItem(new ItemMochila("Protetor Solar", 11, 70));
        knapsackData.addItem(new ItemMochila("Camera", 32, 30));
        knapsackData.addItem(new ItemMochila("Camiseta", 24, 15));
        knapsackData.addItem(new ItemMochila("Calça", 48, 10));
        knapsackData.addItem(new ItemMochila("Guarda Chuva", 73, 40));
        knapsackData.addItem(new ItemMochila("Calça camuflada",42, 70));
        knapsackData.addItem(new ItemMochila("Cueca",43, 75));
        knapsackData.addItem(new ItemMochila("Anotações", 22, 80));
        knapsackData.addItem(new ItemMochila("Oculos", 7, 20));
        knapsackData.addItem(new ItemMochila("Toalha", 18, 12));
        knapsackData.addItem(new ItemMochila("Meias", 4, 50));
        knapsackData.addItem(new ItemMochila("Livro", 30, 10));
        
        TabuSearch buscaTabu = new TabuSearch();
        buscaTabu.Busca(400, knapsackData);
        buscaTabu.run();
        buscaTabu.printData();
        
    }

    private static void annealing(int tamanhoMochila) {
        //Define tamanho e possíveis itens:
        KnapsackData knapsackData = new KnapsackData(tamanhoMochila);
        knapsackData.addItem(new ItemMochila("Mapa", 9, 150));
        knapsackData.addItem(new ItemMochila("Bussola", 13, 35));
        knapsackData.addItem(new ItemMochila("Agua", 153, 200));
        knapsackData.addItem(new ItemMochila("Lanche", 50, 160));
        knapsackData.addItem(new ItemMochila("Suco", 15, 60));
        knapsackData.addItem(new ItemMochila("Lata", 68, 45));
        knapsackData.addItem(new ItemMochila("Banana", 27, 60));
        knapsackData.addItem(new ItemMochila("Maça", 39, 40));
        knapsackData.addItem(new ItemMochila("Queijo", 23, 30));
        knapsackData.addItem(new ItemMochila("Cerveja", 52, 10));
        knapsackData.addItem(new ItemMochila("Protetor Solar", 11, 70));
        knapsackData.addItem(new ItemMochila("Camera", 32, 30));
        knapsackData.addItem(new ItemMochila("Camiseta", 24, 15));
        knapsackData.addItem(new ItemMochila("Calça", 48, 10));
        knapsackData.addItem(new ItemMochila("Guarda Chuva", 73, 40));
        knapsackData.addItem(new ItemMochila("Calça camuflada",42, 70));
        knapsackData.addItem(new ItemMochila("Cueca",43, 75));
        knapsackData.addItem(new ItemMochila("Anotações", 22, 80));
        knapsackData.addItem(new ItemMochila("Oculos", 7, 20));
        knapsackData.addItem(new ItemMochila("Toalha", 18, 12));
        knapsackData.addItem(new ItemMochila("Meias", 4, 50));
        knapsackData.addItem(new ItemMochila("Livro", 30, 10));
            
        // Variaveis p/ Algoritmo:
        double fatorResfriamento = 0.98;
        double temperaturaFinal = 0.2;
        double temperaturaInicial = 100;
        int amostras = 350;
        
        // Resolver
        KnapsackSolver solver = new KnapsackSolver(knapsackData, new SAStrategy(amostras, temperaturaInicial, temperaturaFinal, fatorResfriamento));
        SolucaoMochila solucao = solver.getSolution();

        // Mostra a solução
        System.out.println("Valor Total: " + solucao.getGainedValue());
        System.out.println("Peso Total: " + solucao.getGainedWeight());
        System.out.println("Tempo de execução: " + solucao.getTakenTime() + " ms");
        System.out.println("Itens da mochila: " + solucao.getPickedItem().size() + ":");
        
        // Mostra itens da Mochila:
        for (ItemMochila item : solucao.getPickedItem()) {
            System.out.println(" * " + item.toString());
            //System.out.println(item.getNome().toString() + " Peso: " + item.getWeight() + " Valor: " + item.getValue());
        }
    }

}
