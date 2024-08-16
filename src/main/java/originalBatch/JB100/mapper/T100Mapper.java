package originalBatch.JB100.mapper;

import org.apache.ibatis.annotations.Mapper;

import originalBatch.JB100.model.T100;

@Mapper
/**
 * T100へのDB操作
 */
public interface T100Mapper {
	/**
	 * T100のidの最大値を取得
	 * @return
	 */
	public Integer selectMaxId();

	public void insertT100(T100 t100);
}
