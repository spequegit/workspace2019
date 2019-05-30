package archive02;

import java.io.Serializable;

public class Operation implements Serializable {

	private static final long serialVersionUID = -8379077932042052683L;

	private OperationType type;

	private int maxSpeed;

	public Operation(OperationType type) {
		this.type = type;
	}

	public OperationType getType() {
		return type;
	}

	public void setType(OperationType type) {
		this.type = type;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
}
