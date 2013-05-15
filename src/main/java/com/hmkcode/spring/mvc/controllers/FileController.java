package com.hmkcode.spring.mvc.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/controller")
public class FileController {

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request, HttpServletResponse response) {

		MultipartFile mpf = request.getFile("fileUpload");
		System.out.println(mpf.getOriginalFilename() + " uploaded!");
		InputStream is = null;
		BufferedImage image = null;
		try {
			try {
				is = mpf.getInputStream();
				image = ImageIO.read(is);
			} finally {
				if (is != null) {
					is.close();
				}
			}
			ImageIO.write(image, "png", new File("src/main/webapp/uploads/" + mpf.getOriginalFilename()));
		} catch (IOException e) {
			System.out.println(mpf.getOriginalFilename() + " saving error!");
		}
		return "{\"success\":true}";
	}
}
