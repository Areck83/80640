package uv.mx;
import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class App {
    public static Gson gson = new Gson();
    //base de datos en memoria
    public static Map<String, Usuario> usuarios = new HashMap<>();
    public static void main( String[] args ){
        port(80);
        //Inicializacion de datos
        Usuario u1 = new Usuario("1", "pablo", "1234");
        Usuario u2 = new Usuario("2", "ana", "7890");
        usuarios.put(u1.getId(), u1);
        usuarios.put(u2.getId(), u2);

        System.out.println( "Hello World!" );
        before((req, res)-> res.type("application/json")); //intercepta la llamada antes de ejecutarse
        get("/usuario", (req, res) -> gson.toJson(u1));
        get("/usuarios", (req, res) -> gson.toJson(usuarios));

        post("/", (req, res)->{
            String datosFormulario = req.body();
            Usuario u = gson.fromJson(datosFormulario, Usuario.class);
            usuarios.put(u.getId(), u);
            return "usuario agregado";
        });
    }
}

//curl -X POST -d '{id:"3", nombre:"jose", password:"123"}' http://localhost
