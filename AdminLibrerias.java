import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class Inscripciones {
	static byte MAX_MATERIAS = 5;
	static byte MAX_UC = 21;
	static byte MIN_UC = 2;
	static ArrayList<Alumno> ALUMNOS = new ArrayList<>();
	
	//METODO PRINCIPAL
	public static void main(String arg[]) {
		boolean alumno_inscrito = false;
		byte opc;
		do {
			opc = menu();

			// Valida que hayan alumnos inscritos
			if (opc == 2 && !alumno_inscrito) {
				MCursor.error("DEBE AGREGAR AL MENOS UN ALUMNO");
				continue;
			}
			// Solo acepta opciones del 0 al 2
			if (opc > 2 || opc < 0) { 
				MCursor.error("ESCOJA UNA OPCION DEL MENU");
				continue;
			}

			MCursor.BLinea(30);
			switch(opc) {
				case 1:
					ingresarAlumno();
					alumno_inscrito = true;
					break;
				case 2:
					mostrarAlumno();
					break;
			}//switch
		}while (opc != 0);
	}//main

	//CASO 1: METODO PARA INGRESAR UN ALUMNO
	public static void ingresarAlumno() {
		MCursor.eqline();
		MCursor.TCen("INGRESE LOS DATOS DEL ALUMNO #" + (ALUMNOS.size() + 1));

		// Nombre
		MCursor.eqline();
		MCursor.UCursor(15);
		String nombre;
		do {
			nombre = Leer.CString("NOMBRE = ").toUpperCase();
		} while (nombre.length() < 0);

		// Cedula
		MCursor.eqline();
		String cedula;
		do{
			MCursor.UCursor(15);
			cedula = Leer.CString("CEDULA = ");
		}while (cedula.length() < 0);

		// Sexo
		MCursor.eqline();
		byte sexo;
		do {
			MCursor.UCursor(15);
			sexo = Leer.NByte("SEXO (1)Masculino (2)Femenino = ");
			if ( sexo != 1 && sexo != 2) {
				MCursor.error("EL SEXO DEBE SER (1)Masculino o (2)Femenino");
				continue;
			} else break;
		} while (true);

		// Numero de materias
		MCursor.eqline();
		byte n_materias;
		do {
			MCursor.UCursor(15);
			n_materias = Leer.NByte("NUMERO DE MATERIAS INSCRITAS = ");
			if ( n_materias < 1  || n_materias > MAX_MATERIAS) {
				MCursor.error("EL NUMERO DE MATERIAS DEBE SER DE 1 A " + MAX_MATERIAS);
				continue;
			} else break;
		} while (true);

		// Numero de unidades de credito
		MCursor.eqline();
		byte n_uc;
		do {
			MCursor.UCursor(15);
			n_uc = Leer.NByte("NUMERO DE UNID DE CREDITO = ");
			if ( n_uc < MIN_UC  || n_uc > MAX_UC) {
				MCursor.error("EL NUMERO DE UC DEBE SER DE " + MIN_UC + " A " + MAX_UC);
				continue;
			} else break;
		} while (true);
		
		// Agregar
		ALUMNOS.add(
			new Alumno(
				nombre,
				cedula,
				sexo,
				n_materias,
				n_uc
			)
		);

	}

	//CASO 2: METODO PARA MOSTRAR LOS ALUMNOS
	public static void mostrarAlumno() {
		if(ALUMNOS.isEmpty()) MCursor.error("NO HAY ALUMNOS INSCRITOS");
		else {
			
			MCursor.UCursor(15);
			char alf = Leer
				.CString("¿DESEA VER LA LISTA DE ALUMNOS ORDENADA? (S/N)")
				.toUpperCase().charAt(0);
			// Si se desea, mostrar alfabeticamente.
			if (alf == 'S'){
				Collections.sort(ALUMNOS, new Comparator<Alumno>(){
					@Override
					public int compare(Alumno m1, Alumno m2) {
						// Ordenar por nombre
						return m1.nombre.compareTo(m2.nombre);
					}
				});
			}
			
			// Ahora mostrar
			for(int i = 0; i < ALUMNOS.size(); i++) {
				MCursor.BLinea(30);
				ALUMNOS.get(i).mostrar(i);
				MCursor.Salida();
			}
		}//else
		return;
	}

	//METODO PARA MOSTRAR EL MENU DE OPCIONES
	public static byte menu() {
		MCursor.BLinea(30);
		MCursor.eqline();
		MCursor.TCen("INSCRIPCIONES UNIVERSITARIAS");
		MCursor.TCen("MENU PRINCIPAL DE OPCIONES");
		MCursor.eqline();
		MCursor.TJus("1.- INGRESAR LIBRERIA");
		MCursor.TJus("2.- MODIFICAR LIBRERIA");
		MCursor.TJus("3.- MOSTRAR LIBRERIAS");
		MCursor.TJus("4.- BUSCAR POR NOMBRE");
		MCursor.TJus("5.- BUSCAR POR NUMERO DE RIF");
		MCursor.eqline();
		MCursor.TJus("0.- SALIR DEL SISTEMA");
		MCursor.eqline();
		MCursor.UCursor(25);
		return Leer.NByte("ESCOJA SU OPCION = ");
	}//menu
}//class