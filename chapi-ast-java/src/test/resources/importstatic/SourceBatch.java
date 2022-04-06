package infrastructure;

import static infrastructure.utils.SqlGenerator.generateBatchInsertSql;

public class SourceBatch extends DefaultBatchImpl {
    @Override
    public void execute() {
        String sql = generateBatchInsertSql(table, values);
    }
}

