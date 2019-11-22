
package trabalho.otimizacao.de.algoritmos.Annealing;

import trabalho.otimizacao.de.algoritmos.Annealing.BinarySolution;
import trabalho.otimizacao.de.algoritmos.Annealing.ItemMochila;
import trabalho.otimizacao.de.algoritmos.Annealing.KnapsackData;
import trabalho.otimizacao.de.algoritmos.Annealing.SolucaoMochila;
import trabalho.otimizacao.de.algoritmos.Annealing.KnapsackStrategy;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Eduardo Dumke - M95949
 */
public class SAStrategy implements KnapsackStrategy {

    private double temperature;
    private final double coolingFactor;
    private final double endingTempreture;
    private final int samplingSize;
    private static final double ALPHA = 1000000;
    private final Random random = new Random(System.nanoTime());
    private KnapsackData data;

    public SAStrategy(int samplingSize, 
            double initTemerature, 
            double endingTempreture, 
            double coolingFactor) {
        this.samplingSize = samplingSize;
        this.endingTempreture = endingTempreture;
        this.coolingFactor = coolingFactor;
        this.temperature = initTemerature;
    }

    @Override
    public SolucaoMochila solve(KnapsackData data) {

        this.data = data;

        long start = System.currentTimeMillis();
        BinarySolution current = new BinarySolution(data.getSize());
        BinarySolution best = current;
        current.updateFitness(data, ALPHA);

        while (temperature > endingTempreture) {
            for (int m = 0; m < samplingSize; m++) {
                current = getNextState(current);
                if (current.getFitness() < best.getFitness()) {
                    best = current;
                }
            }
            cool();
        }

        long end = System.currentTimeMillis();
        List<ItemMochila> pickedItem = generateSolution(data, best);
        return new SolucaoMochila(pickedItem, end - start);
    }

    private BinarySolution getNextState(BinarySolution current) {
        BinarySolution newSolution = getNeighbour(current);
        double delta = newSolution.getFitness() - current.getFitness();
        if (delta < 0) {
            return newSolution;
        } else {
            double x = Math.random();
            if (x < Math.exp(-delta / temperature)) {
                return newSolution;
            } else {
                return current;
            }
        }
    }

    private BinarySolution getNeighbour(BinarySolution current) {
        BinarySolution mutated = new BinarySolution(current);
        int x = random.nextInt(current.getSize());
        mutated.flip(x);
        mutated.updateFitness(data, ALPHA);
        return mutated;
    }

    private void cool() {
        temperature *= coolingFactor;
    }
}
