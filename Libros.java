public class Libros {
	int cant;
	double montoVentas;
	double costoTransporte;
	public Libros(int cant, double montoVentas, double costoTransporte) {
		this.cant = cant;
		this.montoVentas = montoVentas;
		this.costoTransporte = costoTransporte;
	}

	public double getmontoTotal() {
		double impuesto = this.montoVentas * 0.14;
		return this.montoVentas + impuesto + this.costoTransporte;
	}
	
	public double getInicialValida() {
		return this.montoVentas * 0.6;
	}
	public boolean esInicialValida(double inicial) {
		return (this.montoVentas * 0.6) < inicial;
	}

	public void mostrar() {
		MCursor.TJus("                CANTIDAD = " + this.cant);
		MCursor.TJus("     MONTO DE LAS VENTAS = " + this.montoVentas);
		MCursor.TJus("    COSTO DEL TRANSPORTE = " + this.costoTransporte);
		MCursor.TJus("             MONTO TOTAL = " + this.getmontoTotal());
		MCursor.eqline();
	}//Mostrar
}
