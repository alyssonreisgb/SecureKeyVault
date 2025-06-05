# 🔐 SecureKeyVault

**SecureKeyVault** é um gerenciador de senhas seguro baseado em console, desenvolvido em Java. Utiliza criptografia AES-256, autenticação em dois fatores (2FA) com TOTP e armazenamento seguro em banco de dados SQLite.

---

## 📦 Funcionalidades

- ✅ Cadastro de senhas com criptografia AES-256
- ✅ Listagem e exclusão de credenciais
- ✅ Autenticação com senha mestre + 2FA (Google Authenticator)
- ✅ Geração de senhas fortes personalizadas
- ✅ Verificação de senhas vazadas via API Have I Been Pwned
- ✅ Armazenamento persistente com SQLite

---

## 📁 Estrutura do Projeto

```

SecureKeyVault/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── app/            → Classe principal (App.java)
│   │   │   ├── core/           → Lógica central (PasswordManager, Authenticator)
│   │   │   ├── crypto/         → Criptografia AES
│   │   │   ├── model/          → Classe Credential
│   │   │   ├── security/       → Verificador de senhas vazadas
│   │   │   ├── storage/        → Conexão e DAO com SQLite
│   │   │   └── utils/          → Gerador de senhas seguras
│   └── resources/
│
├── data/                      → Banco de dados `securekeyvault.db` (criado automaticamente)
├── pom.xml                   → Configurações Maven
└── README.md                 → Este arquivo

````

---

## ▶️ Como Executar

1. **Clone o repositório:**

```bash
git clone https://github.com/alyssonreisgb/SecureKeyVault
cd SecureKeyVault
````

2. **Execute o projeto:**

Você pode **executar diretamente a classe `App.java`** pela sua IDE (como IntelliJ ou VS Code com suporte Maven).

> **Observação importante:** Ao iniciar o app, a pasta `data/` será criada automaticamente (caso não exista), junto com o banco de dados local `securekeyvault.db`.

---

## 🔐 Acesso Inicial

* **Senha Mestre padrão:** `chaveSegura`
* **2FA:** Ao primeiro uso, será exibida uma chave TOTP e um link QR Code.

  * Escaneie com o app Google Authenticator ou insira o código manualmente.
  * O código gerado muda a cada execução do app (chave TOTP temporária).
* Após autenticar, utilize o menu interativo para gerenciar suas credenciais.

---

## ⚙️ Dependências (via Maven)

As dependências são automaticamente gerenciadas via Maven:

* `googleauth` — TOTP 2FA
* `okhttp` — Requisições HTTP (API HaveIBeenPwned)
* `sqlite-jdbc` — Driver JDBC do SQLite
* `bcprov-jdk15on` — Criptografia com BouncyCastle
* `slf4j-simple` — Logging (opcional)

---

## 🔒 Segurança

* 🛡️ **AES-256** para criptografia das senhas armazenadas.
* 🔐 **Autenticação 2FA** com TOTP.
* 🌐 **Verificação de vazamentos** com API Have I Been Pwned.
* 🗃️ **Banco local seguro:** O banco é salvo localmente em `data/securekeyvault.db`.

---

## 📌 Observações

* Ao cada execução, uma **nova chave TOTP é gerada**, exigindo novo pareamento com o autenticador.
* O banco de dados é recriado se removido — mantenha backup se necessário.
* Em ambiente de produção, recomenda-se **armazenar a senha mestre e chave TOTP com segurança**.

## 📄 Licença

Distribuído sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
