package SIGEM;

import java.io.*;
import java.util.Scanner;


public class Repositor extends  Servicos {

    // Arquivo referente ao mostruário
    File mostruario = new File("D:/IdeaProjects/Curso/src/SIGEM/mostruario.txt");

    // Arquivo referente ao estoque
    File estoque = new File("D:/IdeaProjects/Curso/src/SIGEM/produtos.txt");

    // Arquivos que vao auxiliar no funciomanete da classe repositor
    File auxiliar = new File("D:/IdeaProjects/Curso/src/SIGEM/auxiliar.txt");
    File auxiliar2 = new File("D:/IdeaProjects/Curso/src/SIGEM/auxiliar2.txt");

    Scanner entrada = new Scanner(System.in);

    public Repositor(){
        System.out.println("===== REPOSITOR =====");

        while(true){
            // Opções disponíveis para o usuário
            System.out.println("");
            System.out.println("[1] para adicionar produtos no mostruário.");
            System.out.println("[2] para consultar produto no estoque.");
            System.out.println("[3] para consultar produto no mostruário.");
            System.out.println("[4] para fechar o programa.");
            System.out.println("");
            System.out.print("Opção: ");

            int escolha = entrada.nextInt();

            // O que acontece em cada escolha feita pelo usuário
            if (escolha == 1){
                adicionarProdutosMostruario();
            } else if (escolha == 2){
                consultarProdutoEstoque();
            } else if (escolha == 3) {
                consultarProdutoMostruario();
            } else if(escolha == 4){
                break;
            } else {
                System.out.println("Opção Inválida.");
            }
        }
    }

    // Método criado com o intuito de consultar algum produto no mostruário
    public void consultarProdutoMostruario(){
        String marca = null;
        String modelo = null;
        int qtd;
        float preco = 0;
        int quantidade = 0;
        //Flag que sinaliza a existência ou não do produro no cadastro
        boolean flag = false;
        /* O loop irá, repetidamente, solicitar o código de barras de um produto até que um
        código válido seja inserido */
        while(true){
            //Na variável cb será armazenao o código de barras digitado pelo usuário
            Scanner teclado = new Scanner(System.in);
            System.out.print("Por favor, digite o código de barras do produto: ");
            long cb = teclado.nextLong();
            try{
                Scanner leitura = new Scanner(mostruario);
                while(leitura.hasNextLine()){
                    try{
                        Long cbLido = Long.parseLong(leitura.nextLine());
                        marca = leitura.nextLine();
                        modelo = leitura.nextLine();
                        preco = Float.parseFloat(leitura.nextLine());
                        qtd = Integer.parseInt(leitura.nextLine());
                        if(cbLido == cb){
                            System.out.println("Código de barras: " + cb);
                            System.out.println("Marca: " + marca);
                            System.out.println("Modelo: " + modelo);
                            System.out.println("Preço: R$ " + preco);
                            System.out.println("Quantidade: " + qtd);
                            flag = true;
                            break;
                        }
                    }
                    catch(Exception exception){
                        continue;
                    }
                }
            }
            catch(Exception exception){
                System.out.println("Erro ao acessar a base de dados");
            }
            if(!flag){
                System.out.println("O produto não está cadastrado");
            }
            else{
                break;
            }
        }
    }

    // Método criado com o intuito de consultar algum produto no estoque
    public void consultarProdutoEstoque(){
        String marca = null;
        String modelo = null;
        int qtd;
        float preco = 0;
        int quantidade = 0;
        //Flag que sinaliza a existência ou não do produro no cadastro
        boolean flag = false;
        /* O loop irá, repetidamente, solicitar o código de barras de um produto até que um
        código válido seja inserido */
        while(true){
            //Na variável cb será armazenao o código de barras digitado pelo usuário
            Scanner teclado = new Scanner(System.in);
            System.out.print("Por favor, digite o código de barras do produto: ");
            long cb = teclado.nextLong();
            try{
                Scanner leitura = new Scanner(estoque);
                while(leitura.hasNextLine()){
                    try{
                        Long cbLido = Long.parseLong(leitura.nextLine());
                        marca = leitura.nextLine();
                        modelo = leitura.nextLine();
                        preco = Float.parseFloat(leitura.nextLine());
                        qtd = Integer.parseInt(leitura.nextLine());
                        if(cbLido == cb){
                            System.out.println("Código de barras: " + cb);
                            System.out.println("Marca: " + marca);
                            System.out.println("Modelo: " + modelo);
                            System.out.println("Preço: R$ " + preco);
                            System.out.println("Quantidade: " + qtd);
                            flag = true;
                            break;
                        }
                    }
                    catch(Exception exception){
                        continue;
                    }
                }
            }
            catch(Exception exception){
                System.out.println("Erro ao acessar a base de dados");
            }
            if(!flag){
                System.out.println("O produto não está cadastrado");
            }
            else{
                break;
            }
        }
    }

