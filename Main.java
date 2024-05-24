import java.util.Scanner;

class Musica {
    String nome;       
    String genero;        
    int popularidade;

    public Musica(String nome, String genero, int popularidade) {
        this.nome = nome;               
        this.genero = genero;           
        this.popularidade = popularidade; 
    }
}

// ===================================================================================================================================== //

class NoMusica {
    Musica musica;       
    NoMusica esquerda;   
    NoMusica direita;    

    public NoMusica(Musica musica) {
        this.musica = musica;       
        this.esquerda = null;
        this.direita = null;  
    }
}

// ===================================================================================================================================== //

class ArvoreMusica {
    NoMusica raiz;  

    // Construtor da árvore musical
    public ArvoreMusica() {
        this.raiz = null;  // Inicializa a raiz como nula (árvore vazia)
    }

    // Método para inserir uma nova música na árvore
    public void inserir(Musica musica) {
        raiz = inserir(raiz, musica);  
    }

    // Método recursivo para inserir a música na árvore
    private NoMusica inserir(NoMusica noAtual, Musica musica) {
        
        // Se o nó atual é nulo, cria um novo nó com a música
        if (noAtual == null) {
            return new NoMusica(musica);
        }

        // Compara a popularidade da música para decidir a posição na árvore
        if (musica.popularidade < noAtual.musica.popularidade) {
            noAtual.esquerda = inserir(noAtual.esquerda, musica);  // Insere na subárvore esquerda
        } else if (musica.popularidade > noAtual.musica.popularidade) {
            noAtual.direita = inserir(noAtual.direita, musica);  // Insere na subárvore direita
        }

        return noAtual;  // Retorna o nó atual
    }   

    // Método para gerar recomendações de músicas para o usuário
    public void recomendar(Musica musica) {
        System.out.println("Recomendações de músicas do gênero " + musica.genero + ":");
        recomendar(raiz, musica);  // Chama o método recursivo de recomendação
    }

    // Método recursivo para percorrer a árvore e gerar as recomendações
    private void recomendar(NoMusica noAtual, Musica musica) {
        // Se o nó atual não é nulo, percorre a árvore
        if (noAtual != null) {
            recomendar(noAtual.esquerda, musica);  // Percorre a subárvore esquerda

            // Se o gênero da música no nó atual é igual ao gênero desejado, imprime a recomendação
            if (noAtual.musica.genero.equalsIgnoreCase(musica.genero)) {
                System.out.println("Nome: " + noAtual.musica.nome + ", Popularidade: " + noAtual.musica.popularidade);
            }

            recomendar(noAtual.direita, musica);  // Percorre a subárvore direita
        }
    }
}

// ===================================================================================================================================== //

public class Main {
    public static void main(String[] args) {
        ArvoreMusica arvore = new ArvoreMusica();  // Cria uma nova árvore musical

        Scanner sc = new Scanner(System.in);
        int opcao;
        
        System.out.println("ÁRVORE MUSICAL");
        System.out.println("  ");

        do {
            System.out.println("=======================================");
            System.out.println("1 - INSERIR MÚSICA");
            System.out.println("2 - RECOMENDAR MÚSICAS");
            System.out.println("3 - SAIR DO PROGRAMA");
            System.out.println("=======================================");
    
            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:

                    String musicaNova;
                    String generoNovo;
                    int popularidadeNova;

                    System.out.print("1°) Insira o nome da nova música: ");
                    musicaNova = sc.next();

                    System.out.print("2°) Insira o gênero da música: ");
                    generoNovo = sc.next();

                    System.out.print("3°) Insira o ranking de popularidade da música: ");
                    popularidadeNova = sc.nextInt();

                    arvore.inserir(new Musica(musicaNova, generoNovo, popularidadeNova));

                    break;
            
                case 2:

                String generoProcura;

                System.out.println("Qual gênero você quer que recomendemos ?");
                generoProcura = sc.next();

                System.out.println("======= RECOMENDAMOS AS SEGUINTES MÚSICAS =======");

                arvore.recomendar(new Musica(null, generoProcura, 0));
                break;1
            }
        } while(opcao != 3);
    }
}