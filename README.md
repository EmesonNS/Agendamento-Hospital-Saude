# Agendamento-Hospital-Saúde

## 🧭 Roteiro Git

### ✅ **1. Fluxo de Branches**

```
main         ← código de produção
│
├── develop      ← código de integração (merge de funcionalidades)
│   ├── feat/
│   └── fix/
│   └── ...
```

* `main`: código estável e pronto para deploy.
* `develop`: branch base para merge de features.
* `feat/*`: novas funcionalidades.
* `fix/*`: correções de bugs.

---

### 🧪 **2. Processo de Trabalho**

#### 🔄 1. Clonar o projeto:

```bash
git clone https://github.com/EmesonNS/Agendamento-Hospital-Saude
```

#### 🌱 2. Criar uma nova branch:

```bash
git checkout -b feat/
```

#### ✍️ 3. Trabalhar no código

#### 💾 4. Adicionar e commitar:

```bash
git add .
git commit -m "feat: implementar cadastro de paciente"
```

#### 🔄 5. Atualizar com a `develop` (para evitar conflitos):

```bash
git checkout develop
git pull origin develop
git checkout feat/cadastro-paciente
git merge develop
```

#### 🚀 6. Enviar branch ao repositório:

```bash
git push origin feat/cadastro-paciente
```

#### 🔃 7. Abrir Pull Request para `develop`

* Descrever claramente o que a feature faz.
* Marcar os outros para revisar.

#### ✔️ 8. Após aprovação, mergeie na `develop`.

#### 🔁 9. Periodicamente: `develop → main` (deploy ou entrega)

---

### 🛑 **4. Regras importantes**

* Nunca codar diretamente em `main` ou `develop`.
* Cada funcionalidade = 1 branch.
* Sempre descrever bem os commits.
* Fazer `pull` antes de `push`.
* Revisar os pull request.

---

### 💬 **5. Exemplo de mensagens de commit**

| Tipo           | Prefixo     | Exemplo                                  |
| -------------- | ----------- | ---------------------------------------- |
| Funcionalidade | `feat:`     | `feat: criar endpoint de consulta`       |
| Correção       | `fix:`      | `fix: corrigir bug no login`             |
| Estilo         | `style:`    | `style: padronizar indentação`           |
| Refatoração    | `refactor:` | `refactor: isolar lógica de agendamento` |
| Teste          | `test:`     | `test: adicionar teste de integração`    |

---

## 📈 Esquemas do porojeto

### ** 📔 Esqueleto Basico**
![image](https://github.com/user-attachments/assets/17bdc1d9-c310-46e5-b903-e7e9351c7fab)

---

### ** 📑 Modelo ER**
![Imagem do WhatsApp de 2025-06-03 à(s) 13 43 12_1d0f3874](https://github.com/user-attachments/assets/2eca0f44-53c3-4c3e-b018-4076f4d6df7d)

---

### ** 📚 Fluxo de Views**

| View Paciente                                                                                                                             | View Medico                                                                                                                                       | View Admin                                                                                |
|------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
![Imagem do WhatsApp de 2025-06-03 à(s) 16 55 32_c949de28](https://github.com/user-attachments/assets/0810ac00-45a6-4f6e-ba65-89dce6fa7d08) | ![Imagem do WhatsApp de 2025-06-03 à(s) 17 20 41_deeaf78b](https://github.com/user-attachments/assets/30891816-7a9d-48bf-990a-06ef3928cdf5) | ![image](https://github.com/user-attachments/assets/fb1c5f8f-b127-4fce-aba8-51cdd34518cd) |

---



