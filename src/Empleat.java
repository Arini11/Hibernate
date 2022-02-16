import java.math.BigDecimal;

public class Empleat {
	private int emp_no,dept_no;
	private String apellido,dir,oficio,fecha_alt;
	private BigDecimal salario,comision;
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int id) {
		this.emp_no = id;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public int getDept_no() {
		return dept_no;
	}
	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getOficio() {
		return oficio;
	}
	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
	public String getFecha_alt() {
		return fecha_alt;
	}
	public void setFecha_alt(String fecha_alt) {
		this.fecha_alt = fecha_alt;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public BigDecimal getComision() {
		return comision;
	}
	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}
	
}
