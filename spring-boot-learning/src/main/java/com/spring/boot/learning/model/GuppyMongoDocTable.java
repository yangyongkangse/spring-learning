package com.spring.boot.learning.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: yangyongkang
 * Date: 2020/1/3
 * Time: 10:54
 * Description:
 */
@Data
public class GuppyMongoDocTable implements Serializable {
	private static final long serialVersionUID = -6645072320474566788L;
	/**
	 * 文件ID
	 */
	private String fileId;

	/**
	 * 文件存取的路径
	 */
	private String filePath;


	/**
	 * 保存时的文件名
	 */
	private String fileOriginalName;

	/**
	 * 保存时的扩展名
	 */
	private String fileOriginalExtension;

	/**
	 * 文件分类
	 */
	private String fileContentType;

	/**
	 * 业务文件名
	 */
	private String fileName;

	/**
	 * 文件大小
	 */
	private long fileByteSize;

	/**
	 * 文件创建时间
	 */
	private String fileUploadTime;

	/**
	 * 文件创建人
	 */
	private String fileUploadUser;

	/**
	 * 删除标志
	 */
	private int fileIsDeleted;

	/**
	 * 文件版本
	 */
	private int fileVersion;

	/**
	 * MD5值
	 */
	private String fileMd5;

	/**
	 * 文件内容
	 */
	private byte[] fileContent;
}
