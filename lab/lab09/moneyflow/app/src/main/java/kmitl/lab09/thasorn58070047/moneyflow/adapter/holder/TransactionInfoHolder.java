package kmitl.lab09.thasorn58070047.moneyflow.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kmitl.lab09.thasorn58070047.moneyflow.R;

public class TransactionInfoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_transaction_symbol)
    public ImageView image_transaction_symbol;

    @BindView(R.id.text_transaction_detail)
    public TextView text_transaction_detail;

    @BindView(R.id.text_amount_money)
    public TextView text_amount_money;

    public TransactionInfoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
