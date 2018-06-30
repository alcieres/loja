package loja;

/**
 *
 * @author Alcieres
 */
public interface Produto {

public int getId();

public double getPreco();

public boolean setPreco(double preco);

public String getDescricao();

public void setDescricao(String descricao);

public int getEstoque();
        
public boolean adicionaEstoque(int quantidade);

public boolean removeEstoque(int quantidade);

@Override
String toString();
    
}
