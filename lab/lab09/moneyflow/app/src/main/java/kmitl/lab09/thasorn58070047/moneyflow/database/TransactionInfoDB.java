package kmitl.lab09.thasorn58070047.moneyflow.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kmitl.lab09.thasorn58070047.moneyflow.model.TransactionInfo;

@Database(entities = {TransactionInfo.class}, version = 1)
public abstract class TransactionInfoDB extends RoomDatabase{
    public abstract TransactionInfoDAO transactionInfoDAO();
}
