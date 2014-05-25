package utility;

/**
 * @author rees.byars
 */
public interface ComparisonAdapter<T extends Comparable<T>> {

    int compare(T t, T t2);

}
