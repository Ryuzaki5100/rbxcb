package com.cube.demo;

import com.couchbase.client.java.*;
import com.couchbase.client.java.kv.*;
import com.couchbase.client.java.json.*;
import com.couchbase.client.java.query.*;

import java.time.Duration;

public class StartUsing {
    // Update these variables to point to your Couchbase Server instance and credentials.
    static String connectionString = "couchbases://cb.uulndodp9ns9stx0.cloud.couchbase.com";
    static String username = "Atharva";
    static String password = "Atharva@123";
    static String bucketName = "travel-sample";

    public static void main(String... args) {
        Cluster cluster = Cluster.connect(
                connectionString,
                username, password);

        // get a bucket reference
        Bucket bucket = cluster.bucket(bucketName);
//        Bucket bucket1 =
        bucket.waitUntilReady(Duration.ofSeconds(100));

        // get a user-defined collection reference
        Scope scope = bucket.scope("tenant_agent_00");
        Collection collection = scope.collection("users");

        // Upsert Document
        MutationResult upsertResult = collection.upsert(
                "my-document",
                JsonObject.create().put("name", "mike")
        );

        // Get Document
        GetResult getResult = collection.get("my-document");
        String name = getResult.contentAsObject().getString("name");
        System.out.println(name); // name == "mike"

        // Call the query() method on the scope object and store the result.
        Scope inventoryScope = bucket.scope("inventory");
        QueryResult result = inventoryScope.query("SELECT * FROM airline WHERE id = 10;");

        // Return the result rows with the rowsAsObject() method and print to the terminal.
        System.out.println(result.rowsAsObject());
    }
}