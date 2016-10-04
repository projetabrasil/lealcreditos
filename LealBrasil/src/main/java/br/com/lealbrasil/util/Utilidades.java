package br.com.lealbrasil.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import antlr.StringUtils;
import br.com.lealbrasil.model.entities.Agendamento;
import br.com.lealbrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.lealbrasil.model.entities.Movimento_Detalhe_A;

@SuppressWarnings("serial")
public class Utilidades implements Serializable {
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final String caminhoFotoBrinde = "c:/imagens/brindes/";
	private static final String caminhoFotoVouchers = "c:/imagens/vouchers/";
	private static String caminhobase = "/images/";
	private static String tipoImagem = ".png";

	public static String tipodeImagem() {
		return getTipoimagem();
	}

	public static Calendar retornaCalendario() {
		TimeZone tz = TimeZone.getDefault();
		Calendar c = Calendar.getInstance(tz);
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
		String sData = sd.format(c.getTime());

		try {
			c.setTime(sd.parse(sData));
			c.clear(Calendar.ZONE_OFFSET);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c;

	}

	public static Date retornaCalendario2() {
		TimeZone tz = TimeZone.getDefault();
		Calendar c = Calendar.getInstance(tz);
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		String sData = sd.format(c.getTime());

		try {
			c.setTime(sd.parse(sData));
			c.clear(Calendar.ZONE_OFFSET);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date data = new Date();
		data = c.getTime();
		return data;

	}

	public static Calendar retornaData() {
		Calendar c = Calendar.getInstance();
		return c;
	}

	public static Date retornaValidade(int diasValidade) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, +diasValidade);

		return c.getTime();
	}

