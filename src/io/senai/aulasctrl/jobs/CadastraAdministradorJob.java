package io.senai.aulasctrl.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import io.senai.aulasctrl.dao.UsuarioDAO;
import io.senai.aulasctrl.models.Administrador;
import io.senai.aulasctrl.utils.ClassScopedLogger;
import io.senai.aulasctrl.utils.Logger;

@Component
public class CadastraAdministradorJob implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	@Qualifier("usuarioJPA")
	private UsuarioDAO usuarioDAO;
	
	private static Logger logger;
	
	{
		logger = ClassScopedLogger.getInstance(CadastraAdministradorJob.class);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.msg("Checando se administrador existe...");
		final String email = "adm@senai.io";
		
		
		if (usuarioDAO.buscarPorEmail(email) == null) {
			logger.msg("Administrador não existe. Realizando novo cadastro..");
			Administrador adm = new Administrador();
			adm.setEmail(email);
			adm.setNome("Administrador");
			adm.setSenha("admin123");
			adm.setAtivo(true);
			adm.hashearSenha();
			
			usuarioDAO.persistir(adm);
		} else {
			logger.msg("Administrador já existe.");
		}
		
		logger.breakLine();
	}

}
