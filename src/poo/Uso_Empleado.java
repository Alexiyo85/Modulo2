package poo;
import java.util.*;
public class Uso_Empleado {
    public static void main(String[] args) {
        /*
        Empleado empleado1 = new Empleado("Paco Gómez", 85000, 1990,12,17); 
        Empleado empleado2 = new Empleado("Ana López", 95000, 1995,06,02); 
        Empleado empleado3 = new Empleado("Maria Martín", 10500, 2002,03,15); 

        empleado1.subeSueldo(5);
        empleado2.subeSueldo(5);
        empleado3.subeSueldo(5);

        System.out.println("Nombre: " + empleado1.dameNombre() + " Sueldo: " + empleado1.dameSueldo()
        + " Fecha Alta: " + empleado1.dameFechaContrato());
        
        System.out.println("Nombre: " + empleado2.dameNombre() + " Sueldo: " + empleado2.dameSueldo()
        + " Fecha Alta: " + empleado2.dameFechaContrato());

        System.out.println("Nombre: " + empleado3.dameNombre() + " Sueldo: " + empleado3.dameSueldo()
        + " Fecha Alta: " + empleado3.dameFechaContrato());
        */
        Jefatura jefe_RRHH = new Jefatura("Fran Fernández", 55000, 2024, 8, 12);
        jefe_RRHH.establece_incentivo(2570);


        Empleado[] misEmpleados= new Empleado[6];
        misEmpleados[0] = new Empleado("Paco Gómez", 85000, 1990,12,17);
        misEmpleados[1] = new Empleado("Ana López", 95000, 1995,06,02); 
        misEmpleados[2] = new Empleado("Maria Martín", 10500, 2002,03,15); 
        misEmpleados[3] = new Empleado("Antonio Fernández");
        misEmpleados[4] = jefe_RRHH; //Polimorfismo. Principio de Sustitución
        misEmpleados[5] = new Jefatura("Josefa Ferrón", 45000, 1999, 5, 26);

        Jefatura jefe_Finanzas = (Jefatura) misEmpleados[5];
        jefe_Finanzas.establece_incentivo(5000);


        /*for (int i=0; i< misEmpleados.length; i++){ //For Normal
            misEmpleados[i].subeSueldo(5);

        }*/
        
        System.out.println(jefe_Finanzas.tomar_decisiones("Dar más días de vacaciones a los trabajadores"));
        
        for(Empleado e: misEmpleados){ // For Mejorado
            e.subeSueldo(5);
        }
        
        Arrays.sort(misEmpleados);

        /* 
        for (int i=0; i< misEmpleados.length; i++){ //For Normal
            System.out.println("Nombre: " + misEmpleados[i].dameNombre() + " Sueldo: " + misEmpleados[i].dameSueldo()
        + " Fecha Alta: " + misEmpleados[i].dameFechaContrato());

        }*/

    
        for(Empleado e: misEmpleados){ //For Mejorado
            System.out.println("Nombre: " + e.dameNombre() + " Sueldo: " + e.dameSueldo()
        + " Fecha Alta: " + e.dameFechaContrato());
        }


    }

}

class Empleado implements Comparable {
    private String nombre;
    private double sueldo;
    private Date altaContrato;
    private static int IdSiguiente = 0;
    private int id;
    public int compareTo(Object miObjeto) {
    	Empleado otroEmpleado = (Empleado) miObjeto;
    	if(this.sueldo<otroEmpleado.sueldo){
    		return -1;
    	}
    	if(this.sueldo>otroEmpleado.sueldo){
    		return 1;
    	}
    	return 0;
    }

    public Empleado(String nom, double suel, int anio, int mes, int dia) {
        nombre = nom;
        sueldo = suel;
        GregorianCalendar calendario = new GregorianCalendar(anio, mes - 1, dia); // El mes - 1, porque Enero = 0
        altaContrato = calendario.getTime();
        ++IdSiguiente;
        id=IdSiguiente;
    }
    public Empleado(String nom){
        this(nom, 30000,2000,01,01);
    }

    public String dameNombre(){ //getter
        return nombre + " Id: " + id;
    }

    public double dameSueldo(){ //getter
        return sueldo;
    }

    public Date dameFechaContrato(){ //getter
        return altaContrato;
    }

    public void subeSueldo(double porcentaje){ //setter
        double aumento = sueldo *porcentaje/100;
        sueldo+=aumento;
    }


}

class Jefatura extends Empleado implements Jefes{
    public Jefatura(String nom, double suel, int anio, int mes, int dia){
        super(nom, suel, anio, mes, dia);

    }
    private double incetivo;

    public void establece_incentivo(double b){
        incetivo = b;
    }

    public double dameSueldo(){
        double sueldoJefe = super.dameSueldo();
        return sueldoJefe + incetivo;
    }
    
    public String tomar_decisiones (String decision) {
    	return "Un miembro de la dirección ha tomado la decisión de:" + decision;
    }

}
