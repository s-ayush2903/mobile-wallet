package org.mifos.mobilewallet.mifospay.history.ui.adapter;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.mifos.mobilewallet.core.domain.model.Transaction;
import org.mifos.mobilewallet.mifospay.R;
import org.mifos.mobilewallet.mifospay.utils.Constants;
import org.mifos.mobilewallet.mifospay.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by naman on 17/8/17.
 */

public class HistoryAdapter
        extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<Transaction> transactions;
    private Context context;

    @Inject
    public HistoryAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_casual_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);

        String balance = Utils.getFormattedAccountBalance(transaction.getAmount());
        String currency = transaction.getCurrency().getCode();
        holder.tvTransactionAmount.setText(String.format("%s %s", currency, balance));
        holder.tvTransactionDate.setText(transaction.getDate());

        if (isBalancePositive(balance) && context != null) {
            int color = ContextCompat.getColor(context, R.color.colorAccentBlue);
            holder.tvTransactionAmount.setTextColor(color);
        }

        switch (transaction.getTransactionType()) {
            case DEBIT:
                holder.tvTransactionStatus.setText(Constants.DEBIT);
                break;
            case CREDIT:
                holder.tvTransactionStatus.setText(Constants.CREDIT);
                break;
            case OTHER:
                holder.tvTransactionStatus.setText(Constants.OTHER);
                break;
        }
    }

    private boolean isBalancePositive(String balance) {
        balance = balance.replaceAll("[,.]", "");
        return Double.parseDouble(balance) > 0;
    }

    @Override
    public int getItemCount() {
        if (transactions != null) {
            return transactions.size();
        } else {
            return 0;
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<Transaction> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    public ArrayList<Transaction> getTransactions() {
        return (ArrayList<Transaction>) transactions;
    }

    public Transaction getTransaction(int position) {
        return transactions.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_casual_list_title)
        TextView tvTransactionStatus;

        @BindView(R.id.tv_item_casual_list_optional_caption)
        TextView tvTransactionAmount;

        @BindView(R.id.tv_item_casual_list_subtitle)
        TextView tvTransactionDate;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}

