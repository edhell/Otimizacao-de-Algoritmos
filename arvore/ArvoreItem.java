/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.otimizacao.de.algoritmos.Arvore;

/**
 *
 * @author Eduardo Dumke - M95949
 */
public class ArvoreItem {
    public int from;
    public int to; 
    public int cost;

    public ArvoreItem(int from, int to, int cost) {
        this.from = from;		// Inicio
        this.to = to;			// Destino
        this.cost = cost;		// Custo
    }

    public ArvoreItem() {
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    
}
