CREATE DATABASE gerenc_ferramentas;

USE gerenc_ferramentas;

CREATE TABLE ferramentas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(100),
    descricao VARCHAR(255),
    quantidade INT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    fornecedor VARCHAR(100),
    garantia_meses INT,
    ativo BOOLEAN DEFAULT TRUE
);

INSERT INTO ferramentas (codigo, nome, categoria, descricao, quantidade, preco, fornecedor, garantia_meses)
VALUES
('F001', 'Martelo de Unha', 'Ferramenta Manual', 'Martelo com cabo de madeira', 50, 25.90, 'Ferramentas LTDA', 12),
('F002', 'Parafusadeira Elétrica', 'Elétrica', 'Parafusadeira 12V com bateria', 20, 199.99, 'ConstruPrime', 24),
('F003', 'Chave de Fenda', 'Ferramenta Manual', 'Chave de fenda estrela 6mm', 100, 9.50, 'MetalTools', 6),
('F004', 'Serra Circular', 'Elétrica', 'Serra circular 1200W', 10, 349.00, 'ConstruPrime', 12),
('F005', 'Trena 5m', 'Medida', 'Trena de aço com trava', 75, 15.00, 'MedTools', 6);

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(20) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO usuarios (nome, email, senha) VALUES
('Patrick Mendes', 'patrick.bisolato@email.com', 'admin123');
