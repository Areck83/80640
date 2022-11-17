package uv.mx;
import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class AppDb {
    public static Gson gson = new Gson();
    //base de datos en memoria
    //public static Map<String, Usuario> usuarios = new HashMap<>();
    public static void main( String[] args ){
        //port(80); Utilizar solamente el puerto si el servidor apache no se encuentra. Si si, entonces la ruta debe de ser la de 4567/rutaespecificada

        //Esta madre es para lo del error de CORS
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
        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));

        //Inicializacion de datos
        //Usuario u1 = new Usuario("1", "pablo", "1234");
        //Usuario u2 = new Usuario("2", "ana", "7890");
        //usuarios.put(u1.getId(), u1);
        //usuarios.put(u2.getId(), u2);

        System.out.println( "Hello World!" );
        before((req, res)-> res.type("application/json")); //intercepta la llamada antes de ejecutarse
        //get("/usuario", (req, res) -> gson.toJson(u1));
        //get("/usuarios", (req, res) -> gson.toJson(usuarios));
        get("/usuarios", (req, res) -> gson.toJson(DAO.dameUsuarios()));

        post("/", (req, res)->{
            String datosFormulario = req.body();
            Usuario u = gson.fromJson(datosFormulario, Usuario.class);
            //usuarios.put(u.getId(), u);
            //return "usuario agregado";
            return DAO.crearUsuario(u);
        });
    }
}

//curl -X POST -d '{id:"3", nombre:"jose", password:"123"}' http://localhost
//curl -X POST -d '{id:"3", nombre:"jose", password:"123"}' http://localhost:4567/usuarios
//Tener especial cuidado con las comillas


//El insert esta dado por el post
//DAO Data Access Object
// Los ORM son capas de middleware que permiten extraer lo que tienen las bases de datos. 