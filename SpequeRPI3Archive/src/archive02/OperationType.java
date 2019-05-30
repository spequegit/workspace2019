package archive02;

import java.awt.event.KeyEvent;
import java.util.Set;

import com.google.common.collect.Sets;

public enum OperationType {
	FASTER(KeyEvent.VK_A), SLOWER(KeyEvent.VK_Z), SETUP, LEFT(KeyEvent.VK_LEFT), RIGHT(KeyEvent.VK_RIGHT), BACKWARD(KeyEvent.VK_DOWN), BACKWARD_RIGHT(KeyEvent.VK_DOWN,
			KeyEvent.VK_RIGHT), BACKWARD_LEFT(KeyEvent.VK_DOWN, KeyEvent.VK_LEFT), FORWARD(KeyEvent.VK_UP), FORWARD_RIGHT(KeyEvent.VK_UP, KeyEvent.VK_RIGHT), FORWARD_LEFT(KeyEvent.VK_UP,
					KeyEvent.VK_LEFT), END(KeyEvent.VK_X), STOP, SEARCH, PROGRAM_1;

	private Set<Integer> keySet = Sets.newHashSet();

	OperationType(int... keyCodes) {
		for (int keyCode : keyCodes) {
			getKeys().add(keyCode);
		}
	}

	public Set<Integer> getKeys() {
		return keySet;
	}

	public static OperationType findByKeySet(Set<Integer> keySet) {
		for (OperationType operation : values()) {
			boolean size = keySet.size() == operation.getKeys().size();
			boolean contains = keySet.containsAll(operation.getKeys());
			if (size && contains) {
				return operation;
			}
		}
		return null;
	}
}
