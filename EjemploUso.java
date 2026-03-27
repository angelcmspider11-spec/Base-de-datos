/**
 * Ejemplo de uso del sistema de mascotas
 */
public class EjemploUso {
    
    public static void main(String[] args) {
        // Crear instancia del DAO
        MascotaDAO dao = new MascotaDAO();
        
        // 1. Obtener todas las razas de perros (id_especie = 1)
        System.out.println("\n--- RAZAS DE PERROS ---");
        for (String raza : dao.obtenerRazasPorEspecie(1)) {
            System.out.println("• " + raza);
        }
        
        // 2. Obtener todas las razas de gatos (id_especie = 2)
        System.out.println("\n--- RAZAS DE GATOS ---");
        for (String raza : dao.obtenerRazasPorEspecie(2)) {
            System.out.println("• " + raza);
        }
        
        // 3. Obtener todos los colores disponibles
        System.out.println("\n--- COLORES DISPONIBLES ---");
        for (String color : dao.obtenerTodosLosColores()) {
            System.out.println("• " + color);
        }
        
        // 4. Registrar una nueva mascota
        System.out.println("\n--- REGISTRANDO NUEVA MASCOTA ---");
        boolean resultado = dao.registrarMascota(
            1,                    // id_dueno
            "Bella",              // nombre
            2,                    // id_especie (gato)
            1,                    // id_raza (Persa)
            "Hembra",             // sexo
            2                     // edad
        );
        
        if (resultado) {
            System.out.println("✓ Mascota registrada exitosamente");
        } else {
            System.out.println("✗ Error al registrar mascota");
        }
        
        // 5. Obtener información de mascotas
        System.out.println("\n--- INFORMACIÓN DE MASCOTAS ---");
        dao.obtenerInfoMascota(1);  // Max
        dao.obtenerInfoMascota(2);  // Bella (si se registró)
        
        // 6. Ver colores de una mascota
        System.out.println("\n--- COLORES DE MAX ---");
        dao.obtenerColoresMascota(1);
        
        // Cerrar conexión
        dao.cerrar();
    }
}
