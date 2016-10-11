package club.zhcs.agent;

import org.nutz.lang.Lang;
import org.nutz.web.WebLauncher;

public class MainLauncher {

	public static void main(String[] args) {
		String path = System.getProperty("java.library.path");
		if (Lang.isWin()) {
			System.setProperty("java.library.path", path + ";src/main/webapp/WEB-INF/lib");
		} else {
			System.setProperty("java.library.path", path + ":src/main/webapp/WEB-INF/lib");
		}
		WebLauncher.start(args);
	}
}
