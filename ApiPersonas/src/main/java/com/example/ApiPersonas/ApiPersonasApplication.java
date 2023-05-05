package com.example.ApiPersonas;

import BaseDatos.BaseDatos;
import BaseDatos.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiPersonasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPersonasApplication.class, args);
	}
        @GetMapping("/")
        public String inicio(){
            return "Este es el inicio de nuestra API";
        }
        @GetMapping("/saludo")
        public String saludo(@RequestParam String nombre){
            return "hola mundo " + nombre;
        }
        @GetMapping("/listaPersonas")
        public List<Persona> listaPersonas(){
            List <Persona> listado = new ArrayList<>();
            BaseDatos db = new BaseDatos();
            ResultSet registros = db.listaPersona();
            
            try {
                if (registros != null && registros.getRow()==1){
                    do{
                        String cedula = registros.getString("cedula");
                        String nombres = registros.getString("nombres");
                        String apellidos = registros.getString("apellidos");
                        String telefono = registros.getString("telefono");
                        String email = registros.getString("email");
                        
                        Persona temporal = new Persona(cedula, nombres, apellidos, telefono, email);
                        listado.add(temporal);
                        
                        
                    }while(registros.next());
                    
                }
            }catch (SQLException ex) {
                System.out.println("error al extraer registro: " +ex.getMessage());
            }
            
            
            return listado;
            }        

        @GetMapping("/insertarPersona")
        public String insertarPersona(){
            BaseDatos db = new BaseDatos();
            db.insertarPersona("540356", "maria", "garmin", "3100231", "maria@email.com");
            return "OK INSERT";
        }
        @GetMapping("/actualizarPersona")
        public String actualizarPersona(){
            BaseDatos db = new BaseDatos();
            String nombres = "karol";
            String cedula = "540356";
                       
            db.actualizarPersona(nombres, cedula);
            return "OK UPDATE";
        }
        @GetMapping("/eliminarPersona")
        public String eliminarPersona(){
            BaseDatos db = new BaseDatos();
            String cedula = "540356";
                       
            db.eliminarPersona(cedula);
            return "persona eliminada";
        }
}
