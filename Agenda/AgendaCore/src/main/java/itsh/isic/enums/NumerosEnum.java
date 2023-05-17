package itsh.isic.enums;

public enum NumerosEnum {

	CERO(0), UNO(1), DOS(2),;

	private Integer numero;

	NumerosEnum(final Integer num) {
		this.numero = num;
	}

	public Integer getNumero() {
		return numero;
	}

}
