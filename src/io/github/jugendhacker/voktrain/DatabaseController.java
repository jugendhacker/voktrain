package io.github.jugendhacker.voktrain;

import io.github.jugendhacker.voktrain.objects.Language;
import io.github.jugendhacker.voktrain.objects.Word;

import java.sql.*;
import java.util.ArrayList;

/**
 * Erstellt von Julian am 07.07.2018 um 18:59.
 */
public class DatabaseController {
    private String filePath;
    private Connection connection;

    public DatabaseController(){
        filePath = System.getProperty("user.home") + System.getProperty("file.separator") + ".voktrain" + System.getProperty("file.separator") + "db.db";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS words (id INTEGER PRIMARY KEY AUTOINCREMENT, definition VARCHAR(100), definitionLang VARCHAR(100), term VARCHAR(100), termLang VARCHAR(100))");
            statement.close();
        } catch (ClassNotFoundException e) {
            System.out.println("I think SQLite isn't compiled right...");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Word writeWord(Word word){
        if (word.getId() == null) {
            try {
                PreparedStatement prep = connection.prepareStatement("" +
                        "INSERT INTO words (definition, definitionLang, term, termLang)" +
                        "VALUES (?, ?, ?,?)");
                prep.setString(1, word.getDefinition());
                prep.setString(2, word.getDefinitionLang().toString());
                prep.setString(3, word.getTerm());
                prep.setString(4, word.getTermLang().toString());
                prep.addBatch();
                prep.executeBatch();
                ResultSet rs = prep.getGeneratedKeys();
                if (rs.next()){
                    word.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                PreparedStatement prep = connection.prepareStatement("UPDATE words SET definition = ?, definitionLang = ?, term = ?, termLang = ? WHERE id = ?");
                prep.setString(1, word.getDefinition());
                prep.setString(2, word.getDefinitionLang().toString());
                prep.setString(3, word.getTerm());
                prep.setString(4, word.getTermLang().toString());
                prep.setInt(5, word.getId());
                prep.addBatch();
                prep.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return word;
    }

    public ArrayList<Word> getWordsByTermLanguage(Language language){
        ArrayList<Word> wordList = new ArrayList<>();
        try {
            PreparedStatement prep =  connection.prepareStatement("SELECT * FROM words WHERE termLang = ?");
            prep.setString(1, language.toString());
            prep.execute();
            ResultSet resultSet = prep.getResultSet();
            while (resultSet.next()){
                Word word = new Word(resultSet.getString("term"), Language.valueOf(resultSet.getString("termLang")), resultSet.getString("definition"), Language.valueOf(resultSet.getString("definitionLang")));
                word.setId(resultSet.getInt("id"));
                wordList.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wordList;
    }
}
