package originalBatch.JB100.service;

import java.math.BigDecimal;

public interface Jb100Service {

	void exec();

	boolean isInRange(BigDecimal val, BigDecimal lower, BigDecimal upper);

}
