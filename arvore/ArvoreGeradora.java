/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.otimizacao.de.algoritmos.Arvore;

import java.io.*;
import java.util.*;

/**
 *
 * @author Eduardo Dumke - M95949
 */
public class ArvoreGeradora {
    Kruskal k;
    List<ArvoreItem> itens;

    public ArvoreGeradora() {
        this.k = new Kruskal();
        this.itens = new ArrayList<>();
    }
        
    public ArvoreGeradora(List<ArvoreItem> itens) {
        this.k = new Kruskal();
        this.itens = itens;
    }
    
    public void addItem(int from, int to, int cost){
        itens.add(new ArvoreItem(from, to, cost));
    }
    
    public void run(){
        //k.run("c:/index.txt");
        k.run2(itens);
    }
    
}
