package originalBatch.JB100.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class T100 {
	private Integer id;
	private Integer seq;
	private String column01;
	private String column02;
	private String column03;
	private String createdId;
	private LocalDateTime createdAt;
	private String updateId;
	private LocalDateTime updateAt;
}
