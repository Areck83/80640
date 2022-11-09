package mx.uv;
import static spark.Spark.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        options("/*", (request, response) -> {
                String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
                if (accessControlRequestHeaders != null) {
                    response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
                }
                String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
                if (accessControlRequestMethod != null) {
                    response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
                }
                return "OK";
            });
            before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
            


        get("/hello", (req, res) -> "Hello World");
        get("/eduardo", (req, res) -> "Hello Eduardo");
        get("/alex", (req, res) -> "Hello Alex");
        get("/brandon", (req, res) -> "Hello Brandon");
        get("/", (req, res) -> "<h1>Bienvenido</h1><img src='https://www.uv.mx/v2/images/logouv.jpg'>");
        get("/", (req, res) -> "hola");
        
        post("/", (req, res) -> {
            System.out.println(req.queryParams("email") + " " + 
                req.queryParams("password"));
            System.out.println(req.body());

            //Este segmento es para poder leer los elementos en el modo del JSON
            JsonParser parser = new JsonParser(); //Nos va a permitir anailzar un documento
            JsonElement arbol = parser.parse(req.body()); //Esto nos debe devolver un objeto JSON
            JsonObject peticionCliente = arbol.getAsJsonObject();
            System.out.println(peticionCliente.get("email").getAsString());
            System.out.println(peticionCliente.get("password"));
            System.out.println(arbol);

            res.status(200);// Codigo de respuesta
            JsonObject oRespuesta = new JsonObject();
            oRespuesta.addProperty("msj", "Hola");
            //oRespuesta.addProperty("email", req.queryParams("email")); //Usar QueryParams
            oRespuesta.addProperty("email", peticionCliente.get("email").getAsString());
            return oRespuesta;
        });

    }
}


//Payload: Se hace con procesamiento del body
//Query params: Es para leer el payload como url encode
//Jason es la notación de mensaje valor, para concatenar varias cosas debe de llevar un &
//Parser: Es un analizador sintáctico
//Hay 2 formas de enviar cosas: JSON y URL Encode 
