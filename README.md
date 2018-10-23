# SparkAndHash
A small Java Spark webservice for hashing (SHA-256) text.

The app can be run locally by using https://github.com/basic-ohjelmoia/SparkAndHash/blob/master/target/SparkAndHash-1.0-SNAPSHOT.jar

The POST needs to be directed to http://localhost:4567/hash 

Example usage

POST /hash HTTP/1.1
HOST: localhost:4567
content-type: application/json
content-length: 33

{
  "text": "Paradise Cittty"
}

{
"hash": "502a2db874ade482dee8cde681f3727f84b1c85168c9a8fc8e605cde15bbb534"
}
