package edu.unlam.soa.x2.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

			this.processImage(photoName);
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

	@GetMapping(value = "/getLastPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getLastPhoto() throws IOException {

		try {

			List<String> files = Files.list(Paths.get("./photos")).map(Path::toFile).map(File::getAbsolutePath)
					.sorted(Comparator.reverseOrder()).collect(Collectors.toList());

			System.out.println("Foto enviada " + files.get(0));

			return Files.readAllBytes(Paths.get(files.get(0)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void processImage(String name) {

		String result = null;
		String in = null;

		try {

			File pythonProgram = new File("./image-processing/mainFail.py");

			File photo = new File("./photos/" + name + ".jpg");

			String[] cmd = { "python", pythonProgram.getAbsolutePath(), photo.getAbsolutePath() };

			Process p = Runtime.getRuntime().exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			// read the output from the command
			System.out.println("Standard output of the command:\n");

			while ((in = stdInput.readLine()) != null) {
				result = in;
			}

			result = result + " " + name;
			System.out.println("Resultado: " + result);

			File database = new File("./image-processing/results.txt");

			Path path = Paths.get(database.getAbsolutePath());

			Files.write(path, Arrays.asList(result), StandardCharsets.UTF_8,
					Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);

		} catch (IOException e) {
			System.out.println("Exception happened - here's what I know: ");
			e.printStackTrace();
		}
	}

}
