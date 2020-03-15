package com.gp03d.learn.springboot.filestorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.stream.Stream;

@Service
public class FileStorageImpl implements FileStorage {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	/**
	 * 存储文件夹的名字
	 */
	private final Path taskLocation = Paths.get("D:\\filestorage\\task");
	private final Path coursewareLocation = Paths.get("D:\\filestorage\\courseware");


	/**
	 * 把上传的文件拷贝到指定位置
	 * <p>
	 * {@link Path#resolve(String)} : 将给定的路径字符串转换为路径，并以解析方法指定的方式针对该路径解析该字符串。
	 * 例如，假设名称分隔符为“/”，路径表示“foo/bar”，那么使用路径字符串“gus”调用此方法将导致路径“foo/bar/gus”。
	 * </p>
	 * <p>{@link StandardCopyOption#REPLACE_EXISTING} : 替换现有文件（如果存在）</p>
	 *
	 * @param file
	 */
	@Override
	public void store1(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(),
					this.taskLocation.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}

	@Override
	public void store2(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(),
					this.coursewareLocation.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	/**
	 * 加载文件资源
	 * @param filename
	 * @return
	 */
	@Override
	public Resource loadFile1(String filename) {
		try {
			Path file = taskLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error! -> message = " + e.getMessage());
		}
	}

	@Override
	public Resource loadFile2(String filename) {
		try {
			Path file = coursewareLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error! -> message = " + e.getMessage());
		}
	}
	/**
	 * {@link FileSystemUtils#deleteRecursively(File)}: 删除提供的文件 - 对于目录，也以递归方式删除任何嵌套目录或文件。
	 * 注意：与File.delete（）一样，此方法不会抛出任何异常，而是在I / O错误的情况下以静默方式返回false。
	 * 考虑使用deleteRecursively（Path）进行NIO样式的I / O错误处理，明确区分不存在和删除现有文件的失败。
	 */
	//@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(taskLocation.toFile());
	}

	/**
	 * {@link Files#createDirectory(Path, FileAttribute[])} :创建一个新目录。
	 * 检查文件是否存在以及如果目录不存在则创建目录是针对可能影响目录的所有其他文件系统活动的原子操作。
	 * 应该在需要首先创建所有不存在的父目录的地方使用createDirectories方法。
	 * attrs参数是可选的文件属性，可在创建目录时以原子方式设置。 每个属性都由其名称标识。 如果数组中包含多个同名属性，则忽略除最后一次出现的所有属性。
	 */
	//@Override
	public void init() {
		try {
			Files.createDirectory(taskLocation);
		} catch (IOException e) {
			throw new RuntimeException("无法初始化存储空间!");
		}
	}

	/**
	 * 获取所有文件的信息
	 * @return
	 */
	@Override
	public Stream<Path> loadFiles1() {
		try {
			return Files.walk(this.taskLocation, 1)
					.filter(path -> !path.equals(this.taskLocation))
					.map(this.taskLocation::relativize);
		} catch (IOException e) {
			throw new RuntimeException("\"无法读取存储的文件");
		}
	}

	@Override
	public Stream<Path> loadFiles2() {
		try {
			return Files.walk(this.coursewareLocation, 1)
					.filter(path -> !path.equals(this.coursewareLocation))
					.map(this.coursewareLocation::relativize);
		} catch (IOException e) {
			throw new RuntimeException("\"无法读取存储的文件");
		}
	}
}