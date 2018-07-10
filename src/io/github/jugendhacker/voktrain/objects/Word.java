package io.github.jugendhacker.voktrain.objects;

/**
 * Erstellt von Julian am 07.07.2018 um 16:15.
 */
public class Word {
    private Integer id;
    private String term;
    private Language termLang;
    private String definition;
    private Language definitionLang;

    public Word(String term, Language termLang, String definition, Language definitionLang) {
        this.term = term;
        this.termLang = termLang;
        this.definition = definition;
        this.definitionLang = definitionLang;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Language getTermLang() {
        return termLang;
    }

    public void setTermLang(Language termLang) {
        this.termLang = termLang;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Language getDefinitionLang() {
        return definitionLang;
    }

    public void setDefinitionLang(Language definitionLang) {
        this.definitionLang = definitionLang;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

