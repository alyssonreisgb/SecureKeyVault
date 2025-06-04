```markdown
# 🔐 SecureKeyVault

**SecureKeyVault** é um gerenciador de senhas seguro baseado em console, desenvolvido em Java. Ele utiliza criptografia AES, autenticação em dois fatores (2FA) e banco de dados SQLite para garantir segurança e praticidade no gerenciamento de credenciais.

---

## 📦 Funcionalidades

- [x] Cadastro de senhas com criptografia AES-256
- [x] Listagem e exclusão de credenciais salvas
- [x] Autenticação com senha mestre + 2FA (Google Authenticator)
- [x] Geração de senhas fortes personalizadas
- [x] Verificação de senhas vazadas (via API Have I Been Pwned)
- [x] Armazenamento seguro em banco de dados SQLite

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
│   │   └── resources/
│
├── data/                      → Banco de dados `senhas.db`
├── pom.xml                   → Configurações Maven
└── README.md                 → Este arquivo

````

---

## ⚙️ Dependências

As bibliotecas são gerenciadas pelo **Maven**:

```xml
<dependencies>
    <!-- Autenticação TOTP -->
    <dependency>
        <groupId>com.warrenstrange</groupId>
        <artifactId>googleauth</artifactId>
        <version>1.4.0</version>
    </dependency>

    <!-- Cliente HTTP para API -->
    <dependency>
        <groupId>com.squareup.okhttp3</groupId>
        <artifactId>okhttp</artifactId>
        <version>4.10.0</version>
    </dependency>

    <!-- SQLite JDBC -->
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.43.2.0</version>
    </dependency>

    <!-- Criptografia -->
    <dependency>
        <groupId>org.bouncycastle</groupId>
        <artifactId>bcprov-jdk15on</artifactId>
        <version>1.70</version>
    </dependency>

    <!-- (Opcional) Logging SLF4J -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-simple</artifactId>
        <version>2.0.9</version>
    </dependency>
</dependencies>
````

---

## ▶️ Como Executar

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/SecureKeyVault.git
cd SecureKeyVault
```

2. **Compile com o Maven:**

```bash
mvn clean compile
```

3. **Execute o programa:**

```bash
mvn exec:java -Dexec.mainClass="app.App"
```

> Alternativamente, você pode rodar a classe `App.java` direto pelo IntelliJ.

---

## 🔑 Como Usar

* **Senha Mestre padrão:** `chaveSegura`
* **2FA:** Configure o aplicativo (Google Authenticator) com o segredo TOTP gerado no terminal.
* As senhas são armazenadas criptografadas no banco de dados local.

---

## 🔐 Segurança

* **AES-256:** Protege todas as senhas salvas.
* **TOTP 2FA:** Garante autenticação de dois fatores.
* **API Have I Been Pwned:** Verifica se a senha já foi exposta em vazamentos.

---

