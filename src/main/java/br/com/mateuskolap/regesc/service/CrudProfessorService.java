package br.com.mateuskolap.regesc.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.mateuskolap.regesc.orm.Professor;
import br.com.mateuskolap.regesc.repository.ProfessorRepository;

@Service
public class CrudProfessorService {
    private ProfessorRepository professorRepository;  // Dependência para o repositório

    // O Spring cria automaticamente um objeto desse tipo e disponibiliza para quem precisar (Injeção de dependência)
    public CrudProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("Qual ação deseja executar?");
            System.out.println("0 - Voltar");
            System.out.println("1 - Cadastrar novo professor");
            System.out.println("2 - Atualizar um professor");
            System.out.println("3 - Visualizar todos os professores");
            System.out.println("4 - Deletar um professor");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consome a quebra de linha

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.listar();
                    break;
                case 4:
                    this.deletar(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }

        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o prontuario do professor: ");
        String prontuario = scanner.next();

        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor cadastrado com sucesso!\n");
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o ID do professor a ser atualizado: ");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consome a quebra de linha

        // Se encontrado o professor com o id passado pelo usuário
        if (!this.professorRepository.existsById(id)) {
            System.out.println("ID informado (" + id + ") é inválido!\n");
            return;
        }

        Professor professor = this.professorRepository.findById(id).get();
        
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.nextLine();
        
        System.out.print("Digite o prontuario do professor: ");
        String prontuario = scanner.nextLine();
    
        professor.setNome(nome);
        professor.setProntuario(prontuario);

        // Atualiza o professor no banco
        professorRepository.save(professor);

        System.out.println("Professor atualizado com sucesso.\n");
    }

    private void listar() {
        Iterable<Professor> professores = this.professorRepository.findAll();

        professores.forEach(System.out::println);
        System.out.println();
    }

    private void deletar(Scanner scanner) {
        System.out.print("Digite o ID do professor a ser deletado: ");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consome a quebra de linha

        if (this.professorRepository.existsById(id)) {
            this.professorRepository.deleteById(id);
            System.out.println("Professor deletado!\n");
        } else {
            System.out.println("ID informado (" + id + ") é inválido!\n");
        }
    }
}
