package originalBatch.JB100.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Jb100Serviceのテスト")
public class Jb100ServiceTest {

	@Autowired
	private Jb100Service targetService;

	@Test
	@DisplayName("テストケース01")
	public void test01() {
		// テストケースの準備
		BigDecimal val = new BigDecimal("10.5");
		BigDecimal lower = new BigDecimal("10.0");
		BigDecimal upper = new BigDecimal("20.0");

		// テスト実行
		assertTrue(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("テストケース02")
	public void test02() {
		// テストケースの準備
		BigDecimal val = new BigDecimal("10.5");
		BigDecimal lower = new BigDecimal("10.5");
		BigDecimal upper = new BigDecimal("20.0");

		// テスト実行
		assertTrue(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("テストケース03")
	public void test03() {
		// テストケースの準備
		BigDecimal val = new BigDecimal("10.5");
		BigDecimal lower = new BigDecimal("10.0");
		BigDecimal upper = new BigDecimal("10.5");

		// テスト実行
		assertTrue(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("テストケース04")
	public void test04() {
		// テストケースの準備
		BigDecimal val = new BigDecimal("10.5");
		BigDecimal lower = new BigDecimal("9.0");
		BigDecimal upper = new BigDecimal("10.0");

		// テスト実行
		assertFalse(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("テストケース05")
	public void test05() {
		// テストケースの準備
		BigDecimal val = new BigDecimal("-10.5");
		BigDecimal lower = new BigDecimal("-11.0");
		BigDecimal upper = new BigDecimal("-10.0");

		// テスト実行
		assertTrue(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("テストケース06")
	public void test06() {
		// テストケースの準備
		BigDecimal val = new BigDecimal("-10.5");
		BigDecimal lower = new BigDecimal("-10.0");
		BigDecimal upper = new BigDecimal("-9.0");

		// テスト実行
		assertFalse(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("テストケース07")
	public void test07() {
		// テストケースの準備
		BigDecimal val = null;
		BigDecimal lower = new BigDecimal("10.0");
		BigDecimal upper = new BigDecimal("11.0");

		// テスト実行
		assertFalse(targetService.isInRange(val, lower, upper));
	}

}
