package trabalho.otimizacao.de.algoritmos.Annealing;

import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author Eduardo Dumke - M95949
 */
public class ItemMochila implements Comparable<ItemMochila> {

    private final StringProperty nome;
    private final IntegerProperty weight;
    private final IntegerProperty value;

    public ItemMochila(String nome, int weight, int value) {
        this.nome = new SimpleStringProperty(nome);
        this.weight = new SimpleIntegerProperty(weight);
        this.value = new SimpleIntegerProperty(value);
    }

    public String getNome() {
        return nome.get();
    }
    
    public int getWeight() {
        return weight.get();
    }

    public int getValue() {
        return value.get();
    }

    @Override
    public String toString() {
        return "Item: " + nome.get() + " Peso: " + weight.get() + " Valor: " + value.get();
    }

    @Override
    public int compareTo(ItemMochila o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
