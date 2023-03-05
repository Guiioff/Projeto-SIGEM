package SIGEM;

//Pacotes que são necessários
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
//Aqui começa o código da classe Gerente
public class Gerente extends Servicos{
    //O arquivo que armazena os dados sobre os produtos é atribuído a uma String chamada "arquivoP"
    File arquivoP = new File("D:/IdeaProjects/Curso/src/SIGEM/produtos.txt");

    File aux = new File("D:/IdeaProjects/Curso/src/SIGEM/auxiliar.txt");
    File funcionarios = new File("D:/IdeaProjects/Curso/src/SIGEM/funcionarios.txt");

    //Objeto Scanner que irá ler as informações digitadas pelo teclado
    Scanner teclado = new Scanner(System.in);
    //O construtor abaixo é executado assim que o método de login indica que o funcionário logado é um gerente
    public Gerente() {
        //Mensagem de boas vindas
        System.out.println("Olá! O que deseja fazer?");
        //As opções ficarão em loop até que o usuário escolha a opção de fechar o programa
        while(true){
            System.out.println(" ");
            //Abaixo, as opções que o usuário pode escolher
            System.out.println("1 para cadastrar novo produto");
            System.out.println("2 para apagar produto do cadastro");
            System.out.println("3 para cadastrar novo funcionário");
            System.out.println("4 para apagar funcionário do cadastro");
            System.out.println("5 para consultar produto no estoque");
            System.out.println("6 para fechar o programa");
            //É feita a leitura da escolha do usuário
            String escolha = teclado.nextLine();
            //A escolha é avaliada e a ação escolhida é executada
            if(escolha.equals("1")){
                cadastrarProduto();
            }else if(escolha.equals("2")){
                apagarProduto();
            }else if(escolha.equals("3")){
                cadastrarFuncionario();
            }else if (escolha.equals("4")){
                apagarFuncionario();
            }else if (escolha.equals("5")){
                Servicos s = new Servicos();
                s.consultarProduto();
            }else if(escolha.equals("6")){
                break;
            }
            else{
                //Mensagem para caso o usuário digite uma opção inexistente
                System.out.println("Opção inválida");
            }
        }
    }
    //Esse é o código do método que cadastra um novo produto no arquivo de texto
    public void cadastrarProduto(){
        System.out.println(" ");
        //É solicitado que o usuário informe a matrícula, marca, modelo e preço do novo produto
        System.out.print("Digite o código de barras do novo produto: ");
        String matricula = teclado.nextLine();
        System.out.print("Digite a marca do novo produto: ");
        String marca = teclado.nextLine();
        System.out.print("Digite o modelo do novo produto: ");
        String modelo = teclado.nextLine();
        System.out.print("Digite o preço do novo produto: ");
        String preco = teclado.nextLine();
        //O comando para escrever no arquivo precisa estar dentro de uma estrutura try/catch
        try{
            //É criado um objeto FileWriter para escrever no arquivo de texto
            FileWriter escrita = new FileWriter(arquivoP, true);
            //As informações coletadas do usuário são adicionadas ao arquivo
            escrita.write(matricula + "\n");
            escrita.write(marca + "\n");
            escrita.write(modelo + "\n");
            escrita.write(preco + "\n");
            /* A linha a seguir refere-se a quantidade do novo produto. Inicialmente, essa
            quantidade é sempre 0 */
            escrita.write("0\n");
            //Ao fim da adição, o arquivo é fechado e uma mensagem de sucesso é exibida
            escrita.close();
            System.out.println("Produto cadastrado com sucesso");
        }
        catch(Exception exception){
            //Caso ocorra algum erro, a mensagem abaixo é exibida
            System.out.println("Erro ao cadastrar o novo produto");
        }
    }

    public void apagarProduto(){
        try{
            System.out.println(" ");
            System.out.print("Digite o código de barras do produto a ser deletado: ");
            Scanner teclado = new Scanner(System.in);
            String cb = teclado.nextLine();
            Scanner leitura = new Scanner(arquivoP);
            FileWriter escrita = new FileWriter(aux, true);
            int contador = 0;
            while(leitura.hasNextLine()){
                String linhaLida = leitura.nextLine();
                if(!(linhaLida.equals(cb))){
                    escrita.write(linhaLida + "\n");
                    contador++;
                }
                else{
                    break;
                }
            }
            contador += 5;
            Scanner leitura2 = new Scanner(arquivoP);
            while(leitura2.hasNextLine()){
                String linhaLida = leitura2.nextLine();
                if(contador > 0){
                    contador--;
                    continue;
                }
                escrita.write(linhaLida + "\n");
            }
            escrita.close();
            FileWriter escrita2 = new FileWriter(arquivoP, false);
            escrita2.write("");
            escrita2.close();
            Scanner leitura3 = new Scanner(aux);
            FileWriter escrita3 = new FileWriter(arquivoP, true);
            while(leitura3.hasNextLine()){
                String linhaLida = leitura3.nextLine();
                escrita3.write(linhaLida + "\n");
            }
            escrita3.close();
            FileWriter escrita4 = new FileWriter(aux, false);
            escrita4.write("");
            escrita4.close();
            System.out.println("Produto deletado com sucesso");
        }
        catch(Exception exception){
            System.out.println("Erro");
        }
    }

    public void cadastrarFuncionario(){
        System.out.println(" ");
        System.out.print("Digite a matrícula do novo funcionário: ");
        String matricula = teclado.nextLine();
        System.out.print("Digite a senha do novo funcionário: ");
        String senha = teclado.nextLine();
        System.out.print("Digite o cargo do novo funcionário: ");
        String cargo = teclado.nextLine();
        try{
            FileWriter escrita = new FileWriter(funcionarios, true);
            escrita.write(matricula + "\n");
            escrita.write(senha + "\n");
            escrita.write(cargo + "\n");
            escrita.close();
            System.out.println("Funcionario cadastrado com sucesso");
        }
        catch(Exception exception){
            System.out.println("Erro ao cadastrar o novo funcionário");
        }
    }

    public void apagarFuncionario(){
        try{
            System.out.println(" ");
            System.out.print("Digite a matricula do funcionário: ");
            Scanner teclado = new Scanner(System.in);
            String mat = teclado.nextLine();
            Scanner leitura = new Scanner(funcionarios);
            FileWriter escrita = new FileWriter(aux, true);
            int contador = 0;
            while(leitura.hasNextLine()){
                String linhaLida = leitura.nextLine();
                if(!(linhaLida.equals(mat))){
                    escrita.write(linhaLida + "\n");
                    contador++;
                }
                else{
                    break;
                }
            }
            contador += 3;
            Scanner leitura2 = new Scanner(funcionarios);
            while(leitura2.hasNextLine()){
                String linhaLida = leitura2.nextLine();
                if(contador > 0){
                    contador--;
                    continue;
                }
                escrita.write(linhaLida + "\n");
            }
            escrita.close();
            FileWriter escrita2 = new FileWriter(funcionarios, false);
            escrita2.write("");
            escrita2.close();
            Scanner leitura3 = new Scanner(aux);
            FileWriter escrita3 = new FileWriter(funcionarios, true);
            while(leitura3.hasNextLine()){
                String linhaLida = leitura3.nextLine();
                escrita3.write(linhaLida + "\n");
            }
            escrita3.close();
            FileWriter escrita4 = new FileWriter(aux, false);
            escrita4.write("");
            escrita4.close();
            System.out.println("Cadastro deletado com sucesso");
        }
        catch(Exception exception){
            System.out.println("Erro");
        }
    }
}

