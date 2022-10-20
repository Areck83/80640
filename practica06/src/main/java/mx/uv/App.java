package mx.uv;
import static spark.Spark.*;

import com.google.gson.JsonObject;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        get("/hello", (req, res) -> "Hello World");
        get("/eduardo", (req, res) -> "Eduardo");
        get("/alex", (req, res) -> "Alex");
        get("/brandon", (req, res) -> "Hello brandon");
        get("/", (req, res) -> "<h1>Bienvenido</h1><img src='https://www.uv.mx/v2/images/logouv.jpg'> ");

        post("/", (req,res)-> {
            System.out.println(req.queryParams("email") + " " + req.queryParams("password"));
            System.out.println(req.body());
            res.status(200);
            JsonObject oRespuesta = new JsonObject();
            oRespuesta.addProperty("msj","hola");
            oRespuesta.addProperty("email", req.queryParams("email"));
            return oRespuesta;
        });
    }
}
