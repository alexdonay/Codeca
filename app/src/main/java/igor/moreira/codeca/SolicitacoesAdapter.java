package igor.moreira.codeca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SolicitacoesAdapter extends ArrayAdapter<ModeloSolicitacao> {
    private Context context;
    private List<ModeloSolicitacao> solicitacoes;

    public SolicitacoesAdapter(Context context, List<ModeloSolicitacao> solicitacoes) {
        super(context, 0, solicitacoes);
        this.context = context;
        this.solicitacoes = solicitacoes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_solicitacao, parent, false);
        }

        ModeloSolicitacao solicitacao = solicitacoes.get(position);

        TextView textViewData = itemView.findViewById(R.id.textViewData);
        TextView textViewDescricao = itemView.findViewById(R.id.textViewDescricao);
        TextView textViewStatus = itemView.findViewById(R.id.textViewStatus);

        textViewData.setText(solicitacao.getData());
        textViewDescricao.setText(solicitacao.getDescricao());
        textViewStatus.setText(solicitacao.getStatus());

        return itemView;
    }
}
