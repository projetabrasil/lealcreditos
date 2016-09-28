package br.com.lealbrasil.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@RequestScoped
@ManagedBean(name = "voucherfotos")
public class VoucherImagesjsfController {
	@ManagedProperty("#{param.caminho}")
	private String caminhodaImagem;
	@ManagedProperty("#{param.caminho2}")
	private String caminhodaImagem2;

	private StreamedContent foto;
	private StreamedContent foto2;

	public StreamedContent getFoto() throws IOException {
		String branco;
		if (caminhodaImagem == null || caminhodaImagem.isEmpty()) {
			branco = "/imagens/branco.png"; 											
			Path path = Paths.get(branco);

			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto = new DefaultStreamedContent(stream);
			}

		} else {
			
			Path path = Paths.get(caminhodaImagem);
			
			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto = new DefaultStreamedContent(stream);
			} else {
				branco = "/imagens/branco.png";
				path = Paths.get(branco);

				if (Files.exists(path)) {
					InputStream stream = Files.newInputStream(path);
					foto = new DefaultStreamedContent(stream);
				}
			}
		}
		return foto;
	}

	public String getCaminhodaImagem() {
		return caminhodaImagem;
	}

	public void setCaminhodaImagem(String caminhodaImagem) {
		this.caminhodaImagem = caminhodaImagem;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	public StreamedContent getFoto2() throws IOException {
		String branco;
		if (caminhodaImagem2 == null || caminhodaImagem2.isEmpty()) {
			branco = "/imagens/branco.png"; // Utilidades.caminho("Brindes")+"branco"
											// + Utilidades.tipodeImagem();
			Path path = Paths.get(branco);

			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto2 = new DefaultStreamedContent(stream);
			}

		} else {
			Path path = Paths.get(caminhodaImagem2);
			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto2 = new DefaultStreamedContent(stream);
			} else {
				branco = "/imagens/branco.png";
				path = Paths.get(branco);

				if (Files.exists(path)) {
					InputStream stream = Files.newInputStream(path);
					foto2 = new DefaultStreamedContent(stream);
				}
			}
		}
		return foto2;
	}

	public void setFoto2(StreamedContent foto2) {
		this.foto2 = foto2;
	}

	public String getCaminhodaImagem2() {
		return caminhodaImagem2;
	}

	public void setCaminhodaImagem2(String caminhodaImagem2) {
		this.caminhodaImagem2 = caminhodaImagem2;
	}

}
