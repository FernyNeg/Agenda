package itsh.isic.models;

import java.io.Serializable;
import java.util.List;

public class ConsultaList<T> extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7770289893916207614L;

	private List<T> list;
	private String param;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
