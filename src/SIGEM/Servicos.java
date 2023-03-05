package SIGEM;

import java.util.Scanner;
import java.io.File;

public class Servicos {
    //Atributo que armazenará a senha correta do funcionário que está fazendo login
    private String senhaEsperada;

    //Simples contador
    private int contadorAuxiliar;

    //Atributo que armazenará o cargo do funcionário que realizou o login
    private String cargo;

    //Método Get para acessar o cargo do funcionário
    public String getCargo(){
        return this.cargo;
    }

    /* Os objetos abaixo, do tipo File, armazenarão, respectivamente, o arquivo com informações sobre
    os funcionário e o arquivo com informações sobre os produtos */
    File arquivo = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM/funcionarios.txt");
    File arquivo2 = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM/produtos.txt");

    //O método abaixo é invocado assim que o programa iniciar
    public void realizarLogin(){
        //O programa irá, repetidamente, solicitar a matrícula do funcionário até que uma matrícula válida seja inserida
        while(true){
            //Será invocado o método que recebe a matrícula inserida e essa matrícula será armazenada na variável abaixo
            int matriculaRecebida = receberMatricula();
            //É invocado o método que verifica se a matrícula está cadastrada na base
            //A variável "status" indica se a matrícula inserida pelo usuário está, ou não, cadastrada na base
            boolean status = verificarMatricula(matriculaRecebida);
            /* Caso a matrícula esteja cadastrada, o loop atual é encerrado. Caso não, é mostrada uma mensagem
            e o loop executa novamente, mais uma vez solicitando a matrícula do usuário */
            if(status){
                break;
            }
            else{
                System.out.println("A matrícula informada não está cadastrada");
            }
        }
        /* Uma vez confirmada a matrícula, o programa irá, repetidamente, solicitar a senha do funcionário
        até que a senha correta seja digitada */
        while(true){
            //É invocado o método para receber a senha
            String senhaRecebida = receberSenha();
            /* É feita a comparação entre a senha recebida e a senha esperada (já armazenada). Se forem
            iguais, é invocado o método que identifica o cargo do funcionário logado e após isso o loop é encerrado.
            Caso sejam diferentes, é exibida uma mensagem de erro e o loop executa novamente */
            if(senhaRecebida.equals(senhaEsperada)){
                System.out.println("Acesso concedido");
                System.out.println(" ");
                //O método invocado abaixo armazena o cargo do funcionário na variável "cargo"
                cargo = identificarCargo();
                break;
            }
            else{
                System.out.println("Senha Incorreta");
            }
        }
    }


    //O método abaixo é invocado para receber a matrícula do funcionário
    private int receberMatricula(){
        //É exibida uma mensagem inicial
        System.out.print("Por favor, digite sua matrícula: ");
        //O objeto teclado, do tipo Scanner, irá ler a matrícula inserida
        Scanner teclado = new Scanner(System.in);
        //A matrícula digitada pelo usuário será retornada
        return teclado.nextInt();
    }


    //O método abaixo verifica se a matrícula inserida pelo usuário está cadastrada
    /* Esse método retorna o valor true se a matrícula estiver cadastrada e retorna o
    valor false caso não esteja */
    //Esse método recebe como parâmetro a matrícula que foi inserida pelo usuário
    private boolean verificarMatricula(int mat){
        //Essa flag sinaliza se a matrícula está ou não na base de dados
        boolean flag = false;
        //O comando de leitura do arquivo precisa estar dentro de uma estrutura try/catch
        try
        {
            //O objeto "leitura", do tipo Scanner, irá armazenar o conteúdo de cada linha do arquivo de texto
            Scanner leitura = new Scanner(arquivo);
            //O loop abaixo irá ser executado até que todas as linhas do arquvivo tenham sido lidas
            while(leitura.hasNextLine()){
                //Simplesmente um contador
                contadorAuxiliar++;
                /* No arquivo de texto, a senha cadastrada fica na linha abaixo da matrícula. A condicional
                abaixo avalia se a matrícula já foi confirmada como válida. Caso sim, a senha correta é armazenada
                para posterior verificação */
                if(flag){
                    //O comando abaixo armazena o conteúdo da linha na variável, logo depois, o loop é encerrado
                    senhaEsperada = leitura.nextLine();
                    break;
                }
                /* No arquivo de texto, apenas as linhas que contém matrículas podem ter seu conteúdo convertido
                para inteiro. O comando abaixo permite ao programa ler e comparar apenas as linhas que contém matrículas */
                try
                {
                    //A matrícula lida é convertida para inteiro
                    int matriculaLida = Integer.parseInt(leitura.nextLine());
                    /* É feita a comparação da matrícula lida com a matrícula inserida pelo usuário. Caso sejam diferents,
                    a próxima iteração do loop será realizada. Caso sejam iguais, a flag recebe o valor true e o loop segue
                    para a próxima iteração. É nessa próxima iteração que a senha correta do funcionário é armazenada e o
                    loop é encerrado */
                    if(matriculaLida == mat){
                        flag = true;
                        continue;
                    }
                }
                //Caso a linha não contenha uma matrícula, parte-se para a próxima iteração
                catch(Exception exception)
                {
                    continue;
                }
            }
        }
        //Caso ocorra algum erro na leitura do arquivo, uma mensagem é exibida
        catch(Exception exception)
        {
            System.out.println("Ocorreu um erro ao acessar a base de dados");
        }
        //Por fim, o método retorna a flag
        return flag;
    }

    //O método abaixo irá receber a senha digitada pelo usuário
    private String receberSenha(){
        //É exibida uma mensagem
        System.out.print("Por favor, digite sua senha: ");
        //É criado o objeto do tipo Scanner para guardar os caracteres digitados pelo usuário
        Scanner teclado = new Scanner(System.in);
        //Por fim, o método retorna a senha inserida
        return teclado.nextLine();
    }

    //O método abaixo irá identificar o cargo do funcionário logado
    private String identificarCargo(){
        //Contador de escopo local com mesmo valor do contador de escopo global
        int contadorInterno = contadorAuxiliar;
        //Inicialização da String que armazenará o cargo
        String cargo = null;
        try
        {
            /* O contador interno armazena o número da linha em que se encontra a senha do
            funcionário logado. Dessa forma, na linha posterior (ou seja, contador + 1), se
            encontra o cargo desse funcionário. O comando abaixo permite realizar a leitura da
            linha contador + 1 e armazenar seu conteúdo na variável "cargo" */
            Scanner leitura = new Scanner(arquivo);
            while(contadorInterno >= 0){
                cargo = leitura.nextLine();
                contadorInterno--;
            }
        }
        catch(Exception exception)
        {
            System.out.println("Erro");
        }
        //Por fim, a variável que armazena o cargo do funcionário é retornada
        return cargo;
    }

    //O método abaixo pode ser invocado por qualquer um dos três tipos de funcionários
    public void consultarProduto(){
        //Incialização das 4 variáveis que armazenarão as informações do produto consultado
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
                Scanner leitura = new Scanner(arquivo2);
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
}
