import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;

class Serie {
    // ATRIBUTOS PRIVADOS
    private String name;
    private String format;
    private String duration;
    private String fromCountry;
    private String originalLanguage;
    private String originalBroadcaster;
    private String inicialTransmissionDate;
    private int seasonQuantity;
    private int episodeQuantity;

    // CONSTRUTORES

    public Serie() {
    }

    public Serie(String name, String format, String duration, String fromCountry, String originalLanguage,
            String originalBroadcaster, String initialTransmissionDate, int seasonQuantity,
            int episodeQuantity) {
        this.name = name;
        this.format = format;
        this.duration = duration;
        this.fromCountry = fromCountry;
        this.originalLanguage = originalLanguage;
        this.originalBroadcaster = originalBroadcaster;
        this.inicialTransmissionDate = initialTransmissionDate;
        this.seasonQuantity = seasonQuantity;
        this.episodeQuantity = episodeQuantity;
    }

    // Metodos Publicos

    public Serie clone() {
        return new Serie(
                name,
                format,
                duration,
                fromCountry,
                originalLanguage,
                originalBroadcaster,
                inicialTransmissionDate,
                seasonQuantity,
                episodeQuantity);
    }

    public void read(String stringSerie) {

        // How I Met Your Mother;Sitcom;23 min. aprox.;Estados Unidos;Inglês; CBS;19 de
        // setembro de 2005–31 de março de 2014;9;208

        if (stringSerie != null) {
            String[] serieData = stringSerie.split(";");
            setName(serieData[0]);
            setFormat(serieData[1]);
            setDuration(serieData[2]);
            setFromCountry(serieData[3]);
            setOriginalLanguage(serieData[4]);
            setOriginalBroadcaster(serieData[5]);
            setInitialTransmissionDate(serieData[6]);
            setSeasonQuantity(Integer.parseInt(serieData[7]));
            setEpisodeQuantity(Integer.parseInt(serieData[8]));
        }

    }

    public void print() {
        MyIO.println(this.getName() + " ## " + this.getFormat() + " ## " + this.getDuration() + " ## "
                + this.getFromCountry()
                + " ## " + this.getOriginalLanguage() + " ## " + this.getOriginalBroadcaster() + " ## "
                + this.getInitialTransmissionDate() + " ## "
                + this.getSeasonQuantity() + " ## " + this.getEpisodeQuantity());
    }

    // GETTERS e SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalBroadcaster() {
        return originalBroadcaster;
    }

    public void setOriginalBroadcaster(String originalBroadcaster) {
        this.originalBroadcaster = originalBroadcaster;
    }

    public String getInitialTransmissionDate() {
        return inicialTransmissionDate;
    }

    public void setInitialTransmissionDate(String InitialTransmissionDate) {
        this.inicialTransmissionDate = InitialTransmissionDate;
    }

    public int getSeasonQuantity() {
        return seasonQuantity;
    }

    public void setSeasonQuantity(int seasonQuantity) {
        this.seasonQuantity = seasonQuantity;
    }

    public int getEpisodeQuantity() {
        return episodeQuantity;
    }

    public void setEpisodeQuantity(int episodeQuantity) {
        this.episodeQuantity = episodeQuantity;
    }
}

class Reader {
    private BufferedReader input;

