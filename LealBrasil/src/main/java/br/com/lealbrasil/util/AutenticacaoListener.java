package br.com.lealbrasil.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.lealbrasil.controller.AutenticacaojsfController;
import br.com.lealbrasil.model.entities.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {

		String paginaCorrente = Faces.getViewId();
		boolean ehPaginadeAtutenticacao = paginaCorrente.contains("autenticacao.xhtml");
		boolean ehPaginaAlfa = paginaCorrente.contains("alfapage.xhtml");

		if (!ehPaginadeAtutenticacao) {
			if (!ehPaginaAlfa) {
				AutenticacaojsfController autenticacaojsfController = Faces
						.getSessionAttribute("autenticacaojsfController");
				if (autenticacaojsfController == null) {
					
						Faces.navigate("pages/alfapage.xhtml");
						return;
										
				}
				Usuario usuario = autenticacaojsfController.getPerfilLogado().getUsLogado();
				if (usuario == null || autenticacaojsfController.getPerfilLogado().getPaginaAtual() == null) {
					
						Faces.navigate("pages/alfapage.xhtml");
						return;
					
				}
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		//
		return PhaseId.RESTORE_VIEW;
	}

}
