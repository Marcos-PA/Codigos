import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * MIT License
 *
 * Copyright(c) 2023 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class EstoqueTeste {
    Lista_Produtos estoque = new Lista_Produtos(15);

    @Before
    public void prepare() {
    }
    
    @Test 
    public void testarInsercao() throws Exception {
        Produto P1 = new Produto("Notebook", 1000, 45, 5);
        Produto P2 = new Produto("Mouse", 120, 45, 5);
        Produto P3 = new Produto("Teclado", 140, 45, 5);
        Produto P4 = new Produto("Roteador", 300, 45, 5);
        Produto P5 = new Produto("Fone de Ouvido", 35, 45, 5);
        estoque.Guardar(P1);
        estoque.Guardar(P2);
        estoque.Guardar(P3);
        estoque.Guardar(P4);
        estoque.Guardar(P5);
        assertEquals(5, estoque.tamanho);
    }

    @Test
    public void testarExibicap() throws Exception{
        Produto P1 = new Produto("Notebook", 1000, 45, 5);
        Produto P2 = new Produto("Mouse", 120, 45, 5);
        Produto P3 = new Produto("Teclado", 140, 45, 5);
        Produto P4 = new Produto("Roteador", 300, 45, 5);
        Produto P5 = new Produto("Fone de Ouvido", 35, 45, 5);
        estoque.Guardar(P1);
        estoque.Guardar(P2);
        estoque.Guardar(P3);
        estoque.Guardar(P4);
        estoque.Guardar(P5);
        estoque.imprimir();
    }

    @Test
    public void testarCompras() throws Exception{

    }

    @Test
    public void testarVendas() throws Exception{

    }

}
