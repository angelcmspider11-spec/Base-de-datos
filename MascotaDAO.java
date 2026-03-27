import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar mascotas en la base de datos MySQL
 */
public class MascotaDAO {
    
    private String url = "jdbc:mysql://localhost:3306/mascota_db";
    private String usuario = "root";
    private String password = "";
    private Connection conexion;
    
    /**
     * Constructor - Establece la conexión con la base de datos
     */
    public MascotaDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("✓ Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver MySQL no encontrado");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: No se pudo conectar a la base de datos");
            e.printStackTrace();
        }
    }
    
    /**
     * Registrar una nueva mascota
     */
    public boolean registrarMascota(int idDueno, String nombre, int idEspecie, 
                                    int idRaza, String sexo, int edad) {
        String sql = "INSERT INTO mascotas (id_dueno, nombre, id_especie, id_raza, sexo, edad) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idDueno);
            pst.setString(2, nombre);
            pst.setInt(3, idEspecie);
            pst.setInt(4, idRaza);
            pst.setString(5, sexo);
            pst.setInt(6, edad);
            
            int resultado = pst.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar mascota: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtener todas las razas de una especie
     */
    public List<String> obtenerRazasPorEspecie(int idEspecie) {
        List<String> razas = new ArrayList<>();
        String sql = "SELECT nombre_raza FROM razas WHERE id_especie = ?";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idEspecie);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                razas.add(rs.getString("nombre_raza"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener razas: " + e.getMessage());
        }
        
        return razas;
    }
    
    /**
     * Obtener todos los colores disponibles
     */
    public List<String> obtenerTodosLosColores() {
        List<String> colores = new ArrayList<>();
        String sql = "SELECT nombre_color FROM colores ORDER BY nombre_color";
        
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                colores.add(rs.getString("nombre_color"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener colores: " + e.getMessage());
        }
        
        return colores;
    }
    
    /**
     * Agregar colores a una mascota
     */
    public boolean agregarColorAMascota(int idMascota, int idColor, int porcentaje, String posicion) {
        String sql = "INSERT INTO mascota_colores (id_mascota, id_color, porcentaje, posicion) " +
                     "VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idMascota);
            pst.setInt(2, idColor);
            pst.setInt(3, porcentaje);
            pst.setString(4, posicion);
            
            int resultado = pst.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar color: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtener información de una mascota
     */
    public void obtenerInfoMascota(int idMascota) {
        String sql = "SELECT m.nombre, d.nombre as dueno_nombre, d.apellido, e.nombre_especie, " +
                     "r.nombre_raza, m.sexo, m.edad " +
                     "FROM mascotas m " +
                     "JOIN duenos d ON m.id_dueno = d.id_dueno " +
                     "JOIN especies e ON m.id_especie = e.id_especie " +
                     "JOIN razas r ON m.id_raza = r.id_raza " +
                     "WHERE m.id_mascota = ?";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idMascota);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                System.out.println("\n=== INFORMACIÓN DE MASCOTA ===");
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Dueño: " + rs.getString("dueno_nombre") + " " + rs.getString("apellido"));
                System.out.println("Especie: " + rs.getString("nombre_especie"));
                System.out.println("Raza: " + rs.getString("nombre_raza"));
                System.out.println("Sexo: " + rs.getString("sexo"));
                System.out.println("Edad: " + rs.getInt("edad") + " años");
                System.out.println("============================\n");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener información: " + e.getMessage());
        }
    }
    
    /**
     * Obtener colores de una mascota
     */
    public void obtenerColoresMascota(int idMascota) {
        String sql = "SELECT c.nombre_color, mc.porcentaje, mc.posicion " +
                     "FROM mascota_colores mc " +
                     "JOIN colores c ON mc.id_color = c.id_color " +
                     "WHERE mc.id_mascota = ?";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idMascota);
            ResultSet rs = pst.executeQuery();
            
            System.out.println("Colores de la mascota:");
            while (rs.next()) {
                System.out.println("  - " + rs.getString("nombre_color") + 
                                 " (" + rs.getInt("porcentaje") + "%) en " + 
                                 rs.getString("posicion"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener colores: " + e.getMessage());
        }
    }
    
    /**
     * Cerrar conexión
     */
    public void cerrar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}
