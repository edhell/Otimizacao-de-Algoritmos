package trabalho.otimizacao.de.algoritmos.Annealing;

import trabalho.otimizacao.de.algoritmos.Annealing.ItemMochila;
import java.util.List;

/**
 *
 * @author Eduardo Dumke - M95949
 */
public final class SolucaoMochila {

    private final List<ItemMochila> pickedItem;
    private long gainedValue;
    private long gainedWeight;
    private long takenTime;

    public SolucaoMochila(List<ItemMochila> pickedItem, long takenTime) {
        this.pickedItem = pickedItem;
        gainedValue = -1;
        gainedWeight = -1;
        this.takenTime = takenTime;
    }

    /**
     * @return the pickedItem
     */
    public List<ItemMochila> getPickedItem() {
        return pickedItem;
    }

    /**
     * @return the gainedValue
     */
    public long getGainedValue() {
        if (gainedValue == -1) {
            gainedValue = pickedItem.stream().mapToInt(item -> item.getValue()).sum();
        }
        return gainedValue;
    }

    /**
     * @return the gainedWeight
     */
    public long getGainedWeight() {
        if (gainedWeight == -1) {
            gainedWeight = pickedItem.stream().mapToInt(item -> item.getWeight()).sum();
        }
        return gainedWeight;
    }

    /**
     * 
     * @param takenTime taken time in ms
     */
    public void setTakenTime(long takenTime) {
        this.takenTime = takenTime;
    }

    /**
     * @return the takenTime
     */
    public long getTakenTime() {
        return takenTime;
    }

    
}
