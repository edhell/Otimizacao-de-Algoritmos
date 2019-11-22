/*
 */
package trabalho.otimizacao.de.algoritmos.Arvore;

import java.io.*;
import java.util.*;

/**
 *
 * @author Eduardo Dumke - M95949
 */
public class Kruskal {
  private final int MAX_NODES = 21;
  private HashSet nodes[];               // Matriz de componentes conectados
  private TreeSet allEdges;              // Fila prioritária de objetos de borda
  private Vector allNewEdges;            // Bordas na árvore de abrangência mínima

  Kruskal() {
    nodes = new HashSet[MAX_NODES];      // Criar matriz para componentes
    allEdges = new TreeSet(new Edge());  // Criar fila de prioridade vazia
    allNewEdges = new Vector(MAX_NODES); // Criar vetor para arestas MST
  }

    public void run(String arquivo){
    System.out.println("Rodando [Kruskal] 2 - Informando Arquivo");
        if (arquivo.isEmpty()) {
            System.out.println("Usage: java Kruskal <fileName>");
            return;
        }
        
        Kruskal k = new Kruskal();
        
        k.lerDados(arquivo);
        k.executarKruskal();
        k.imprimirCaminho();
    }
  
    public void run2(List<ArvoreItem> itens){
        System.out.println("Rodando [Kruskal] 2 - Informando itens");
        
        Kruskal k = new Kruskal();
        k.lerDados2(itens);
        k.executarKruskal();
        k.imprimirCaminho();
    }
  
    private void lerDados(String fileName) {
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader buff = new BufferedReader(file);
            String line = buff.readLine();
            while (line != null) {
                StringTokenizer tok = new StringTokenizer(line, " ");
                int from = Integer.valueOf(tok.nextToken()).intValue();
                int to   = Integer.valueOf(tok.nextToken()).intValue();
                int cost = Integer.valueOf(tok.nextToken()).intValue();

                allEdges.add(new Edge(from, to, cost));  // Update priority queue
                if (nodes[from] == null) {
                    // Create set of connect components [singleton] for this node
                    nodes[from] = new HashSet(2*MAX_NODES);
                    nodes[from].add(new Integer(from));
                }

                if (nodes[to] == null) {
                    // Create set of connect components [singleton] for this node
                    nodes[to] = new HashSet(2*MAX_NODES);
                    nodes[to].add(new Integer(to));
                }

                line = buff.readLine();
            }
            buff.close();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void lerDados2(List<ArvoreItem> itens) {
        try {
            
            for (int i = 0; i < itens.size(); i++){
                int from = itens.get(i).from;
                int to   = itens.get(i).to;
                int cost = itens.get(i).cost;
                
                allEdges.add(new Edge(from, to, cost));  // Atualizar fila de prioridade
                if (nodes[from] == null) {
                    // Criando um conjunto de componentes de conexão [singleton] para este nó
                    nodes[from] = new HashSet(2*MAX_NODES);
                    nodes[from].add(new Integer(from));
                }

                if (nodes[to] == null) {
                    // Criando um conjunto de componentes de conexão [singleton] para este nó
                    nodes[to] = new HashSet(2*MAX_NODES);
                    nodes[to].add(new Integer(to));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void executarKruskal() {
    int size = allEdges.size();
    for (int i=0; i<size; i++) {
      Edge curEdge = (Edge) allEdges.first();
      if (allEdges.remove(curEdge)) {
        // Remoção da fila de prioridade: allEdges

        if (nosEmConjuntosDiferentes(curEdge.from, curEdge.to)) {
          // System.out.println("Nós estão em conjuntos diferentes ...");
          HashSet src, dst;
          int dstHashSetIndex;

          if (nodes[curEdge.from].size() > nodes[curEdge.to].size()) {
            // Precisa transferir todos os nós, incluindo curEdge.to
            src = nodes[curEdge.to];
            dst = nodes[dstHashSetIndex = curEdge.from];
          } else {
            // Precisa transferir todos os nós, incluindo curEdge.from
            src = nodes[curEdge.from];
            dst = nodes[dstHashSetIndex = curEdge.to];
          }

          Object srcArray[] = src.toArray();
          int transferSize = srcArray.length;
          for (int j=0; j<transferSize; j++) {
            // move cada nó de set: src para set:dst e atualize o índice apropriado na matriz: nodes
            if (src.remove(srcArray[j])) {
              dst.add(srcArray[j]);
              nodes[((Integer) srcArray[j]).intValue()] = nodes[dstHashSetIndex];
            } else {
              // This is a serious problem
              System.out.println("Algo errado: Definir união");
              System.exit(1);
            }
          }

          allNewEdges.add(curEdge); // Adicionar nova aresta ao vetor de aresta MST
        } else {
          // System.out.println("Nós estão no mesmo conjunto");
        }

      } else {
        System.out.println("TreeSet deveria ter contido esse elemento!!");
        System.exit(1);
      }
    }
  }

    private boolean nosEmConjuntosDiferentes(int a, int b) {
        // retorna true se os nós do gráfico (a, b) estiverem em diferentes
        // componentes conectados, ou seja, o conjunto para 'a' é diferente disso para 'b'
        return(!nodes[a].equals(nodes[b]));
    }

    private void imprimirCaminho() {
        System.out.println("A árvore de abrangência mínima gerada pelo Algoritmo é: ");
        while (!allNewEdges.isEmpty()) {
            // Para cada aresta em Vetor de arestas MST
            Edge e = (Edge) allNewEdges.firstElement();
            System.out.println("Nodes: (" + e.from + ", " + e.to + ") Custo: " + e.cost);
            allNewEdges.remove(e);
        }
  }

  class Edge implements Comparator {
    // Classe interna para representar arestas + pontos finais
    public int from, to, cost;
    
    public Edge() {
    }
    
    public Edge(int f, int t, int c) {
      from = f; to = t; cost = c;
    }
    
    public int compare(Object o1, Object o2) {
      // Usado para comparações durante operações de adição / remoção
      int cost1 = ((Edge) o1).cost;
      int cost2 = ((Edge) o2).cost;
      int from1 = ((Edge) o1).from;
      int from2 = ((Edge) o2).from;
      int to1   = ((Edge) o1).to;
      int to2   = ((Edge) o2).to;

      if (cost1<cost2)
        return(-1);
      else if (cost1==cost2 && from1==from2 && to1==to2)
        return(0);
      else if (cost1==cost2)
        return(-1);
      else if (cost1>cost2)
        return(1); 
      else
        return(0);
    }
    
    public boolean equals(Object obj) {
      // Usado para comparações durante operações de adição / remoção
      Edge e = (Edge) obj;
      return (cost==e.cost && from==e.from && to==e.to);
    }
  }

}
