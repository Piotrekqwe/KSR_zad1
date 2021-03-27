package ksr.pl.kw.extraction;

import org.jsoup.nodes.Document;
import java.util.List;

public class ArticleDeserializer {

    private String filePath = "K:\\PL_INFORMATYKA\\SEMESTR_6\\Komputerowe_systemy_rozpoznawania\\Projekt_1\\articles";
    private List<Document> documents;
    private List<ArticleDTO> trainArticles;
    private List<ArticleDTO> testArticles;

    public String getFilePath(){
        return filePath;
    }


}
