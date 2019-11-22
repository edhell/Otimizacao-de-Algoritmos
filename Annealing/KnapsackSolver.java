
package trabalho.otimizacao.de.algoritmos.Annealing;

import trabalho.otimizacao.de.algoritmos.Annealing.ItemMochila;
import trabalho.otimizacao.de.algoritmos.Annealing.KnapsackData;
import trabalho.otimizacao.de.algoritmos.Annealing.SolucaoMochila;
import java.util.List;

/**
 *
 * @author Eduardo Dumke - M95949
 */
public class KnapsackSolver {

    private final KnapsackData data;
    private SolucaoMochila bestSolution;
    private KnapsackStrategy strategy;

    public KnapsackSolver(KnapsackData data, KnapsackStrategy strategy) {
        this.data = data;
        this.strategy = strategy;
    }

    private SolucaoMochila find() {
        return strategy.solve(data);
    }

    /**
     * for stochastic algorithms the output of the best solution is different
     *
     * @return best founded solution
     */
    public SolucaoMochila getSolution() {
        if (bestSolution == null) {
            bestSolution = find();
        }
        return bestSolution;
    }

    public long getTakenTime() {
        return getSolution().getTakenTime();
    }

    public List<ItemMochila> getSelectedItem() {
        return getSolution().getPickedItem();
    }

}
