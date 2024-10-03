import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KattisSampleFileTest {
  private static final String INPUT_FILEEXTENSION = ".in";
  private static final String OUTPUT_FILEEXTENSION = ".out";
  private final InputStream standardIn = System.in;
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() throws FileNotFoundException {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setIn(standardIn);
    System.setOut(standardOut);
  }

  @ParameterizedTest
  @MethodSource("providedTestFiles")
  void test(int testFile) throws FileNotFoundException, IOException {
    String inputFile = String.format("src/test/resources/%d%s", testFile, INPUT_FILEEXTENSION);
    String expectedOutputFile = String.format("src/test/resources/%d%s", testFile, OUTPUT_FILEEXTENSION);

    try (FileInputStream fileInputStream = new FileInputStream(inputFile);
        FileInputStream expectedOutput = new FileInputStream(expectedOutputFile)) {
      System.setIn(fileInputStream);
      Main.main(new String[0]);
      assertEquals(readFile(expectedOutput), outputStreamCaptor.toString());
    } catch (IOException e) {
      fail(e);
    }
  }

  private static Stream<Arguments> providedTestFiles() {
    return Stream.of(new File("src/test/resources").listFiles())
        .filter(file -> !file.isDirectory())
        .map(File::getName)
        .filter(fileName -> fileName.endsWith(INPUT_FILEEXTENSION))
        .map(fileName -> fileName.substring(0, fileName.length() - INPUT_FILEEXTENSION.length()))
        .map(fileName -> Arguments.of(fileName));
  }

  private String readFile(FileInputStream file) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        sb.append(line);
        sb.append('\n');
      }
      return sb.toString();
    }
  }
}
