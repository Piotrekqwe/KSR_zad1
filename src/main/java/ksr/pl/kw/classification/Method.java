package ksr.pl.kw.classification;

import ksr.pl.kw.extraction.ArticleCharacteristic;

public enum Method {
    EUCLIDES{
        @Override
        double process(double[] test, double[] learn) {
            return 0;
        }
    },
    MANHATTAN{
        @Override
        double process(double[] test, double[] learn) {
            return 0;
        }
    },
    CHEBYSHEV{
        @Override
        double process(double[] test, double[] learn) {
            return 0;
        }
    };

    abstract double process(double[] test, double[] learn);
}
