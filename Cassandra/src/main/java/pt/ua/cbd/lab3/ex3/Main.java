package pt.ua.cbd.lab3.ex3;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;


public class Main {
    private static CqlSession user_session;
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder().build()) {                                           // (1)
            user_session = session;
            ResultSet rs = session.execute("select release_version from system.local");               // (2)
            Row row = rs.one();
            System.out.println(row.getString("release_version"));                                      // (3)

            System.out.println("############################################################");
            System.out.println("A)");

            final String key_pace = "CREATE KEYSPACE IF NOT EXISTS exercicio WITH REPLICATION = { 'class' : 'NetworkTopologyStrategy', 'datacenter1' : 3 }";

            user_session.execute(key_pace);
            user_session.execute("USE exercicio");

            user_session.execute("DROP TABLE IF EXISTS user");

            final String CREATETABLE =  "CREATE TABLE IF NOT EXISTS user (" +
                    "User_ID text, name text, age int, PRIMARY KEY (User_ID))";

            user_session.execute(CREATETABLE);


            to_add("1",  "Bruno", 20);

            find("1");

            to_update("1", "Bruno o melhor do mundo");

            find("1");

            System.out.println("############################################################");
            System.out.println("B)");

            user_session.execute("USE videos");

            ResultSet event;

            System.out.println("");
            event = user_session.execute("SELECT  * FROM videos where video_id=ce98ee4c-2873-41b6-b741-8ea5a46a70eb LIMIT 3 ALLOW FILTERING");

            for (Row r : event) {
                System.out.println(r.getFormattedContents());
            }

            System.out.println("->tags de um aventura\n"); 
            event = user_session.execute("SELECT * FROM video_gestao WHERE tags CONTAINS 'aventura' ALLOW FILTERING");

            for (Row r : event) {
                System.out.println(r.getFormattedContents());
            }

            System.out.println("-> Todos os seguidores (followers) de determinado video; \n"); 
            event = user_session.execute("SELECT user_id FROM video_followers WHERE video_id = f02c55af-db01-42b3-ae19-27ff7952d457");

            for (Row r : event) {
                System.out.println(r.getFormattedContents());
            }

           

        
            System.out.println("->Os ultimos 10 videos, ordenado inversamente pela data da partilhada\n");
            event = user_session.execute("SELECT * FROM video_gestao  LIMIT 10");

            for (Row r : event) {
                System.out.println(r.getFormattedContents());
                System.out.println("------------------------------------------------------------\n");

            }

        }
    }


    private  static void to_add( String User_ID, String name, int age) {
        String insert_query = String.format("INSERT INTO exercicio.user (User_ID, name, age) VALUES ('%s', '%s', %d)",
                User_ID, name, age);
        user_session.execute(insert_query);
        System.out.println("->User  inserted sucessfully!");
    }


    private static void find( String User_ID) {
        String select_query = String.format("SELECT * FROM exercicio.user WHERE User_ID = '%s'", User_ID);

        ResultSet rs = user_session.execute(select_query);
        Row row = rs.one();

        if (row != null) {
            
            System.out.println(" | UU ID : " + row.getString("User_ID") + " | Name  : " + row.getString("name") + " | Age   : " + row.getInt("age") + " |");

        } else {
            System.out.println("->No register found!");
        }
    }

    private static void to_update(String User_ID, String new_name) {
        String updateQuery = String.format("UPDATE exercicio.user SET name = '%s' WHERE User_ID = '%s'", new_name, User_ID);

        user_session.execute(updateQuery);
        System.out.println("->Efetuado com sucesso!");
    }

}