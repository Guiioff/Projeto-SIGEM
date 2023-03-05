package SIGEM;
import java.io.*;
import java.util.Scanner;

public class Estoquista {

    // Arquivo do estoque
    File estoque = new File("D:/IdeaProjects/Curso/src/SIGEM/produtos.txt");

    // Arquivo feito para auxiliar a atualização que será feita no estoque
    File auxiliar = new File("D:/IdeaProjects/Curso/src/SIGEM/auxiliar.txt");
    Scanner entrada = new Scanner(System.in);
    public Estoquista(){
        System.out.println("===== ESTOQUISTA =====");

        while (true){
            System.out.println("");
            // Opções disponíveis para o usuário
            System.out.println("[1] para adicionar produtos no estoque.");
            System.out.println("[2] para fechar o programa.");
            System.out.println("");
            System.out.print("Opção: ");

            // Escolha do usuário
            int escolha = entrada.nextInt();

            // Ações da escolha do usuário
            if (escolha == 1){
                adicionarProdutosEstoque();
            } else if (escolha == 2){
                break;
            } else {
                // Essa mensagem irá aparecer caso o usuário digite algo diferente de 1 e 2
                System.out.println("Opção Inválida.");
            }
        }
    }


    // Método criado com o intuito de verificar se o produto existe ou não no estoque
    // Esse método retorna um valor TRUE ou FALSE
    public boolean consultarProdutoEstoque(long codigoDeBarras) throws FileNotFoundException {
        String marca = null;
        String modelo = null;
        float preco = 0;
        int quantidade = 0;
        boolean flag = false;
        while(true){
            Scanner teclado = new Scanner(System.in);
            try{
                Scanner leitura = new Scanner(estoque);
                while(leitura.hasNextLine()){
                    try{
                        Long cbLido = Long.parseLong(leitura.nextLine());
                        marca = leitura.nextLine();
                        modelo = leitura.nextLine();
                        preco = Float.parseFloat(leitura.nextLine());
                        quantidade = Integer.parseInt(leitura.nextLine());

                        if(cbLido == codigoDeBarras){
                            return true;
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
                return false;
            }
            else{
                break;
            }
        }
        return flag;
    }

    // Método utilizado para adicionar produtos no estoque
    public void adicionarProdutosEstoque(){

        try {
            // Inicialmente o sistema lê o código de barras do produto que o estoquista quer adicionar
            System.out.print("Digite o Código de Barras do produto: ");
            long codigoLido = entrada.nextLong();

            // Depois, ele lê a quantidade de itens que vão ser adicionadas
            System.out.print("Quantas Unidades do produto você deseja adicionar ao estoque? ");
            int quantidadeNova = entrada.nextInt();

            // O Estoquista não tem o direito de adicionar novos produtos
            // Logo se, por meio do código de barras, for verificado que o produto não existe cadastrado no estoque, essa mensagem será exibida
            if (consultarProdutoEstoque(codigoLido) == false){
                System.out.println("Produto não cadastrado. Entre em Contato com o Gerente.");
            }
            // Caso o produto exista no estoque, o programa continuará
            else {
                // Por meio do Scanner é verificado as informações dos produtos no estoque
                Scanner leitura = new Scanner(estoque);
                while(leitura.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura.nextLine());
                    String marca = leitura.nextLine();
                    String modelo = leitura.nextLine();
                    float preco = Float.parseFloat(leitura.nextLine());
                    int quantidade = Integer.parseInt(leitura.nextLine());

                    // Caso o código digitado, for igual ao lido de algum produto durante a verificação do estoque
                    // ele será adicionado ao arquivo auxiliar com as novas quantidades desejadas pelo estoquista
                    if(codigoDeBarra != codigoLido){
                        FileWriter escritor1 = new FileWriter(auxiliar,true);
                        escritor1.write(codigoDeBarra + "\n");
                        escritor1.write(marca + "\n");
                        escritor1.write(modelo + "\n");
                        escritor1.write(preco + "\n");
                        escritor1.write(quantidade + "\n");
                        escritor1.close();

                    // Caso contrário, o restante dos produtos não sofrerão alterações e serão copiados TAMBÉM para o arquivo auxiliar
                    } else if (codigoDeBarra == codigoLido){
                        FileWriter escritor2 = new FileWriter(auxiliar,true);
                        escritor2.write(codigoDeBarra + "\n");
                        escritor2.write(marca + "\n");
                        escritor2.write(modelo + "\n");
                        escritor2.write(preco + "\n");
                        escritor2.write(quantidade + quantidadeNova + "\n");
                        escritor2.close();
                    }
                }
                // Após todos os produtos do estoque já estarem no arquivo auxiliar (com suas quantidades corretas)
                // O arquivo do estoque será limpo e ficará vazio
                PrintWriter limpador = new PrintWriter(estoque);
                limpador.flush();
                limpador.close();

                // A partir de agora o Scanner vai ler os produtos que estão no arquivo auxiliar
                Scanner leitura2 = new Scanner(auxiliar);
                while(leitura2.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura2.nextLine());
                    String marca = leitura2.nextLine();
                    String modelo = leitura2.nextLine();
                    float preco = Float.parseFloat(leitura2.nextLine());
                    int quantidade = Integer.parseInt(leitura2.nextLine());

                // E escrever novamente no estoque, só que agora com todas as informações corretas
                    FileWriter escritor3 = new FileWriter(estoque,true);
                    escritor3.write(codigoDeBarra + "\n");
                    escritor3.write(marca + "\n");
                    escritor3.write(modelo + "\n");
                    escritor3.write(preco + "\n");
                    escritor3.write(quantidade + "\n");
                    escritor3.close();
                }
                // Logo após, a transferência para o estoque, o arquivo auxiliar é limpo e fica totalmente vazio
                PrintWriter limpador2 = new PrintWriter(auxiliar);
                limpador2.flush();
                limpador2.close();
            }

        } catch (Exception exception){
            // Em caso de algum erro durante todo o caminho, essa mensagem será exibida
            System.out.println("Erro ao adicionar produto no estoque.");
        }
    }
}
