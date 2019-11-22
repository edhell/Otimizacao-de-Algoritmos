
package trabalho.otimizacao.de.algoritmos.Annealing;

import trabalho.otimizacao.de.algoritmos.Annealing.BinarySolution;
import trabalho.otimizacao.de.algoritmos.Annealing.ItemMochila;
import trabalho.otimizacao.de.algoritmos.Annealing.KnapsackData;
import trabalho.otimizacao.de.algoritmos.Annealing.SolucaoMochila;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo Dumke - M95949
 */
@FunctionalInterface
public interface KnapsackStrategy {

    /**
     * solve the knapsack problem
     *
     * @param data Knapsack Data
     * @return best founded solution
     * @see com.mhrimaz.model.KnapsackData
     */
    public SolucaoMochila solve(KnapsackData data);

    /**
     * generate list of items from the binary solution 
     * @param knapsackData Knapsack data
     * @param solution Binary solution of the problem
     * @return list of the selected items
     */
    public default List<ItemMochila> generateSolution(KnapsackData knapsackData, BinarySolution solution) {
        List<ItemMochila> pickedItem = new ArrayList<>();
        for (int i = 0; i < knapsackData.getSize(); i++) {
            if (solution.getBit(i) == 1) {
                pickedItem.add(knapsackData.getData(i));
            }
        }
        return pickedItem;
    }
}
