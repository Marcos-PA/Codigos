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

class ListaLinear {

    private Serie lista[];
    public int primeiro;
    public int ultimo;
    public int tamanho;

    public ListaLinear(int tamanho) {

        lista = new Serie[tamanho];
        tamanho = 0;
        primeiro = 0;
        ultimo = 0;
    }

    public boolean listaVazia() {
        return (primeiro == ultimo);
    }

    public boolean listaCheia() {
        return (ultimo == lista.length);
    }

    public void inserir(Serie novo, int posicao) throws Exception {

        if (!listaCheia()) {
            if ((posicao >= 0) && (posicao <= tamanho)) {
                for (int i = ultimo; i > posicao; i--)
                    lista[i] = lista[i - 1];

                lista[posicao] = novo;

                ultimo++;
                tamanho++;
            } else
                throw new Exception("Não foi possível inserir o item na lista: posição informada é inválida!");
        } else
            throw new Exception("Não foi possível inserir o item na lista: a lista está cheia!");
    }

    public Serie remover(int posicao) throws Exception {

        Serie removido;

        if (!listaVazia()) {
            if ((posicao >= 0) && (posicao < tamanho)) {

                removido = lista[posicao];
                tamanho--;

                for (int i = posicao; i < tamanho; i++) {
                    lista[i] = lista[i + 1];
                }

                ultimo--;

                return removido;
            } else
                throw new Exception("Não foi possível remover o item da lista: posição informada é inválida!");
        } else
            throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");
    }

    public void imprimir() throws Exception {

        if (!listaVazia()) {

            for (int i = primeiro; i < ultimo; i++) {
                System.out.print("[" + i + "]");
                lista[i].print();
            }
        } else
            throw new Exception("Não foi possível imprimir o conteúdo da lista: a lista está vazia!");
    }

    public void imprimirDesempilhados() throws Exception {

        if (!listaVazia()) {
            for (int i = primeiro; i < ultimo; i++) {
                System.out.print("(R) ");
                String Saida = lista[i].getName();
                System.out.println(Saida);
            }
        } else
            throw new Exception("Não foi possível imprimir o conteúdo da lista: a lista está vazia!");
    }

    public Serie procurar(String nome) {
        if (!listaVazia()) {
            for (int i = 0; i < lista.length; i++) {
                int position = i;
                if (lista[position].getName().equals(nome)) {
                    return lista[position];
                }
            }
        }
        return null;

    }
}

class Main {
    public static void main(String[] args) throws Exception {

        MyIO.setCharset("UTF-8");
        ListaLinear auxiliar = new ListaLinear(62);
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
                int position = auxiliar.ultimo;
                auxiliar.inserir(serieInstance, position);
            } catch (Exception e) {
            }

            serieData = reader.read();

        }

        // Making a new array to storage the privious amont of Series
        ListaLinear Series = new ListaLinear(62);
        String l;

        while ((l = MyIO.readLine()) != null && !l.equals("FIM")) {
            Serie leitura = auxiliar.procurar(l);
            int position = Series.ultimo;
            Series.inserir(leitura, position);

        }
        /// Reading the number of queue operations I(inpilha), R(remove)
        int quantidade = MyIO.readInt();
        ListaLinear desenpilhadoSeries = new ListaLinear(62);
        for (int i = 0; i < quantidade; i++) {
            String command = MyIO.readLine();
            try {
                if (command.contains("II")) {
                    command = command.replace("II ", "");
                    Serie leitura = auxiliar.procurar(command);
                    Series.inserir(leitura, Series.primeiro);
                }

                if (command.contains("I*")) {
                    command = command.replace("I* ", "");
                    String possition = command.substring(0,command.indexOf(" "));
                    int aux = Integer.parseInt(possition);
                    command = command.replace(possition+" ", "");
                    Serie leitura = auxiliar.procurar(command);
                    Series.inserir(leitura, aux );

                }

                if (command.contains("IF")) {
                    command = command.replace("IF ", "");
                    Serie leitura = auxiliar.procurar(command);
                    Series.inserir(leitura, Series.ultimo);

                }
                if (command.equals("RI")) {
                    Serie desenpilhado = new Serie();
                    desenpilhado = Series.remover(Series.primeiro);
                    desenpilhadoSeries.inserir(desenpilhado, desenpilhadoSeries.ultimo);
                }
                if (command.contains("R*")) {
                    Serie desenpilhado = new Serie();
                    command = command.replace("R* ", "");
                    int aux = Integer.parseInt(command);
                    desenpilhado = Series.remover(aux);
                    desenpilhadoSeries.inserir(desenpilhado, desenpilhadoSeries.ultimo);

                }
                if (command.equals("RF")) {
                    Serie desenpilhado = new Serie();
                    desenpilhado = Series.remover(Series.ultimo - 1);
                    desenpilhadoSeries.inserir(desenpilhado, desenpilhadoSeries.ultimo);

                }
            } catch (Exception e) {
            }

        }
        desenpilhadoSeries.imprimirDesempilhados();
        Series.imprimir();

    }
}
