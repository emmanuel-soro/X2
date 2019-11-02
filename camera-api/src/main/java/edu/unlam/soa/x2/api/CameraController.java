package edu.unlam.soa.x2.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CameraController {

	@GetMapping("/photo")
	public ResponseEntity<String> takePhoto() {

		String photoName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		File file = new File("./ffmpeg/bin/ffmpeg.exe");
		String absolutePath = file.getAbsolutePath();

		File photoPath = new File("./photos");
		String photosAbsolutePath = photoPath.getAbsolutePath();

		String[] cmd = { absolutePath, "-y", "-i", "rtsp://admin:admin@192.168.1.195:554/h264/ch1/main/av_stream",
				"-vframes", "1", photosAbsolutePath + "\\" + photoName + ".jpg" };

		System.out.println("Foto tomada con nombre " + photoName + ".jpg");

		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return new ResponseEntity<String>(photoName, HttpStatus.OK);
	}

	@GetMapping(value = "/getPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getPhoto(@RequestParam String id) throws IOException {

		File photo = new File("./photos/" + id + ".jpg");

		return Files.readAllBytes(Paths.get(photo.getAbsolutePath()));

	}

	public Set<String> listFilesUsingJavaIO(String dir) {
		return Stream.of(new File(dir).listFiles()).filter(file -> !file.isDirectory()).map(File::getAbsolutePath)
				.collect(Collectors.toSet());
	}

}
