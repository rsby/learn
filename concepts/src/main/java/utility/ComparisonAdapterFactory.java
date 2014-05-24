package utility;

/**
 * @author rees.byars
 */
public interface ComparisonAdapterFactory {

    <T extends Comparable<T>> ComparisonAdapter<T> newAdapterFor(T t, IndexToElementConverter<T> indexToElementConverter);

    final class Min implements ComparisonAdapterFactory {

        @Override public <T extends Comparable<T>> ComparisonAdapter<T> newAdapterFor(
                final T t, final IndexToElementConverter<T> indexToElementConverter) {
            return new ComparisonAdapter<T>() {
                @Override public boolean comesAfter(T other) {
                    return t.compareTo(other) > 0;
                }

                @Override public boolean comesAfter(int otherIndex) {
                    return t.compareTo(indexToElementConverter.get(otherIndex)) > 0;
                }
            };
        }
    }

    final class Max implements ComparisonAdapterFactory {

        @Override public <T extends Comparable<T>> ComparisonAdapter<T> newAdapterFor(
                final T t, final IndexToElementConverter<T> indexToElementConverter) {
            return new ComparisonAdapter<T>() {
                @Override public boolean comesAfter(T other) {
                    return t.compareTo(other) < 0;
                }

                @Override public boolean comesAfter(int otherIndex) {
                    return t.compareTo(indexToElementConverter.get(otherIndex)) < 0;
                }
            };
        }
    }

}
