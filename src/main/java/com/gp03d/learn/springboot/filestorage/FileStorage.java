package com.gp03d.learn.springboot.filestorage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 文件存储服务
 */
public interface FileStorage {
	/**
	 * 把文件上传到指定位置
	 * @param file
	 */
	void store1(MultipartFile file);
	void store2(MultipartFile file);

	/**
	 * 加载文件资源
	 * @param filename
	 * @return
	 */
	Resource loadFile1(String filename);
	Resource loadFile2(String filename);

	/**
	 * 删除目录及目录下所有的文件
	 */
	//void deleteAll();

	/**
	 * 初始化存储空间
	 */
	//void init();

	/**
	 *	获取所有文件的信息
	 * @return
	 */
	Stream<Path> loadFiles1();
	Stream<Path> loadFiles2();
}
