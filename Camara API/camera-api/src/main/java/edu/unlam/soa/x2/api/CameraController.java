package edu.unlam.soa.x2.api;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo")
public class CameraController {

	@GetMapping
	public ResponseEntity<String> takePhoto() {

		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		File file = new File("src/main/resources/ffmpeg/bin/ffmpeg.exe");
		String absolutePath = file.getAbsolutePath();

		System.out.println("Absoluth path: " + absolutePath);

		String[] cmd = { absolutePath, "-y", "-i", "rtsp://admin:admin@192.168.1.195:554/h264/ch1/main/av_stream",
				"-vframes", "1", time + ".jpg" };
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return new ResponseEntity<String>("Works!!!", HttpStatus.OK);
	}

}
