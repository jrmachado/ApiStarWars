# ApiStarWars

# 1. Sobre o projeto
    O objetivo deste projeto é criar uma API rest que armazenará dados como nome de um planeta, clima e terreno.
    E retornar também a quantidade de aparições de cada planeta nos filmes de Star Wars,
    registrada na API pública do Star Wars:  https://swapi.co/
    Esse projeto será consumido por um jogo.

# 2.Tecnologias utilizadas
    Para o presente projeto foi utilizado a linguagem Java na sua versão 1, Spring Boot 2.7.15 e a IDE Eclipse.
    Para a persistência dos dados foi utilizado o banco de dados H2.
    No Projeto foi utilizado swagger e a URL para acesso da mesma: http://localhost:8080/swagger-ui.html#/ 
	
# 3.Configurando a API
    Para utilizar o projeto deverá ser instalado o Java SDK 11, o Eclipse.
    Após o download do projeto e inserir o mesmo no diretorio raiz do seu workspace do Eclipse.
    Importar o projeto e dar Run em Spring Boot no Eclipse .
	
# 4.Funcionalidades

	1 - Criar Planeta
			Para criar um planete deve ser feita a requisição post para http://localhost:8080/api/sw/planeta/criar
			json:
			{
			   "nome": "Alderaan",
			   "clima": "Temperado",
			   "terreno": "Montanhoso"
			}
			
	2 - Listar Planetas	
			Para listar os planetas cadastrados deve ser feita a solicitação para http://localhost:8080/api/sw/planeta/listar
			
	3 - Busca por ID
			Para efetuar a busca por ID deve fazer uma solicitação get para o endpoint junto com o ID  
			EX: http://localhost:8080/api/sw/planeta/buscarPorId/id
			
	3 - Busca por Nome
			Para efetuar a busca por NOME deve fazer uma solicitação get para o endpoint junto com o NOME  
			EX: http://localhost:8080/api/sw/planeta/buscarPorNome?nome=NOME
			
	3 - Deletar Planeta
			Para efetuar a exclusão de um planeta deve fazer uma solicitação delete para o endpoint junto com o ID  
			EX: http://localhost:8080/api/sw/planeta/delete/ID
			
	4 - Pode ser efeturado também via Swagger
			http://localhost:8080/swagger-ui.html#/ 
