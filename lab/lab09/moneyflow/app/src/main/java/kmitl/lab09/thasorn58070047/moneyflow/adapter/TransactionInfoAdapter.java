package kmitl.lab09.thasorn58070047.moneyflow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab09.thasorn58070047.moneyflow.R;
import kmitl.lab09.thasorn58070047.moneyflow.adapter.holder.TransactionInfoHolder;
import kmitl.lab09.thasorn58070047.moneyflow.model.TransactionInfo;

public class TransactionInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<TransactionInfo> transactionInfoList;
    private TransactionInfoAdapterListener listener;

    public TransactionInfoAdapter(Context mContext, TransactionInfoAdapterListener listener) {

        this.mContext = mContext;
        this.listener = listener;
        transactionInfoList = new ArrayList<>();

    }

    public interface TransactionInfoAdapterListener {

        void onItemTouched(TransactionInfo transactionInfo);

    }

    public void setTransactionInfoList(List<TransactionInfo> transactionInfoList) {

        this.transactionInfoList = transactionInfoList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_transaction, parent, false);
        TransactionInfoHolder transactionInfoHolder = new TransactionInfoHolder(view);

        return transactionInfoHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        configureTransactionInfoHolder((TransactionInfoHolder) holder, position);

    }

    @Override
    public int getItemCount() {
        return transactionInfoList.size();
    }

    private void configureTransactionInfoHolder(TransactionInfoHolder transactionInfoHolder, final int position){

        transactionInfoHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemTouched(transactionInfoList.get(position));
            }
        });

        transactionInfoHolder.text_transaction_detail.setText(transactionInfoList.get(position).getDescribe());
        transactionInfoHolder.text_amount_money.setText(String.valueOf(transactionInfoList.get(position).getAmount()));
        transactionInfoHolder.image_transaction_symbol.setImageDrawable(transactionInfoList.get(position).getType().equals("income") ?
                mContext.getResources().getDrawable(R.drawable.ic_plus) : mContext.getResources().getDrawable(R.drawable.ic_minus));

    }
}
