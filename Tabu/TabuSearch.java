package trabalho.otimizacao.de.algoritmos.Tabu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import trabalho.otimizacao.de.algoritmos.Annealing.KnapsackData;

/**
 *
 * @author Eduardo Dumke - M95949
 */
public class TabuSearch{

    double pesoMaximoMochila = 400;     // Peso máximo Mochila, padrão, alterada por parametro
    private int maxIteracoes = 400;     // Iterações sem melhora

    private List<String> itemNames = new ArrayList<String>();
    private List<Integer> pesos = new ArrayList<Integer>();
    private List<Integer> valores = new ArrayList<Integer>();
    
    private double alpha;               // Somatório de todas as utilidades

    private int[] solucaoAtual;
    private int[] solucaoMelhor;
    private double melhorAvaliacao;
    private int melhorIteracao = 0;     // Melhor iteração

    private List<Integer> tabu;

    public void Busca(int tamanhoMochila, KnapsackData knapsackData) {
        pesoMaximoMochila = tamanhoMochila;
        
        for (int i = 0; i < knapsackData.getSize(); i++) {
            itemNames.add(knapsackData.getItem(i).getNome());
            pesos.add(knapsackData.getItem(i).getWeight());
            valores.add(knapsackData.getItem(i).getValue());
        }
        
        tabu = new ArrayList<>();

        solucaoAtual = new int[knapsackData.getSize()];
        solucaoMelhor = new int[knapsackData.getSize()];
        initAlpha();

        initFirstSolution();

        melhorAvaliacao = avaliacao(solucaoAtual);
        solucaoMelhor = solucaoAtual.clone();
        melhorIteracao = 0;
    }

    private void initAlpha() {
        for (double d : valores) {
            alpha += d;
        }
    }

    private void initFirstSolution() {
        Random r = new Random();

        for (int i = 0; i < solucaoAtual.length; i++) {
            //currentSolution[i] = r.nextInt(2);
            solucaoAtual[i] = 1;
        }
    }

    /**
     * Método principal
     */
    public void run() {
        int itAtual = 0;
        int random = 0;

        while ((itAtual - melhorIteracao) < maxIteracoes) {
            itAtual++;

            int[] bestNeighbor = buscarMelhorVizinho(solucaoAtual);
            solucaoAtual = bestNeighbor.clone();
            double aval = avaliacao(bestNeighbor);

            if (aval > melhorAvaliacao) {
                melhorAvaliacao = aval;
                melhorIteracao = itAtual;
                solucaoMelhor = bestNeighbor;
            }
        }
    }
    
    public void run2() {
        int itAtual = 0;
        int random = 0;

        while ((itAtual - melhorIteracao) < maxIteracoes) {
            itAtual++;
            //random = getRandomPosition();

            if (random == itemNames.size()) {
                random = 0;
            }

            System.out.println("\n" + Arrays.toString(solucaoAtual));
            System.out.println("Random: " + random);

            if (!isTabu(random)) {
                addToTabu(random);

                if (solucaoAtual[random] == 0) {
                    solucaoAtual[random] = 1;
                } else {
                    solucaoAtual[random] = 0;
                }
            } else {
                if (funcaoAspiracao2(random, solucaoAtual.clone())) {
                    if (solucaoAtual[random] == 0) {
                        solucaoAtual[random] = 1;
                    } else {
                        solucaoAtual[random] = 0;
                    }
                }
            }

            int[] melhorVizinho = buscarMelhorVizinho(solucaoAtual);

            double aval = avaliacao(melhorVizinho);

            System.out.println("Iteracao: " + itAtual + " avaliacao: " + aval);

            if (aval > melhorAvaliacao) {
                melhorAvaliacao = aval;
                melhorIteracao = itAtual;
                solucaoMelhor = melhorVizinho;
            }
            random++;
        }
    }
    
