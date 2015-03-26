/**
 * File modified by : Benoît
 */
package fr.cursusSopra.action;

import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.model.Collaborateurs;

public class AboutAction extends ActionSupportExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 524351754340439768L;

	private List<Collaborateurs> listCollab = new ArrayList<Collaborateurs>();

	public AboutAction() {
		listCollab
				.add(new Collaborateurs(
						"se-faire-des-amis-a-grenoble.jpg",
						"pac.jpg",
						"Pierre-Alexandre Casula",
						"Type1 & Type2 Manager",
						"Grenoble, France",
						"pac@lesfinesgueules.fr",
						"",
						"Après un beau parcours à la tête du CEA, Pierre-Alexandre nous a rejoint mi-fév 2015.",
						"POO, Struts, Git (Push and Pull), ..."));

		listCollab
				.add(new Collaborateurs(
						"Les-pentes-de-la-Croix-Rousse_banniere1.jpg",
						"cb.jpg",
						"Cécile Bernard",
						"Expert in User Relations",
						"Bourg-en-Bresse, France",
						"cb@lesfinesgueules.fr",
						"",
						"Après avoir géré les trains à l'heure de la SNCF, Cécile a décidé de se consacrer au bien et bon manger de sa région.",
						"Développement d'interfaces Struts."));

		listCollab.add(new Collaborateurs("menthon-st-bernard02.jpg", "nm.jpg",
				"Nicolas Métifiot", "Google Maps Chief-Engineer",
				"Menthon-Saint-Bernard, France", "nm@lesfinesgueules.fr", "",
				"Après avoir ravaillé à l'intérieur des nano-composants, essaie de les maîtriser de l'extérieur. Il y arrive le bougre !", "Polymorphisme, Java, = et ==, ..."));

		listCollab.add(new Collaborateurs("Lyon_vue.jpg", "jc.jpg",
				"Julien Caillon", "Bootstrap Techical Engineer",
				"Doit bien être de quelque part", "jc@lesfinesgueules.fr", "",
				"N'a pas pu supporter Romain plus de six semaines. Spécialiste des paniers sans fonds.",
				"Javascript, surtout la partie Java. Mais CSS ou les emoticons."));

		listCollab
				.add(new Collaborateurs(
						"clermont.jpg",
						"bl.jpg",
						"Benoît Lacroix",
						"Lead",
						"Clermont, France",
						"bl@lesfinesgueules.fr",
						"",
						"A enfin compris que l'intelligence informatique était à Lyon, engage un business d'import-export de fromages avec Gergovie.",
						"JavaScript, Division entière."));

		listCollab.add(new Collaborateurs("montagne-neige.jpg", "jj.jpg",
				"Julien Joly", "Tempo Manager", "Annecy, France",
				"jj@lesfinesgueules.fr", "",
				"A compris qu'il y avait une vie après Alès. Julien rejoint notre agence d'Annecy dès le 30 mars afin d'approcher les rudes montagnards et leur fromages forts.",
				"Spécialiste du micro-editing."));

		listCollab
				.add(new Collaborateurs(
						"lyon-quai-saone-julien-reboulet-300x200.jpg",
						"rg.png",
						"Romain Guigard",
						"Responsable BDD",
						"Lyon, France",
						"rg@lesfinesgueules.fr",
						"",
						"Depuis tout petit a voulu faire du Pacbase, il a réussi et c'est bien la seule chose qu'il ait réussi de sa vie",
						"COBOL, Pac, POO, un peu de Java aussi mais que le jeudi soir..."));

		listCollab
				.add(new Collaborateurs("CoucherSoleilMinimes.jpg",
						"gavroche.jpg", "Gavroche",
						"Responsable du bien-être et des approvisionnements",
						"Morlaix, France", "gavroche@lesfinesgueules.fr", "",
						"A testé pour vous tous les bouchons lyonnais",
						"Sommeil flash, veille technologique sur les bons plats lyonnais, gardiennage des zones sensibles (salle de déjeuner,...)."));

		listCollab.add(new Collaborateurs("CharlieHebdo.png", "dl.png",
				"Daniel Lucazeau", "Grande Gueule en Chef",
				"La Rochelle, France", "dl@lesfinesgueules.fr", "",
				"Sévit encore et encore sur les cursus Sopra !",
				"Responsable des déblocages de pull-push."));
	}

	@Override
	public String execute() {

		return SUCCESS;
	}

	public List<Collaborateurs> getListCollab() {
		return listCollab;
	}
}