    // Método criado com o intuito de informar quantas unidades do produto há no estoque
    private void consultarProdutoEstoque(long codigoDeBarras) {
        String marca = null;
        String modelo = null;
        float preco = 0;
        int quantidade = 0;
        boolean flag = false;
        while (true) {
            try {
                Scanner leitura = new Scanner(estoque);
                while (leitura.hasNextLine()) {
                    try {
                        Long cbLido = Long.parseLong(leitura.nextLine());
                        marca = leitura.nextLine();
                        modelo = leitura.nextLine();
                        preco = Float.parseFloat(leitura.nextLine());
                        quantidade = Integer.parseInt(leitura.nextLine());
                        if (cbLido == codigoDeBarras) {
                            System.out.println("Há " + quantidade + " unidades do " + modelo + " da marca " + marca + " no estoque.");

                            if (quantidade == 0) {
                                System.out.println("Não há unidades do " + modelo + " da marca " + marca + " no estoque.");
                            }
                            flag = true;
                            break;
                        }
                    } catch (Exception exception) {
                        continue;
                    }
                }
            } catch (Exception exception) {
                System.out.println("Erro ao acessar a base de dados");
            }
            if (!flag) {
                break;
            } else {
                break;
            }
        }

    }

    // Método criado com o intuito de informar quantas unidades do produto há no mostruário
    private void consultarProdutoMostruario(long codigoDeBarras) {
        String marca = null;
        String modelo = null;
        float preco = 0;
        int quantidade = 0;
        boolean flag = false;
        while (true) {
            try {
                Scanner leitura = new Scanner(mostruario);
                while (leitura.hasNextLine()) {
                    try {
                        Long cbLido = Long.parseLong(leitura.nextLine());
                        marca = leitura.nextLine();
                        modelo = leitura.nextLine();
                        preco = Float.parseFloat(leitura.nextLine());
                        quantidade = Integer.parseInt(leitura.nextLine());
                        if (cbLido == codigoDeBarras) {
                            System.out.println("Há " + quantidade + " unidades do " + modelo + " da marca " + marca + " no mostruário.");

                            if (quantidade == 0) {
                                System.out.println("Não há unidades do " + modelo + " da marca " + marca + " no mostruário.");
                            }
                            flag = true;
                            break;
                        }
                    } catch (Exception exception) {
                        continue;
                    }
                }
            } catch (Exception exception) {
                System.out.println("Erro ao acessar a base de dados");
            }
            if (!flag) {
                System.out.println("O produto não está no mostruário.");
                break;
            } else {
                break;
            }
        }

    }

    // Método criado com o intuito de verificado se o produto está ou não no mostruário
    // retorna um valor booleano
    private boolean consultarProdutoMostruarioBoolean(long codigoDeBarras) throws FileNotFoundException {
        String marca = null;
        String modelo = null;
        float preco = 0;
        int quantidade = 0;
        boolean flag = false;
        while (true) {
            Scanner teclado = new Scanner(System.in);
            try {
                // Ele começa a ler o que está no mostruário
                Scanner leitura = new Scanner(mostruario);
                while (leitura.hasNextLine()) {
                    try {
                        Long cbLido = Long.parseLong(leitura.nextLine());
                        marca = leitura.nextLine();
                        modelo = leitura.nextLine();
                        preco = Float.parseFloat(leitura.nextLine());
                        quantidade = Integer.parseInt(leitura.nextLine());

                        // Caso o código de barras encontre um com o mesmo valor, é retornado true
                        if (cbLido == codigoDeBarras) {
                            return true;
                        }
                        else {
                        }
                    } catch (Exception exception) {
                        continue;
                    }
                }
            } catch (Exception exception) {
                System.out.println("Erro ao acessar a base de dados");
            }
            // Caso não encontre nada, retorna false
            if (!flag) {
                return false;
            } else {
                break;
            }
        }
        return flag;
    }

