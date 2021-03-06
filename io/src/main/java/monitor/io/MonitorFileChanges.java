package monitor.io;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * java 7 监控文件状态(修改，删除，增加) 2014年9月1日 19:47:21
 * 
 * 经过测试：
 * 1) 注册register path注册的是文件夹，每个文件夹（包括子目录）都需要注册。
 *    在运行时创建新目录时，需要register新创建的目录。
 * 2) 文件夹重命名：触发文件夹的Delete旧文件夹和create新文件夹
 *    然后注册(update)新的文件夹
 * 3) 文件重命名：触发delete旧文件，create新文件，再触发modify新文件
 * 4) 移动文件夹等价于重命名
 * 5) 有个问题，当删除一个文件夹，这个文件夹有子文件夹，子文件夹里有文件，
 *    那么会出现删除不了的问题，尚无解决办法
 */
public class MonitorFileChanges {

	Map<WatchKey, Path> keys = new ConcurrentHashMap<WatchKey, Path>();
	private static WatchService watcher = null;

	static {
		try {
			watcher = FileSystems.getDefault().newWatchService(); // 构建文件监控服务
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void register(Path dir) throws IOException { // IOException
															// ,InterruptedException{
		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE,
				ENTRY_MODIFY); // 给文件注册监听事件

		Path existing = keys.get(key);
		if (existing == null) {
			System.out.format("register: %s\n", dir);
		} else if (!dir.equals(existing)) {
			System.out.format("update: %s -> %s\n", existing, dir);
		}

		keys.put(key, dir);
	}

	@SuppressWarnings("unchecked")
	static <T> WatchEvent<Path> cast(WatchEvent<?> event) {
		return (WatchEvent<Path>) event;
	}

	private void registPath(File files) throws IOException {
		for (File file : files.listFiles()) {
			if (file.isDirectory()) {
				register(Paths.get(file.getPath()));
				registPath(file);
			}
		}
	}

	private void fileWatch(Path dir) throws IOException, InterruptedException {
		register(dir);// 先注册主文件夹
		registPath(dir.toFile());// 再通过递归方式将子文件夹也注册进去
		while (true) {
			// 等待监视事件发生
			WatchKey key = watcher.take();

			// System.out.println(key.getClass().getName());
			Path path = keys.get(key);
			if (path == null) {
				return;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();
				if (kind == OVERFLOW) {
					continue;
				}

				// 目录监视事件的上下文是文件名
				WatchEvent<Path> evt = cast(event);
				Path name = evt.context();
				Path child = path.resolve(name);
				System.out.format(
						new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
								.format(new Date()) + "  %s|%s\n", event.kind()
								.name(), child);
				if (child.toFile().isDirectory()
						&& event.kind().name().equals("ENTRY_CREATE")) {// 对于新增的文件夹以及其子文件夹也要加入监控
					register(child);
					registPath(child.toFile());
				}
			}

			// 重置 key
			boolean valid = key.reset();
			if (!valid) {
				keys.remove(key);
				if (keys.isEmpty()) {
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		MonitorFileChanges fileWatch = new MonitorFileChanges();

		Path dir = Paths.get("C:/test/");
		try {
			fileWatch.fileWatch(dir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
