
package trabalho.otimizacao.de.algoritmos.Annealing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo Dumke - M95949
 */
public class KnapsackData {

    private final List<ItemMochila> availableItem;
    private final int maximumWeight;

    public KnapsackData(int maxSize) {
        availableItem = new ArrayList<>();
        this.maximumWeight = maxSize;
    }

    public int getMaximumWeight() {
        return maximumWeight;
    }

    public int getSize() {
        return availableItem.size();
    }
    
    public ItemMochila getData(int index) {
        return availableItem.get(index);
    }

    public void addItem(int index, ItemMochila item) {
        availableItem.add(index, item);
    }

    public void addItem(ItemMochila item) {
        availableItem.add(item);
    }

    public void addItem(List<ItemMochila> item) {
        availableItem.addAll(item);
    }

    public ItemMochila getItem(int index) {
        return availableItem.get(index);
    }

}
