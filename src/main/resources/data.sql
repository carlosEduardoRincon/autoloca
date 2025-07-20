CREATE TABLE veiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    placa VARCHAR(255),
    cor VARCHAR(255),
    ano INT,
    valor_diaria DECIMAL(10,2)
);

CREATE TABLE pessoas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255)
);

CREATE TABLE alugueis (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT NOT NULL ,
    veiculo_id BIGINT NOT NULL,
    data_inicio DATE,
    data_fim DATE,
    valor_total DECIMAL(10,2),
    FOREIGN KEY (pessoa_id) REFERENCES pessoas(id),
    FOREIGN KEY (veiculo_id) REFERENCES veiculos(id)
);


INSERT INTO veiculos (marca, modelo, placa, cor, ano, valor_diaria) VALUES ('Chevete', 'GTI', 'ABC-1234', 'Amarelo','2002', 50.00);

INSERT INTO pessoas (nome, cpf, email, telefone) VALUES ('Carlos', '1231231231', 'carlos@email', '112832983928');

INSERT INTO alugueis (pessoa_id, veiculo_id, data_inicio, data_fim, valor_total) VALUES (1,1,'2025-10-01', '2025-10-10', 500.00)