    /**
     * Encontra o melhor vizinho da solução.
     *
     * @param solucaoAtual
     * @return
     */
    private int[] buscarMelhorVizinho(int[] solucaoAtual) {
        int[][] vizinhos = new int[solucaoAtual.length][solucaoAtual.length];

        for (int i = 0; i < solucaoAtual.length; i++) {
            int[] temp = solucaoAtual.clone();
            if (temp[i] == 0) {
                temp[i] = 1;
            } else {
                temp[i] = 0;
            }

            vizinhos[i] = temp.clone();
        }

        int[] melhorVizinho = new int[solucaoMelhor.length];
        double melhorValor = 0;
        int tabuPos = 0;

        for (int i = 0; i < vizinhos.length; i++) {
            double val = avaliacao(vizinhos[i]);
            if (val > melhorValor) {
                if (isTabu(i)) {
                    if (funcaoAspiracao(vizinhos[i])) {
                        melhorVizinho = vizinhos[i].clone();
                        melhorValor = val;
                        tabuPos = i;
                    }
                } else {
                    melhorVizinho = vizinhos[i].clone();
                    melhorValor = val;
                    tabuPos = i;
                }
            }
        }
        addToTabu(tabuPos);
        return melhorVizinho;
    }

    /**
     * Avalia se a solução gerada é melhor
     */
    private double avaliacao(int[] solution) {
        double valor = 0;
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] == 1) {
                valor += valores.get(i);
            }
        }

        double peso = 0;
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] == 1) {
                peso += pesos.get(i);
            }
        }

        //System.out.println("Aval Valor: " + valor);
        //System.out.println("Aval peso: " + peso);
        double max = Math.max(0, peso - pesoMaximoMochila);

        return valor - alpha * max;
    }

    /**
     * Adiciona para a lista tabu caso o tamanho dela for maior que 15
     *
     * @param value
     */
    private void addToTabu(int value) {
        if (tabu.size() > 15) {
            tabu.remove(0);
        }
        tabu.add(value);
    }

    private boolean isTabu(int i) {
        return tabu.contains(i);
    }

    /**
     * Função que avalia se deve ser permitido um movimento tabu
     */
    private boolean funcaoAspiracao(int[] solucao) {
        double valor = 0;

        for (int i = 0; i < solucao.length; i++) {
            if (solucao[i] == 1) {
                valor += valores.get(i);
            }
        }

        double peso = 0;
        for (int i = 0; i < solucao.length; i++) {
            if (solucao[i] == 1) {
                peso += pesos.get(i);
            }
        }

        //System.out.println("Aval Beneficio: " + beneficio);
        //System.out.println("Aval peso: " + peso);
        double max = Math.max(0, peso - pesoMaximoMochila);

        double aval = valor - alpha * max;

        return aval > melhorAvaliacao;
    }
    
    private boolean funcaoAspiracao2(int posicao, int[] solucao) {
        if (solucao[posicao] == 0) {
            solucao[posicao] = 1;

        } else {
            solucao[posicao] = 0;
        }

        double valor = 0;

        for (int i = 0; i < solucao.length; i++) {
            if (solucao[i] == 1) {
                valor += valores.get(i);
            }
        }

        double peso = 0;

        for (int i = 0; i < solucao.length; i++) {
            if (solucao[i] == 1) {
                peso += pesos.get(i);
            }
        }
        
        System.out.println("Aval Valor: " + valor);
        System.out.println("Aval peso: " + peso);

        double max = Math.max(0, peso - pesoMaximoMochila);
        double aval = valor - alpha * max;
        return aval > melhorAvaliacao;

    }
        
    // Imprime
    public void printData() {
        System.out.println("Itens Mochila: " + Arrays.toString(solucaoMelhor));
        System.out.println("Valor Total: " + melhorAvaliacao);
        System.out.println("Melhor Interação: " + melhorIteracao);

        double valorTotal = 0;
        double pesoTotal =  0;
        for (int i = 0; i < solucaoMelhor.length; i++) {
            if (solucaoMelhor[i] == 1) {
                System.out.println("Item: " + itemNames.get(i) + " Peso: " + pesos.get(i) + " Valor: " + valores.get(i));
                valorTotal += valores.get(i);
                pesoTotal += pesos.get(i);
            }
        }
        System.out.println("Valor real: " + valorTotal);
        System.out.println("Peso real: " + pesoTotal);
    }
    
}