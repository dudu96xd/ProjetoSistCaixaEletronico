<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="ContaController.do?acao=index&idConta=${conta.idConta}">Caixa
				Eletrônico</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="ContaController.do?acao=telaSacar&idConta=${conta.idConta}">Sacar</a>
				</li>
				<li><a
					href="ContaController.do?acao=telaDepositar&idConta=${conta.idConta}">Depositar</a>
				</li>
				<li><a
					href="ContaController.do?acao=Extrato&idConta=${conta.idConta}">Extrato</a>
				</li>
				<li><a
					href="ContaController.do?acao=telaTransf&idConta=${conta.idConta}">Transferir</a>
				</li>
				<li><a
					href="login.jsp">Logout</a>
				</li>
			</ul>
            </div>
        </div>
    </nav>