    // Método criado com o intuito de verificar se a quantidade X de produtos que o usuário quer retirar do estoque é viável
    private boolean verificarQuantEstoque(long codigoDeBarrasLido, int quant) throws FileNotFoundException {
        Scanner verificador = new Scanner(estoque);
        while (verificador.hasNextLine()){
            Long codigoDeBarra = Long.parseLong(verificador.nextLine());
            String marca = verificador.nextLine();
            String modelo = verificador.nextLine();
            float preco = Float.parseFloat(verificador.nextLine());
            int quantidade = Integer.parseInt(verificador.nextLine());

            if (codigoDeBarrasLido == codigoDeBarra){

                // Caso a quantidade que o usuário quer retirar do estoque for menor ou igual a quantidade que existe no estoque retorna TRUE
                if (quantidade - quant >= 0){
                    return true;

                // Caso contrário, retorna FALSE
                } else if (quantidade - quant < 0) {
                    return false;
                }
            }
        }

        return false;
    }

    // Método de adicionar produtos ao mostruário
    public void adicionarProdutosMostruario() {

        try {
            // O usuário digita o código de barras do produto
            System.out.print("Informe o código de barras do produto: ");
            long codigoLido = entrada.nextLong();

            // Logo após esses dois métodos são acionados, mostrando a disponibilidade do mesmo tanto no mostruário quanto no estoque
            consultarProdutoMostruario(codigoLido);
            consultarProdutoEstoque(codigoLido);

            // O usuário digita quantas unidades ele quer adicionar no mostruário
            System.out.print("Informe a quantidade unidades que você vai tirar do estoque e colocar no mostruário: ");
            int quantidadeNova = entrada.nextInt();

            // Caso o produto já esteja no mostruário e no estoque
            if(consultarProdutoMostruarioBoolean(codigoLido) == true){

                //Primeiro ocorre uma leitura no mostuário
                Scanner leitura = new Scanner(mostruario);
                while (leitura.hasNextLine()) {
                    Long codigoDeBarra = Long.parseLong(leitura.nextLine());
                    String marca = leitura.nextLine();
                    String modelo = leitura.nextLine();
                    float preco = Float.parseFloat(leitura.nextLine());
                    int quantidade = Integer.parseInt(leitura.nextLine());

                    if (codigoLido == codigoDeBarra){
                        // Os PRODUTOS vão sendo tranferidos para o auxiliar
                        FileWriter escritor1 = new FileWriter(auxiliar,true);
                        escritor1.write(codigoDeBarra + "\n");
                        escritor1.write(marca + "\n");
                        escritor1.write(modelo + "\n");
                        escritor1.write(preco + "\n");
                        // Caso seja viável tranferir do estoque para o mostruário, essa ação é executada
                        if (verificarQuantEstoque(codigoLido,quantidadeNova) == true) {
                            escritor1.write(quantidade + quantidadeNova + "\n");
                            escritor1.close();
                        // Caso não seja viável ocorrer essa transferência a quantidade não vai ser alterada
                        } else if (verificarQuantEstoque(codigoLido,quantidadeNova) == false){
                            escritor1.write(quantidade + "\n");
                            escritor1.close();
                        }
                    } else if (codigoLido != codigoDeBarra) {
                        // Os PRODUTOS vão sendo tranferidos para o auxiliar
                        FileWriter escritor2 = new FileWriter(auxiliar,true);
                        escritor2.write(codigoDeBarra + "\n");
                        escritor2.write(marca + "\n");
                        escritor2.write(modelo + "\n");
                        escritor2.write(preco + "\n");
                        escritor2.write(quantidade + "\n");
                        escritor2.close();
                    }
                }

                // Em seguida ocorre uma leitura no estoque
                Scanner leitura2 = new Scanner(estoque);
                while(leitura2.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura2.nextLine());
                    String marca = leitura2.nextLine();
                    String modelo = leitura2.nextLine();
                    float preco = Float.parseFloat(leitura2.nextLine());
                    int quantidade = Integer.parseInt(leitura2.nextLine());

                    if(codigoDeBarra != codigoLido){
                        // Os PRODUTOS vão sendo tranferidos para o auxiliar2
                        FileWriter escritor1 = new FileWriter(auxiliar2,true);
                        escritor1.write(codigoDeBarra + "\n");
                        escritor1.write(marca + "\n");
                        escritor1.write(modelo + "\n");
                        escritor1.write(preco + "\n");
                        escritor1.write(quantidade + "\n");
                        escritor1.close();

                    }
                    else if (codigoLido == codigoDeBarra) {
                        // Os PRODUTOS vão sendo tranferidos para o auxiliar2
                        FileWriter escritor2 = new FileWriter(auxiliar2,true);
                        escritor2.write(codigoDeBarra + "\n");
                        escritor2.write(marca + "\n");
                        escritor2.write(modelo + "\n");
                        escritor2.write(preco + "\n");
                        // Caso seja viável ocorrer a transferência a quantidade do produto no estoque é atualizada
                        if (verificarQuantEstoque(codigoLido,quantidadeNova) == true) {
                            escritor2.write(quantidade - quantidadeNova + "\n");
                            escritor2.close();
                        // Caso a verificação seja igual a FALSE, essa mensagem vai aparecer e a quantidade não será alterada
                        } else if (verificarQuantEstoque(codigoLido,quantidadeNova) == false){
                            System.out.println("Não há essa quantidade de produtos desse modelo no estoque. Tente Novamente.");
                            escritor2.write(quantidade + "\n");
                            escritor2.close();
                        }
                    }



                }

                // Após todo as informações atuais estarem nos arquivos auxiliares
                // As informações do estoque são apagadas
                PrintWriter limpador = new PrintWriter(estoque);
                limpador.flush();
                limpador.close();

                // As informações do mostruário são apagadas
                PrintWriter limpador2 = new PrintWriter(mostruario);
                limpador.flush();
                limpador.close();

                // A partir daqui às informações no arquivo auxiliar, referente ao MOSTRUÁRIO, vão ser tranferidas para o arquivo mostruário
                Scanner leitura3 = new Scanner(auxiliar);
                while (leitura3.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura3.nextLine());
                    String marca = leitura3.nextLine();
                    String modelo = leitura3.nextLine();
                    float preco = Float.parseFloat(leitura3.nextLine());
                    int quantidade = Integer.parseInt(leitura3.nextLine());

                    FileWriter escritor3 = new FileWriter(mostruario,true);
                    escritor3.write(codigoDeBarra + "\n");
                    escritor3.write(marca + "\n");
                    escritor3.write(modelo + "\n");
                    escritor3.write(preco + "\n");
                    escritor3.write(quantidade + "\n");
                    escritor3.close();
                }

                // A partir daqui as informações no arquivo auxiliar2, referente ao ESTOQUE, vão ser tranferidas para o arquivo estoque
                Scanner leitura4 = new Scanner(auxiliar2);
                while (leitura4.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura4.nextLine());
                    String marca = leitura4.nextLine();
                    String modelo = leitura4.nextLine();
                    float preco = Float.parseFloat(leitura4.nextLine());
                    int quantidade = Integer.parseInt(leitura4.nextLine());

                    FileWriter escritor4 = new FileWriter(estoque,true);
                    escritor4.write(codigoDeBarra + "\n");
                    escritor4.write(marca + "\n");
                    escritor4.write(modelo + "\n");
                    escritor4.write(preco + "\n");
                    escritor4.write(quantidade + "\n");
                    escritor4.close();
                }

