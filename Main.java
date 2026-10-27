import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    // Configurações de conexão com o MySQL local (WampServer)
    private static final String URL = "jdbc:mysql://localhost:3306/agenda?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        System.out.println("=== Agenda Simples com Banco de Dados ===");

        while (opcao != 3) {
            System.out.println("\n1 - Cadastrar Contato");
            System.out.println("2 - Listar Contatos");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = sc.nextInt();
                sc.nextLine(); // limpar buffer do teclado

                if (opcao == 1) {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();

                    inserirContato(nome, telefone);

                } else if (opcao == 2) {
                    listarContatos();
                } else if (opcao == 3) {
                    System.out.println("A encerrar o programa...");
                } else {
                    System.out.println("Opção inválida! Escolha 1, 2 ou 3.");
                }

            } catch (Exception e) {
                System.out.println("Erro na entrada de dados. Tente novamente.");
                sc.nextLine(); // limpar entrada incorreta
            }
        }
        sc.close();
    }

    // Método para inserir contatos no banco de dados
    public static void inserirContato(String nome, String telefone) {
        String sql = "INSERT INTO contatos (nome, telefone) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.executeUpdate();
            System.out.println(">> Contato salvo com sucesso no banco!");

        } catch (SQLException e) {
            System.out.println(">> Erro ao salvar: " + e.getMessage());
        }
    }

    // Método para listar os contatos guardados no banco
    public static void listarContatos() {
        String sql = "SELECT * FROM contatos";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
            
            System.out.println("\n--- Lista de Contatos no Banco ---");
            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String tel = rs.getString("telefone");
                System.out.println("ID: " + id + " | Nome: " + nome + " | Tel: " + tel);
            }
            if (!encontrou) {
                System.out.println("Nenhum contato encontrado.");
            }

        } catch (SQLException e) {
            System.out.println(">> Erro ao listar: " + e.getMessage());
        }
    }
}