	public static Date retornaHora(String hora) {
		Date d = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

			d = sdf.parse(hora);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String retiraCaracteres(String texto) {
		if (texto == null)
			texto = "";
		texto = texto.replaceAll("[^\\p{ASCII}]", "").replaceAll("[.-]", "").replaceAll("\\/", "").replaceAll("\\(", "")
				.replaceAll("\\)", "").replaceAll(" ", "");
		texto = texto.replaceAll("_", "");
		return texto;
	}

	public static String retiraVazios(String texto) {
		if (texto == null)
			texto = "";
		texto.trim();
		return texto;
	}

	public static boolean estaVazio(String texto) {
		texto = StringUtils.stripBack(texto, " \t");
		texto = StringUtils.stripFront(texto, " \t");
		return texto.length() <= 0;
	}

	public static String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean isValidCPF(String cpf) {
		if ((cpf == null) || (cpf.length() != 11))
			return false;

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		boolean retorno = cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
		return retorno;
	}

	public static boolean isValidCNPJ(String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14))
			return false;

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	public static boolean isEmailValid(String email) {
		if ((email == null) || (email.trim().length() == 0))
			return false;

		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static void abrirfecharDialogos(String dialogo, boolean abrir) {
		RequestContext context = RequestContext.getCurrentInstance();
		String finalidade;
		if (abrir)
			finalidade = "show";
		else
			finalidade = "hide";
		context.execute("PF('" + dialogo + "')." + finalidade + "();");
	}

	public static List<Enum_Aux_Sim_ou_Nao> listaSN() {
		List<Enum_Aux_Sim_ou_Nao> lista = new ArrayList<Enum_Aux_Sim_ou_Nao>();
		Enum_Aux_Sim_ou_Nao[] lSN;
		lSN = Enum_Aux_Sim_ou_Nao.values();

		for (Enum_Aux_Sim_ou_Nao l : lSN) {
			lista.add(l);
		}
		return lista;
	}

	public static List<Enum_Aux_Tipo_Item_de_Movimento> listaTipoItemdeMovimento() {
		List<Enum_Aux_Tipo_Item_de_Movimento> lista = new ArrayList<Enum_Aux_Tipo_Item_de_Movimento>();
		Enum_Aux_Tipo_Item_de_Movimento[] lSN;
		lSN = Enum_Aux_Tipo_Item_de_Movimento.values();

		for (Enum_Aux_Tipo_Item_de_Movimento l : lSN) {
			lista.add(l);
		}
		return lista;
	}

	public static String buscarCep(String cep) {
		String json;

		try {
			URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder jsonSb = new StringBuilder();
			br.lines().forEach(l -> jsonSb.append(l.trim()));
			json = jsonSb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return json;
	}

	private static String getCaminhofotobrinde() {
		return caminhoFotoBrinde;
	}

	private static String getCaminhofotovouchers() {
		return caminhoFotoVouchers;
	}

	private static String getTipoimagem() {
		return tipoImagem;
	}

	public static StreamedContent retornaFoto(String caminhodoArquivo) throws IOException {
		String branco;
		StreamedContent foto = null;
		if (caminhodoArquivo == null || caminhodoArquivo.isEmpty()) {
			branco = "/imagens/branco.png"; // Utilidades.caminho("Brindes")+"branco"
											// + Utilidades.tipodeImagem();
			Path path = Paths.get(branco);

			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto = new DefaultStreamedContent(stream);
			}

		} else {
			Path path = Paths.get(caminhodoArquivo);
			if (Files.exists(path.getFileName())) {
				InputStream stream = Files.newInputStream(path);
				foto = new DefaultStreamedContent(stream);
			} else {
				branco = "/imagens/branco.png";
				path = Paths.get(branco);

				if (Files.exists(path.getFileName())) {
					InputStream stream = Files.newInputStream(path);
					foto = new DefaultStreamedContent(stream);
				}
			}
		}
		return foto;
	}

	public static String caminho(String tipo) {
		String caminho;
		if (tipo.toUpperCase().equals("BRINDES"))
			caminho = getCaminhofotobrinde();
		else
			caminho = getCaminhofotovouchers();
		return caminho;
	}

	public static String getCaminhobase() {
		return caminhobase;
	}

	public static void setCaminhobase(String caminhobase) {
		Utilidades.caminhobase = caminhobase;
	}

	public static void setTipoimagem(String tipoimagem) {
		tipoImagem = tipoimagem;
	}

	public static String randon(String param) {

		String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";
		Random random = new Random();
		String armazenaChaves = "";
		Long valor;
		String novoValor;
		int index = -1;
		for (int i = 0; i < 4; i++) {
			index = random.nextInt(letras.length());
			armazenaChaves += letras.substring(index, index + 1);
			valor = 0l;
			novoValor = "" + valor;
			while (novoValor.length() <= 1) {
				valor = random.nextInt(10) * Long.valueOf(param);
				novoValor = "" + valor;
			}

			armazenaChaves += novoValor.substring(1, 2);
			if (i % 2 == 0)
				armazenaChaves += "-";
		}

		String invertida = new StringBuilder(armazenaChaves).reverse().toString();
		return invertida;
	}

	public static void pdf(String caminho, Movimento_Detalhe_A mDA, Agendamento ag) {
		Document document = new Document();
		try {
			// String k = "<html><body> This is my Project </body></html>";
			OutputStream file = new FileOutputStream(new File(caminho));

			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();
			Image img = Image.getInstance(mDA.getCaminhoDaImagem());
			String dados = "Nome          :" + ag.getId_Pessoa_Cliente().getDescricao() + "\n" + "Codigo        : "
					+ mDA.getCodigo() + "\n" + "Telefone      : " + ag.getId_Pessoa_Cliente().getFone_1() + "\n"
					+ "Agendado para : " + getDataPorExtenso(ag.getDataAgendamento().getTime()) + "\n";

			document.add(img);
			document.add(new Paragraph(dados));
			InputStream is = new ByteArrayInputStream(mDA.getRegulamento().getBytes());
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
			document.close();
			file.close();

			java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
			desktop.open(new File(caminho));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getDataPorExtenso(Date data) {
		DateFormat dfmt = new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
		return dfmt.format(data);
	}

}
