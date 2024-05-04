package originalBatch.JB100;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import originalBatch.JB100.service.Jb100Service;

@Component
@ConditionalOnProperty(value = "exec", havingValue = "JB100")
public class Jb100Runner implements CommandLineRunner, ExitCodeExceptionMapper {

	private final Jb100Service jb100Service;

	private static final Logger LOGGER = LoggerFactory.getLogger(Jb100Runner.class);

	public Jb100Runner(Jb100Service jb100Service) {
		this.jb100Service = jb100Service;
	}

	@Override
	public void run(String... args) throws Exception {
		// バッチ処理
		LOGGER.info("[開始]Jb100Runner");
		// 引数不正時に例外をスロー
		if (args.length != 0) {
			throw new IllegalArgumentException("Invalid number of arguments.");
		}

		this.jb100Service.exec();

		LOGGER.info("[終了]Jb100Runner");
	}

	@Override
	public int getExitCode(Throwable exception) {
		if (Objects.isNull(exception)) {
			return 1;
		}
		// バッチ処理異常時の終了コード判定
		//		if (exception.getCause() instanceof IllegalArgumentException) {
		//			IllegalArgumentException ex = (IllegalArgumentException) exception.getCause();
		//			// ...
		//		}
		return 9;
	}
}