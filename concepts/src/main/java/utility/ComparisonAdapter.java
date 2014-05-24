package utility;

/**
 * @author rees.byars
 */
public interface ComparisonAdapter<T extends Comparable<T>> {

    boolean comesAfter(T other);

    boolean comesAfter(int otherIndex);

}
