
package com.acme.sparkandhash;

import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;
import static spark.Spark.*;


/**
 * This is a simple Spark based webservice endpoint which offers SHA-256 hashing for a request containing a 'text' parameter string.
 * The POST should be directed to /hash.
 * 
 * 
 * @author Tuomas Honkala
 */
public class SparkAndHash {


    /**
     * Primitive for holding the unhashed text.
     */
    public class PlainText {

        public String text;

    }

    /**
     * Primitive for holding the hashed text.
     */
    public class HashedText {

        public String hash;

    }


 public static void main(String[] args) { 

        get("/", (req, res) -> "Hello world!");

        post("/hash", (request, response) -> {
            response.type("application/json");

            PlainText text = new Gson().fromJson(request.body(), PlainText.class);
            System.out.println(request.body());

            
            if (text.text == null) {                                            // If no text parameter is found in the POST, then return an error
                response.status(400);
                response.type("text/plain");
                return "BAD REQUEST: Text parameter is missing!";
            } else if (text.text.length() < 1) {                                // If the text parameter contains an empty string, then return an error
                response.status(400);
                response.type("text/plain");
                return "BAD REQUEST: Text parameter is empty!";
            }

            HashedText hash = new Gson().fromJson(request.body(), HashedText.class); 
            hash.hash = DigestUtils.sha256Hex(text.text);                       // Here we hash the text that was POSTed.

            System.out.println("..." + hash.hash);

            return new Gson().toJson(hash);                                     // Here we return the hashed text as a Json.
        });
    }
}
