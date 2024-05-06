package originalBatch.JB100.mapper;

import org.apache.ibatis.annotations.Mapper;

import originalBatch.JB100.model.T100;

@Mapper
public interface T100Mapper {
	public Integer selectMaxId();

	public void insertT100(T100 t100);
}
