package cleaner;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.stereotype.Component;

/**
 * Hello world!
 *
 */
@Component
public class CleanerController {

	public void clean() throws FileNotFoundException {

		File folder = new File("d://movies/bajki");
		File[] files = folder.listFiles();

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				normalize(files[i]);
			}
		}

	}

	private void normalize(File file) {
		String name = file.getName();

		name = name.replace("(", "");
		name = name.replace(")", "");

		System.out.println(name);
	}

	public static void main(String[] args) throws FileNotFoundException {
		new CleanerController().clean();
	}
}
