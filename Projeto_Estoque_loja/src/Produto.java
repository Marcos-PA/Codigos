import org.omg.CORBA.StringHolder;

public class Produto {
    public String description;
    public int quantidade;
    public double custo, preco_revenda;
    private double valor_imposto, lucro;

    /**
     * Cria um objeto produto com atributos passados por parametro
     * 
     * @param description   : Descriação do produto contendo no minimo 3 letras
     * @param custo         : Valor do custo de produção do produto
     * @param preco_revenda : Valor ao qual o produto sera vendido
     */
    public Produto(String description, double custo, int lucro, int quantidade) {
        if (lucro >= 30 && lucro <= 80) {
            this.description = description;
            this.custo = custo;
            this.preco_revenda = valor_Revenda(custo, lucro);
            this.quantidade = quantidade;
        } else {
            System.out.println("Margem de lucro invalida");
        }

    }

    // #region Valor De Revenda
    /**
     * Um produto tem um preço de custo e um preço de venda. O preço de venda é
     * assim calculado:
     * preço de custo + margem de lucro + valor dos impostos.
     * 
     * @apiNote Valor do imposto sera sempre de 18% sobre a soma do custo e da
     *          margem de lucro.
     * @apiNote Valor da Margem de Lucro sempre estara entre 30% e 80% do valor do
     *          custo do produto.
     * @param custo
     * @param lucro
     * @return Retorna em double o valor de revenda calculado para o produto
     */
    public double valor_Revenda(double custo, double lucro) {
        double valor_revenda = 0;
        if (lucro >= 30 && lucro <= 80) {
            valor_revenda = custo + (custo * (lucro / 100)) + (custo * (18 / 100));
        } else {
            System.out.println("Margem de lucro invalida");
        }
        return valor_revenda;
    }
    // #endregion
    // #region Mostrar Dados

    public void print() {
        MyIO.println("Nome: " + this.description + String.format("\n custo: R$%.2f", this.custo)
                + String.format("\n Valor de Venda: R$%.2f", this.preco_revenda));
    }
    // #endregion

}
