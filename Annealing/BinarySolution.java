
package trabalho.otimizacao.de.algoritmos.Annealing;

import java.util.Arrays;

/**
 *
 * @author Eduardo Dumke - M95949
 */
public final class BinarySolution {

    private final byte[] chromosome;
    private double fitness;
    private long weight;

    public BinarySolution(int size) {
        chromosome = new byte[size];
        fitness = Double.MAX_VALUE;
        weight = Long.MAX_VALUE;
    }

    public BinarySolution(BinarySolution other) {
        this.chromosome = other.chromosome.clone();
        fitness = other.getFitness();
        weight = other.getWeight();
    }

    public byte getBit(int position) {
        return chromosome[position];
    }

    public double getFitness() {
        return fitness;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void flip(int position) {
        chromosome[position] = (byte) (chromosome[position] ^ 1);
    }

    public void shuffle() {
        for (int i = 0; i < chromosome.length; i++) {
            if (Math.random() > 0.3) {
                chromosome[i] = 0;
            } else {
                chromosome[i] = 1;
            }
        }
    }

    public int getSize() {
        return chromosome.length;
    }

    public void updateFitness(KnapsackData data, double alpha) {
        long sumVal = 0, sumWeight = 0;
        for (int i = 0; i < data.getSize(); i++) {
            ItemMochila item = data.getData(i);
            if (getBit(i) == 1) {
                sumWeight += item.getWeight();
            } else {
                sumVal += item.getValue();
            }
        }
        double violation = Math.max((double) sumWeight / data.getMaximumWeight() - 1, 0);
        setWeight(sumWeight);
        setFitness(sumVal + alpha * violation);
    }

    @Override
    public String toString() {
        return Arrays.toString(chromosome);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Arrays.hashCode(this.chromosome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BinarySolution other = (BinarySolution) obj;
        return Arrays.equals(this.chromosome, other.chromosome);
    }

}