                // Após toda a tranferência ocorrer de forma correta, os as informações presentes nos arquivos auxiliares são apagadas
                PrintWriter limpador3 = new PrintWriter(auxiliar);
                limpador.flush();
                limpador.close();

                PrintWriter limpador4 = new PrintWriter(auxiliar2);
                limpador.flush();
                limpador.close();

            }

            // Caso o produto não esteja no mostruário, somente no estoque
            else if (consultarProdutoMostruarioBoolean(codigoLido) == false) {
                // Ocorre uma leitura no estoque
                Scanner leitura1 = new Scanner(estoque);
                while (leitura1.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura1.nextLine());
                    String marca = leitura1.nextLine();
                    String modelo = leitura1.nextLine();
                    float preco = Float.parseFloat(leitura1.nextLine());
                    int quantidade = Integer.parseInt(leitura1.nextLine());

                    if (codigoLido != codigoDeBarra){
                        // Os produtos começam a ser escrito no arquivo auxiliar
                        FileWriter escritor1 = new FileWriter(auxiliar,true);
                        escritor1.write(codigoDeBarra + "\n");
                        escritor1.write(marca + "\n");
                        escritor1.write(modelo + "\n");
                        escritor1.write(preco + "\n");
                        escritor1.write(quantidade + "\n");
                        escritor1.close();
                    }
                    else if (codigoLido == codigoDeBarra){
                        // E as informações do produto escolhido são atualizadas e transferidas para o mostruário,já que ele não existia antes
                        FileWriter escritor2 = new FileWriter(mostruario,true);
                        escritor2.write(codigoDeBarra + "\n");
                        escritor2.write(marca + "\n");
                        escritor2.write(modelo + "\n");
                        escritor2.write(preco + "\n");
                        // Caso seja viável a transferência, a quantidade é alterada
                        if (verificarQuantEstoque(codigoLido,quantidadeNova) == true) {
                            escritor2.write(quantidadeNova + "\n");
                            escritor2.close();

                        // Caso não seja viável a transferência, a quantidade permanece a mesma
                        } else if (verificarQuantEstoque(codigoLido,quantidadeNova) == false){
                            System.out.println("Não há essa quantidade de produtos desse modelo no estoque. Tente Novamente.");
                            escritor2.write(quantidade + "\n");
                            escritor2.close();
                        }

                        // As informações atuais, referente ao produto no estoque, são transferidos para o arquivo auxiliar
                        FileWriter escritor3 = new FileWriter(auxiliar,true);
                        escritor3.write(codigoDeBarra + "\n");
                        escritor3.write(marca + "\n");
                        escritor3.write(modelo + "\n");
                        escritor3.write(preco + "\n");
                        escritor3.write(quantidade - quantidadeNova + "\n");
                        escritor3.close();
                    }
                }
                // O arquivo estoque é limpo
                PrintWriter limpador = new PrintWriter(estoque);
                limpador.flush();
                limpador.close();

                //A partir as informações atuais do estoque são transferidas do arquivo auxiliar para o arquivo estoque
                Scanner leitura2 = new Scanner(auxiliar);
                while (leitura2.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura2.nextLine());
                    String marca = leitura2.nextLine();
                    String modelo = leitura2.nextLine();
                    float preco = Float.parseFloat(leitura2.nextLine());
                    int quantidade = Integer.parseInt(leitura2.nextLine());

                    FileWriter escritor4 = new FileWriter(estoque,true);
                    escritor4.write(codigoDeBarra + "\n");
                    escritor4.write(marca + "\n");
                    escritor4.write(modelo + "\n");
                    escritor4.write(preco + "\n");
                    escritor4.write(quantidade + "\n");
                    escritor4.close();
                }
                // Por fim o arquivo auxiliar é limpo
                PrintWriter limpador2 = new PrintWriter(auxiliar);
                limpador2.flush();
                limpador2.close();

            }

        } catch (Exception exception) {
            // Em caso de algum erro durante todo o caminho, essa mensagem será exibida
            System.out.println("Erro ao adicionar produto no mostruário.");

        }
    }
}

