
package pt.ua.cbd.lab4.ex4;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static org.neo4j.driver.Values.parameters;

public class Neo4jCsvImporter {
    private final Driver driver;
  
    public Neo4jCsvImporter(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public void close() throws Exception {
        driver.close();
    }



    public void importCsvData(String csvFilePath) {
        try (Session session = driver.session();
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {

            String line;
            br.readLine(); // Pular cabeçalho
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    String product = data[0].trim();
                    String subProduct = data[1].trim();
                    String company = data[2].trim();
                    String issue = data[3].trim();
                    String dateReceived = data[4].trim();


                    //--> Cria ou encontra Produto
                    String findOrCreateProduct = "MERGE (p:Product {name: $productName}) RETURN p";
                    session.run(findOrCreateProduct, parameters("productName", product));

                    //--> Cria ou encontra Subproduto, se existir
                    if (!subProduct.isEmpty()) {
                        String findOrCreateSubProduct = "MERGE (sp:SubProduct {name: $subProductName}) RETURN sp";
                        session.run(findOrCreateSubProduct, parameters("subProductName", subProduct));

                        // Relaciona Produto com Subproduto
                        String relateProductToSubProduct = "MATCH (p:Product {name: $productName}), (sp:SubProduct {name: $subProductName}) MERGE (p)-[:HAS]->(sp)";
                        session.run(relateProductToSubProduct, parameters("productName", product, "subProductName", subProduct));
                    }

                    //--> Cria ou encontra Empresa
                    String findOrCreateCompany = "MERGE (c:Company {name: $companyName}) RETURN c";
                    session.run(findOrCreateCompany, parameters("companyName", company));

                    //--> Cria Reclamação
                    String createComplaint = "CREATE (com:Complaint {issue: $issue, dateReceived: $dateReceived})";
                    session.run(createComplaint, parameters("issue", issue, "dateReceived", dateReceived));

                    //--> Relaciona Empresa com Reclamação
                    String relateCompanyToComplaint = "MATCH (c:Company {name: $companyName}), (com:Complaint {issue: $issue, dateReceived: $dateReceived}) MERGE (c)-[:RECEIVES]->(com)";
                    session.run(relateCompanyToComplaint, parameters("companyName", company, "issue", issue, "dateReceived", dateReceived));
                }
            }
          } catch (IOException e) {
        System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        e.printStackTrace();
    }
    }

    public void executeQueriesAndWriteToFile(String outputFilePath) {
        String[] queries = {
            //1--> Conta o número de produtos distintos
            "MATCH (p:Product) RETURN count(distinct p)",

            //2--> Lista todas as empresas e o número de reclamações recebidas
            "MATCH (c:Company)-[:RECEIVES]->(com:Complaint) RETURN c.name, count(com) as ComplaintsCount",

            //3--> Lista todas as reclamações sobre um produto específico
            "MATCH (com:Complaint)-[:RELATED_TO]->(:Product {name: 'SpecificProductName'}) RETURN com.issue",

            //4--> Lista produtos e suas categorias de subprodutos
            "MATCH (p:Product)-[:HAS]->(sp:SubProduct) RETURN p.name, collect(sp.name) as SubProducts",

            //5--> Lista reclamações recebidas após uma data específica
            "MATCH (com:Complaint) WHERE com.dateReceived >= '2017-01-01' RETURN com.issue, com.dateReceived",

            //6--> Lista empresas cujo nome contém 'INC'
            "MATCH (c:Company) WHERE c.name CONTAINS 'INC' RETURN c.name",

            //7--> Conta o número de subprodutos para cada produto
            "MATCH (p:Product)-[:HAS]->(sp:SubProduct) RETURN p.name, count(sp) as SubProductCount",

            //8--> Lista reclamações e as empresas relacionadas
            "MATCH (c:Company)-[:RECEIVES]->(com:Complaint) RETURN com.issue, c.name",

            //9--> Lista produtos sem reclamações
            "MATCH (p:Product) WHERE NOT (p)-[:RELATED_TO]->(:Complaint) RETURN p.name",

            //10--> Lista as últimas 5 reclamações recebidas
            "MATCH (com:Complaint) RETURN com.issue ORDER BY com.dateReceived DESC LIMIT 5"
        };

    try (Session session = driver.session();
         BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

        // Adicione seu nome e nome da universidade ao início do arquivo
        writer.write("|| Nome: Gabriel Teixeira   ||\n");
        writer.write("|| n mecanográfico: 107876  || \n");
        writer.write("\n");

        for (String query : queries) {
            var result = session.run(query);
            writer.write("Query: " + query + "\nResultados:\n");

            int count = 0;
            for (var record : result.list()) {
                writer.write(record.toString() + "\n");
                count++;
            }

            if (count == 0) {
                writer.write("Nenhum resultado encontrado para a consulta.\n");
            }

            writer.write("\n");
        }
        writer.flush();
    } catch (IOException e) {
        System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        e.printStackTrace();
    }
}

}




