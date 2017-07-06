import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class AdminLibrerias {
	static ArrayList<Comprador> COMPRADORES = new ArrayList<>();
	
	//METODO PRINCIPAL
	public static void main(String arg[]) {
		boolean cliente_agregado = false;
		byte opc;
		do {
			opc = menu();

			// Valida que hayan alumnos inscritos
			if (opc == 2 && !cliente_agregado) {
				MCursor.error("DEBE AGREGAR AL MENOS UN ALUMNO");
				continue;
			}
			// Solo acepta opciones del 0 al 5
			if (opc > 5 || opc < 0) { 
				MCursor.error("ESCOJA UNA OPCION DEL MENU");
				continue;
			}

			MCursor.BLinea(30);
			switch(opc) {
				case 1:
					ingresarCliente();
					cliente_agregado = true;
					break;
				case 2:
					// modificarDatos();
					break;
				case 3:
					mostrarListado();
					break;
				case 4:
					buscarPorNombre();
					break;
				case 5:
					// buscarPorRif();
					break;
			}//switch
		}while (opc != 0);
	}//main

	//CASO 1: METODO PARA INGRESAR UN COMPRADOR
	public static void ingresarCliente() {
		MCursor.eqline();
		MCursor.TCen("INGRESE LOS DATOS DEL CLIENTE #" + (COMPRADORES.size() + 1));

		// Nombre de la libreria
		MCursor.eqline();
		MCursor.UCursor(15);
		String nombreLibreria;
		do {
			nombreLibreria = Leer.CString("NOMBRE DE LA LIBRERIA = ").toUpperCase();
		} while (nombreLibreria.length() < 0);

		// Numero de rif
		MCursor.eqline();
		String numRif;
		do{
			MCursor.UCursor(15);
			numRif = Leer.CString("NUMERO DE RIF = ").toUpperCase();
		}while (numRif.length() < 0);

		// Nombre del representante
		MCursor.eqline();
		String nombreRepresentante;
		do {
			MCursor.UCursor(15);
			nombreRepresentante = Leer.CString("NOMBRE DEL REPRESENTANTE = ");
		} while (nombreRepresentante.length() < 0);

		// Cantidad de Libros
		MCursor.eqline();
		int cantidadLibros = 0;
		do {
			MCursor.UCursor(15);
			cantidadLibros = Leer.NInt("CANTIDAD DE LIBROS = ");
			if (cantidadLibros <= 0) {
				MCursor.error("LA CANTIDAD DE LIBROS DEBE SER MAYOR A 0");
				continue;
			}
			break;
		} while (true);

		// Monto de las ventas
		MCursor.eqline();
		double montoVentas = 0;
		do {
			MCursor.UCursor(15);
			montoVentas = Leer.NDouble("MONTO DE LAS VENTAS = ");
			if (montoVentas < 0) {
				MCursor.error("EL MONTO DE LAS VENTSA DEBE SER MAYOR O IGUAL A 0");
				continue;
			}
			break;
		} while (true);

		// Costo del transporte
		MCursor.eqline();
		double costoTransporte = 0;
		do {
			MCursor.UCursor(15);
			costoTransporte = Leer.NDouble("COSTO DEL TRANSPORTE = ");
			if (costoTransporte < 0) {
				MCursor.error("EL COSTO DEL TRANSPORTE DEBE SER MAYOR O IGUAL A 0");
				continue;
			}
			break;
		} while (true);

		// Crear un objeto tipo Libros (caracteristicas)
		Libros caractLibros = new Libros(cantidadLibros, montoVentas, costoTransporte);

		// Monto inicial
		MCursor.eqline();
		double inicial = 0;
		do {
			MCursor.UCursor(15);
			inicial = Leer.NDouble("MONTO DE LA INICIAL A PAGAR = ");
			if (caractLibros.esInicialValida(inicial)) {
				break;
			}
			MCursor.error("LA INICIAL DEBE SER MAYOR A " + caractLibros.getInicialValida());
			continue;
		} while (true);

		// Numero de meses
		MCursor.eqline();
		int n_m = 0;
		do {
			MCursor.UCursor(15);
			n_m = Leer.NInt("¿EN CUANTOS MESES PAGARA? (6)(8) O (10)= ");
			if (n_m == 6 || n_m == 8 || n_m == 10) {
				break;
			}
			MCursor.error("EL NUMERO DE MESES DEBE SER (6)(8) O (10)");
			continue;
		} while (true);
		
		// Agregar un comprador a memoria
		COMPRADORES.add(
			new Comprador(
				nombreLibreria,
				numRif,
				nombreRepresentante,
				caractLibros,
				inicial,
				n_m
			)
		);
	}

	//CASO 3: METODO PARA MOSTRAR EL LISTADO DE COMPRADORES
	public static void mostrarListado() {
		if(COMPRADORES.isEmpty()) MCursor.error("NO HAY ALUMNOS INSCRITOS");
		else {
			
			// Ordenar alfabeticamente por el campo nombreLibreria
			Collections.sort(COMPRADORES, new Comparator<Comprador>(){
				@Override
				public int compare(Comprador m1, Comprador m2) {
					// Ordenar por nombreLibreria
					return m1.nombreLibreria.compareTo(m2.nombreLibreria);
				}
			});
			
			// Ahora mostrar
			COMPRADORES.forEach(e -> {
				MCursor.BLinea(30);
				e.mostrar();
				MCursor.Salida();
			});
		}//else
		return;
	}

	// CASO 4: METODO PARA BUSCAR POR NOMBRE

	public static void buscarPorNombre() {
		MCursor.eqline();
		MCursor.TCen("INGRESE EL NOMBRE DE LA LIBREIA QUE DESEA BUSCAR");
		MCursor.eqline();
		String n_l;
		do {
			n_l = Leer.CString("LIBRERIA = ").toUpperCase();
		} while (n_l.length() < 0);
		
		final String n_libreria = n_l;

		// COMPRADORES.clone()  metodo para copiar el array el array copiado deberias filtrarlo

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