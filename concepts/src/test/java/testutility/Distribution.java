package testutility;

import java.util.Random;

/**
 * from google caliper example
 */
public enum Distribution {

    SAWTOOTH {
        @Override public Integer[] create(int length) {
            Integer[] result = new Integer[length];
            for (int i = 0; i < length; i += 5) {
                result[i] = 0;
                result[i + 1] = 1;
                result[i + 2] = 2;
                result[i + 3] = 3;
                result[i + 4] = 4;
            }
            return result;
        }
    },

    INCREASING {
        @Override public Integer[] create(int length) {
            Integer[] result = new Integer[length];
            for (int i = 0; i < length; i++) {
                result[i] = i;
            }
            return result;
        }
    },

    DECREASING {
        @Override public Integer[] create(int length) {
            Integer[] result = new Integer[length];
            for (int i = 0; i < length; i++) {
                result[i] = length - i;
            }
            return result;
        }
    },

    RANDOM {
        @Override public Integer[] create(int length) {
            Random random = new Random();
            Integer[] result = new Integer[length];
            for (int i = 0; i < length; i++) {
                result[i] = random.nextInt();
            }
            return result;
        }
    };

    public abstract Integer[] create(int length);

}
