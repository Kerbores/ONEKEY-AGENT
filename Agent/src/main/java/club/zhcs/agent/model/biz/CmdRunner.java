package club.zhcs.agent.model.biz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.nutz.filepool.FilePool;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Streams;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.google.common.collect.Lists;

/**
 * @author kerbores
 *
 * @email kerbores@gmail.com
 *
 */
@IocBean
public class CmdRunner {

	Log log = Logs.getLog(CmdRunner.class);

	@Inject
	FilePool pool;

	/**
	 * 同步执行
	 * 
	 * @param cmd
	 *            命令
	 * @return 命令结果
	 */
	public List<String> exe(String cmd) {
		Process process = null;
		List<String> processList = new ArrayList<String>();
		try {
			process = Runtime.getRuntime().exec(cmd);
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		} catch (IOException e) {
			log.error(e);
			return Lang.array2list(new String[] { e.getMessage() });
		}
		return processList;
	}

	/**
	 * 异步执行
	 * 
	 * @param cmd
	 *            命令
	 * @return 命令结果 id
	 */
	public long exeAsyn(String cmd) {
		Process process = null;
		File file = pool.createFile("txt");
		try {
			process = Runtime.getRuntime().exec(cmd);
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				Files.write(file, line);
			}
			input.close();
		} catch (IOException e) {
			log.error(e);
			Files.write(file, e.getMessage());
		}
		return pool.getFileId(file);
	}

	/**
	 * 获取命令返回
	 * 
	 * @param id
	 *            命令结果 id
	 * @param start
	 *            开始行
	 * @param end
	 *            结束行
	 * @return 内容列表
	 * @throws FileNotFoundException
	 */
	public List<String> getCmdContent(long id, int start, int end) throws FileNotFoundException {
		File file = pool.getFile(id, "txt");
		if (file == null) {
			return Lists.newArrayList();
		}
		FileReader in = new FileReader(file);
		LineNumberReader reader = new LineNumberReader(in);

		List<String> target = Lists.newArrayList();
		if (start > end) {// 处理起止位置
			int temp = start;
			start = end;
			end = temp;
		}
		int lines = end - start;

		reader.setLineNumber(start);// 到起始行
		for (int i = 0; i < lines; i++) { // 开始读
			try {
				target.add(reader.readLine());
			} catch (IOException e) {
				log.error(e);
				break;
			}
		}
		Streams.safeClose(reader);// 关闭流
		return target;
	}

}
