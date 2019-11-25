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
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
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

		// String photoName =
		// LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		long timeInMilis = Instant.now().toEpochMilli();

		String photoName = String.valueOf(timeInMilis);

		File file = new File("./ffmpeg/bin/ffmpeg.exe");
		String absolutePath = file.getAbsolutePath();

		File photoPath = new File("./photos");
		String photosAbsolutePath = photoPath.getAbsolutePath();

		String[] cmd = { absolutePath, "-y", "-i", "rtsp://admin:admin@192.168.1.195:554/h264/ch1/main/av_stream",
				"-vframes", "1", photosAbsolutePath + "\\" + photoName + ".jpg" };

		try {
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
			process.destroy();
			System.out.println("Foto tomada con nombre " + photoName + ".jpg");

			this.processImage(photoName);

		} catch (Exception e) {
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

	@GetMapping("/processImage")
	public ResponseEntity<String> processImage(@RequestParam String name) {

		String result = null;

		try {

			File pythonProgram = new File("./image-processing/mainFail.py");
			File photo = new File("./photos/" + name + ".jpg");

			String[] cmd = { "python", pythonProgram.getAbsolutePath(), photo.getAbsolutePath() };

			Process process = Runtime.getRuntime().exec(cmd);

			process.waitFor();

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			String line;
			while ((line = stdInput.readLine()) != null) {
				result = line;
			}
			stdInput.close();

			while ((line = stdErr.readLine()) != null) {
				System.err.println(line);
			}
			stdErr.close();

			process.waitFor();
			process.destroy();

			result = name + " " + result;
			System.out.println("Resultado: " + result);
			System.out.println();

			File database = new File("./image-processing/results.txt");

			Path path = Paths.get(database.getAbsolutePath());

			Files.write(path, Arrays.asList(result), StandardCharsets.UTF_8,
					Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);

			return new ResponseEntity<String>(result, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getDatabase")
	public @ResponseBody List<Resultado> getDatabase() {

		File file = new File("./image-processing/results.txt");

		List<String> list = new ArrayList<>(0);
		List<Resultado> listPersona = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(file.getAbsolutePath()))) {

			list = br.lines().collect(Collectors.toList());

			for (String elemento : list) {

				String[] res = elemento.split(" ");

				listPersona.add(new Resultado(res[0], res[1]));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPersona;
	}

}
