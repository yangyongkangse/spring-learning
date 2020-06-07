package com.spring.boot.learning.tools;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.spring.boot.learning.model.GuppyMongoDocTable;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author: yangyongkang
 * Date: 2020/1/3
 * Time: 10:53
 * Description:
 */
@Component
@Log4j2
public class GuppyMongoUtils {

	private MongoTemplate mongoTemplate ;

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	private GridFsTemplate fileTemplate;

	@Autowired
	public void setGridFsTemplate(GridFsTemplate fileTemplate) {
		this.fileTemplate = fileTemplate;
	}

	private GridFsOperations fileOperations;

	@Autowired
	public void setGridFsOperations(GridFsOperations fileOperations) {
		this.fileOperations = fileOperations;
	}

	/**
	 * @author: yangyongkang
	 * date:2020/1/3
	 * time:11:01
	 * description:自动创建并保存文档
	 **/
	public GuppyMongoDocTable storeDoc(FileInputStream input) {
		String autoFileName = String.valueOf(Calendar.getInstance().getTimeInMillis());
		log.info("======== 准备自动命名并保存文档: " + autoFileName + " ======");
		String saveFileId = storeDoc(input, autoFileName);
		GuppyMongoDocTable autoDoc = new GuppyMongoDocTable();
		autoDoc.setFileId(saveFileId);
		autoDoc.setFileName(autoFileName);
		log.info("======== 文档: " + autoFileName + " 已成功保存[store]: ObjectID(" + saveFileId + ") ========");
		return autoDoc;
	}


	/**
	 * @author: yangyongkang
	 * date:2020/1/3
	 * time:11:01
	 * description:保存文档
	 **/
	public String storeDoc(FileInputStream input, String fileName) {
		log.info("======== 准备保存文档: " + fileName + " ======");
		ObjectId mongoObjectId = fileTemplate.store(input, fileName);
		log.info("======== 文档: " + fileName + " 已成功保存[store]: ObjectID(" + mongoObjectId.toHexString() + ") ========");
		return mongoObjectId.toHexString();
	}

	/**
	 * @author: yangyongkang
	 * date:2020/1/3
	 * time:11:01
	 * description:保存文档
	 **/
	public String storeDoc(FileInputStream input, String fileName, String fileContentType) {
		log.info("======== 准备保存文档: " + fileName + "." + fileContentType + " ======");
		ObjectId mongoObjectId = fileTemplate.store(input, fileName, fileContentType);
		log.info("======== 文档: " + fileName + "." + fileContentType + " 已成功保存[store]: ObjectID(" + mongoObjectId.toHexString() + ") ========");
		return mongoObjectId.toHexString();
	}

	/**
	 * @author: yangyongkang
	 * date:2020/1/3
	 * time:11:01
	 * description:上传文档
	 **/
	public String uploadDoc(FileInputStream uploadStream, String fileName) {
		log.info("======== 准备上传文档: " + fileName + " ======");
		GridFSBucket docFileBucket = GridFSBuckets.create(mongoTemplate.getDb());
		ObjectId mongoOjbectId = docFileBucket.uploadFromStream(fileName, uploadStream);
		log.info("======== 文档: " + fileName + " 已上传成功[upload]: ObjectID(" + mongoOjbectId.toHexString() + ") ========");
		return mongoOjbectId.toHexString();
	}

	/**
	 * @author: yangyongkang
	 * date:2020/1/3
	 * time:11:02
	 * description:根据id获取文档
	 **/
	public GridFSFile getDocById(String fileId) {
		log.info("======== 准备下载文档: ObjectId(" + fileId + ") ======");
		Query docQuery = Query.query(Criteria.where("_id").is(fileId));
		return fileOperations.findOne(docQuery);
	}

	/**
	 * @author: yangyongkang
	 * date:2020/1/3
	 * time:11:02
	 * description:根据文档id删除文档
	 **/
	public boolean deleteDocById(String fileId) {
		boolean deleteResult = false;
		try {
			log.info("======== 准备删除文档: ObjectId(" + fileId + ") ======");
			Query docQuery = Query.query(Criteria.where("_id").is(fileId));
			GridFSFile docFile = fileOperations.findOne(docQuery);
			log.info("======== 已确认文档: " + docFile.toString() + " ======");
			GridFSBucket docFileBucket = GridFSBuckets.create(mongoTemplate.getDb());
			docFileBucket.delete(docFile.getObjectId());
			log.info("======== 已删除文档: ObjectId(" + fileId + ") ======");
			deleteResult = true;
		} catch (Exception e) {
			log.error("======== 【警告】删除文档: ObjectId(" + fileId + ")出现异常 ======");
			e.printStackTrace();
		}
		return deleteResult;
	}

	/**
	 * @author: yangyongkang
	 * date:2020/1/3
	 * time:11:02
	 * description:按照id下载文档
	 **/
	public void downloadDoc(String fileId, HttpServletResponse response) throws IOException {
		log.info("======== 准备下载文档: ObjectId(" + fileId + ") ======");
		Query docQuery = Query.query(Criteria.where("_id").is(fileId));
		GridFSFile docFile = fileOperations.findOne(docQuery);
		log.info("======== 已确认文档: " + docFile.toString() + " ======");
		GridFSBucket docFileBucket = GridFSBuckets.create(mongoTemplate.getDb());
		ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
		docFileBucket.downloadToStream(docFile.getObjectId(), downloadStream);
		log.info("========文档: " + docFile.toString() + " 已下载成功[download]: ObjectID(" + docFile.getObjectId().toHexString() + ") ======");
		// 设置文件名
		response.addHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(docFile.getFilename(), "UTF-8"));
		downloadStream.writeTo(response.getOutputStream());
	}
}
