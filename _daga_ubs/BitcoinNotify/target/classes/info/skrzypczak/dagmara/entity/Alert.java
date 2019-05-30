package main.info.skrzypczak.dagmara.entity;

import java.math.BigDecimal;

public class Alert {

	protected String pair;
	protected BigDecimal limit;

	public Alert() {
	}

	public Alert(String pair, BigDecimal limit) {
		this.pair = pair;
		this.limit = limit;
	}

	public String getPair() {
		return pair;
	}

	public BigDecimal getLimit() {
		return limit;
	}

	public void setPair(String pair) {
		this.pair = pair;
	}

	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((limit == null) ? 0 : limit.hashCode());
		result = prime * result + ((pair == null) ? 0 : pair.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alert other = (Alert) obj;
		if (limit == null) {
			if (other.limit != null)
				return false;
		} else if (!limit.equals(other.limit))
			return false;
		if (pair == null) {
			if (other.pair != null)
				return false;
		} else if (!pair.equals(other.pair))
			return false;
		return true;
	}

}
