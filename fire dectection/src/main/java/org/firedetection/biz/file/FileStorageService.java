package org.firedetection.biz.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.firedetection.FileStorageProperties;
import org.firedetection.biz.board.service.FileService;
import org.firedetection.biz.board.vo.FileListVO;
import org.firedetection.biz.web.common.SHA256_Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
	private final Path pdfStorageLocation;
	private final Path fileStorageLocation;

	@javax.annotation.Resource(name = "FileService")
	FileService file_service;
	
	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		this.pdfStorageLocation = Paths.get(fileStorageProperties.getPdfdDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			InputStream tmp = file.getInputStream();
			SHA256_Stream sha = new SHA256_Stream();
			String hash = sha.sha256(tmp);
			String sp[] = fileName.split("\\.");
			System.out.println("file name : " + fileName + " length : " + sp.length);
			Path targetLocation = this.fileStorageLocation.resolve(hash + "." + sp[sp.length - 1]);
			Path pdfLocation = this.pdfStorageLocation.resolve(hash);

			// pdf convert
			System.out.println("file hash : " + hash + "." + sp[sp.length - 1]);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			fileName = hash + "." + sp[sp.length - 1];

			File sourceFile = new File(targetLocation.toString());
			System.out.println("양념 소스 : "+ sourceFile.toString());
			File destinationFile = new File(pdfLocation.toString());
			System.out.println("후첨 소스 : "+destinationFile.toString());

			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
			}

			PDDocument doc = PDDocument.load(sourceFile);
			PDFRenderer renderer = new PDFRenderer(doc);

			for (int i = 0; i < doc.getNumberOfPages(); i++) {
				BufferedImage image = renderer.renderImageWithDPI(i, 200); // 해상도 조절
				ImageIO.write(image, "JPEG", new File(String.format("%s/%d.jpg", destinationFile, (i + 1))));
				file_service.insertfilesname(new FileListVO(hash,i+"jpg"));
			}

			doc.close();

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file hash error");
		}
		return fileName;
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}
}