    public Reader(String fileName) {
        try {
            input = new BufferedReader(new FileReader(fileName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void closeFile() {

        try {
            input.close();
        } catch (Exception e) {
            System.out.println("Error while closing : " + e);
        }
    }

    @SuppressWarnings("finally")
    public String read() {

        String inputText = null;

        try {
            inputText = input.readLine();
        } catch (EOFException e) {
            inputText = null;
        } catch (IOException e) {
            System.out.println("Reading error: " + e);
            inputText = null;
        } finally {
            return inputText;
        }
    }
}

class Celula {

    public Serie item;
    public Celula proximo;
    public Celula anterior;

    public Celula(Serie novo) {
        item = novo;
        proximo = null;
    }

    public Celula() {

        item = new Serie();
        proximo = null;
    }

    public Serie getItem() {
        return item;
    }

    public void setItem(Serie item) {
        this.item = item;
    }

    public Celula getProximo() {
        return proximo;
    }

    public void setProximo(Celula proximo) {
        this.proximo = proximo;
    }
    public Celula getAnterior() {
        return anterior;
    }
    public void setAnterior(Celula anterior) {
        this.anterior = anterior;
    }
}

class ListaDuplamenteEncadeada {

    public Celula primeiro;
    public Celula ultimo;
    public int tamanho;

    public ListaDuplamenteEncadeada() {
        Celula sentinela;
        sentinela = new Celula();
        primeiro = sentinela;
        ultimo = sentinela;
    }

    public boolean listaVazia() {

        boolean resp;

        if (primeiro == ultimo)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public void inserir(Serie novo, int posicao) throws Exception {

        Celula anterior, novaCelula, proximaCelula;

        if ((posicao >= 0) && (posicao <= tamanho)) {
            anterior = primeiro;
            for (int i = 0; i < posicao; i++)
                anterior = anterior.getProximo();

            novaCelula = new Celula(novo);

            proximaCelula = anterior.getProximo();

            anterior.setProximo(novaCelula);
            novaCelula.setProximo(proximaCelula);

            if (posicao == tamanho) // a inserção ocorreu na última posição da lista
                ultimo = novaCelula;

            tamanho++;

        } else
            throw new Exception("Não foi possível inserir o item na lista: a posição informada é inválida!");
    }

    public void inserirInicio(Serie novo) throws Exception {

        int posicao = 0;
        Celula anterior, novaCelula, proximaCelula;

        if ((posicao >= 0) && (posicao <= tamanho)) {
            anterior = primeiro;
            for (int i = 0; i < posicao; i++)
                anterior = anterior.getProximo();

            novaCelula = new Celula(novo);

            proximaCelula = anterior.getProximo();

            anterior.setProximo(novaCelula);
            novaCelula.setProximo(proximaCelula);

            if (posicao == tamanho) // a inserção ocorreu na última posição da lista
                ultimo = novaCelula;

            tamanho++;

        } else
            throw new Exception("Não foi possível inserir o item na lista: a posição informada é inválida!");
    }

    public void inserirFinal(Serie cc) {
        Celula aux = new Celula();

        // inserção de uma nova célula ao final da lista encadeada.
        ultimo.proximo = aux;
        aux.anterior = ultimo;
        // inserção da conta-corrente, passada como parâmetro para esse método, no final
        // da lista encadeada.
        aux.item = cc;

        // atualização do ponteiro de controle "ultimo" para a última célula da lista
        // encadeada.
        ultimo = ultimo.proximo;

        tamanho++;
    }

    public Serie retirarInicio() throws Exception {
        int posicao = 0;
        Celula anterior, celulaRemovida, proximaCelula;

        if (!listaVazia()) {
            if ((posicao >= 0) && (posicao < tamanho)) {
                anterior = primeiro;
                for (int i = 0; i < posicao; i++)
                    anterior = anterior.getProximo();

                celulaRemovida = anterior.getProximo();

                proximaCelula = celulaRemovida.getProximo();

                anterior.setProximo(proximaCelula);
                celulaRemovida.setProximo(null);

                if (celulaRemovida == ultimo)
                    ultimo = anterior;

                tamanho--;

                return (celulaRemovida.getItem());
            } else
                throw new Exception("Não foi possível remover o item da lista: a posição informada é inválida!");
        } else
            throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");

    }

    public Serie remover(int posicao) throws Exception {

        Celula anterior, celulaRemovida, proximaCelula;

        if (!listaVazia()) {
            if ((posicao >= 0) && (posicao < tamanho)) {
                anterior = primeiro;
                for (int i = 0; i < posicao; i++)
                    anterior = anterior.getProximo();

                celulaRemovida = anterior.getProximo();

                proximaCelula = celulaRemovida.getProximo();

                anterior.setProximo(proximaCelula);
                celulaRemovida.setProximo(null);

                if (celulaRemovida == ultimo)
                    ultimo = anterior;

                tamanho--;

                return (celulaRemovida.getItem());
            } else
                throw new Exception("Não foi possível remover o item da lista: a posição informada é inválida!");
        } else
            throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");
    }

    public Serie removerFinal() throws Exception {

        Celula removida, penultima;
		
		if (! listaVazia()) {
			
			removida = ultimo;
			
			penultima = ultimo.getAnterior();
			penultima.setProximo(null);
			removida.setAnterior(null);
			
			ultimo = penultima;
			
			tamanho--;
			
			return (removida.getItem());
		} else
			throw new Exception("Não foi possível remover o último item da lista: a lista está vazia!");
    }

    public void imprimir() throws Exception {

        Celula aux;

        if (!listaVazia()) {
            aux = primeiro.proximo;
            int i = 0;
            while (aux != null) {
                System.out.print("["+i+"]");
                aux.item.print();
                aux = aux.proximo;
                i = i + 1;
            }
        } else
            throw new Exception("Não foi possível imprimir o conteúdo da lista: a lista está vazia!");
    }

    public void mostrarDesempilados() throws Exception {
        Celula aux;
        if (!listaVazia()) {
            aux = primeiro.proximo;
            while (aux != null) {
                System.out.print("(R) ");
                String Saida = aux.item.getName();
                System.out.println(Saida);
                aux = aux.proximo;
            }
        } else {
            throw new Exception("Não deu pra imprimir");
        }
    }

    public Serie procurar(String nome) {
        Celula aux;
        if (!listaVazia()) {
            aux = primeiro.proximo;
            while (aux != null) {
                if (aux.item.getName().equals(nome)) {
                    return aux.item;
                }
                aux = aux.proximo;
            }

        }

        return null;
    }
}

public class MainEncadeada {
    public static void main(String[] args) throws Exception {

        MyIO.setCharset("UTF-8");
        ListaDuplamenteEncadeada auxiliar = new ListaDuplamenteEncadeada();
        Reader reader = new Reader("//tmp/data.txt");
        String serieData;

        // Discards the first pilha
        reader.read();
        // Reads the second pilha
        serieData = reader.read();

        // This pilha bellow was excrutiating

        while (serieData != null && !serieData.equals("FIM")) {
            Serie serieInstance = new Serie();
            serieInstance.read(serieData);

            try {
                auxiliar.inserirFinal(serieInstance);
            } catch (Exception e) {
            }

            serieData = reader.read();

        }
        // Making a new array to storage the privious amont of Series
        ListaDuplamenteEncadeada Series = new ListaDuplamenteEncadeada();
        String l;

        while ((l = MyIO.readLine()) != null && !l.equals("FIM")) {
            Serie leitura = auxiliar.procurar(l);
            Series.inserirFinal(leitura);

        }
        /// Reading the number of queue operations I(inpilha), R(remove)
        int quantidade = MyIO.readInt();
        ListaDuplamenteEncadeada desenpilhadoSeries = new ListaDuplamenteEncadeada();
        for (int i = 0; i < quantidade; i++) {
            String command = MyIO.readLine();
            try {
                if (command.contains("II")) {
                    command = command.replace("II ", "");
                    Serie leitura = auxiliar.procurar(command);
                    Series.inserirInicio(leitura);
                }

                if (command.contains("I*")) {
                    command = command.replace("I* ", "");
                    String possition = command.substring(0, command.indexOf(" "));
                    int aux = Integer.parseInt(possition);
                    command = command.replace(possition + " ", "");
                    Serie leitura = auxiliar.procurar(command);
                    Series.inserir(leitura, aux);

                }

                if (command.contains("IF")) {
                    command = command.replace("IF ", "");
                    Serie leitura = auxiliar.procurar(command);
                    Series.inserirFinal(leitura);

                }
                if (command.contains("RI")) {
                    Serie desenpilhado = new Serie();
                    desenpilhado = Series.retirarInicio();
                    desenpilhadoSeries.inserirFinal(desenpilhado);
                }
                if (command.contains("R*")) {
                    Serie desenpilhado = new Serie();
                    command = command.replace("R* ", "");
                    int aux = Integer.parseInt(command);
                    desenpilhado = Series.remover(aux);
                    desenpilhadoSeries.inserirFinal(desenpilhado);

                }
                if (command.contains("RF")) {
                    Serie desenpilhado = new Serie();
                    desenpilhado = Series.removerFinal();
                    desenpilhadoSeries.inserirFinal(desenpilhado);

                }
            } catch (Exception e) {
            }

        }
        desenpilhadoSeries.mostrarDesempilados();
        Series.imprimir();

    }
}
