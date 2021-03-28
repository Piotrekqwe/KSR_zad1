package ksr.pl.kw.gui;

import ksr.pl.kw.classification.Country;
import ksr.pl.kw.extraction.ArticleCharacteristic;

public class ClassifiedArticle {
    ArticleCharacteristic article;
    Country country;

    public ClassifiedArticle(ArticleCharacteristic article, Country country) {
        this.article = article;
        this.country = country;
    }
}
