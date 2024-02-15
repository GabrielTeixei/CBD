package pt.ua.cbd.lab4.ex4;
public class Main {
    public static void main(String... args) {
        Neo4jCsvImporter importer = new Neo4jCsvImporter("bolt://localhost:7687", "", "");
        try {
            importer.importCsvData("src/main/java/pt/ua/cbd/lab4/ex4/prod.csv"); // Caminho corrigido
            importer.executeQueriesAndWriteToFile("CBD_L44c_output.txt");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                importer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
