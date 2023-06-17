package igor.moreira.codeca;

import android.view.Display;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import java.util.ArrayList;
import java.util.List;

public class BdSingleton {
    private static BdSingleton instance;
    private ModeloUsuario usuarios;
    private List<ModeloSolicitacao> solicitacoes;

    private BdSingleton() {
        usuarios = new ModeloUsuario();
        solicitacoes = new ArrayList<>();
    }

    public static BdSingleton getInstance() {
        if (instance == null) {
            instance = new BdSingleton();
        }
        return instance;
    }

    public ModeloUsuario getUsuarios() {
        return usuarios;
    }

    public List<ModeloSolicitacao> getSolicitacoes() {
        return solicitacoes;
    }
    public void addUsuario(ModeloUsuario usuario){
       this.usuarios = usuario;
    }
    public void addSolicitacao(ModeloSolicitacao solicitacao){
        solicitacoes.add(solicitacao);
    }
    public int nextSolucitacaoIndex() {
            return solicitacoes.size();
    }
}
