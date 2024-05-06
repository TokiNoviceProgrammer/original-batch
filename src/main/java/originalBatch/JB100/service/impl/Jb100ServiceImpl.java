package originalBatch.JB100.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import originalBatch.JB100.mapper.T100Mapper;
import originalBatch.JB100.model.T100;
import originalBatch.JB100.service.Jb100Service;

@Service
//@Transactional
public class Jb100ServiceImpl implements Jb100Service {

	private final T100Mapper t100Mapper;
	private static final int COMMIT_SIZE = 5;
	private final PlatformTransactionManager platformTransactionManager;//トランザクションを管理するためのインターフェース

	public Jb100ServiceImpl(T100Mapper t100Mapper,
			PlatformTransactionManager platformTransactionManager) {
		this.t100Mapper = t100Mapper;
		this.platformTransactionManager = platformTransactionManager;
	}

	@Override
	public void exec() {
		Integer maxId = this.t100Mapper.selectMaxId();
		int startId = Objects.isNull(maxId) ? 1 : maxId + 1;

		TransactionStatus tSts = this.startTransaction();//トランザクション開始

		for (int seq = 1; seq <= 15; seq++) {
			T100 t100 = createT100(startId, seq);
			this.t100Mapper.insertT100(t100);
			if (seq % COMMIT_SIZE == 0) {
				this.endTransaction(tSts);//トランザクション終了(5件ごとにコミット)
				tSts = this.startTransaction();//トランザクション開始
			}
		}
	}

	private T100 createT100(int id, int seq) {
		T100 t100 = new T100();
		t100.setId(id);
		t100.setSeq(seq);
		t100.setCreatedId("JB100");
		t100.setUpdateId("JB100");
		return t100;
	}

	/**
	 * 新しいトランザクションを開始する
	 * @return
	 */
	private TransactionStatus startTransaction() {
		// DefaultTransactionDefinitionとは以下の通り
		// ・TransactionDefinitionインターフェースの実装クラス
		// ・トランザクションの基本的な定義を提供するクラス(Springがデフォルトで提供するトランザクション定義クラス)
		// ・通常、DefaultTransactionDefinitionを直接使用する必要はないが、トランザクションの動作をカスタマイズする場合に使用する
		DefaultTransactionDefinition dtDef = new DefaultTransactionDefinition();
		// 指定されたトランザクション定義に基づいて新しいトランザクションを開始
		TransactionStatus tSts = this.platformTransactionManager.getTransaction(dtDef);
		// 開始したトランザクションの状態を表すTransactionStatusオブジェクトを返却
		return tSts;
	}

	/**
	 * トランザクションを終了してコミットする
	 * @param tSts TransactionStatusはトランザクションの状態を表すオブジェクト
	 */
	private void endTransaction(TransactionStatus tSts) {
		this.platformTransactionManager.commit(tSts);
	}

}
