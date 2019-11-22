
package trabalho.otimizacao.de.algoritmos.Fluxo;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Eduardo Dumke - M95949
 */
public class FordFulkerson {
    private int quant_vertices;
    private int[][] matrizAdjacencia; 
    private Queue<Integer> fila; 
    private boolean[] visitados;
    private int[] caminho; 
    private int[][] grafoResidual;
    private int inicio;
    private int destino;

    public FordFulkerson(int quant, int matriz[][], int inicio, int destino){
        this.quant_vertices = quant;
        this.matrizAdjacencia = matriz;
        this.grafoResidual = new int[quant][quant];
        this.inicio = inicio;
        this.destino = destino;
        this.caminho = new int[quant];
        this.visitados = new boolean[quant];
        this.fila = new LinkedList<Integer>();
    }
	
    // Executa o algoritmo e retorna o fluxo maximo
    public int run(){
        int fluxoMaximo = 0;
        
        // Faz uma copia da matriz do grafo no grafo residual
        for (int v1 = 0; v1 < this.quant_vertices; v1++){            
            for (int v2 = 0; v2 < this.quant_vertices; v2++){
                grafoResidual[v1][v2] = matrizAdjacencia[v1][v2];
            }
        }
        
        // Busca um caminho ate que todos os caminhos sejam percorridos
        while (buscaCaminho()){
            int fluxo = Integer.MAX_VALUE; // Seta um valor suficiente grande para iniciar a variavel
            int i;
            
            // Percorre o caminho e salva a aresta de menor capacidade para definir o valor do fluxo
            for (int v = this.destino; v != this.inicio; v = this.caminho[v]){
                i = this.caminho[v];
                if(this.grafoResidual[i][v] < fluxo){
                	fluxo = this.grafoResidual[i][v];
                }
            }
            
            // Atualiza os valores no grafo residual
            for (int v = this.destino; v != this.inicio; v = caminho[v]){
                i = caminho[v];
                grafoResidual[i][v] -= fluxo;
                grafoResidual[v][i] += fluxo;
            }
            fluxoMaximo += fluxo;	
        }
        return fluxoMaximo;	
    }
	
    // Faz uma busca em largura no grafo (representado pela matriz de adjacencia)
    public boolean buscaCaminho(){
        boolean existeCaminho = false;
        
        // Limpar as variaveis
        for(int v = 0; v < this.quant_vertices; v++){
            caminho[v] = -1;
            visitados[v] = false;
        }
 
        fila.add(this.inicio);				// Add o vertice inicial na fila (FIFO)
        visitados[this.inicio] = true;
 
        while (!fila.isEmpty()){			// Enquanto a fila nao estiver vazia:
            int vertice = fila.remove();	// Tira o primeiro vertice adicionado
            
            for(int i = 0; i < this.quant_vertices; i++){
            	// Se existir uma ligacao entre os vertices, a capacidade for maior que zero e ainda nao foi visitado
            	if (this.grafoResidual[vertice][i] > 0 &&  !this.visitados[i]){
                    this.caminho[i] = vertice;	// Add ele no vetor que guarda o caminho
                    fila.add(i);				// Add o vertice no final da fila
                    this.visitados[i] = true;	// Marcar como visitado
                }
            }
        }
        
        if(this.visitados[this.destino]){		// Verifica se um caminho até o destino foi formado
            existeCaminho = true;
        }
        
        return existeCaminho;
    }
}
