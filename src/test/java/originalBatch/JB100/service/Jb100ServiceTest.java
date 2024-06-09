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
	@DisplayName("値が範囲内にある場合（中間値）")
	public void test01() {
		BigDecimal val = new BigDecimal("10.5");
		BigDecimal lower = new BigDecimal("10.0");
		BigDecimal upper = new BigDecimal("20.0");
		assertTrue(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("値が範囲の下限と等しい場合")
	public void test02() {
		BigDecimal val = new BigDecimal("10.0");
		BigDecimal lower = new BigDecimal("10.0");
		BigDecimal upper = new BigDecimal("20.0");
		assertTrue(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("値が範囲の上限と等しい場合")
	public void test03() {
		BigDecimal val = new BigDecimal("20.0");
		BigDecimal lower = new BigDecimal("10.0");
		BigDecimal upper = new BigDecimal("20.0");
		assertTrue(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("値が範囲外の場合（上限を超える）")
	public void test04() {
		BigDecimal val = new BigDecimal("21.0");
		BigDecimal lower = new BigDecimal("10.0");
		BigDecimal upper = new BigDecimal("20.0");
		assertFalse(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("値が範囲外の場合（下限未満）")
	public void test05() {
		BigDecimal val = new BigDecimal("9.0");
		BigDecimal lower = new BigDecimal("10.0");
		BigDecimal upper = new BigDecimal("20.0");
		assertFalse(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("値が負の範囲内にある場合")
	public void test06() {
		BigDecimal val = new BigDecimal("-10.5");
		BigDecimal lower = new BigDecimal("-11.0");
		BigDecimal upper = new BigDecimal("-10.0");
		assertTrue(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("値が負の範囲外の場合")
	public void test07() {
		BigDecimal val = new BigDecimal("-10.5");
		BigDecimal lower = new BigDecimal("-10.0");
		BigDecimal upper = new BigDecimal("-9.0");
		assertFalse(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("値がnullの場合")
	public void test08() {
		BigDecimal val = null;
		BigDecimal lower = new BigDecimal("10.0");
		BigDecimal upper = new BigDecimal("11.0");
		assertFalse(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("下限がnullの場合")
	public void test09() {
		BigDecimal val = new BigDecimal("10.5");
		BigDecimal lower = null;
		BigDecimal upper = new BigDecimal("11.0");
		assertFalse(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("上限がnullの場合")
	public void test10() {
		BigDecimal val = new BigDecimal("10.5");
		BigDecimal lower = new BigDecimal("10.0");
		BigDecimal upper = null;
		assertFalse(targetService.isInRange(val, lower, upper));
	}

	@Test
	@DisplayName("下限が上限より大きい場合")
	public void test11() {
		BigDecimal val = new BigDecimal("10.5");
		BigDecimal lower = new BigDecimal("20.0");
		BigDecimal upper = new BigDecimal("10.0");
		assertFalse(targetService.isInRange(val, lower, upper));
	}
}
