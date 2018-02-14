package com.jt.manage.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
/**
 * 图片上传
 * @author 18679
 *
 */
@Controller
public class PicUploadController {
	
	
	@ResponseBody
	@RequestMapping("/pic/upload")
	public PicUploadResult upload(MultipartFile uploadFile) throws IllegalStateException, IOException{
		//图片对象
		PicUploadResult result=new PicUploadResult();
		
		
		//1.校验图片扩展名是否正确
		String oldName = uploadFile.getOriginalFilename();
		String extName = oldName.substring(oldName.lastIndexOf("."));
		
		boolean matches = oldName.matches("^.*(?i)(jpg|jpeg|png|bmp|gif)$");  //jpg,jpeg,gif,png
		
		if(!matches){
			result.setError(1);//0代表正常，1代表错误
			return result;
		}
		
		//2.是否是木马文件
		try {
			BufferedImage image=ImageIO.read(uploadFile.getInputStream());
			result.setHeight(image.getHeight()+"");
			result.setWidth(image.getWidth()+"");
		} catch (Exception e) {
			result.setError(1);
			return result;
		}
		
		//3.生成图片的路径（绝对和相对路径）
		String dir="D:/jt-upload/images/";
		String path=new SimpleDateFormat("yyyy/MM/dd/").format(new Date()); 
		File _dir = new File(dir+path);
		if(!_dir.exists()){
			_dir.mkdirs();
		}
		String newFileName=System.currentTimeMillis()+""+RandomUtils.nextInt(100, 999)+extName;
		//相对路径
		result.setUrl("http://image.jt.com/"+path+newFileName);
		//绝对路径
		uploadFile.transferTo(new File(dir+path+newFileName));
		return result;
	}
	
}
