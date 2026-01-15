# ⚔️ CiberDuelo — Batalha de Hackers

CiberDuelo é um jogo de batalha por turnos em **console**, onde dois hackers se enfrentam utilizando **cartas digitais** de ataque, defesa e suporte.  
O objetivo é reduzir a vida do adversário até **0** usando estratégia e gerenciamento de energia. O projeto foi feito como um trabalho da disciplina de Programação Orientada a Objetos.

---

## 👥 Integrantes do Grupo

Lucas Gonçalves Dahbar - 202476028  
Renato de Souza Nandes - 202476043  

---

## 🎮 Mecânica do Jogo

Cada jogador inicia com:

- ❤️ **100 pontos de vida**
- ⚡ **10 pontos de energia**

O jogo acontece em **turnos alternados**, e em cada turno o jogador pode jogar uma ou mais cartas, desde que tenha energia suficiente.

### 🃏 Tipos de cartas
- **Ataque:** causam dano ao inimigo (ex: DDoS, Ransomware, Phishing, Exploit)
- **Defesa:** reduzem/impedem dano (ex: Firewall, Criptografia, Detecção de Intrusão)
- **Suporte:** modificam o combate, podendo:
  - aumentar o próprio ataque
  - reduzir o ataque do oponente
  - restaurar vida (máximo 100)

---

## 🧩 Montagem do Deck

Cada hacker deve montar um deck com **10 cartas**, sendo obrigatoriamente:

- ✅ 4 cartas de **Ataque**
- ✅ 4 cartas de **Defesa**
- ✅ 2 cartas de **Suporte**

O sistema também oferece opção de **seleção aleatória do deck** respeitando essas regras.  
O **BOT sempre usa a seleção aleatória**.

---

## 🤖 Modos de Jogo

- 👤 Humano vs Humano
- 👤 Humano vs BOT

Configuração fixa do BOT:
- Nome: **BOT**
- Identificador: **202565001**

---

## ⚙️ Regras de Combate (Resumo)

### Cálculo de dano
- **Ataque vs Defesa:**  
  `Dano = ATKtotal − DEFtotal`  
  (Se defesa for maior, o dano é 0)

- **Defesa vs Defesa:**  
  Nenhum dano é causado.

- **Ataque vs Ataque:**  
  Ambos recebem o dano total do ataque do adversário.

### Energia por turno
Ao final da ação do turno:
- o jogador ganha **+1 energia** (máximo 10)

---

## 🔁 Replay da Partida

Ao final do jogo, o sistema gera automaticamente um arquivo de replay contendo:

- cartas escolhidas no início da partida
- ações realizadas em cada turno
- vida e energia de cada hacker ao longo do jogo
- vencedor final

---

## 🛠️ Tecnologias Utilizadas

- **Java**
- Execução via **console/terminal**
- Leitura de arquivos `.csv` (cartas)
- Escrita de arquivo `.txt` (replay)

---

## ▶️ Como Executar

> (Ajuste os comandos conforme a estrutura do seu projeto)

### Clonar o repositório
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
