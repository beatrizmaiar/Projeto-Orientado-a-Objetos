#include <stdio.h>

// Interface para definir o contrato de aluguel (Interface Segregation Principle)
typedef struct {
    int (*alugar)(int);
    int (*devolver)(int);
} AluguelInterface;

// Implementação da classe Aluguel
typedef struct {
    AluguelInterface interface;
    int bikes_disponiveis;
} Aluguel;

// Método para alugar uma bicicleta (Single Responsibility Principle)
int alugar_bicicleta(int quantidade_bikes) {
    if (quantidade_bikes > 0) {
        printf("%d bicicletas alugadas com sucesso!\n", quantidade_bikes);
        return 1;
    } else {
        printf("Desculpe, não há bicicletas disponíveis para aluguel.\n");
        return 0;
    }
}

// Método para devolver uma bicicleta (Single Responsibility Principle)
int devolver_bicicleta(int quantidade_bikes) {
    // Lógica para processar a devolução das bicicletas
    printf("%d bicicletas devolvidas com sucesso!\n", quantidade_bikes);
    return 1;
}

// Inicialização do objeto Aluguel
Aluguel criar_aluguel() {
    Aluguel aluguel;
    aluguel.interface.alugar = alugar_bicicleta;
    aluguel.interface.devolver = devolver_bicicleta;
    aluguel.bikes_disponiveis = 10;
    return aluguel;
}

int main() {
    // Criar um objeto de aluguel (Dependency Inversion Principle)
    Aluguel aluguel = criar_aluguel();
    aluguel.interface.alugar(3);
    aluguel.interface.devolver(3);

    return 0;
}