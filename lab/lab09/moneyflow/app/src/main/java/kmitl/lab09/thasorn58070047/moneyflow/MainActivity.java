package kmitl.lab09.thasorn58070047.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kmitl.lab09.thasorn58070047.moneyflow.adapter.TransactionInfoAdapter;
import kmitl.lab09.thasorn58070047.moneyflow.database.TransactionInfoDB;
import kmitl.lab09.thasorn58070047.moneyflow.model.Balance;
import kmitl.lab09.thasorn58070047.moneyflow.model.TransactionInfo;

public class MainActivity extends AppCompatActivity implements TransactionInfoAdapter.TransactionInfoAdapterListener{

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.text_balance)
    TextView text_balance;

    @BindView(R.id.btn_transaction)
    Button btn_transaction;

    private TransactionInfoDB transactionInfoDB;
    private TransactionInfoAdapter transactionInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Create activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Show friendly toast
        Toast.makeText(MainActivity.this,
                "็Have fun with moneyflow app!\nMulti-language support", Toast.LENGTH_LONG).show();

        ButterKnife.bind(this);
        initialInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void initialInstance(){

        //start database in activity
        transactionInfoDB = Room.databaseBuilder(this,
                TransactionInfoDB.class,
                "TRANSACTION_INFO_DB")
                .fallbackToDestructiveMigration()
                .build();

        transactionInfoAdapter = new TransactionInfoAdapter(this, this);
        recyclerView.setAdapter(transactionInfoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void loadData() {

        new AsyncTask<Void, Void, Balance>() {
            @Override
            protected Balance doInBackground(Void... voids) {
                return transactionInfoDB.transactionInfoDAO().getBalance();
            }

            @Override
            protected void onPostExecute(Balance balance) {
                int total = balance.getBalance();
                double balanceRatio = (double) total/balance.getSum_income();

                //Check color by money
                if (balanceRatio < 0.25) {
                    text_balance.setTextColor(Color.parseColor("#ff0000"));
                } else if (balanceRatio <= 0.5) {
                    text_balance.setTextColor(Color.parseColor("#ff7700"));
                } else {
                    text_balance.setTextColor(Color.parseColor("#00ff00"));
                }

                text_balance.setText(NumberFormat.getNumberInstance().format(total));
            }
        }.execute();

        new AsyncTask<Void, Void, List<TransactionInfo>>() {
            @Override
            protected List<TransactionInfo> doInBackground(Void... voids) {
                return transactionInfoDB.transactionInfoDAO().getAll();
            }

            @Override
            protected void onPostExecute(List<TransactionInfo> transactionInfos) {
                transactionInfoAdapter.setTransactionInfoList(transactionInfos);
                transactionInfoAdapter.notifyDataSetChanged();
            }
        }.execute();

    }

    @OnClick(R.id.btn_transaction)
    public void onBtnTransactionTouched(){

       Intent intent = new Intent(this, TransactionActivity.class);
       startActivity(intent);

    }

    @Override
    public void onItemTouched(TransactionInfo transactionInfo) {

        Intent intent = new Intent(this, TransactionActivity.class);
        intent.putExtra("transactionInfo", transactionInfo);
        startActivity(intent);

    }
}
