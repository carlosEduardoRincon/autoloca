CREATE TABLE veiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    placa VARCHAR(255),
    cor VARCHAR(255),
    ano INT,
    valor_diaria DECIMAL(10,2)
);

INSERT INTO veiculos (marca, modelo, placa, cor, ano, valor_diaria) VALUES ('Chevete', 'GTI', 'ABC-1234', 'Amarelo','2002', 50.00);

CREATE TABLE pessoas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255)
);

INSERT INTO pessoas (nome, cpf, email, telefone) VALUES ('Carlos', '1231231231', 'carlos@email', '112832983928');