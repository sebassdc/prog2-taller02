public class Comprador {

	String nombreLibreria;
	String numRIF;
	String nombreRepres;
	Libros caractLibros;
	double montoInicial;
	int numeroMeses;

	public Comprador(
		String nombreLibreria,
		String numRIF,
		String nombreRepres,
		Libros caractLibros,
		double montoInicial,
		int numeroMeses
	) {
		this.nombreLibreria = nombreLibreria;
		this.numRIF = numRIF;
		this.nombreRepres = nombreRepres;
		this.caractLibros = caractLibros;
		this.montoInicial = montoInicial;
		this.numeroMeses = numeroMeses;
	}

	public double getMensualidad() {
		double subtotal = this.caractLibros.getmontoTotal() - montoInicial;
		double total = (subtotal / this.numeroMeses) * 1.125;
		return total;
	}

	public void mostrar() {
		MCursor.TCen("============================================================");
		MCursor.TJus("   NOMBRE DE LA LIBRERIA = " + this.nombreLibreria);
		MCursor.TJus("           NUMERO DE RIF = " + this.numRIF);
		MCursor.TJus("NOMBRE DEL REPRESENTANTE = " + this.nombreRepres);
		this.caractLibros.mostrar();
		MCursor.TJus("           MONTO INICIAL = " + this.montoInicial);
		MCursor.TJus("             MENSUALIDAD = " + this.getMensualidad());
		MCursor.TCen("============================================================");
	}//Mostrar
}
