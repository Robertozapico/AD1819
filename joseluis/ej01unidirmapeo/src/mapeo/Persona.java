package mapeo;

/**
 *
 * @author alumno
 */
public class Persona {

    private long id;
    private String nombre;
    
    private Direccion direccion;
    
    public Persona() {
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
