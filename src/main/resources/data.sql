CREATE TABLE veiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    placa VARCHAR(255),
    cor VARCHAR(255),
    ano INT,
    valor_diaria DECIMAL(10,2)
);

INSERT INTO veiculos (marca, modelo, placa, cor, ano, valor_diaria) VALUES ('Chevete', 'GTI', 'ABC-1234', 'Amarelo','2002', 50.00)