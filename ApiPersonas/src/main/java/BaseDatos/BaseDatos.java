
package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BaseDatos {
    Connection conexion;
    Statement manipular_db;
    public BaseDatos(){
        String user_db="root";
        String password="";
        String url ="jdbc:mysql://localhost:3306/actividad_bd";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar libreria: "+ ex.getMessage());
        }
        try {
            conexion = DriverManager.getConnection(url, user_db, password);
            manipular_db = conexion.createStatement();
        } catch (SQLException ex) {
            System.out.println("Error al conectarse: "+ ex.getMessage());
        }
        
    }
    
    public ResultSet listaPersona(){
        ResultSet registros= null;
        try {
            registros = manipular_db.executeQuery("SELECT * FROM personas");
            registros.next();
            
        } catch (SQLException ex) {
            System.out.println("Error al hacer select: "+ ex.getMessage());       
        }
        return registros;
    }
    
    public void insertarPersona(String cedula, String nombres, String apellidos, String telefono, String email){
        try {
                int proceso = manipular_db.executeUpdate("INSERT INTO personas(cedula, nombres, apellidos, telefono, email) VALUES ('"+cedula+"','"+nombres+"','"+apellidos+"','"+telefono+"','"+email+"')");
            
                if (proceso == 1){
                    System.out.println("Se inserto con exito");
                }else {
                    System.out.println("Error al insertar");
                }
            } catch (SQLException ex) {
                System.out.println("Error al insertar: "+ ex.getMessage());
            }
    }
    public void actualizarPersona(String nombres, String cedula){
        try {
                int proceso_update = manipular_db.executeUpdate("UPDATE personas SET nombres ='"+nombres+"' WHERE cedula = '"+cedula+"'");
                if (proceso_update == 1){
                    System.out.println("Editado con exito");
                }else{
                    System.out.println("Error al editar");
                }
            } catch (SQLException ex) {
                System.out.println("Error al editar: "+ex.getMessage());
            }
    }
    public void eliminarPersona(String cedula){   
        try {
                    int proceso_delete = manipular_db.executeUpdate("DELETE FROM personas WHERE cedula = '"+cedula+"'");
                    if (proceso_delete == 1){
                        System.out.println("Un usuario fue eliminado");
                    }else{
                        System.out.println("Error al eliminar");
                    }
                } catch (SQLException ex) {
                    System.out.println("Error al eliminar: "+ex.getMessage());
                }
    }    
}
