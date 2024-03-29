public class Lista_Produtos {
    private Produto lista[];
    public int primeiro;
    public int ultimo;
    public int tamanho;
    public int quantidade;
    public double valor_vendas;

    public Lista_Produtos(int tamanho) {
        lista = new Produto[tamanho];
        tamanho = 0;
        primeiro = 0;
        ultimo = 0;
    }

    // #region Verificação do estado da Lista
    /**
     * Vai fazer a veririficacao de se a lista se encontra vazia
     * 
     * @return Verdairo para sim, Falso para não
     */
    public boolean listaVazia() {
        return (primeiro == ultimo);
    }

    /**
     * Vai realizar a verificacao de se a lista se encontra cheia
     * 
     * @return Verdairo para sim, Falso para não
     */
    public boolean listaCheia() {
        return (ultimo == lista.length);
    }
    // #endregion

    /**
     * Faz a ação de inserir um novo produto na lista presente no estoque
     * 
     * @param novo    Nome do novo produto que sera adicionado no estoque
     * @throws Exception
     */
    public void Guardar(Produto novo) throws Exception {

        if (!listaCheia()) {
            if (ultimo <= tamanho) {
                lista[ultimo] = novo;
                ultimo++;
                tamanho++;
            }
        }
    }

    /**
     * Faz a pesquisa do produto deseijado na lista e retorna o objeto
     * @param nome Nome do produto
     * @return o objeto do produto ou então null caso não encontrado
     */
    public Produto procurar(String nome) {
        if (!listaVazia()) {

            for (int i = primeiro; i < ultimo; i++) {
                if (lista[i].description.equals(nome)) {
                    return lista[i];
                }
            }
        }
        return null;
    }

    public void imprimir() throws Exception {

        if (!listaVazia()) {

            for (int i = primeiro; i < ultimo; i++) {
                System.out.print("[" + i + "] ");
                lista[i].print();
            }
        } else
            throw new Exception("Não foi possível imprimir o conteúdo da lista: a lista está vazia!");
    }
}
