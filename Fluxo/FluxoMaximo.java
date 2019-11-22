package trabalho.otimizacao.de.algoritmos.Fluxo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import trabalho.otimizacao.de.algoritmos.Fluxo.FordFulkerson;

/**
 *
 * @author Eduardo Dumke - M95949
 */
public class FluxoMaximo {
    int matriz[][];
    int tamanho;
        
    public FluxoMaximo() {
        gerarMatriz(10);
        tamanho = 10;
    }
    
    public void gerarMatriz(int tamanhoMatriz){
        matriz = new int[tamanhoMatriz][tamanhoMatriz];
        tamanho = tamanhoMatriz;
        
        Random r = new Random();
        
        for(int m = 0; m < matriz.length; m++){
            for(int n = 0; n < matriz.length; n++){
                if(r.nextBoolean()){
                    matriz[m][n] = r.nextInt(30);
                }
                else{
                    matriz[m][n] = 0;
                }
            }
        }
    }
    
    public void lerArquivoMatriz(String local) {
        try {
            //BufferedReader dados = new BufferedReader(new FileReader("c:/matriz1.txt"));
            BufferedReader dados = new BufferedReader(new FileReader(local));

            System.out.println("Lendo arquivo da matriz de adjacencia... ");
            int quant;
            
            String linha = dados.readLine();
            quant = Integer.parseInt(linha);
            System.out.println("Matriz de tamanho " + quant + "x" + quant);
            matriz = new int[quant][quant];

            int i = 0;
            while (dados.ready()) { 
                linha = dados.readLine();

                if (linha.matches("\\s*")){
                    continue; 
                }

                StringTokenizer token = new StringTokenizer(linha, " ");
                int j = 0;
                while (token.hasMoreTokens()) {
                    String peso = new String(token.nextToken());
                    matriz[i][j] = Integer.parseInt(peso);
                    j++;				
                }		
                i++;
            }
            
            dados.close();
            
            System.out.println("Matriz de adjacencia do arquivo: ");
            for(int m = 0; m < matriz.length; m++){
                for(int n = 0; n < matriz.length; n++){
                    if(matriz[m][n] < 10) System.out.print(matriz[m][n] + "  ");
                    else System.out.print(matriz[m][n] + " ");    
                    
                }
                System.out.print("\n");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        try {
            System.out.println("Matriz de adjacencia: ");
            for(int m = 0; m < matriz.length; m++){
                for(int n = 0; n < matriz.length; n++){
                    if(matriz[m][n] < 10) System.out.print(matriz[m][n] + "  ");
                    else System.out.print(matriz[m][n] + " ");    
                    
                }
                System.out.print("\n");
            }
            
            // Pega as informações de entrada e saída:
            //Scanner scanner = new Scanner(System.in);
            //System.out.println("Informe o no de entrada (0-" + tamanho + "): ");
            //int inicio = scanner.nextInt();
                    
            //System.out.println("Informe o no de saida (0-" + tamanho + "): ");
            //int destino = scanner.nextInt();
            //scanner.close();
            
            // Automático
            int inicio = 0;
            int destino = tamanho/2;
            
            FordFulkerson algoritmo = new FordFulkerson(tamanho, matriz, inicio, destino);
            int fluxoMaximo = algoritmo.run();
            System.out.println("(Automático) Inicio: " + inicio + " | Destino: " + destino);
            System.out.println("Fluxo maximo: " + fluxoMaximo);
			
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
