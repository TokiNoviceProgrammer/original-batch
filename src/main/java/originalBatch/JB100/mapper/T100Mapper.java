package originalBatch.JB100.mapper;

import org.apache.ibatis.annotations.Mapper;

import originalBatch.JB100.model.T100;

/**
 * T100へのDB操作
 */
@Mapper
public interface T100Mapper {
	/**
	 * T100のidの最大値を取得
	 * @return
	 */
	public Integer selectMaxId();

	/**
	 * T100のデータを登録
	 * @param t100
	 */
	public void insertT100(T100 t100);
}
